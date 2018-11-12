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

package com.btc.redg.runtime.defaultvalues;

import com.btc.redg.runtime.defaultvalues.pluggable.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;


public class PluggableDefaultValueStrategyTest {

    @Test
    public void testStrategy_NoProvider() {
        PluggableDefaultValueStrategy strategy = new PluggableDefaultValueStrategy();
        assertNull(strategy.getDefaultValue(TestUtils.getCM("", "", "", Integer.class, true), Integer.class));
        assertThat(strategy.getProviders()).isEmpty();
    }

    @Test
    public void testProvider_ReplaceProviders() {
        PluggableDefaultValueStrategy strategy = new PluggableDefaultValueStrategy();
        strategy.addProvider(new StaticNumberProvider(42L));
        assertThat(strategy.getDefaultValue(TestUtils.getCM("", "", "", Integer.class, true), Integer.class)).isEqualTo(42);
        strategy.setProviders(Collections.singletonList(new StaticNumberProvider(21L)));
        assertThat(strategy.getDefaultValue(TestUtils.getCM("", "", "", Integer.class, true), Integer.class)).isEqualTo(21);
    }

    @Test
    public void testProvider_ConstantValueProvider() {
        PluggableDefaultValueStrategy strategy = new PluggableDefaultValueStrategy();
        strategy.addProvider(new ConstantValueProvider(42L));
        assertThat(strategy.getDefaultValue(TestUtils.getCM("", "", "", Integer.class, true), Integer.class)).isNull();
        assertThat(strategy.getDefaultValue(TestUtils.getCM("", "", "", Long.class, true), Long.class)).isEqualTo(42L);

    }

    @Test
    public void testProvider_CustomConditionalProvider() {
        PluggableDefaultValueProvider p = new CustomConditionalProvider(cm -> cm.getDbName().equals("REDG"), new StaticNumberProvider(1));
        assertThat(p.getDefaultValue(TestUtils.getCM("", "", "", Integer.class, true), Integer.class)).isNull();
        assertThat(p.getDefaultValue(TestUtils.getCM("", "", "REDG", Long.class, true), Long.class)).isEqualTo(1L);
        assertThat(p.getDefaultValue(TestUtils.getCM("", "", "REDG", String.class, true), String.class)).isNull();
        assertThat(p.getDefaultValue(TestUtils.getCM("", "", "", String.class, true), String.class)).isNull();

        assertThat(p.willProvide(TestUtils.getCM("", "", "", Integer.class, true))).isFalse();
        assertThat(p.willProvide(TestUtils.getCM("", "", "REDG", Long.class, true))).isTrue();
        assertThat(p.willProvide(TestUtils.getCM("", "", "REDG", String.class, true))).isFalse();
        assertThat(p.willProvide(TestUtils.getCM("", "", "", String.class, true))).isFalse();

    }

    @Test
    public void testProvider_StaticDateProvider() {
        final Date date = new Date(1234567891011L);

        PluggableDefaultValueStrategy strategy = new PluggableDefaultValueStrategy();
        strategy.addProvider(new StaticDateProvider(date));

        assertNull(strategy.getDefaultValue(TestUtils.getCM("", "", "", String.class, false), String.class));
        assertNull(strategy.getDefaultValue(TestUtils.getCM("", "", "", String.class, true), String.class));
        assertEquals(date, strategy.getDefaultValue(TestUtils.getCM("", "", "", java.sql.Date.class, false), java.sql.Date.class));
        assertEquals(date, strategy.getDefaultValue(TestUtils.getCM("", "", "", Date.class, true), Date.class));
        assertEquals(new java.sql.Date(date.getTime()),
                strategy.getDefaultValue(TestUtils.getCM("", "", "", java.sql.Date.class, true), java.sql.Date.class));
        assertEquals(new Timestamp(date.getTime()), strategy.getDefaultValue(TestUtils.getCM("", "", "", Timestamp.class, true), Timestamp.class));
        assertEquals(new Time(date.getTime()), strategy.getDefaultValue(TestUtils.getCM("", "", "", Time.class, true), Time.class));
        assertEquals(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()),
                strategy.getDefaultValue(TestUtils.getCM("", "", "", ZonedDateTime.class, true), ZonedDateTime.class));

