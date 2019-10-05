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

import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {

    private static final Logger LOGGER = Logger.getLogger(Utils.class.getName());

    /**
     * Returns true if a string is a non-negative integer, false otherwise.
     *
     * @param number
     * @return
     */
    public static boolean isNonnegativeInt(String number) {
        boolean result = true;

        try {
            Integer.parseUnsignedInt(number);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.FINE, String.format("The string %s could not be interepreted as a non-negative integer", number));
            result = false;
        }

        return result;
    }

    /**
     * Returns true if a string is an integer, false otherwise.
     *
     * @param number
     * @return
     */
    public static boolean isInteger(String number) {
        boolean result = true;

        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.FINE, String.format("The string %s could not be interepreted as an integer", number));
            result = false;
        }

        return result;
    }
}
