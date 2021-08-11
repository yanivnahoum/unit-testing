package com.att.tlv.training.test.assertions;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIOException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class ExceptionAssertions {

    @Test
    void testBasicExceptionAssertions() {

        List<String> list = List.of("zero", "one", "two", "three", "four");

        assertThat(list).hasSize(5);

        try {
            list.get(9); // Boom!
        } catch (Exception e) {
            // You can check exception type
            assertThat(e).isInstanceOf(IndexOutOfBoundsException.class);

            // You can check if exception has no cause
            assertThat(e).hasNoCause();

            assumingThat(isJava8(), () -> {
                // You can check exception message
                assertThat(e).hasMessage("Index: 9, Size: 5");
                // String#format syntax support
                assertThat(e).hasMessage("Index: %d, Size: %d", 9, 5);
            });

            assumingThat(isJava11(), () -> {
                // You can check exception message
                assertThat(e).hasMessage("Index 9 out of bounds for length 5");
                // String#format syntax support
                assertThat(e).hasMessage("Index %d out of bounds for length %d", 9, 5);
            });

            // Sometimes message are not entirely predictable, you can then check for start, end or containing string.
            assertThat(e).hasMessageStartingWith("Index")
                    .hasMessageContaining("9")
                    .hasMessageEndingWith("5");
            // This is equivalent to:
            assertThat(e.getMessage()).startsWith("Index")
                    .contains("9")
                    .endsWith("5");
        }
    }

    @Test
    void testCauseType() {
        Throwable throwable = new Throwable(new NullPointerException("boom"));

        assertThat(throwable).hasCause(new NullPointerException("boom"));

        // hasCauseInstanceOf will match super class.
        assertThat(throwable).hasCauseInstanceOf(NullPointerException.class);
        assertThat(throwable).hasCauseInstanceOf(RuntimeException.class);

        // hasCauseExactlyInstanceOf will match only exact same type
        assertThat(throwable).hasCauseExactlyInstanceOf(NullPointerException.class);
        try {
            assertThat(throwable).hasCauseExactlyInstanceOf(RuntimeException.class);
        } catch (AssertionError e) {
            e.printStackTrace();
        }

        Throwable throwableRoot = new Throwable(new IllegalStateException(new NullPointerException()));

        // hasRootCauseInstanceOf will match super class
        assertThat(throwableRoot).hasRootCauseInstanceOf(NullPointerException.class);
        assertThat(throwableRoot).hasRootCauseInstanceOf(RuntimeException.class);

        // hasRootCauseExactlyInstanceOf will match only exact same type
        assertThat(throwableRoot).hasRootCauseExactlyInstanceOf(NullPointerException.class);
    }

    @Test
    void test_assertThatThrownBy() {
        assertThatThrownBy(() -> validate(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid");
    }

    private void validate(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Invalid value: " + value);
        }
    }

    @Test
    void test_assertThatExceptionOfType() {
        assertThatExceptionOfType(IOException.class).isThrownBy(() -> {throw new IOException("boom!");})
                .withMessage("boom!")
                .withNoCause();

        // Shortcut for IOException, NullPointerException, IllegalArgumentException, IllegalStateException:
        assertThatIOException().isThrownBy(() -> {throw new IOException("boom!");})
                .withMessage("boom!");
    }

    @Test
    void test_doesNotThrowAnyException() {
        assertThatCode(() -> doSomething("hello")).doesNotThrowAnyException();
    }

    private int doSomething(String s) {
        return s.length();
    }

    @Test
    void assertException() {
        // A condition accepts a predicate and a description (formatted strings accepted)
        Condition<MyException> aTotalGreaterThan10 = new Condition<>(e -> e.getTotal() > 10, "a total that is greater than 10");

        assertThatExceptionOfType(MyException.class).isThrownBy(this::throwMyException)
                .withMessage("An error occurred")
                // We can use the predicate directly
                .matches(e -> e.getTotal() > 10)
                // Or the condition - assertion failures will use our description
                .has(aTotalGreaterThan10);

        // Another possible syntax
        MyException myException = catchThrowableOfType(this::throwMyException, MyException.class);
        assertThat(myException.getMessage()).isEqualTo("An error occurred");
        assertThat(myException.getTotal()).isGreaterThan(10);
    }

    private void throwMyException() throws MyException {
        throw new MyException("An error occurred", 20);
    }

    private static boolean isJava8() {
        return javaVersion().startsWith("1.8");
    }

    private static boolean isJava11() {
        return javaVersion().startsWith("11");
    }

    private static String javaVersion() {
        return System.getProperty("java.version");
    }
}

class MyException extends Exception {

    private final int total;

    MyException(String message, int total) {
        super(message);
        this.total = total;
    }

    int getTotal() {
        return total;
    }
}