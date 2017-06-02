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

package com.btc.redg.runtime;

/**
 * Exception indicating that an entity could not be inserted into a database. The message should contain detailed information about the cause, the exception
 * that caused this exception is available via {@link InsertionFailedException#getCause()}
 */
public class InsertionFailedException extends RuntimeException {

    public InsertionFailedException() {
        super();
    }

    public InsertionFailedException(final String message) {
        super(message);
    }

    public InsertionFailedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InsertionFailedException(final Throwable cause) {
        super(cause);
    }

    protected InsertionFailedException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
