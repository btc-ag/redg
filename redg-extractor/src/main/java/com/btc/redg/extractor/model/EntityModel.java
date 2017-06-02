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

package com.btc.redg.extractor.model;

import com.btc.redg.extractor.model.entitynameprovider.DefaultEntityNameProvider;
import com.btc.redg.extractor.model.entitynameprovider.EntityNameProvider;
import com.btc.redg.models.TableModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntityModel {

    private static EntityNameProvider entityNameProvider = new DefaultEntityNameProvider();

    public static EntityNameProvider getEntityNameProvider() {
        return entityNameProvider;
    }

    public static void setEntityNameProvider(final EntityNameProvider entityNameProvider) {
        EntityModel.entityNameProvider = entityNameProvider;
    }

    private String variableName;

    private TableModel tableModel;

    private Map<String, String> values;

    private Map<String, EntityModel> nullableRefs;

    private List<EntityModel> notNullRefs;

    public EntityModel(final TableModel tableModel) {
        this(entityNameProvider.getName(tableModel), tableModel);
    }

    public EntityModel(final String variableName, final TableModel tableModel) {
        this.variableName = variableName;
        this.tableModel = tableModel;
        this.values = new HashMap<>();
        this.notNullRefs = new ArrayList<>();
        this.nullableRefs = new HashMap<>();
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(final String variableName) {
        this.variableName = variableName;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(final Map<String, String> values) {
        this.values = values;
    }

    public void addValues(final String methodName, final String methodParameter) {
        this.values.put(methodName, methodParameter);
    }

    public List<EntityModel> getNotNullRefs() {
        return notNullRefs;
    }

    public void setNotNullRefs(final List<EntityModel> notNullRefs) {
        this.notNullRefs = notNullRefs;
    }

    public Map<String, EntityModel> getNullableRefs() {
        return nullableRefs;
    }

    public void setNullableRefs(final Map<String, EntityModel> nullableRefs) {
        this.nullableRefs = nullableRefs;
    }

    public void addNullableRef(final String name, final EntityModel entityModel) {
        this.nullableRefs.put(name, entityModel);
    }

    public void addNotNullRef(final EntityModel entityModel) {
        this.notNullRefs.add(entityModel);
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    public List<EntityModel> getAllRefs() {
        return Stream.concat(this.notNullRefs.stream(), this.nullableRefs.values().stream()).collect(Collectors.toList());
    }
}