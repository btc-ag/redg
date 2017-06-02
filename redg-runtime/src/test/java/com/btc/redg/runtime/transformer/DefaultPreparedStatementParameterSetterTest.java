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

package com.btc.redg.runtime.transformer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Types;

import org.junit.Test;
import org.mockito.Mockito;

import com.btc.redg.runtime.AttributeMetaInfo;


public class DefaultPreparedStatementParameterSetterTest {

    @Test
    public void testTransform() throws Exception {
        PreparedStatement preparedStatementMock = Mockito.mock(PreparedStatement.class);

        DefaultPreparedStatementParameterSetter parameterSetter = new DefaultPreparedStatementParameterSetter();
        parameterSetter.setParameter(preparedStatementMock, 1, "test", createMockAttributeMetaInfo(), null);

        Mockito.verify(preparedStatementMock).setObject(1, "test", Types.VARCHAR);
    }

    private AttributeMetaInfo createMockAttributeMetaInfo() {
        return new AttributeMetaInfo("", "", "", "", Types.VARCHAR, String.class, false);
    }
}
