package com.att.tlv.training.test.exercises.mocks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WebTargetProviderTest {
    
    private WebTargetProvider webTargetProvider;
    
    @Before
    public void setUp() {
        // TODO init
    }

    @Test
    public void getWithNewUrl_shouldReturnWebTargetFromClient() {
        // TODO test WebTargetProvider.get() with a new url.
    }
    
    @Test
    public void getWithExistingUrl_shouldReturnCachedWebTarget() {
        // TODO test WebTargetProvider.get() with an existing url.
    }
    
    @Test
    public void getWithNullUrl_shouldThrowNullPointerException() {
        // TODO test WebTargetProvider.get() with a null url - Client.target throws a NullPointerException
        // We'd like to make sure it isn't suppressed.
    }
}
