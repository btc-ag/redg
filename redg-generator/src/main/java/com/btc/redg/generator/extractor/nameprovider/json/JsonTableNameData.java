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

package com.btc.redg.generator.extractor.nameprovider.json;

import java.util.HashMap;

public class JsonTableNameData {

    private String name;
    private HashMap<String, String> columns;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public HashMap<String, String> getColumns() {
        return columns;
    }

    public void setColumns(final HashMap<String, String> columns) {
        this.columns = columns;
    }
}
