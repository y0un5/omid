/**
 * Copyright 2011-2015 Yahoo Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yahoo.omid.transaction;

import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.List;

/**
 * This interface defines the transaction state & behavior exposed to users.
 */
public interface Transaction {
    
    public enum Status {
        RUNNING, COMMITTED, ROLLEDBACK
    }

    /**
     * Returns the transaction identifier
     * @return the transaction identifier
     */
    public long getTransactionId();

    /**
     * Returns the current transaction {@link Status}
     * @return transaction status
     */
    public Status getStatus();

    /**
     * Forces the transaction to rollback, even when there's an intention
     * to commit it.
     */
    public void setRollbackOnly();

    /**
     * Returns whether the transaction was marked for rollback or not
     * @return whether the transaction is marked for rollback or not
     */
    public boolean isRollbackOnly();



    /**
     * Set of methods to attach some metadata to a transaction object. One example
     * of such metadata are notifications
     *
     *
     * Expects they metadata stored under key "key" to be of the "Set" type,
     * append "value" to the existing set or creates a new one
     */
    public void appendMetadata(String key, Object value);
    public void setMetadata(String key, Object value);
    public Optional<Object> getMetadata(String key);
}

