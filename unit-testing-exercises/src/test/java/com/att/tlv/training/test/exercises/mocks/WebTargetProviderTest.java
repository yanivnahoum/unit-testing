package com.att.tlv.training.test.exercises.mocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WebTargetProviderTest {
    
    private WebTargetProvider webTargetProvider;
    
    @BeforeEach
    void setUp() {
        // TODO init
    }

    @Test
    void getWithNewUrl_shouldReturnWebTargetFromClient() {
        // TODO test WebTargetProvider.get() with a new url.
    }
    
    @Test
    void getWithExistingUrl_shouldReturnCachedWebTarget() {
        // TODO test WebTargetProvider.get() with an existing url.
    }
    
    @Test
    void getWithNullUrl_shouldThrowNullPointerException() {
        // TODO test WebTargetProvider.get() with a null url - Client.target throws a NullPointerException
        // We'd like to make sure it isn't suppressed.
    }
}
