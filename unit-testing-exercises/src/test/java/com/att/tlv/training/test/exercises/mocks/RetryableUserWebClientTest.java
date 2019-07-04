package com.att.tlv.training.test.exercises.mocks;

import com.att.tlv.training.test.exercises.data.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.singletonList;

class RetryableUserWebClientTest {

    private static final List<Person> EXPECTED_PERSONS = singletonList(new Person(123L, "John"));
    private RetryableUserWebClient retryableClient;

    @BeforeEach
    void setUp() {
        // TODO set up the RetryableUserWebClient correctly
    }

    @Test
    void givenNoExceptionsAreThrown_whenGetPersons_thenResultIsReturned() {
        // TODO complete
    }

    @Test
    void givenMaxAttemptsMinusOneExceptionsAreThrown_whenGetPersons_thenResultIsReturned() {
        // TODO complete
    }

    @Test
    void givenMaxAttemptsExceptionsAreThrown_whenGetPersons_thenLastExceptionIsThrown() {
        // TODO complete
    }
}