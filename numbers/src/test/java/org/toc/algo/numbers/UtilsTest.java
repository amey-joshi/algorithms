/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.toc.algo.numbers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author amey
 */
public class UtilsTest {

    public UtilsTest() {
    }

    /**
     * Test of isNonnegativeLong method, of class Utils.
     */
    @org.junit.jupiter.api.Test
    public void testIsNonnegativeLong() {
        assertTrue(Utils.isNonnegativeLong("2"));
        assertTrue(Utils.isNonnegativeLong("0"));
        assertFalse(Utils.isNonnegativeLong("-1"));
        assertFalse(Utils.isNonnegativeLong("2.1"));
        assertFalse(Utils.isNonnegativeLong("-1.4"));
    }

    /**
     * Test of isLong method, of class Utils.
     */
    @org.junit.jupiter.api.Test
    public void testIsInteger() {
        assertTrue(Utils.isLong("1"));
        assertTrue(Utils.isLong("0"));
        assertTrue(Utils.isLong("-1"));
        assertFalse(Utils.isLong("1.0"));
        assertFalse(Utils.isLong("0.0"));
        assertFalse(Utils.isLong("-1.0"));
        assertFalse(Utils.isLong("123456789012345678901234567890"));
    }

}
