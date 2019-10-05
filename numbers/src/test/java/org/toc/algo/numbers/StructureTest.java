/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * License); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * AS IS BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.toc.algo.numbers;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author amey
 */
public class StructureTest {

    public StructureTest() {
    }

    /**
     * Test of isPalindrome method, of class Structure.
     */
    @Test
    public void testIsPalindrome() {
        assertTrue(Structure.isPalindrome(12321));
        assertFalse(Structure.isPalindrome(1234));
    }

    /**
     * Test of sumOfDigits method, of class Structure.
     */
    @Test
    public void testSumOfDigits() {
        assertEquals(6, Structure.sumOfDigits(123));
        boolean exception = false;
        try {
            long sum = Structure.sumOfDigits(-123);
        } catch (IllegalArgumentException e) {
            exception = true;
        }
        assertTrue(exception);
    }

    /**
     * Test of allPrimeFactors method, of class Structure.
     */
    @Test
    public void testAllPrimeFactors() {
        List<Long> factors = Structure.allPrimeFactors(6L);

        assertEquals(2, factors.size());
        assertTrue(factors.contains(2L));
        assertTrue(factors.contains(3L));

        boolean exception = false;
        factors.clear();
        try {
            factors = Structure.allFactors(-6L);
        } catch (IllegalArgumentException e) {
            exception = true;
        }
        assertTrue(exception);
        assertTrue(factors.isEmpty());

        factors = Structure.allFactors(1L);
        assertEquals(1L, factors.size());
    }

    /**
     * Test of allFactors method, of class Structure.
     */
    @Test
    public void testAllFactors() {
        List<Long> factors = Structure.allFactors(6L);

        assertEquals(4, factors.size());
        assertTrue(factors.contains(1L));
        assertTrue(factors.contains(2L));
        assertTrue(factors.contains(3L));
        assertTrue(factors.contains(6L));

        boolean exception = false;
        factors.clear();
        try {
            factors = Structure.allFactors(-6L);
        } catch (IllegalArgumentException e) {
            exception = true;
        }
        assertTrue(exception);
        assertTrue(factors.isEmpty());

        factors = Structure.allFactors(1L);
        assertEquals(1L, factors.size());
        assertTrue(factors.contains(1L));
    }

    /**
     * Test of sumOfFactors method, of class Structure.
     */
    @Test
    public void testSumOfFactors() {
        assertEquals(6, Structure.sumOfFactors(6L));
        assertEquals(1, Structure.sumOfFactors(5L));

        boolean exception = false;
        try {
            Structure.sumOfFactors(-6L);
        } catch (IllegalArgumentException e) {
            exception = true;
        }
        assertTrue(exception);
    }

    /**
     * Test of isPerfect method, of class Structure.
     */
    @Test
    public void testIsPerfect() {
        assertTrue(Structure.isPerfect(6));
        assertFalse(Structure.isPerfect(9));

        boolean exception = false;
        try {
            assertTrue(Structure.isPerfect(-6));
        } catch (IllegalArgumentException e) {
            exception = true;
        }
        assertTrue(exception);

        exception = false;
        try {
            assertTrue(Structure.isPerfect(0));
        } catch (IllegalArgumentException e) {
            exception = true;
        }

        assertTrue(exception);
    }

}
