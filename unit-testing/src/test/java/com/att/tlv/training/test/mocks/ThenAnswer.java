package com.att.tlv.training.test.mocks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.answer;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ThenAnswer {
    
    @Mock
    private List<String> names;
    
    @Test
    public void test() {
        String element = "hello";
        assertThat(names.add(element)).isFalse();
        when(names.add(anyString())).thenAnswer(answer(this::add));
        
        assertThat(names.add(element)).isTrue();
    }
    
    private boolean add(String element) {
        System.out.printf("adding [%s]%n", element);
        return true;
    }
}
