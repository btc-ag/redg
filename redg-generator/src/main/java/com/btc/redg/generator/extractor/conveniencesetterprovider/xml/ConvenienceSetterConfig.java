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

import java.util.List;

public class ConvenienceSetterConfig {

    private List<DataTypeConvenienceSetterConfig> convenienceSetterConfigs;

    public List<DataTypeConvenienceSetterConfig> getConvenienceSetterConfigs() {
        return convenienceSetterConfigs;
    }

    public void setConvenienceSetterConfigs(List<DataTypeConvenienceSetterConfig> convenienceSetterConfigs) {
        this.convenienceSetterConfigs = convenienceSetterConfigs;
    }
}
