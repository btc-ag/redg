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

package com.btc.redg.runtime.dummy;

import com.btc.redg.runtime.AbstractRedG;
import com.btc.redg.runtime.RedGEntity;

/**
 * The interface for RedG's dummy mechanism.
 *
 * When implementing a custom DummyFactory, {@link DummyFactory#getDummy(AbstractRedG, Class)} has to return valid dummy entities.
 * The {@link DummyPostProcessor} support is needed for the visualization feature, so if you want to use that, you have to implement it properly
 * or dummy entities might not get flagged as such in the visualization.
 */
public interface DummyFactory {

    <T extends RedGEntity> T getDummy(AbstractRedG redG, Class<T> dummyClass);

    void addDummyPostProcessor(DummyPostProcessor dummyPostProcessor);
    void removeDummyPostProcessor(DummyPostProcessor dummyPostProcessor);
    void removeAllDummyPostProcessors();
}
