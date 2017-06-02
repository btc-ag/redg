/*
 * Copyright 2017 BTC Business Technology AG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.btc.redg.jpa;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Persistence;
import javax.persistence.metamodel.EmbeddableType;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.IdentifiableType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.MappedSuperclassType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.Type;

import com.btc.redg.generator.extractor.datatypeprovider.DataTypeProvider;
import com.btc.redg.generator.extractor.datatypeprovider.DefaultDataTypeProvider;
import com.btc.redg.generator.extractor.nameprovider.NameProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import schemacrawler.schema.ForeignKey;
import schemacrawler.schema.ForeignKeyColumnReference;
import schemacrawler.schema.Table;

/**
 * @author Yann Massard (yamass@gmail.com)
 */
public class JpaMetamodelRedGProvider implements NameProvider, DataTypeProvider {
	private static final Logger LOG = LoggerFactory.getLogger(JpaMetamodelRedGProvider.class);

	private final Map<? extends Class<?>, ? extends ManagedType<?>> managedTypesByClass;
	private final Map<String, ManagedType> managedTypesByTableName = new HashMap<>();
	private final Map<QualifiedColumnName, SingularAttribute> singularAttributesByColumnName = new HashMap<>();
	private final Map<ForeignKeyRelation, SingularAttribute> singularAttributesByForeignKeyRelation = new HashMap<>();

	private DataTypeProvider fallbackDataTypeProvider = new DefaultDataTypeProvider();

	public static JpaMetamodelRedGProvider fromPersistenceUnit(String perstistenceUnitName) {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(perstistenceUnitName, properties);

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return new JpaMetamodelRedGProvider(entityManager.getMetamodel());
	}

	public JpaMetamodelRedGProvider(Metamodel metaModel) {
		managedTypesByClass = metaModel.getManagedTypes().stream().collect(Collectors.toMap(Type::getJavaType, managedType -> managedType));

		for (ManagedType<?> managedType : metaModel.getManagedTypes()) {
			Class<?> javaType = managedType.getJavaType();
			if (javaType == null) {
				// envers makes problems here.
				continue;
			}
			managedTypesByTableName.put(getTableName(javaType), managedType);

			boolean isMappedSuperClassType = managedType instanceof MappedSuperclassType;
			Inheritance inheritanceAnnotation = javaType.getAnnotation(Inheritance.class);
			InheritanceType inheritanceType = inheritanceAnnotation == null ? InheritanceType.SINGLE_TABLE : inheritanceAnnotation.strategy();

			if (managedType instanceof EntityType
					|| isMappedSuperClassType && (inheritanceType == InheritanceType.JOINED || inheritanceType == InheritanceType.SINGLE_TABLE)) {
				analyzeAttributes(managedType, getTableName(javaType));
			}

			ManagedType superClassManagedType = managedTypesByClass.get(javaType.getSuperclass());
			if (superClassManagedType != null && superClassManagedType.getJavaType() != null) {
				analyzeAttributes(managedType, getTableName(superClassManagedType.getJavaType()));
			}
		}
	}

	private void analyzeAttributes(ManagedType<?> managedType, String targetTableName) {
		managedType.getSingularAttributes().forEach(attribute -> {
			ManagedType<?> targetEntity = managedTypesByClass.get(attribute.getJavaType());
			if (targetEntity != null && attribute.getType() instanceof IdentifiableType) { // this is a relation
				List<String> idAttributeNames = targetEntity.getSingularAttributes().stream()
						.filter(this::isIdAttribute)
						.map(this::getSingularAttributeColumnName)
						.collect(Collectors.toList());

				JoinColumns joinColumnsAnnotation = ((AnnotatedElement) attribute.getJavaMember()).getAnnotation(JoinColumns.class);
				JoinColumn joinColumnAnnotation = ((AnnotatedElement) attribute.getJavaMember()).getAnnotation(JoinColumn.class);
				JoinColumn[] joinColumns = joinColumnsAnnotation != null ? joinColumnsAnnotation.value() : joinColumnAnnotation != null ? new JoinColumn[]{joinColumnAnnotation} : null;
				Map<String, String> referenceColumnNamesMap;
				if (joinColumns != null) {
					referenceColumnNamesMap = Arrays.stream(joinColumns)
							.collect(Collectors.toMap(JoinColumn::name, joinColumn -> joinColumn.referencedColumnName().length() > 0 ? joinColumn.referencedColumnName() : idAttributeNames.get(0)));
				} else {
					referenceColumnNamesMap = idAttributeNames.stream()
							.collect(Collectors.toMap(idAttributeName -> attribute.getName().toUpperCase() + "_" + idAttributeName, idAttributeName -> idAttributeName));
				}
				singularAttributesByForeignKeyRelation.put(new ForeignKeyRelation(targetTableName, getTableName(targetEntity.getJavaType()), referenceColumnNamesMap),
						attribute);
			} else if (targetEntity != null && attribute.getType() instanceof EmbeddableType) {
				analyzeAttributes((EmbeddableType) attribute.getType(), targetTableName);
			} else {
				String columnName = getSingularAttributeColumnName(attribute);
				singularAttributesByColumnName.put(new QualifiedColumnName(targetTableName, columnName), attribute);
			}
		});
	}

	@Override
	public String getClassNameForTable(Table table) {
		ManagedType managedType = managedTypesByTableName.get(table.getName());
		return managedType != null ? managedType.getJavaType().getSimpleName() : null;
	}

	@Override
	public String getMethodNameForColumn(schemacrawler.schema.Column column) {
		SingularAttribute singularAttribute = singularAttributesByColumnName.get(new QualifiedColumnName(column.getParent().getName(), column.getName()));
		return singularAttribute != null ? singularAttribute.getName() : null;
	}