        assertEquals(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()),
                strategy.getDefaultValue(TestUtils.getCM("", "", "", LocalDateTime.class, true), LocalDateTime.class));
        assertEquals(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate(),
                strategy.getDefaultValue(TestUtils.getCM("", "", "", LocalDate.class, true), LocalDate.class));
        assertEquals(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalTime(),
                strategy.getDefaultValue(TestUtils.getCM("", "", "", LocalTime.class, true), LocalTime.class));

        assertEquals(OffsetDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()),
                strategy.getDefaultValue(TestUtils.getCM("", "", "", OffsetDateTime.class, true), OffsetDateTime.class));
        assertEquals(OffsetDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toOffsetTime(),
                strategy.getDefaultValue(TestUtils.getCM("", "", "", OffsetTime.class, true), OffsetTime.class));
    }

    @Test
    public void testProvider_CurrentDateProvider() {
        PluggableDefaultValueStrategy strategy = new PluggableDefaultValueStrategy();
        strategy.addProvider(new CurrentDateProvider());

        Date d = strategy.getDefaultValue(TestUtils.getCM("", "", "", Date.class, false), Date.class);
        assertTrue(Duration.between(new Date().toInstant(), d.toInstant()).abs().getSeconds() < 2);
        ZonedDateTime zdt = strategy.getDefaultValue(TestUtils.getCM("", "", "", ZonedDateTime.class, true), ZonedDateTime.class);
        assertTrue(Duration.between(ZonedDateTime.now(), zdt).abs().getSeconds() < 2);
    }

    @Test
    public void testProvider_StaticNumberProvider() {
        PluggableDefaultValueStrategy strategy = new PluggableDefaultValueStrategy();
        strategy.addProvider(new StaticNumberProvider(42L));

        assertEquals(Long.valueOf(42L), strategy.getDefaultValue(TestUtils.getCM("", "", "", Long.class, false), Long.class));
        assertNull(strategy.getDefaultValue(TestUtils.getCM("", "", "", String.class, false), String.class));
        assertEquals(42L, (long) strategy.getDefaultValue(TestUtils.getCM("", "", "", Long.class, true), Long.class));
        assertEquals(42, (int) strategy.getDefaultValue(TestUtils.getCM("", "", "", Integer.class, true), Integer.class));
        assertEquals(42.0, strategy.getDefaultValue(TestUtils.getCM("", "", "", Double.class, true), Double.class), 0.0);
        assertEquals(42.0f, strategy.getDefaultValue(TestUtils.getCM("", "", "", Float.class, true), Float.class), 0f);
        assertEquals(new BigDecimal(42), strategy.getDefaultValue(TestUtils.getCM("", "", "", BigDecimal.class, true), BigDecimal.class));
        assertEquals((byte) 42, (byte) strategy.getDefaultValue(TestUtils.getCM("", "", "", Byte.class, true), Byte.class));
        assertEquals((short) 42, (short) strategy.getDefaultValue(TestUtils.getCM("", "", "", Short.class, true), Short.class));
        assertEquals(42, strategy.getDefaultValue(TestUtils.getCM("", "", "", AtomicInteger.class, true), AtomicInteger.class).get());
        assertEquals(42L, strategy.getDefaultValue(TestUtils.getCM("", "", "", AtomicLong.class, true), AtomicLong.class).get());

        assertNull(new StaticNumberProvider(BigDecimal.ONE).convertNumber(new BigDecimal(0), String.class));
    }

    @Test
    public void testProvider_IncrementingNumberProvider() {
        PluggableDefaultValueStrategy strategy = new PluggableDefaultValueStrategy();
        strategy.addProvider(new IncrementingNumberProvider());

        assertEquals(1L, (long) strategy.getDefaultValue(TestUtils.getCM("", "", "", Long.class, false), Long.class));
        assertEquals(1L, (long) strategy.getDefaultValue(TestUtils.getCM("TEST", "TABLE", "COL1", Long.class, true), Long.class));
        assertEquals(2, (int) strategy.getDefaultValue(TestUtils.getCM("TEST", "TABLE", "COL1", Integer.class, true), Integer.class));

        assertEquals(1L, (long) strategy.getDefaultValue(TestUtils.getCM("TEST", "TABLE", "COL2", Long.class, true), Long.class));
    }

    @Test
    public void testProvider_ConstantStringProvider() {
        PluggableDefaultValueStrategy strategy = new PluggableDefaultValueStrategy();
        strategy.addProvider(new ConstantStringProvider("HelloWorld"));

        assertEquals("HelloWorld", strategy.getDefaultValue(TestUtils.getCM("", "", "", String.class, false), String.class));
        assertEquals("HelloWorld", strategy.getDefaultValue(TestUtils.getCM("", "", "", String.class, true), String.class));
    }

    @Test
    public void testProvider_ConditionalProvider() {
        PluggableDefaultValueStrategy strategy = new PluggableDefaultValueStrategy();
        strategy.addProvider(new ConditionalProvider(new ConstantStringProvider("Hello"), ".+", ".+LE", "HELLO"));
        strategy.addProvider(new ConditionalProvider(new ConstantStringProvider("World"), "TEST.*", ".+", "WOR.+"));
        strategy.addProvider(new ConstantStringProvider("Fallback"));

        assertEquals("Fallback", strategy.getDefaultValue(TestUtils.getCM("", "", "", String.class, false), String.class));
        assertEquals("Hello", strategy.getDefaultValue(TestUtils.getCM("TEST", "TABLE", "HELLO", String.class, true), String.class));
        assertEquals("Fallback", strategy.getDefaultValue(TestUtils.getCM("TEST", "ELBAT", "HELLO", String.class, true), String.class));
        assertEquals("World", strategy.getDefaultValue(TestUtils.getCM("TEST", "TABLE", "WORLD", String.class, true), String.class));
        assertEquals("World", strategy.getDefaultValue(TestUtils.getCM("TEST", "TABLE", "WORD", String.class, true), String.class));
        assertEquals("Fallback", strategy.getDefaultValue(TestUtils.getCM("TSET", "TABLE", "WORD", String.class, true), String.class));
        assertEquals("Fallback", strategy.getDefaultValue(TestUtils.getCM("TEST", "TABLE", "COLUMN", String.class, true), String.class));
    }

}
