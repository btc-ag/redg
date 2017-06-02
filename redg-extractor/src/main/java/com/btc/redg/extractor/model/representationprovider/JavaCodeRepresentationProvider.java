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
import com.btc.redg.models.ColumnModel;
import com.btc.redg.models.ForeignKeyModel;

public interface JavaCodeRepresentationProvider {

    String getCodeForColumnValue(Object value, String sqlType, int sqlTypeInt, String javaType);

    String getCodeForFKValue(EntityModel model, ForeignKeyModel foreignKeyModel);
}
