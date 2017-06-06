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

package com.btc.redg.generator.extractor.datatypeprovider.json;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import com.btc.redg.generator.extractor.datatypeprovider.DataTypeProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import schemacrawler.schema.Column;

/**
 * <p>
 * A data type provider that reads the types from a json file.
 * </p><p>
 * The format for the file is:
 * </p>
 * <p><blockquote><pre>
 * {
 *     "SCHEMA.TABLE": {
 *         "COLUMN" : "full.class.Name",
 *         "OTHER_COLUMN" : "possibly.other.Class",
 *         ...
 *     },
 *     ...
 * }
 * </pre></blockquote></p>
 */
public class JsonFileDataTypeProvider implements DataTypeProvider {

    private final HashMap<String, HashMap<String, String>> mappings;

    private final DataTypeProvider fallbackProvider;

    /**
     * Creates a {@link DataTypeProvider} that uses a JSON file as its data source. If no data type can be read from the JSON, the type is queried from the
     * {@code fallbackProvider}.
     *
     * @param jsonFile The JSON file that specifies the wanted data types. May not be {@code null}. See class documentation for format.
     * @param fallbackProvider The provider to be queried if no type can be found in the JSON file. May not be {@code null}
     * @throws IOException Gets thrown when the JSON file could not be read or parsed
     */
    public JsonFileDataTypeProvider(File jsonFile, DataTypeProvider fallbackProvider) throws IOException {
        this.fallbackProvider = fallbackProvider;
        Objects.requireNonNull(jsonFile);
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, HashMap<String, String>>> typeRef = new TypeReference<HashMap<String, HashMap<String, String>>>() {
        };
        mappings = mapper.readValue(jsonFile, typeRef);
    }

    @Override
    public String getCanonicalDataTypeName(final Column column) {
        HashMap<String, String> tableMap = mappings.get(column.getParent().getFullName());
        if (tableMap != null) {
            String className = tableMap.get(column.getName());
            if (className != null) {
                return className;
            }
        }
        return fallbackProvider.getCanonicalDataTypeName(column);
    }
}
