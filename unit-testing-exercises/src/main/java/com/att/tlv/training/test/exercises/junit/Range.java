package com.att.tlv.training.test.exercises.junit;

import static com.google.common.base.Preconditions.checkArgument;

public class Range {

    private final int start;
    private final int end;

    public Range(int start, int end) {
        checkArgument(start <= end, "Start must be less than or equal to end, but %s is greater than %s", start, end);
        this.start = start;
        this.end = end;
    }

    public boolean includes(int value) {
        return value >= start && value <= end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
