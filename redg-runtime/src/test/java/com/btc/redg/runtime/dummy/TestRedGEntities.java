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

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import com.btc.redg.runtime.AbstractRedG;
import com.btc.redg.runtime.AttributeMetaInfo;
import com.btc.redg.runtime.RedGEntity;

class TestRedGEntity1 implements RedGEntity {

    public TestRedGEntity1(AbstractRedG redG) {

    }

    public TestRedGEntity1() {

    }

    @Override
    public String getSQLString() {
        return null;
    }

    @Override
    public String getPreparedStatementString() {
        return null;
    }

    @Override
    public Object[] getPreparedStatementValues() {
        return new Object[0];
    }

    @Override
    public AttributeMetaInfo[] getPreparedStatementValuesMetaInfos() {
        return new AttributeMetaInfo[0];
    }

    @Override
    public List<RedGEntity> getDependencies() {
        return Collections.emptyList();
    }
}

class TestRedGEntity2 implements RedGEntity {


    public TestRedGEntity2(AbstractRedG redG, String explicitAttribute, TestRedGEntity1 entity1, TestRedGEntity1 entity2) {
        // public constructor
    }

    TestRedGEntity2(AbstractRedG redG, TestRedGEntity1 entity1, TestRedGEntity1 entity2) {
        // dummy constructor
    }

    public TestRedGEntity2(int whatever) {

    }

    @Override
    public String getSQLString() {
        return null;
    }

    @Override
    public String getPreparedStatementString() {
        return null;
    }

    @Override
    public Object[] getPreparedStatementValues() {
        return new Object[0];
    }

    @Override
    public AttributeMetaInfo[] getPreparedStatementValuesMetaInfos() {
        return new AttributeMetaInfo[0];
    }

    @Override
    public List<RedGEntity> getDependencies() {
        return Collections.emptyList();
    }
}

class TestRedGEntity3 implements RedGEntity {


    private TestRedGEntity3(AbstractRedG redG, TestRedGEntity1 entity1, TestRedGEntity1 entity2) {

    }


    @Override
    public String getSQLString() {
        return null;
    }

    @Override
    public String getPreparedStatementString() {
        return null;
    }

    @Override
    public Object[] getPreparedStatementValues() {
        return new Object[0];
    }

    @Override
    public AttributeMetaInfo[] getPreparedStatementValuesMetaInfos() {
        return new AttributeMetaInfo[0];
    }

    @Override
    public List<RedGEntity> getDependencies() {
        return Collections.emptyList();
    }
}

class TestRedGEntity4 implements RedGEntity {


    @Override
    public String getSQLString() {
        return null;
    }

    @Override
    public String getPreparedStatementString() {
        return null;
    }

    @Override
    public Object[] getPreparedStatementValues() {
        return new Object[0];
    }

    @Override
    public AttributeMetaInfo[] getPreparedStatementValuesMetaInfos() {
        return new AttributeMetaInfo[0];
    }

    @Override
    public List<RedGEntity> getDependencies() {
        return Collections.emptyList();
    }
}
