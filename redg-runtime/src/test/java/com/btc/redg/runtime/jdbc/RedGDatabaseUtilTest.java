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

package com.btc.redg.runtime.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import com.btc.redg.runtime.InsertionFailedException;
import com.btc.redg.runtime.RedGEntity;
import com.btc.redg.runtime.mocks.MockEntity1;
import com.btc.redg.runtime.mocks.MockEntity2;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RedGDatabaseUtilTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testInsertDataIntoDatabase() throws Exception {
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        stmt.execute("CREATE TABLE TEST (CONTENT VARCHAR2(50 CHAR))");

        List<MockEntity1> gObjects = IntStream.rangeClosed(1, 20).mapToObj(i -> new MockEntity1()).collect(Collectors.toList());

        RedGDatabaseUtil.insertDataIntoDatabase(gObjects, connection);

        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM TEST");
        rs.next();
        assertEquals(20, rs.getInt(1));

    }

    @Test
    public void testInsertDataIntoDatabase_FailPreparedStatement() throws Exception {
        thrown.expect(InsertionFailedException.class);
        thrown.expectMessage("Could not get prepared statement for class");

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Mock reason"));

        List<MockEntity1> gObjects = IntStream.rangeClosed(1, 20).mapToObj(i -> new MockEntity1()).collect(Collectors.toList());

        RedGDatabaseUtil.insertDataIntoDatabase(gObjects, mockConnection);
    }

    @Test
    public void testInsertDataIntoDatabase_FailOnPreparedStatementSetValue() throws Exception {
        thrown.expect(InsertionFailedException.class);
        thrown.expectMessage("Setting value for statement failed");

        Connection mockConnection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        Mockito.doThrow(new SQLException("Mock reason")).when(preparedStatement).setObject(anyInt(), any(), anyInt());
        when(mockConnection.prepareStatement(anyString())).thenReturn(preparedStatement);

        List<MockEntity1> gObjects = IntStream.rangeClosed(1, 20).mapToObj(i -> new MockEntity1()).collect(Collectors.toList());

        RedGDatabaseUtil.insertDataIntoDatabase(gObjects, mockConnection);
    }

    @Test
    public void testInsertDataIntoDatabase_FailOnPreparedStatementExecute() throws Exception {
        thrown.expect(InsertionFailedException.class);
        thrown.expectMessage("SQL execution failed");

        Connection mockConnection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        Mockito.doThrow(new SQLException("Mock reason")).when(preparedStatement).execute();
        when(mockConnection.prepareStatement(anyString())).thenReturn(preparedStatement);

        List<MockEntity1> gObjects = IntStream.rangeClosed(1, 20).mapToObj(i -> new MockEntity1()).collect(Collectors.toList());

        RedGDatabaseUtil.insertDataIntoDatabase(gObjects, mockConnection);
    }

    @Test
    public void testInsertDataIntoDatabase_FailOnCommit() throws Exception {
        thrown.expect(InsertionFailedException.class);
        thrown.expectMessage("Commit failed");

        Connection mockConnection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(preparedStatement);
        Mockito.doThrow(new SQLException("Mock reason")).when(mockConnection).commit();

        List<RedGEntity> gObjects = IntStream.rangeClosed(1, 20).mapToObj(i -> new MockEntity1()).collect(Collectors.toList());
        gObjects.add(new MockEntity2());

        RedGDatabaseUtil.insertDataIntoDatabase(gObjects, mockConnection);
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:mem:test", "", "");
    }

}