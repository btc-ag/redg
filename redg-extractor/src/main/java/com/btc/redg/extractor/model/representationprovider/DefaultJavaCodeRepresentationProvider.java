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

package com.btc.redg.extractor.model.representationprovider;

import com.btc.redg.extractor.model.EntityModel;
import com.btc.redg.models.ForeignKeyModel;

import java.math.BigDecimal;

public class DefaultJavaCodeRepresentationProvider implements JavaCodeRepresentationProvider {

    @Override
    public String getCodeForColumnValue(final Object value, final String sqlType, final int sqlTypeInt, final String javaType) {
        if (value == null) {
            return "null";
        }
        if (javaType.matches("(java\\.lang\\.)?String$")) {
            return "\"" + value + "\"";
        }
        if (javaType.matches("(java\\.lang\\.)?[lL]ong$")) {
            return value.toString() + "L";
        }
        if (javaType.matches("(java\\.lang\\.)?[fF]loat$")) {
            return value.toString() + "F";
        }
        if (javaType.matches("(java\\.lang\\.)?[bB]oolean$")) {
            if (value instanceof Number) {
                return Boolean.toString((((Number) value).intValue() != 0));
            }
            if (value instanceof String) {
                return Boolean.toString(((String) value).matches("^[YyJjTt]")); //regex for Yes, Ja, True
            }
            return value.toString();
        }
        if (javaType.equals("java.math.BigDecimal")) {
            return "new java.math.BigDecimal(\"" + value.toString() + "\")";
        }
        if (javaType.equals("java.sql.Timestamp")) {
            return "java.sql.Timestamp.valueOf(" + value.toString() + ")";
        }
        return value.toString();
    }

    @Override
    public String getCodeForFKValue(final EntityModel model, final ForeignKeyModel foreignKeyModel) {
        return model.getVariableName();
    }
}
