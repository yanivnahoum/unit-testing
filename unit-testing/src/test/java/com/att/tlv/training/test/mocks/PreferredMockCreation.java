package com.att.tlv.training.test.mocks;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mock;

// Note the runner! 'org.mockito.junit' and not 'org.mockito.runners'
//@RunWith(MockitoJUnitRunner.class)
public class PreferredMockCreation {

    @Mock
    private List<String> strings;
    @Mock
    private Map<Long, Map<String, List<String>>> complexMap;
    
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
    
    @Test
    public void test() {
        boolean added = strings.add("hello");
    }
}