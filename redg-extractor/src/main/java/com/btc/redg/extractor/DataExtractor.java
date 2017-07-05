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

package com.btc.redg.extractor;

import com.btc.redg.extractor.generationmodes.DependencyAlreadyExcludedException;
import com.btc.redg.extractor.generationmodes.EntityInclusionMode;
import com.btc.redg.extractor.model.EntityModel;
import com.btc.redg.extractor.model.ExistingEntityModel;
import com.btc.redg.extractor.model.ReferencingEntityModel;
import com.btc.redg.extractor.model.representationprovider.DefaultJavaCodeRepresentationProvider;
import com.btc.redg.extractor.model.representationprovider.JavaCodeRepresentationProvider;
import com.btc.redg.models.ColumnModel;
import com.btc.redg.models.ForeignKeyModel;
import com.btc.redg.models.TableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.sql.DataSource;

public class DataExtractor {

    private static final Logger LOG = LoggerFactory.getLogger(DataExtractor.class);

    private static final String SELECT_FORMAT_STRING = "SELECT * FROM %s";

    private JavaCodeRepresentationProvider jcrProvider = new DefaultJavaCodeRepresentationProvider();
    private Function<EntityModel, EntityInclusionMode> entityModeDecider = (em) -> EntityInclusionMode.ADD_NEW;

    public JavaCodeRepresentationProvider getJcrProvider() {
        return jcrProvider;
    }

    public void setJcrProvider(final JavaCodeRepresentationProvider jcrProvider) {
        this.jcrProvider = jcrProvider;
    }

    public Function<EntityModel, EntityInclusionMode> getEntityModeDecider() {
        return entityModeDecider;
    }

    public void setEntityModeDecider(final Function<EntityModel, EntityInclusionMode> entityModeDecider) {
        this.entityModeDecider = entityModeDecider;
    }

