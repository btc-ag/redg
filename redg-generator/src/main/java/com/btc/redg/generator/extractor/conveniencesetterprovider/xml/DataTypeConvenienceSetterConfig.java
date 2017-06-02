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

package com.btc.redg.generator.extractor.conveniencesetterprovider.xml;

import com.btc.redg.models.ConvenienceSetterModel;

import java.util.List;

public class DataTypeConvenienceSetterConfig {

    private String javaDataTypeName;
    private List<ConvenienceSetterModel> convenienceSetters;

    public String getJavaDataTypeName() {
        return javaDataTypeName;
    }

    public void setJavaDataTypeName(String javaDataTypeName) {
        this.javaDataTypeName = javaDataTypeName;
    }

    public List<ConvenienceSetterModel> getConvenienceSetters() {
        return convenienceSetters;
    }

    public void setConvenienceSetters(List<ConvenienceSetterModel> convenienceSetters) {
        this.convenienceSetters = convenienceSetters;
    }
}
