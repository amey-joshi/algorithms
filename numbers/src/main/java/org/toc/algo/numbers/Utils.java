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

import java.util.logging.Logger;

public class Utils {

    private static final Logger LOGGER = Logger.getLogger(Utils.class.getName());

    /**
     * Returns true if a string is a non-negative long integer, false otherwise.
     *
     * @param number
     * @return
     */
    public static boolean isNonnegativeLong(String number) {
        boolean result = true;

        try {
            Long.parseUnsignedLong(number);
        } catch (NumberFormatException e) {
            LOGGER.fine(e.getMessage());
            result = false;
        }

        return result;
    }

    /**
     * Returns true if a string is a long integer, false otherwise.
     *
     * @param number
     * @return
     */
    public static boolean isLong(String number) {
        boolean result = true;

        try {
            Long.parseLong(number);
        } catch (NumberFormatException e) {
            LOGGER.fine(e.getMessage());
            result = false;
        }

        return result;
    }
}