	private boolean isIdAttribute(SingularAttribute attribute) {
		return ((AnnotatedElement) attribute.getJavaMember()).isAnnotationPresent(Id.class);
	}

	private String getSingularAttributeColumnName(SingularAttribute attribute) {
		Column annotation = ((AnnotatedElement) attribute.getJavaMember()).getAnnotation(Column.class);
		return annotation != null && annotation.name().length() > 0 ?
				annotation.name().toUpperCase() : attribute.getName().toUpperCase();
	}

	@Override
	public String getMethodNameForForeignKey(ForeignKey foreignKey) {
		String referencingTableName = foreignKey.getColumnReferences().get(0).getForeignKeyColumn().getParent().getName();
		String referencedTableName = foreignKey.getColumnReferences().get(0).getPrimaryKeyColumn().getParent().getName();
		Map<String, String> referenceColumnNamesMap = foreignKey.getColumnReferences().stream()
				.collect(Collectors.toMap(columnReference -> columnReference.getForeignKeyColumn().getName(), columnReference -> columnReference.getPrimaryKeyColumn().getName()));
		SingularAttribute singularAttribute = singularAttributesByForeignKeyRelation.get(new ForeignKeyRelation(referencingTableName, referencedTableName, referenceColumnNamesMap));
		return singularAttribute != null ? singularAttribute.getName() : null;
	}

	@Override
	public String getMethodNameForIncomingForeignKey(ForeignKey foreignKey) {
		return null;
	}

	private static String getTableName(Class javaType) {
		javax.persistence.Table tableAnnotation = ((AnnotatedElement) javaType).getAnnotation(javax.persistence.Table.class);
		javax.persistence.Entity entityAnnotation = ((AnnotatedElement) javaType).getAnnotation(javax.persistence.Entity.class);
		boolean tableNameSet = tableAnnotation != null && tableAnnotation.name().length() > 0;
		boolean entityNameSet = entityAnnotation != null && entityAnnotation.name().length() > 0;
		return tableNameSet ? tableAnnotation.name().toUpperCase() : entityNameSet ? entityAnnotation.name().toUpperCase() : javaType.getSimpleName().toUpperCase();
	}

	@Override
	public String getCanonicalDataTypeName(schemacrawler.schema.Column column) {
		SingularAttribute singularAttribute;
		if (column.isPartOfForeignKey()) {
			Optional<ForeignKeyColumnReference> foreignKeyColumnReferenceOptional = column.getParent().getForeignKeys().stream()
					.flatMap(foreignKeyColumnReferences -> foreignKeyColumnReferences.getColumnReferences().stream())
					.filter(foreignKeyColumnReference -> foreignKeyColumnReference.getForeignKeyColumn().getName().equals(column.getName()))
					.findFirst();

			if (foreignKeyColumnReferenceOptional.isPresent()) {
				ForeignKeyColumnReference ref = foreignKeyColumnReferenceOptional.get();
				SingularAttribute targetSingularAttribute =
						singularAttributesByColumnName.get(new QualifiedColumnName(ref.getPrimaryKeyColumn().getParent().getName(), ref.getPrimaryKeyColumn().getName()));
				if (targetSingularAttribute != null) {
					return targetSingularAttribute.getJavaType().getCanonicalName();
				} else {
					LOG.warn("Could not find target singular attribute for column " + column.getParent().getName() + "." + column.getName());
					return fallbackDataTypeProvider.getCanonicalDataTypeName(column);
				}
			} else {
				return fallbackDataTypeProvider.getCanonicalDataTypeName(column);
			}
		} else {
			singularAttribute =
					singularAttributesByColumnName.get(new QualifiedColumnName(column.getParent().getName(), column.getName()));
			if (singularAttribute != null) {
				return singularAttribute.getJavaType().getCanonicalName();
			} else {
				return fallbackDataTypeProvider.getCanonicalDataTypeName(column);
			}
		}
	}

	private static class QualifiedColumnName {
		String tableName;
		String columnName;

		public QualifiedColumnName(String tableName, String columnName) {
			this.tableName = tableName;
			this.columnName = columnName;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			QualifiedColumnName that = (QualifiedColumnName) o;
			return Objects.equals(tableName, that.tableName) &&
					Objects.equals(columnName, that.columnName);
		}

		@Override
		public int hashCode() {
			return Objects.hash(tableName, columnName);
		}

		@Override
		public String toString() {
			return tableName + "." + columnName;
		}
	}

	private static class ForeignKeyRelation {
		private final String referencingTableName;
		private final String referencedTableName;
		private final Map<String, String> referencingColumn2ReferencedColumn;

		public ForeignKeyRelation(String referencingTableName, String referencedTableName, Map<String, String> referencingColumn2ReferencedColumn) {
			this.referencingTableName = referencingTableName;
			this.referencedTableName = referencedTableName;
			this.referencingColumn2ReferencedColumn = referencingColumn2ReferencedColumn;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			ForeignKeyRelation that = (ForeignKeyRelation) o;
			return Objects.equals(referencingTableName, that.referencingTableName) &&
					Objects.equals(referencedTableName, that.referencedTableName) &&
					Objects.equals(referencingColumn2ReferencedColumn, that.referencingColumn2ReferencedColumn);
		}

		@Override
		public int hashCode() {
			return Objects.hash(referencingTableName, referencedTableName, referencingColumn2ReferencedColumn);
		}
	}

	public void setFallbackDataTypeProvider(DataTypeProvider fallbackDataTypeProvider) {
		this.fallbackDataTypeProvider = fallbackDataTypeProvider;
	}
}