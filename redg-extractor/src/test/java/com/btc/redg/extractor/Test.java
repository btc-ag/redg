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

package com.btc.redg.extractor;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.mockito.Mockito;

import com.btc.redg.extractor.model.EntityModel;
import com.btc.redg.extractor.tablemodelextractor.TableModelExtractor;
import com.btc.redg.models.TableModel;

public class Test {

    private static final String EXCHANGE_RATE = "rO0ABXNyAB5jb20uYnRjLnJlZGcubW9kZWxzLlRhYmxlTW9kZWx0b/Ys7hpPuAIACloAGGhhc0NvbHVtbnNBbmRGb3JlaWduS2V5c0wACWNsYXNzTmFtZXQAEkxqYXZhL2xhbmcvU3RyaW5nO0wAB2NvbHVtbnN0ABBMamF2YS91dGlsL0xpc3Q7TAALZm9yZWlnbktleXNxAH4AAkwAE2luY29taW5nRm9yZWlnbktleXNxAH4AAkwAF2pvaW5UYWJsZVNpbXBsaWZpZXJEYXRhdAAPTGphdmEvdXRpbC9NYXA7TAAEbmFtZXEAfgABTAALcGFja2FnZU5hbWVxAH4AAUwAC3NxbEZ1bGxOYW1lcQB+AAFMAAdzcWxOYW1lcQB+AAF4cAF0AA5NeUV4Y2hhbmdlUmF0ZXNyABNqYXZhLnV0aWwuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpleHAAAAAEdwQAAAAEc3IAH2NvbS5idGMucmVkZy5tb2RlbHMuQ29sdW1uTW9kZWxnpctHtwTU/AIADVoAEWV4cGxpY2l0QXR0cmlidXRlWgAHbm90TnVsbFoAEHBhcnRPZkZvcmVpZ25LZXlaABBwYXJ0T2ZQcmltYXJ5S2V5SQAKc3FsVHlwZUludFoABnVuaXF1ZUwAEmNvbnZlbmllbmNlU2V0dGVyc3EAfgACTAAPZGJGdWxsVGFibGVOYW1lcQB+AAFMAAZkYk5hbWVxAH4AAUwAC2RiVGFibGVOYW1lcQB+AAFMAAxqYXZhVHlwZU5hbWVxAH4AAUwABG5hbWVxAH4AAUwAB3NxbFR5cGVxAH4AAXhwAAEAAQAAAAMBc3IAH2phdmEudXRpbC5Db2xsZWN0aW9ucyRFbXB0eUxpc3R6uBe0PKee3gIAAHhwdAAcIlJULVRFIi5QVUJMSUMuRVhDSEFOR0VfUkFURXQAAklEdAANRVhDSEFOR0VfUkFURXQAFGphdmEubWF0aC5CaWdEZWNpbWFsdAACaWR0AAdERUNJTUFMc3EAfgAIAAEBAAAAAAMAcQB+AAt0ABwiUlQtVEUiLlBVQkxJQy5FWENIQU5HRV9SQVRFdAAMUkVGRVJFTkNFX0lEcQB+AA5xAH4AD3QAC3JlZmVyZW5jZUlkcQB+ABFzcQB+AAgAAAAAAAAADABxAH4AC3QAHCJSVC1URSIuUFVCTElDLkVYQ0hBTkdFX1JBVEV0AApGSVJTVF9OQU1FcQB+AA50ABBqYXZhLmxhbmcuU3RyaW5ndAAJZmlyc3ROYW1ldAAHVkFSQ0hBUnNxAH4ACAAAAQAAAAAMAHEAfgALdAAcIlJULVRFIi5QVUJMSUMuRVhDSEFOR0VfUkFURXQAD1BSRVZfRklSU1RfTkFNRXEAfgAOcQB+ABl0AA1wcmV2Rmlyc3ROYW1lcQB+ABt4c3EAfgAGAAAAAncEAAAAAnNyACNjb20uYnRjLnJlZGcubW9kZWxzLkZvcmVpZ25LZXlNb2RlbLAijqX419iQAgAEWgAHbm90TnVsbEwADGphdmFUeXBlTmFtZXEAfgABTAAEbmFtZXEAfgABTAAKcmVmZXJlbmNlc3EAfgADeHABdAANTXlFeGNoYW5nZVJlZnQAFnJlZmVyZW5jZUlkRXhjaGFuZ2VSZWZzcgARamF2YS51dGlsLkhhc2hNYXAFB9rBwxZg0QMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAMdwgAAAAQAAAAAXEAfgAUc3IAKWNvbS5idGMucmVkZy5tb2RlbHMuRm9yZWlnbktleUNvbHVtbk1vZGVsSnvgqnao6n4CAAhJAApzcWxUeXBlSW50TAAPZGJGdWxsVGFibGVOYW1lcQB+AAFMAAZkYk5hbWVxAH4AAUwAC2RiVGFibGVOYW1lcQB+AAFMAAlsb2NhbE5hbWVxAH4AAUwACWxvY2FsVHlwZXEAfgABTAAXcHJpbWFyeUtleUF0dHJpYnV0ZU5hbWVxAH4AAUwAB3NxbFR5cGVxAH4AAXhwAAAAA3QAHCJSVC1URSIuUFVCTElDLkVYQ0hBTkdFX1JBVEVxAH4AFHEAfgAOdAALcmVmZXJlbmNlSWRxAH4AD3QAAmlkcQB+ABF4c3EAfgAhAHQADk15RXhjaGFuZ2VSYXRldAAJY29tcG9zaXRlc3EAfgAlP0AAAAAAAAx3CAAAABAAAAACcQB+ABRzcQB+ACcAAAADdAAcIlJULVRFIi5QVUJMSUMuRVhDSEFOR0VfUkFURXEAfgAUcQB+AA50AAtyZWZlcmVuY2VJZHEAfgAPdAALcmVmZXJlbmNlSWRxAH4AEXEAfgAec3EAfgAnAAAADHQAHCJSVC1URSIuUFVCTElDLkVYQ0hBTkdFX1JBVEVxAH4AHnEAfgAOdAANcHJldkZpcnN0TmFtZXEAfgAZdAAJZmlyc3ROYW1lcQB+ABt4eHNxAH4ABgAAAAF3BAAAAAFzcgArY29tLmJ0Yy5yZWRnLm1vZGVscy5JbmNvbWluZ0ZvcmVpZ25LZXlNb2RlbIOEJFLnwObUAgAFWgAHbm90TnVsbEwADWF0dHJpYnV0ZU5hbWVxAH4AAUwAGHJlZmVyZW5jaW5nQXR0cmlidXRlTmFtZXEAfgABTAAVcmVmZXJlbmNpbmdFbnRpdHlOYW1lcQB+AAFMABdyZWZlcmVuY2luZ0phdmFUeXBlTmFtZXEAfgABeHABdAAZZXhjaGFuZ2VSYXRlc0ZvckNvbXBvc2l0ZXQACWNvbXBvc2l0ZXQADEV4Y2hhbmdlUmF0ZXQADk15RXhjaGFuZ2VSYXRleHNxAH4AJT9AAAAAAAAAdwgAAAAQAAAAAHh0AAxFeGNoYW5nZVJhdGV0AAxjb20uZGVtby5wa2d0ABwiUlQtVEUiLlBVQkxJQy5FWENIQU5HRV9SQVRFcQB+AA4=";
    private static final String EXCHANGE_REF = "rO0ABXNyAB5jb20uYnRjLnJlZGcubW9kZWxzLlRhYmxlTW9kZWx0b/Ys7hpPuAIACloAGGhhc0NvbHVtbnNBbmRGb3JlaWduS2V5c0wACWNsYXNzTmFtZXQAEkxqYXZhL2xhbmcvU3RyaW5nO0wAB2NvbHVtbnN0ABBMamF2YS91dGlsL0xpc3Q7TAALZm9yZWlnbktleXNxAH4AAkwAE2luY29taW5nRm9yZWlnbktleXNxAH4AAkwAF2pvaW5UYWJsZVNpbXBsaWZpZXJEYXRhdAAPTGphdmEvdXRpbC9NYXA7TAAEbmFtZXEAfgABTAALcGFja2FnZU5hbWVxAH4AAUwAC3NxbEZ1bGxOYW1lcQB+AAFMAAdzcWxOYW1lcQB+AAF4cAB0AA1NeUV4Y2hhbmdlUmVmc3IAE2phdmEudXRpbC5BcnJheUxpc3R4gdIdmcdhnQMAAUkABHNpemV4cAAAAAJ3BAAAAAJzcgAfY29tLmJ0Yy5yZWRnLm1vZGVscy5Db2x1bW5Nb2RlbGely0e3BNT8AgANWgARZXhwbGljaXRBdHRyaWJ1dGVaAAdub3ROdWxsWgAQcGFydE9mRm9yZWlnbktleVoAEHBhcnRPZlByaW1hcnlLZXlJAApzcWxUeXBlSW50WgAGdW5pcXVlTAASY29udmVuaWVuY2VTZXR0ZXJzcQB+AAJMAA9kYkZ1bGxUYWJsZU5hbWVxAH4AAUwABmRiTmFtZXEAfgABTAALZGJUYWJsZU5hbWVxAH4AAUwADGphdmFUeXBlTmFtZXEAfgABTAAEbmFtZXEAfgABTAAHc3FsVHlwZXEAfgABeHAAAQABAAAAAwFzcgAfamF2YS51dGlsLkNvbGxlY3Rpb25zJEVtcHR5TGlzdHq4F7Q8p57eAgAAeHB0ABsiUlQtVEUiLlBVQkxJQy5FWENIQU5HRV9SRUZ0AAJJRHQADEVYQ0hBTkdFX1JFRnQAFGphdmEubWF0aC5CaWdEZWNpbWFsdAACaWR0AAdERUNJTUFMc3EAfgAIAAEAAAAAAAwAcQB+AAt0ABsiUlQtVEUiLlBVQkxJQy5FWENIQU5HRV9SRUZ0AAROQU1FcQB+AA50ABBqYXZhLmxhbmcuU3RyaW5ndAAEbmFtZXQAB1ZBUkNIQVJ4c3EAfgAGAAAAAHcEAAAAAHhzcQB+AAYAAAABdwQAAAABc3IAK2NvbS5idGMucmVkZy5tb2RlbHMuSW5jb21pbmdGb3JlaWduS2V5TW9kZWyDhCRS58Dm1AIABVoAB25vdE51bGxMAA1hdHRyaWJ1dGVOYW1lcQB+AAFMABhyZWZlcmVuY2luZ0F0dHJpYnV0ZU5hbWVxAH4AAUwAFXJlZmVyZW5jaW5nRW50aXR5TmFtZXEAfgABTAAXcmVmZXJlbmNpbmdKYXZhVHlwZU5hbWVxAH4AAXhwAXQAJmV4Y2hhbmdlUmF0ZXNGb3JSZWZlcmVuY2VJZEV4Y2hhbmdlUmVmdAAWcmVmZXJlbmNlSWRFeGNoYW5nZVJlZnQADEV4Y2hhbmdlUmF0ZXQADk15RXhjaGFuZ2VSYXRleHNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAB3CAAAABAAAAAAeHQAC0V4Y2hhbmdlUmVmdAAMY29tLmRlbW8ucGtndAAbIlJULVRFIi5QVUJMSUMuRVhDSEFOR0VfUkVGcQB+AA4=";

