package com.att.tlv.training.test.exercises.mocks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestClientTest {
    
    private RestClient restClient;

    @Before
    public void setUp() {
        // TODO init
    }

    @Test
    public void testGet() {
        // TODO test RestClient.get() happy scenario
        // Verify all required behavior
    }
    
    @Test
    public void testGetFails() {
        // TODO test RestClient.get() unhappy scenario - webTargetProvider.get() throws.
        // Verify all required behavior
    }
}