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


import com.btc.redg.extractor.model.EntityModel;
import com.btc.redg.extractor.tablemodelextractor.TableModelExtractor;
import com.btc.redg.models.TableModel;
import org.junit.Ignore;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@Ignore
public class Test {

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
}