    @org.junit.Test
    @Ignore
    public void test() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        final Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
                "system", "oracle");
        // TODO: don't make this rely on any local path... then remove @Ignore and add real test here
        final Path classDir = Paths.get("D:\\redg\\redg-playground\\target\\test-classes");
        List<TableModel> tableModels = TableModelExtractor.extractTableModelFromClasses(classDir,
                "com.btc.redg.generated", "G");


        final DataExtractor dataExtractor = new DataExtractor();
        List<EntityModel> entities = dataExtractor.extractAllData(connection, tableModels);
        System.out.println(new CodeGenerator().generateCode("hallo", "TestClass", "RedG", entities));

    }

    @org.junit.Test
    public void testExtractAllDataFromSelfReferencingCompositeForeignKey() throws SQLException {
        final Connection connectionMock = Mockito.mock(Connection.class);
        final Statement statementMock = Mockito.mock(Statement.class);
        final ResultSet resultSetMock = Mockito.mock(ResultSet.class);

        Mockito
                .doNothing()
                .when(connectionMock).setAutoCommit(false);

        Mockito
                .when(connectionMock.createStatement())
                .thenReturn(statementMock);

        Mockito
                .doNothing()
                .when(statementMock).setFetchSize(50);

        Mockito
                .doReturn(resultSetMock)
                .when(statementMock).executeQuery(Mockito.anyString());

        Mockito
                .doReturn(resultSetMock)
                .when(statementMock).executeQuery(Mockito.anyString());

        Mockito
                .doReturn(true, true, false, true, false)
                .when(resultSetMock).next();

        Mockito
                .doReturn("Lea")
                .when(resultSetMock).getObject("NAME");

        Mockito
                .doReturn(1)
                .when(resultSetMock).getObject("REFERENCE_ID");

        Mockito
                .doReturn(null, "Flo1")
                .when(resultSetMock).getObject("PREV_FIRST_NAME");

        Mockito
                .doReturn("Flo1", "Flo2")
                .when(resultSetMock).getObject("FIRST_NAME");

        Mockito
                .doReturn(11L, 12L, 1L)
                .when(resultSetMock).getObject("ID");

        List<EntityModel> entityModels = new DataExtractor().extractAllData(
                connectionMock, Arrays.asList(deserializeTableModel(EXCHANGE_RATE), deserializeTableModel(EXCHANGE_REF)));

        Assert.assertEquals(
                Helpers.getResourceAsString("test-exchange-rate.java"),
                new CodeGenerator().generateCode("com.github.zemke", "RedG", "RedG", entityModels));
    }

    private TableModel deserializeTableModel(String serializedTableModel) {
        byte[] data = java.util.Base64.getDecoder().decode(serializedTableModel);
        try {
            java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.ByteArrayInputStream(data));
            return (TableModel) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
