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

package com.btc.redg.runtime;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import com.btc.redg.runtime.defaultvalues.DefaultDefaultValueStrategy;
import com.btc.redg.runtime.defaultvalues.pluggable.PluggableDefaultValueStrategy;
import com.btc.redg.runtime.insertvalues.DefaultSQLValuesFormatter;
import com.btc.redg.runtime.mocks.MockEntity1;
import com.btc.redg.runtime.mocks.MockEntity2;
import com.btc.redg.runtime.mocks.MockRedG;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class AbstractRedGTest {

    @Test
    public void testDefaultValueStrategySetGet() {
        PluggableDefaultValueStrategy strategy = new PluggableDefaultValueStrategy();
        MockRedG mockRedG = new MockRedG();

        assertTrue(mockRedG.getDefaultValueStrategy() instanceof DefaultDefaultValueStrategy);
        mockRedG.setDefaultValueStrategy(strategy);
        assertEquals(strategy, mockRedG.getDefaultValueStrategy());
        mockRedG.setDefaultValueStrategy(null);
        assertTrue(mockRedG.getDefaultValueStrategy() instanceof DefaultDefaultValueStrategy);
    }

    @Test
    public void testInsertValuesFormatterSetGet() {
        MockRedG mockRedG = new MockRedG();
        DefaultSQLValuesFormatter formatter = new DefaultSQLValuesFormatter();

        assertTrue(mockRedG.getSqlValuesFormatter() instanceof DefaultSQLValuesFormatter);
        assertNotEquals(formatter, mockRedG.getSqlValuesFormatter());
        mockRedG.setSqlValuesFormatter(formatter);
        assertEquals(formatter, mockRedG.getSqlValuesFormatter());
        mockRedG.setSqlValuesFormatter(null);
        assertTrue(mockRedG.getSqlValuesFormatter() instanceof DefaultSQLValuesFormatter);
        assertNotEquals(formatter, mockRedG.getSqlValuesFormatter());
    }

    @Test
    public void testFindSingleEntity() {
        MockRedG mockRedG = new MockRedG();
        MockEntity1 entity1 = new MockEntity1();
        MockEntity2 entity2 = new MockEntity2();

        mockRedG.addEntity(entity1);
        mockRedG.addEntity(entity2);

        assertEquals(entity1, mockRedG.findSingleEntity(MockEntity1.class, e -> e.toString().equals("MockEntity1")));
        assertEquals(entity2, mockRedG.findSingleEntity(MockEntity2.class, e -> e.toString().equals("MockEntity2")));

        boolean exceptionThrown = false;
        try {
            assertNull(mockRedG.findSingleEntity(MockEntity1.class, e -> false));
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void testFindAllObjects() {
        MockRedG mockRedG = new MockRedG();
        List<MockEntity1> entities = IntStream.rangeClosed(1, 20).mapToObj(i -> new MockEntity1()).collect(Collectors.toList());
        entities.forEach(mockRedG::addEntity);

        assertEquals(entities, mockRedG.findEntities(MockEntity1.class, e -> true));
        assertTrue(mockRedG.findEntities(MockEntity2.class, e -> true).isEmpty());
    }

    @Test
    public void testGenerateInsertStatements() {
        MockRedG mockRedG = new MockRedG();
        List<MockEntity1> entities = IntStream.rangeClosed(1, 20).mapToObj(i -> new MockEntity1()).collect(Collectors.toList());
        List<String> results = IntStream.rangeClosed(1, 20).mapToObj(i -> "INSERT").collect(Collectors.toList());
        entities.forEach(mockRedG::addEntity);

        assertEquals(results, mockRedG.generateSQLStatements());
    }

}
