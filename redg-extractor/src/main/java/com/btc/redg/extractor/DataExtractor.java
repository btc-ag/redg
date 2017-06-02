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

import com.btc.redg.extractor.model.EntityModel;
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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataExtractor {

    private static final Logger LOG = LoggerFactory.getLogger(DataExtractor.class);

    private static final String SELECT_FORMAT_STRING = "SELECT * FROM %s";

    private JavaCodeRepresentationProvider jcrProvider = new DefaultJavaCodeRepresentationProvider();

    public List<EntityModel> extractAllData(final Connection connection, final List<TableModel> tableModels) throws SQLException {

        final List<EntityModel> entities = new LinkedList<>();
        LOG.debug("Disabling auto commit for connection...");
        connection.setAutoCommit(false);
        LOG.debug("Creating JDBC statement...");
        final Statement st = connection.createStatement();
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
                                referencedEntity.addValues(fkcm.getName(),
                                        jcrProvider.getCodeForColumnValue(value, fkcm.getSqlType(), fkcm.getSqlTypeInt(), fkcm.getLocalType()));
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
                        entityModel.addValues(cm.getName(),
                                jcrProvider.getCodeForColumnValue(value, cm.getSqlType(), cm.getSqlTypeInt(), cm.getJavaTypeName()));
                    }
                }
                entities.add(entityModel);

            }
            LOG.debug("Extracted {} entities from table {}", counter, tableModel.getSqlFullName());
        }

        // now resolve all referencing entities
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

        // now sort all entities
        LOG.debug("Sorting entities...");
        return EntityModelSorter.sortEntityModels(entities);
    }

    private EntityModel findCorrectEntity(final ReferencingEntityModel ref, final List<EntityModel> entities) {
        return entities.stream()
                .filter(e -> {
                    for (final ColumnModel cm : e.getTableModel().getPrimaryKeyColumns()) {
                        final String entityVal = e.getValues().get(cm.getName());
                        final String refVal = ref.getValues().get(cm.getName());
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
