package com.att.tlv.training.test.answers.junit;

import com.att.tlv.training.test.exercises.junit.Range;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class JunitParamsTestAnswer {

    @DisplayName("Range.includes(<value>) should return true when value equals")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1})
    void rangeIncludesShouldReturnTrue(int value) {
        Range range = new Range(-1, 1);
        boolean isIncluded = range.includes(value);
        assertThat(isIncluded).isTrue();
    }

    @DisplayName("new Range(<start>, <end>) should throw IllegalArgumentException when <start> is greater than <end>")
    @ParameterizedTest
    @CsvSource({
            "-2, -3",
            "3, 2",
            "2, -2"
    })
    void newRangeShouldThrow(int start, int end) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Range(start, end));
    }


    @DisplayName("new Range(<start>, <end>) should throw IllegalArgumentException when <start> is greater than <end>")
    @ParameterizedTest
    @MethodSource
    void newRangeShouldThrowWithArguments(int start, int end) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Range(start, end));
    }

    private static Stream<Arguments> newRangeShouldThrowWithArguments() {
        return Stream.of(
                arguments(-2, -3),
                arguments(3, 2),
                arguments(2, -2)
        );
    }
}



