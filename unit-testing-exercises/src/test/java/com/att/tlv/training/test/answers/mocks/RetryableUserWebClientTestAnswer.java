package com.att.tlv.training.test.answers.mocks;

import com.att.tlv.training.test.exercises.data.Person;
import com.att.tlv.training.test.exercises.mocks.RetryableUserWebClient;
import com.att.tlv.training.test.exercises.mocks.UserWebClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.UncheckedIOException;
import java.net.SocketTimeoutException;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetryableUserWebClientTestAnswer {

    private static final List<Person> EXPECTED_PERSONS = singletonList(new Person(123L, "John"));
    @Mock
    private UserWebClient userWebClient;
    private RetryableUserWebClient retryableClient;

    @BeforeEach
    void setUp() {
        retryableClient = new RetryableUserWebClient(userWebClient, 3);
    }

    @Test
    void givenNoExceptionsAreThrown_whenGetPersons_thenResultIsReturned() {
        when(userWebClient.getPersons()).thenReturn(EXPECTED_PERSONS);

        Collection<Person> persons = retryableClient.getPersons();

        assertThat(persons).isSameAs(EXPECTED_PERSONS);
    }

    @Test
    void givenMaxAttemptsMinusOneExceptionsAreThrown_whenGetPersons_thenResultIsReturned() {
        UncheckedIOException ex = new UncheckedIOException(new SocketTimeoutException("timeout"));
        when(userWebClient.getPersons()).thenThrow(ex)
                                        .thenThrow(ex)
                                        .thenReturn(EXPECTED_PERSONS);

        Collection<Person> persons = retryableClient.getPersons();

        assertThat(persons).isSameAs(EXPECTED_PERSONS);
    }

    @Test
    void givenMaxAttemptsExceptionsAreThrown_whenGetPersons_thenLastExceptionIsThrown() {
        UncheckedIOException ex = new UncheckedIOException(new SocketTimeoutException("timeout"));
        UncheckedIOException lastEx = new UncheckedIOException(new SocketTimeoutException("last attempt"));
        when(userWebClient.getPersons()).thenThrow(ex)
                                        .thenThrow(ex)
                                        .thenThrow(lastEx);


        assertThatThrownBy(retryableClient::getPersons).hasCause(lastEx);
    }
}