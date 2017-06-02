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

package com.btc.redg.runtime.util;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.junit.Assert;
import org.junit.Test;

public class DateConverterTest {

    private static final OffsetDateTime EXPECTED_DATETIME = OffsetDateTime.of(2017, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
    private static final OffsetDateTime EXPECTED_DATETIME2 = OffsetDateTime.of(2017, 1, 2, 3, 0, 0, 0, ZoneOffset.UTC);
    private static final OffsetDateTime EXPECTED_DATE = OffsetDateTime.of(2017, 1, 2, 0, 0, 0, 0, ZoneOffset.UTC);

    @Test
    public void testParseInstant() throws Exception {
        Assert.assertEquals(EXPECTED_DATETIME, DateConverter.parseInstant("2017-01-02T03:04:05.000Z").atOffset(ZoneOffset.UTC));
        Assert.assertEquals(EXPECTED_DATETIME, DateConverter.parseInstant("2017-01-02T03:04:05").atOffset(ZoneOffset.UTC));
        Assert.assertEquals(EXPECTED_DATE, DateConverter.parseInstant("2017-01-02").atOffset(ZoneOffset.UTC));
        Assert.assertEquals(EXPECTED_DATETIME2, DateConverter.parseInstant("2017-01-02-03:00").atOffset(ZoneOffset.UTC));
    }

}