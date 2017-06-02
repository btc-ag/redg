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

package com.btc.redg.models;

import java.io.Serializable;


public class ForeignKeyColumnModel implements Serializable{

    private String name;
    private String localName;
    private String localType;
    private String dbName;
    private String dbTableName;
    private String dbFullTableName;
    private String sqlType;
    private int sqlTypeInt;

    public ForeignKeyColumnModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(final String localName) {
        this.localName = localName;
    }

    public String getLocalType() {
        return localType;
    }

    public void setLocalType(final String localType) {
        this.localType = localType;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(final String dbName) {
        this.dbName = dbName;
    }

    public String getDbTableName() {
        return dbTableName;
    }

    public void setDbTableName(final String dbTableName) {
        this.dbTableName = dbTableName;
    }

    public String getDbFullTableName() {
        return dbFullTableName;
    }

    public void setDbFullTableName(final String dbFullTableName) {
        this.dbFullTableName = dbFullTableName;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(final String sqlType) {
        this.sqlType = sqlType;
    }

    public int getSqlTypeInt() {
        return sqlTypeInt;
    }

    public void setSqlTypeInt(final int sqlTypeInt) {
        this.sqlTypeInt = sqlTypeInt;
    }
}
