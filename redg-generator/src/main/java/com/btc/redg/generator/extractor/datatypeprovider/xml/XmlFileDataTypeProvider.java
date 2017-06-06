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

package com.btc.redg.generator.extractor.datatypeprovider.xml;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.btc.redg.generator.extractor.datatypeprovider.DataTypeProvider;
import com.thoughtworks.xstream.XStream;

import schemacrawler.schema.Column;

/**
 * <p>
 * A data type provider that reads the types from a json file.
 * </p><p>
 * The format for the file is:
 * </p><p><blockquote><pre>
 * &lt;typeMappings&gt;
 *     &lt;tableTypeMappings&gt;
 *         &lt;table name="AUCTION"&gt;
 *              &lt;column name="ID"&gt;java.lang.String&lt;/column&gt;
 *         &lt;/table&gt;
 *     &lt;/tableTypeMappings&gt;
 *     &lt;!-- TODO --&gt;
 *     &lt;defaultTypeMappings&gt;
 *         &lt;type sql="DECIMAL(1)"&gt;java.lang.Boolean&lt;/type&gt;
 *     &lt;/defaultTypeMappings&gt;
 * &lt;/typeMappings&gt;
 * </pre></blockquote></p>
 */
public class XmlFileDataTypeProvider implements DataTypeProvider {
    private TypeMappings typeMappings;
    private DataTypeProvider fallbackDataTypeProvider;

    public XmlFileDataTypeProvider(Reader xmlReader, DataTypeProvider fallbackDataTypeProvider) throws IOException {
        this(deserialiseXml(xmlReader), fallbackDataTypeProvider);
    }

    public XmlFileDataTypeProvider(TypeMappings typeMappings, DataTypeProvider fallbackDataTypeProvider) {
        this.typeMappings = typeMappings;
        this.fallbackDataTypeProvider = fallbackDataTypeProvider;
    }

    static TypeMappings deserialiseXml(Reader xmlReader) {
        TypeMappings typeMappings;
        XStream xStream = createXStream();
        typeMappings = (TypeMappings) xStream.fromXML(xmlReader, new TypeMappings());
        return typeMappings;
    }

    static XStream createXStream() {
        XStream xStream = new XStream();
        xStream.processAnnotations(new Class[]{
                ColumnTypeMapping.class,
                DefaultTypeMapping.class,
                TableTypeMapping.class,
                TypeMappings.class
        });
        xStream.useAttributeFor(TableTypeMapping.class, "tableName");
        xStream.addDefaultImplementation(ArrayList.class, List.class);
        return xStream;
    }

    @Override
    public String getCanonicalDataTypeName(final Column column) {
        String dataTypeByName = getDataTypeByName(column.getParent().getName(), column.getName());
        return dataTypeByName != null ? dataTypeByName : fallbackDataTypeProvider.getCanonicalDataTypeName(column);
    }

    String getDataTypeByName(String tableName, String columnName) {
        return typeMappings.getTableTypeMappings().stream()
                .filter(tableTypeMapping -> tableName.matches(tableTypeMapping.getTableName()))
                .flatMap(tableTypeMapping -> tableTypeMapping.getColumnTypeMappings().stream())
                .filter(columnTypeMapping -> columnName.matches(columnTypeMapping.getColumnName()))
                .findFirst()
                .map(ColumnTypeMapping::getJavaType)
                .orElse(null);
    }
}
