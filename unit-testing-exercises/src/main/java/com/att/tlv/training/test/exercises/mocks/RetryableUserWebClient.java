package com.att.tlv.training.test.exercises.mocks;

import com.att.tlv.training.test.exercises.data.Person;

import java.util.Collection;
import java.util.function.Supplier;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

public class RetryableUserWebClient implements UserWebClient {

    private final UserWebClient userWebClient;
    private final int maxAttempts;

    public RetryableUserWebClient(UserWebClient userWebClient, int maxAttempts) {
        checkArgument(maxAttempts > 0, "Number of attempts must be greater than 0");
        this.maxAttempts = maxAttempts;
        this.userWebClient = requireNonNull(userWebClient);
    }

    @Override
    public Collection<Person> getPersons() {
        return retry(userWebClient::getPersons);
    }

    @Override
    public Person getPerson(long id) {
        return retry(() -> userWebClient.getPerson(id));
    }

    private <T> T retry(Supplier<T> request) {
        Exception lastException = null;
        for (int i = 1; i <= maxAttempts; i++) {
            try {
                return request.get();
            }
            catch (Exception ex) {
                lastException = ex;
            }
        }

        throw new RuntimeException(lastException);
    }
}
