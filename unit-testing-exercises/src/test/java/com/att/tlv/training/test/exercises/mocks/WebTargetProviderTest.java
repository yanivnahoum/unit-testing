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
    public void testGetNew() {
        // TODO test WebTargetProvider.get() with a new url.
    }
    
    @Test
    public void testGetExisting() {
        // TODO test WebTargetProvider.get() with an existing url.
    }
    
    @Test
    public void testGetWithInvalidURL() {
        // TODO test WebTargetProvider.get() with an invalid url - Client.target throws an IllegalArgumentException
    }
    
    @Test
    public void testGetWithNullURL() {
        // TODO test WebTargetProvider.get() with a null url - Client.target throws a NullPointerException
    }
}
