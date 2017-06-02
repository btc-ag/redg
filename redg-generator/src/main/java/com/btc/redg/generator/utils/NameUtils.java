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

package com.btc.redg.generator.utils;

/**
 * Contains static helper methods for converting SQL names to java names
 */
public class NameUtils {

    public static String firstCharacterToLowerCase(final String s) {
        if (s == null || s.length() < 1) {
            return s;
        }
        final char[] letters = s.toCharArray();
        letters[0] = Character.toLowerCase(letters[0]);
        return new String(letters);
    }

    public static String firstCharacterToUpperCase(final String s) {
        if (s == null || s.length() < 1) {
            return s;
        }
        final char[] letters = s.toCharArray();
        letters[0] = Character.toUpperCase(letters[0]);
        return new String(letters);
    }

    public static String escapeQuotationMarks(final String s) {
        return s.replace("\"", "\\\"");
    }
}
