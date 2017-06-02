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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * RedG's default dummy factory. It should be able to provide a dummy for the most common scenarios. Special use cases
 * might render this unusable (circulatory references). In these cases, you can write your own {@link DummyFactory}.
 *
 * This DummyFactory supports {@link DummyPostProcessor}s, which are called after a dummy is created before it is returned.
 * When the factory creates transitive dummies, it gets called for each transitive entity as well.
 */
public class DefaultDummyFactory implements DummyFactory {

    private final Map<Class<? extends RedGEntity>, RedGEntity> dummyCache = new HashMap<>();

    private final List<DummyPostProcessor> dummyPostProcessorList = new ArrayList<>();

    /**
     * Returns a dummy entity for the requested type.
     * All this method guarantees is that the returned entity is a valid entity with all non null foreign key relations filled in,
     * it does not guarantee useful or even semantically correct data.
     * The dummy objects get taken either from the list of objects to insert from the redG object or are created on the fly. The results are cached and the
     * same entity will be returned for consecutive calls for an entity of the same type.
     *
     * @param redG       The redG instance
     * @param dummyClass The class specifying the wanted entity type
     * @param <T>        The wanted entity type
     * @return a dummy object of thew required type
     * @throws DummyCreationException When a new dummy has to be created but this creation fails
     */
    @Override
    public <T extends RedGEntity> T getDummy(final AbstractRedG redG, final Class<T> dummyClass) {
        // check if a dummy for this type already exists in cache
        if (this.dummyCache.containsKey(dummyClass)) {
            return dummyClass.cast(this.dummyCache.get(dummyClass));
        }
        final T obj = createNewDummy(redG, dummyClass); // if no one is found, create new
        this.dummyCache.put(dummyClass, obj);
        T processedObject = obj;
        for (DummyPostProcessor dpp : this.dummyPostProcessorList) {
            processedObject = dpp.processDummy(processedObject);
        }
        return processedObject;
    }

    @Override
    public void addDummyPostProcessor(final DummyPostProcessor dummyPostProcessor) {
        if (!this.dummyPostProcessorList.contains(dummyPostProcessor)) {
            this.dummyPostProcessorList.add(dummyPostProcessor);
        }
    }

    @Override
    public void removeDummyPostProcessor(final DummyPostProcessor dummyPostProcessor) {
        this.dummyPostProcessorList.remove(dummyPostProcessor);
    }

    @Override
    public void removeAllDummyPostProcessors() {
        this.dummyPostProcessorList.clear();
    }

    /**
     * Creates a new dummy entity of the required type. Required non null foreign keys will be taken from the {@link #getDummy(AbstractRedG, Class)} method
     * and will be created if necessary as well. If the creation fails for some reason, a {@link DummyCreationException} will be thrown.
     *
     * @param redG       The redG instance
     * @param dummyClass The class specifying the wanted entity type
     * @param <T>        The wanted entity type
     * @return A new dummy entity of the required type. It has already been added to the redG object and can be used immediately.
     * @throws DummyCreationException If no fitting constructor is found or instantiation fails
     */
    private <T extends RedGEntity> T createNewDummy(AbstractRedG redG, Class<T> dummyClass) {
        Constructor constructor = Arrays.stream(dummyClass.getDeclaredConstructors())
                .filter(this::isDummyRedGEntityConstructor)
                .findFirst().orElseThrow(() -> new DummyCreationException("Could not find a fitting constructor"));
        Object[] parameter = new Object[constructor.getParameterCount()];
        parameter[0] = redG;
        for (int i = 1; i < constructor.getParameterCount(); i++) {
            parameter[i] = getDummy(redG, constructor.getParameterTypes()[i]);
        }

        try {
            constructor.setAccessible(true);
            T obj = dummyClass.cast(constructor.newInstance(parameter));
            redG.addEntity(obj);
            return obj;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new DummyCreationException("Instantiation of the dummy failed", e);
        }
    }

    /**
     * Checks whether the constructor matches the standard redG constructor (AbstractRedG as first param  and zero or more classes extending RedGEntity).
     *
     * @param constructor The constructor to check
     * @return {@code true} if the constructor matches the standard redG constructor, {@code false} otherwise
     */
    private boolean isDummyRedGEntityConstructor(Constructor constructor) {
        if (constructor.getParameterCount() < 1) {
            return false;
        }
        if (!AbstractRedG.class.isAssignableFrom(constructor.getParameterTypes()[0])) {
            return false;
        }
        for (int i = 1; i < constructor.getParameterCount(); i++) {
            if (!RedGEntity.class.isAssignableFrom(constructor.getParameterTypes()[i])) {
                return false;
            }
        }
        return true;
    }
}
