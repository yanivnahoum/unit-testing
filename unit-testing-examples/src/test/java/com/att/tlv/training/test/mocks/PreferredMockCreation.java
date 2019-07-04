package com.att.tlv.training.test.mocks;

import com.att.tlv.training.test.data.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@ExtendWith(MockitoExtension.class)
class PreferredMockCreation {

    @Mock
    private List<String> strings;
    @Mock
    private Map<Long, Map<String, List<String>>> complexMap;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.initMocks(this);
    }

    @Test
    void test() {
        boolean added = strings.add("hello");
        Map<String, List<String>> previousValue = complexMap.put(10L, Collections.emptyMap());
    }

    @Test
    void testWithLocalMock(@Mock List<Person> persons) {
        Person person = persons.get(10);
    }
}