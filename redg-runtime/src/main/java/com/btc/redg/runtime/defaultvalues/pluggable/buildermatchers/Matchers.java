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

package com.btc.redg.runtime.defaultvalues.pluggable.buildermatchers;

import com.btc.redg.models.ColumnModel;
import com.btc.redg.runtime.defaultvalues.pluggable.CustomConditionalProvider;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * The class containing all the static matchers for the attributes that can be checked with the builder
 */
public class Matchers {

    public static CustomConditionalProvider.ProvideCondition tableName(final Predicate<String> condition) {
        return (cM) -> condition.test(cM.getDbTableName());
    }

    public static CustomConditionalProvider.ProvideCondition fullTableName(final Predicate<String> condition) {
        return (cM) -> condition.test(cM.getDbFullTableName());
    }

    public static CustomConditionalProvider.ProvideCondition columnName(final Predicate<String> condition) {
        return (cM) -> condition.test(cM.getDbName());
    }

    public static CustomConditionalProvider.ProvideCondition type(final Predicate<Class<?>> condition) {
        return (cM) -> condition.test(cM.getJavaTypeAsClass());
    }

    public static CustomConditionalProvider.ProvideCondition notNull(final Predicate<Boolean> condition) {
        return (cM) -> condition.test(cM.isNotNull());
    }

    public static CustomConditionalProvider.ProvideCondition isNotNull() {
        return ColumnModel::isNotNull;
    }

    public static CustomConditionalProvider.ProvideCondition isUnique() {
        return ColumnModel::isUnique;
    }

    public static CustomConditionalProvider.ProvideCondition isPrimary() {
        return ColumnModel::isPartOfPrimaryKey;
    }

    /**
     * A condition that returns true if all of the provided conditions return true. Equivalent to logical AND operator.
     *
     * @param conditions The conditions to be checked
     * @return A condition that returns true if all passed conditions return true
     */
    public static CustomConditionalProvider.ProvideCondition allOf(final CustomConditionalProvider.ProvideCondition... conditions) {
        return (cM) -> Arrays.stream(conditions)
                .allMatch(c -> c.matches(cM));
    }

    /**
     * A condition that returns true if any of the provided conditions return true. Equivalent to logical OR operator.
     *
     * @param conditions The conditions to be checked
     * @return A condition that returns true if any of the passed conditions return true
     */
    public static CustomConditionalProvider.ProvideCondition anyOf(final CustomConditionalProvider.ProvideCondition... conditions) {
        return (cM) -> Arrays.stream(conditions)
                .anyMatch(c -> c.matches(cM));
    }

    /**
     * A condition that returns true if exactly one of the provided conditions return true. Equivalent to logical XOR operator.
     *
     * @param conditions The conditions to be checked
     * @return A condition that returns true if exactly one of the passed conditions return true
     */
    public static CustomConditionalProvider.ProvideCondition oneOf(final CustomConditionalProvider.ProvideCondition... conditions) {
        return (cM) -> Arrays.stream(conditions)
                .map(c -> c.matches(cM)).filter(b -> b).count() == 1;
    }
}
