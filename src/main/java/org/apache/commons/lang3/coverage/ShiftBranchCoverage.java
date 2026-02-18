/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.lang3.coverage;

public final class ShiftBranchCoverage {
    private static final boolean[] HIT = new boolean[200];

    private ShiftBranchCoverage() {
    }

    public static void hit(final int id) {
        HIT[id] = true;
    }

    public static void report() {
        for (int i = 0; i < HIT.length; i++) {
            if (HIT[i]) {
                System.out.println("BRANCH " + i + " HIT");
            }
        }
    }
}