    public List<EntityModel> extractAllData(final DataSource dataSource, final List<TableModel> tableModels) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            return extractAllData(connection, tableModels);
        }
    }

    public List<EntityModel> extractAllData(final Connection connection, final List<TableModel> tableModels) throws SQLException {
        final List<EntityModel> entities = extractEntityModels(connection, tableModels);
        resolveReferences(entities);
        return sortEntities(entities);
    }

    private List<EntityModel> extractEntityModels(Connection connection, List<TableModel> tableModels) throws SQLException {
        final List<EntityModel> entities = new LinkedList<>();
        LOG.debug("Disabling auto commit for connection...");
        connection.setAutoCommit(false);
        LOG.debug("Creating JDBC statement...");

        try (Statement st = connection.createStatement()) {
            LOG.debug("Setting fetch size to 50 rows...");
            st.setFetchSize(50);

            for (final TableModel tableModel : tableModels) {
                LOG.debug("Fetching data from table {}...", tableModel.getSqlFullName());
                final ResultSet rs = st.executeQuery(String.format(SELECT_FORMAT_STRING, tableModel.getSqlFullName()));
                long counter = 0;
                while (rs.next()) {
                    ++counter;

                    final EntityModel entityModel = new EntityModel(tableModel);
                    for (final ForeignKeyModel fkm : tableModel.getForeignKeys()) {
                        final EntityModel referencedEntity = new ReferencingEntityModel(fkm.getJavaTypeName());
                        fkm.getReferences().forEach((name, fkcm) -> {
                            try {
                                final Object value = rs.getObject(fkcm.getDbName());
                                if (value != null) {
                                    referencedEntity.addValues(fkcm.getPrimaryKeyAttributeName(), new EntityModel.ValueModel(
                                            jcrProvider.getCodeForColumnValue(value, fkcm.getSqlType(), fkcm.getSqlTypeInt(), fkcm.getLocalType()), EntityModel.ValueModel.ForeignKeyState.UNKNOWN));
                                }
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        });
                        if (fkm.isNotNull()) {
                            entityModel.addNotNullRef(referencedEntity);
                        } else {
                            if (referencedEntity.getValues().size() > 0) {
                                entityModel.addNullableRef(fkm.getName(), referencedEntity);
                            }
                        }

                    }

                    for (final ColumnModel cm : tableModel.getColumns()) {
                        final Object value = rs.getObject(cm.getDbName());
                        if (value != null) {
                            entityModel.addValues(cm.getName(), new EntityModel.ValueModel(
                                    jcrProvider.getCodeForColumnValue(value, cm.getSqlType(), cm.getSqlTypeInt(), cm.getJavaTypeName()),
                                    cm.isPartOfForeignKey() ? EntityModel.ValueModel.ForeignKeyState.FK : EntityModel.ValueModel.ForeignKeyState.NON_FK));
                        }
                    }
                    entities.add(entityModel);

                }
                LOG.debug("Extracted {} entities from table {}", counter, tableModel.getSqlFullName());
            }
        }
        return entities;
    }

    private void resolveReferences(List<EntityModel> entities) {
        LOG.debug("Resolving references...");
        for (final EntityModel entity : entities) {
            List<EntityModel> newNotNullRefs = new LinkedList<>();
            for (final EntityModel refEntity : entity.getNotNullRefs()) {
                if (refEntity instanceof ReferencingEntityModel) {
                    newNotNullRefs.add(findCorrectEntity((ReferencingEntityModel) refEntity, entities));
                }
            }
            entity.setNotNullRefs(newNotNullRefs);
            Map<String, EntityModel> newNullableRefs = new HashMap<>();
            entity.getNullableRefs().forEach((name, value) -> {
                if (value instanceof ReferencingEntityModel) {
                    newNullableRefs.put(name, findCorrectEntity((ReferencingEntityModel) value, entities));
                }
            });
            entity.setNullableRefs(newNullableRefs);
        }
    }

    private List<EntityModel> sortEntities(List<EntityModel> entities) {
        LOG.debug("Sorting entities...");
        List<EntityModel> sortedEntities = EntityModelSorter.sortEntityModels(entities);
        ListIterator<EntityModel> sortedIterator = sortedEntities.listIterator();

        List<EntityModel> removedEntities = new LinkedList<>();
        while (sortedIterator.hasNext()) {
            EntityModel entityModel = sortedIterator.next();

            EntityInclusionMode mode = this.entityModeDecider.apply(entityModel);
            switch (mode) {
                case EXCLUDE:
                    removedEntities.add(entityModel);
                    sortedIterator.remove();
                    break;
                case ADD_NEW:
                    for (final EntityModel dependency : entityModel.getNotNullRefs()) {
                        if (removedEntities.contains(dependency)) {
                            throw new DependencyAlreadyExcludedException(entityModel);
                        }
                        entityModel.getNullableRefs().entrySet().removeIf(entry -> removedEntities.contains(entry.getValue()));
                    }
                    break;
                case USE_EXISTING:
                    ExistingEntityModel existingEntityModel = ExistingEntityModel.fromEntityModel(entityModel);
                    sortedIterator.set(existingEntityModel);
                    // replace all references to old entityModel with reference to new existingEntityModel
                    for (final EntityModel eM : sortedEntities) {
                        ListIterator<EntityModel> innerIterator = eM.getNotNullRefs().listIterator();
                        while (innerIterator.hasNext()) {
                            if (innerIterator.next().equals(entityModel)) {
                                innerIterator.set(existingEntityModel);
                            }
                        }
                        eM.setNullableRefs(eM.getNullableRefs().entrySet().stream()
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey,
                                        e -> (e.getValue().equals(entityModel)) ? existingEntityModel : e.getValue()
                                )));
                    }
                    break;
            }
        }
        return sortedEntities;
    }

    private EntityModel findCorrectEntity(final ReferencingEntityModel ref, final List<EntityModel> entities) {
        return entities.stream()
                .filter(e -> {
                    if (!ref.getTypeName().equals(e.getTableModel().getClassName())) {
                        return false;
                    }
                    for (final ColumnModel cm : e.getTableModel().getPrimaryKeyColumns()) {

                        final String entityVal = e.getValues().get(cm.getName()).getValue();
                        final String refVal = ref.getValues().get(cm.getName()).getValue();
                        if (!entityVal.equals(refVal)) {
                            return false;
                        }

                    }
                    return true;
                })
                .findFirst().orElseGet(() -> {
                    LOG.warn("Could not find referenced entity!");
                    return null;
                });
    }


}
