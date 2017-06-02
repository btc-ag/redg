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

import java.util.Date;

/**
 * A {@link com.btc.redg.runtime.defaultvalues.pluggable.PluggableDefaultValueProvider} always returning the current date.
 */
public class CurrentDateProvider extends AbstractDateProvider {

    @Override
    public <T> T getDefaultValue(final ColumnModel columnModel, final Class<T> type) {
        return convertDate(new Date(), type);
    }
}
