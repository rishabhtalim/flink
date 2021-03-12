/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.table.planner.plan.logical;

import java.time.Duration;
import java.util.Objects;

import static org.apache.flink.util.Preconditions.checkNotNull;
import static org.apache.flink.util.TimeUtils.formatWithHighestUnit;

/** Logical representation of a cumulative window specification. */
public class CumulativeWindowSpec implements WindowSpec {
    private final Duration maxSize;
    private final Duration step;

    public CumulativeWindowSpec(Duration maxSize, Duration step) {
        this.maxSize = checkNotNull(maxSize);
        this.step = checkNotNull(step);
    }

    @Override
    public String toSummaryString(String windowing) {
        return String.format(
                "CUMULATE(%s, max_size=[%s], step=[%s])",
                windowing, formatWithHighestUnit(maxSize), formatWithHighestUnit(step));
    }

    public Duration getMaxSize() {
        return maxSize;
    }

    public Duration getStep() {
        return step;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CumulativeWindowSpec that = (CumulativeWindowSpec) o;
        return maxSize.equals(that.maxSize) && step.equals(that.step);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CumulativeWindowSpec.class, maxSize, step);
    }
}
