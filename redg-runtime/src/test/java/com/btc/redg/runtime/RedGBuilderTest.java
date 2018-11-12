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

import com.btc.redg.runtime.defaultvalues.DefaultDefaultValueStrategy;
import com.btc.redg.runtime.defaultvalues.pluggable.PluggableDefaultValueStrategy;
import com.btc.redg.runtime.dummy.DefaultDummyFactory;
import com.btc.redg.runtime.dummy.DummyFactory;
import com.btc.redg.runtime.insertvalues.DefaultSQLValuesFormatter;
import com.btc.redg.runtime.insertvalues.SQLValuesFormatter;
import com.btc.redg.runtime.transformer.DefaultPreparedStatementParameterSetter;
import com.btc.redg.runtime.transformer.PreparedStatementParameterSetter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.MethodRule;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import static org.junit.Assert.*;


public class RedGBuilderTest {

    private final String redGClassData = "yv66vgAAADQAFAoABAAQCAARBwASBwATAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1i" +
            "ZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABHRoaXMBAB1MY29tL2J0Yy9yZWRnL2dlbmVy" +
            "YXRlZC9SZWRHOwEAFGdldFZpc3VhbGl6YXRpb25Kc29uAQAUKClMamF2YS9sYW5nL1N0cmluZzsB" +
            "AApTb3VyY2VGaWxlAQAJUmVkRy5qYXZhDAAFAAYBAARub3BlAQAbY29tL2J0Yy9yZWRnL2dlbmVy" +
            "YXRlZC9SZWRHAQAhY29tL2J0Yy9yZWRnL3J1bnRpbWUvQWJzdHJhY3RSZWRHACEAAwAEAAAAAAAC" +
            "AAEABQAGAAEABwAAAC8AAQABAAAABSq3AAGxAAAAAgAIAAAABgABAAAABQAJAAAADAABAAAABQAK" +
            "AAsAAAABAAwADQABAAcAAAAtAAEAAQAAAAMSArAAAAACAAgAAAAGAAEAAAAIAAkAAAAMAAEAAAAD" +
            "AAoACwAAAAEADgAAAAIADw==";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testBuilder_AllDefault() {
        MyRedG redG = new RedGBuilder<>(MyRedG.class)
                .build();
        assertTrue(redG.getDefaultValueStrategy() instanceof DefaultDefaultValueStrategy);
        assertTrue(redG.getDummyFactory() instanceof DefaultDummyFactory);
        assertTrue(redG.getSqlValuesFormatter() instanceof DefaultSQLValuesFormatter);
        assertTrue(redG.getPreparedStatementParameterSetter() instanceof DefaultPreparedStatementParameterSetter);
    }


    @Test
    public void testBuilder_ClassPrivate() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Could not instantiate RedG instance");
        AbstractRedG redG = new RedGBuilder<>(PrivateRedG.class).build();
    }

    @Test
    public void testBuilder_DefaultConstructor() throws Exception {
        try {
            AbstractRedG redG = new RedGBuilder<>().build();
        } catch (RuntimeException e) {
            assertEquals("Could not load default RedG class", e.getMessage());
        }
        final ClassLoader classLoader = this.getClass().getClassLoader();
        final Method defineClassMethod = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
        defineClassMethod.setAccessible(true);
        // load class definition
        byte[] classDef = java.util.Base64.getDecoder().decode(this.redGClassData);
        defineClassMethod.invoke(classLoader, "com.btc.redg.generated.RedG", classDef, 0, classDef.length);

        try {
        AbstractRedG redG = new RedGBuilder<>().build();
        } catch (RuntimeException e) {
            assertEquals("Could not instantiate RedG instance", e.getMessage());
        }
    }

    @Test
    public void testBuilder_Customization() throws Exception {
        final PreparedStatementParameterSetter setter = new PreparedStatementParameterSetter() {
            @Override
            public void setParameter(final PreparedStatement statement, final int parameterIndex, final Object object, final AttributeMetaInfo attributeMetaInfo, final Connection connection) throws SQLException {

            }
        };
        final DummyFactory dummyFactory = new DummyFactory() {
            @Override
            public <T extends RedGEntity> T getDummy(final AbstractRedG redG, final Class<T> dummyClass) {
                return null;
            }

            @Override
            public boolean isDummy(final RedGEntity entity) {
                return false;
            }
        };
        final SQLValuesFormatter formatter = new SQLValuesFormatter() {
            @Override
            public <T> String formatValue(final T value, final String sqlDataType, final String fullTableName, final String tableName, final String columnName) {
                return null;
            }
        };

        MyRedG redG = new RedGBuilder<>(MyRedG.class)
                .withDefaultValueStrategy(new PluggableDefaultValueStrategy())
                .withDummyFactory(dummyFactory)
                .withPreparedStatementParameterSetter(setter)
                .withSqlValuesFormatter(formatter)
                .build();
        assertTrue(redG.getDefaultValueStrategy() instanceof PluggableDefaultValueStrategy);
        assertEquals(dummyFactory, redG.getDummyFactory());
        assertEquals(setter, redG.getPreparedStatementParameterSetter());
        assertEquals(formatter, redG.getSqlValuesFormatter());
    }

    @Test
    public void testBuilder_ErrorOnReUse() throws Exception {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Using the builder after build() was called is not allowed!");
        RedGBuilder builder = new RedGBuilder<>(MyRedG.class);
        builder.build();
        builder.withSqlValuesFormatter(null);
    }

    @Test
    public void testBuilder_ErrorOnReUse2() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Using the builder after build() was called is not allowed!");

        RedGBuilder builder = new RedGBuilder<>(MyRedG.class);
        builder.build();
        builder.withDefaultValueStrategy(null);
    }

    @Test
    public void testBuilder_ErrorOnReUse3() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Using the builder after build() was called is not allowed!");

        RedGBuilder builder = new RedGBuilder<>(MyRedG.class);
        builder.build();
        builder.withPreparedStatementParameterSetter(null);
    }

    @Test
    public void testBuilder_ErrorOnReUse4() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Using the builder after build() was called is not allowed!");

        RedGBuilder builder = new RedGBuilder<>(MyRedG.class);
        builder.build();
        builder.withDummyFactory(null);
    }

    public static class MyRedG extends AbstractRedG {

        @Override
        public String getVisualizationJson() {
            return "nope";
        }
    }

    public static class PrivateRedG extends AbstractRedG {

        private PrivateRedG() {

        }

        @Override
        public String getVisualizationJson() {
            return "nope";
        }
    }

}