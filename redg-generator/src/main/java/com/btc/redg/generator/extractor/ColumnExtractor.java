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

package com.btc.redg.generator.extractor;

import com.btc.redg.generator.extractor.conveniencesetterprovider.ConvenienceSetterProvider;
import com.btc.redg.generator.extractor.datatypeprovider.DataTypeProvider;
import com.btc.redg.generator.extractor.explicitattributedecider.ExplicitAttributeDecider;
import com.btc.redg.generator.extractor.nameprovider.NameProvider;
import com.btc.redg.generator.utils.NameUtils;
import com.btc.redg.models.ColumnModel;
import com.btc.redg.models.ModelUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import schemacrawler.schema.Column;

import java.util.Objects;

/**
 * Class that provides a method to extract a {@link ColumnModel} from a {@link ColumnModel}.
 */
public class ColumnExtractor {

    private static final Logger LOG = LoggerFactory.getLogger(ColumnExtractor.class);

    private final DataTypeProvider dataTypeProvider;
    private final NameProvider nameProvider;
    private final ExplicitAttributeDecider explicitAttributeDecider;
    private final ConvenienceSetterProvider convenienceSetterProvider;

    public ColumnExtractor(final DataTypeProvider dataTypeProvider, final NameProvider nameProvider,
            final ExplicitAttributeDecider explicitAttributeDecider, final ConvenienceSetterProvider convenienceSetterProvider) {
        this.explicitAttributeDecider = explicitAttributeDecider;
        this.convenienceSetterProvider = convenienceSetterProvider;
        Objects.requireNonNull(dataTypeProvider);
        Objects.requireNonNull(nameProvider);
        this.dataTypeProvider = dataTypeProvider;
        this.nameProvider = nameProvider;
    }

    /**
     * Fills a {@link ColumnModel} with information from a {@link Column}.
     * @param column The column
     * @return The filled model
     */
    public ColumnModel extractColumnModel(Column column) {
        LOG.debug("Extracting model for column {}", column.getName());
        ColumnModel model = new ColumnModel();
        model.setName(this.nameProvider.getMethodNameForColumn(column));
        model.setDbName(ModelUtil.removeQuotes(column.getName()));
        model.setDbTableName(column.getParent().getName());
        model.setDbFullTableName(column.getParent().getFullName());

        model.setSqlType(column.getColumnDataType().getName());
        model.setSqlTypeInt(column.getColumnDataType().getJavaSqlType().getJavaSqlType());
        String javaDataTypeName = dataTypeProvider.getCanonicalDataTypeName(column);
        model.setJavaTypeName(javaDataTypeName);
        model.setNotNull(!column.isNullable());
        model.setPartOfPrimaryKey(column.isPartOfPrimaryKey());
        model.setPartOfForeignKey(column.isPartOfForeignKey());
        model.setExplicitAttribute(explicitAttributeDecider.isExplicitAttribute(column));
        model.setUnique(column.isPartOfUniqueIndex() || column.isPartOfPrimaryKey());

        model.setConvenienceSetters(convenienceSetterProvider.getConvenienceSetters(column, javaDataTypeName));

        return model;
    }
}
