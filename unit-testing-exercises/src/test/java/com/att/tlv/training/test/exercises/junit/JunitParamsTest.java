package com.att.tlv.training.test.exercises.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class JunitParamsTest {

    @DisplayName("Range.includes(<value>) should return true")
    @Nested
    class RangeTest {

        private Range range;

        @BeforeEach
        void setUp() {
            range = new Range(-1, 1);
        }

        @Test
        void whenValueEqualsStart() {
            int value = range.getStart();
            boolean isIncluded = range.includes(value);
            assertThat(isIncluded).isTrue();
        }

        @Test
        void whenValueEqualsEnd() {
            int value = range.getEnd();
            boolean isIncluded = range.includes(value);
            assertThat(isIncluded).isTrue();
        }

        @Test
        void whenValueBetweenStartAndEnd() {
            int value = range.getStart() + 1;
            boolean isIncluded = range.includes(value);
            assertThat(isIncluded).isTrue();
        }
    }

    @DisplayName("new Range(<start>, <end>) should throw IllegalArgumentException when <start> is greater than <end>")
    @Nested
    class IllegalRangeTest {

        @Test
        void whenStartAndEndAreNegative() {
            assertThatIllegalArgumentException().isThrownBy(() -> new Range(-2, -3));
        }

        @Test
        void whenStartAndEndArePositive() {
            assertThatIllegalArgumentException().isThrownBy(() -> new Range(3, 2));
        }

        @Test
        void whenStartIsPositiveAndEndIsNegative() {
            assertThatIllegalArgumentException().isThrownBy(() -> new Range(2, -2));
        }
    }
}