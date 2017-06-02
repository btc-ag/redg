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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;


public class DefaultDummyFactoryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetDummy_createNew() throws Exception {
        AbstractRedG redG = spy(AbstractRedG.class);

        DefaultDummyFactory factory = new DefaultDummyFactory();
        assertNotNull(factory);

        TestRedGEntity1 entity1 = factory.getDummy(redG, TestRedGEntity1.class);
        assertNotNull(entity1);

        //assure it was properly added
        assertEquals(entity1, redG.findSingleEntity(TestRedGEntity1.class, e -> true));
    }

    @Test
    public void testGetDummy_reuseCachedObject() throws Exception {
        AbstractRedG redG = spy(AbstractRedG.class);

        DefaultDummyFactory factory = new DefaultDummyFactory();
        assertNotNull(factory);

        TestRedGEntity1 entity1 = factory.getDummy(redG, TestRedGEntity1.class);
        assertNotNull(entity1);

        TestRedGEntity1 entity2 = factory.getDummy(redG, TestRedGEntity1.class);
        assertNotNull(entity1);

        //assure it was properly added
        assertEquals(entity1, entity2);
    }

    @Test
    public void testGetDummy_transitiveDependencies() throws Exception {
        AbstractRedG redG = spy(AbstractRedG.class);

        DefaultDummyFactory factory = new DefaultDummyFactory();
        assertNotNull(factory);

        TestRedGEntity2 entity2 = factory.getDummy(redG, TestRedGEntity2.class);
        assertNotNull(entity2);

        assertTrue(redG.findSingleEntity(TestRedGEntity1.class, e -> true) != null);
        assertTrue(redG.findSingleEntity(TestRedGEntity2.class, e -> true) != null);
    }

    @Test
    public void testGetDummy_NoFittingConstructor() throws Exception {
        thrown.expect(DummyCreationException.class);
        thrown.expectMessage("Could not find a fitting constructor");
        AbstractRedG redG = spy(AbstractRedG.class);

        DefaultDummyFactory factory = new DefaultDummyFactory();
        assertNotNull(factory);

        factory.getDummy(redG, TestRedGEntity4.class);
    }
}
