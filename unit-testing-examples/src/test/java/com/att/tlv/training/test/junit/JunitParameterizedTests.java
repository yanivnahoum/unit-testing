package com.att.tlv.training.test.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.DayOfWeek;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.params.provider.EnumSource.Mode.MATCH_ALL;

class JunitParameterizedTests {

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"  ", "\n", "\t"})
    void trimShouldRemoveWhiteSpacesFromText(String text) {
        String trimmedTest = text.trim();
        assertThat(trimmedTest).isEmpty();
    }

    @Nested
    class CalculatorTest {
        private Calculator calculator;

        @BeforeEach
        void setUp() {
            calculator = new Calculator();
        }

        @ParameterizedTest
        @ValueSource(ints = {-2, 0, 4, 20, 100})
        void isEven(int value) {
            boolean even = calculator.isEven(value);
            assertThat(even).isTrue();
        }

        @ParameterizedTest
        @CsvSource({
                "1, 1",
                "0, 0",
                "-1, 1"
        })
        void absShouldReturnAbsolute(int input, int expected) {
            int actual = calculator.abs(input);
            assertThat(actual).isEqualTo(expected);
        }
    }

    @ParameterizedTest
    @MethodSource("maxShouldReturnGreaterNumber")
    void maxShouldJustWork(int x, int y, int expected) {
        var calculator = new Calculator();
        int actual = calculator.max(x, y);
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> maxShouldReturnGreaterNumber() {
        return Stream.of(
                arguments(1, 2, 2),
                arguments(2, 1, 2),
                arguments(1, 1, 1)
        );
    }

    @ParameterizedTest
    @MethodSource
    void maxShouldReturnGreaterNumber(int x, int y, int expected) {
        var calculator = new Calculator();
        int actual = calculator.max(x, y);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void maxShouldReturnGreaterNumber(ArgumentsAccessor accessor) {
        int x = accessor.getInteger(0);
        int y = accessor.getInteger(1);
        int expected = accessor.getInteger(2);

        var calculator = new Calculator();
        int actual = calculator.max(x, y);
        assertThat(actual).isEqualTo(expected);
    }

    @Nested
    class EnumSources {

        @ParameterizedTest
        @EnumSource(DayOfWeek.class)
        void enumSourceValue_shouldBeBetween1And7(DayOfWeek dayOfWeek) {
            int value = dayOfWeek.getValue();
            assertThat(value).isBetween(1, 7);
        }

        @ParameterizedTest
        @EnumSource(value = DayOfWeek.class, mode = MATCH_ALL,
                names = {"^.+DAY$", "^(T|S).+$"})
        void enumSourceMatchesCondition(DayOfWeek dayOfWeek) {
            String name = dayOfWeek.name();
            assertThat(name).matches(day -> (day.startsWith("T") || day.startsWith("S"))
                    && day.endsWith("DAY"));
        }
    }
}

class Calculator {

    boolean isEven(int value) {
        return value % 2 == 0;
    }

    int abs(int value) {
        if (value < 0) {
            return -value;
        }
        return value;
    }

    int max(int x, int y) {
        return x > y ? x : y;
    }
}
