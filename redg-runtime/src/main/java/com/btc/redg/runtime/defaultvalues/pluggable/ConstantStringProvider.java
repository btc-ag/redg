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

package com.btc.redg.runtime.defaultvalues.pluggable;

import com.btc.redg.models.ColumnModel;

/**
 * A {@link PluggableDefaultValueProvider} that returns a constant string.
 */
public class ConstantStringProvider implements PluggableDefaultValueProvider {

    private final String content;

    public ConstantStringProvider(final String content) {
        this.content = content;
    }

    @Override
    public boolean willProvide(final ColumnModel columnModel) {
        return String.class.equals(columnModel.getJavaTypeAsClass());
    }

    @Override
    public <T> T getDefaultValue(final ColumnModel columnModel, final Class<T> type) {
        return type.cast(content);
    }
}
