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

package com.btc.redg.generator.extractor.explicitattributedecider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import schemacrawler.schema.Column;
import schemacrawler.schema.ForeignKey;
import schemacrawler.schema.ForeignKeyColumnReference;

/**
 * <p>
 * An {@link ExplicitAttributeDecider} reading explicit attribute information from a JSON file.
 * </p><p>
 * The format for the file is:
 * </p><p><blockquote><pre>
 * {
 *     "TABLENAME": [EXPLICITCOLUMNNAME1, EXPLICITCOLUMNNAME2, ...],
 *     ...
 * }
 * </pre></blockquote></p>
 */
public class JsonFileExplicitAttributeDecider implements ExplicitAttributeDecider {

    private final Map<String, JsonExplicitModel> explicitDataByTableRegex;

    public JsonFileExplicitAttributeDecider(File jsonFile) throws IOException {
        this(new InputStreamReader(new FileInputStream(jsonFile), "UTF-8"));
    }

    public JsonFileExplicitAttributeDecider(Reader reader) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, JsonExplicitModel>> typeRef = new TypeReference<HashMap<String, JsonExplicitModel>>() {
        };
        explicitDataByTableRegex = mapper.readValue(reader, typeRef);
    }

    @Override
    public boolean isExplicitAttribute(Column column) {
        String tableName = column.getParent().getName();
        String columnName = column.getName();

        return explicitDataByTableRegex.keySet().stream()
                .filter(tableName::matches)
                .flatMap(tableNameRegex -> {
                    if (explicitDataByTableRegex.get(tableNameRegex).attributes == null) {
                        return null;
                    }
                    return Arrays.stream(explicitDataByTableRegex.get(tableNameRegex).attributes);
                })
                .anyMatch(columnName::matches);
    }

    @Override
    public boolean isExplicitForeignKey(final ForeignKey foreignKey) {
        String tableName = foreignKey.getColumnReferences().get(0).getForeignKeyColumn().getParent().getName();

        return explicitDataByTableRegex.keySet().stream()
                .filter(tableName::matches)
                .flatMap(tableNameRegex -> {
                    if (explicitDataByTableRegex.get(tableNameRegex).relations == null) {
                        return null;
                    }
                    return Arrays.stream(explicitDataByTableRegex.get(tableNameRegex).relations);
                })
                .anyMatch(regexes -> matchesColumns(regexes, foreignKey));
    }

    private static boolean matchesColumns(final String[] regexes, final ForeignKey foreignKey) {
        /*return foreignKey.getColumnReferences().stream()
                .map(ForeignKeyColumnReference::getForeignKeyColumn)
                .map(Column::getName)
                .anyMatch(name -> name.matches(regex));*/
        List<String> columnNames = foreignKey.getColumnReferences().stream()
                .map(ForeignKeyColumnReference::getForeignKeyColumn)
                .map(Column::getName)
                .collect(Collectors.toList());
        if (columnNames.size() != regexes.length) {
            return false;
        }
        final Stack<Integer> usedFields = new Stack<>();
        for (final String colName : columnNames) {
            boolean foundMatch = false;
            for (int i = 0; i < regexes.length; i++) {
                if (!usedFields.contains(i)) {
                    foundMatch = colName.matches(regexes[i]);
                    if (foundMatch) {
                        usedFields.push(i);
                        break;
                    }
                }
            }
            if (!foundMatch) {
                return false;
            }
        }
        return true;

    }

    private static class JsonExplicitModel {
        public String[] attributes;
        public String[][] relations;
    }
}
