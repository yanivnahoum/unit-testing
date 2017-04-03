package com.att.tlv.training.test.mocks;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

// Note the runner! 'org.mockito.junit' and not 'org.mockito.runners'
@RunWith(MockitoJUnitRunner.class)
public class PreferredMockCreation {
    
    @Mock
    private List<String> strings;
    
    @Mock
    private Map<Long, Map<String, List<String>>> complexMap;
    
    @Test
    public void test() {
        boolean added = strings.add("hello");
    }
}