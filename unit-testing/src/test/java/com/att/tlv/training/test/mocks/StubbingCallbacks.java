package com.att.tlv.training.test.mocks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.answer;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StubbingCallbacks {
    
    @Mock
    private List<String> strings;
    
    @Test
    public void stubWithCallback() {
        // Originally List.add returns the default value for boolean - false
        assertThat(strings.add("hello")).isFalse();
        
        // We can activate an Answer, a callback that will calculate the value only when invoked
        when(strings.add(anyString())).thenAnswer(invocation -> true);
        assertThat(strings.add("hello")).isTrue();
        
        // But what if we need the argument specified to our method?
        // Then we can either extract it from the 'invocation' argument above
        // by calling invocation.getArgument(0),  
        // or much simpler, use AdditionalAnswers.answer()
        when(strings.add(anyString())).thenAnswer(answer(this::logAdd));        
        assertThat(strings.add("hello")).isTrue();
    }
    
    private boolean logAdd(String element) {
        System.out.printf("adding [%s]%n", element);
        return true;
    }
    
    @Test
    public void stubVoidMethodWithCallback() {
        // We already know that this does not compile
        //when(strings.clear())...
        // Like doThrow, we have method doAnswer
        doAnswer(invocation -> logClear()).when(strings).clear();
        strings.clear();
    }
    
    private Object logClear() {
        System.out.println("Clearing list");
        return null;
    }
}
