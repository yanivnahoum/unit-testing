package com.att.tlv.training.test.mocks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Stubbing {
    
    @Mock
    private List<String> strings;
    
    @Test
    public void basicStubbing() {
        // Default value: 
        String value = strings.get(5);        
        assertThat(value).isNull();
        
        // Now how about some stubbing?
        // Note the method call is inside the when()
        when(strings.get(5)).thenReturn("hello");
        // Let's make sure it worked
        value = strings.get(5);        
        assertThat(value).isEqualTo("hello");
        
        // Let's make sure it still works
        for (int i = 0; i < 100; i++) {
            value = strings.get(5);        
            assertThat(value).isEqualTo("hello");
        }
        
        // strings.get(4) wasn't stubbed!
        value = strings.get(4);        
        assertThat(value).isNull();
        
        // Last stub counts!
        when(strings.get(5)).thenReturn("goodbye");
        value = strings.get(5);        
        assertThat(value).isEqualTo("goodbye");
    }
    
    public void stubbingWithArgumentMatchers() {
        // We can stub with argument matchers
        when(strings.get(anyInt())).thenReturn("Shalom");
        String value = strings.get(1024);        
        assertThat(value).isEqualTo("Shalom");
        
        // Or more complex ones:
        when(strings.get(intThat(i -> i % 2 == 0))).thenReturn("Even");
        when(strings.get(intThat(i -> i % 2 == 1))).thenReturn("Odd");
        String value1 = strings.get(20);        
        assertThat(value1).isEqualTo("Even");
        String value2 = strings.get(21);        
        assertThat(value2).isEqualTo("Odd");
        
        // Argument matching with more than one argument:
        when(strings.set(5, "a")).thenReturn("b");
        String previousValue = strings.set(5, "a");        
        assertThat(previousValue).isEqualTo("b");
        
        // What about mixing raw values with matchers?
        // Can't do this:
        // when(strings.set(5, anyString())).thenReturn("b");
        // eq() at our service!
        when(strings.set(eq(5), anyString())).thenReturn("b");
        previousValue = strings.set(5, "bla bla");        
        assertThat(previousValue).isEqualTo("b");
    }
    
    @Test
    public void stubbingWithExceptions() {
        // Stub to throw exception:
        when(strings.get(5)).thenThrow(new IllegalArgumentException("Exception thrown by mock"));
        // Assert
        assertThatThrownBy(() -> strings.get(5)).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    public void stubbingConsecutiveCalls() {
        when(strings.get(5)).thenReturn("Hello")
                .thenReturn("how")
                .thenReturn("are")
                .thenReturn("you?");
        
        assertThat(strings.get(5)).isEqualTo("Hello");
        assertThat(strings.get(5)).isEqualTo("how");
        assertThat(strings.get(5)).isEqualTo("are");
        assertThat(strings.get(5)).isEqualTo("you?");

        
        // In any additional calls - last stub wins 
        assertThat(strings.get(5)).isEqualTo("you?");
        assertThat(strings.get(5)).isEqualTo("you?");
        
        // Another approach:
        when(strings.get(5)).thenReturn("Hello", "how", "are", "you?");
        
        assertThat(strings.get(5)).isEqualTo("Hello");
        assertThat(strings.get(5)).isEqualTo("how");
        assertThat(strings.get(5)).isEqualTo("are");
        assertThat(strings.get(5)).isEqualTo("you?");
        
        
        // How about consecutive exceptions?
        when(strings.get(5)).thenThrow(new IllegalArgumentException())
                .thenThrow(new NullPointerException());
        
        assertThatThrownBy(() -> strings.get(5)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> strings.get(5)).isInstanceOf(NullPointerException.class);
        
        // Let's mix them!
        when(strings.get(4)).thenReturn("Hello", "Goodbye")
            .thenThrow(new IllegalArgumentException(), new NullPointerException());
                
        assertThat(strings.get(4)).isEqualTo("Hello");
        assertThat(strings.get(4)).isEqualTo("Goodbye");        
        assertThatThrownBy(() -> strings.get(4)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> strings.get(4)).isInstanceOf(NullPointerException.class);
        
        // Again, in any additional calls - last stub wins 
        assertThatThrownBy(() -> strings.get(4)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> strings.get(4)).isInstanceOf(NullPointerException.class);
    }
    
    // Why would we need to stub void methods?
    @Test
    public void stubbingVoidMethods() {
        // Unfortunately, this doesn't compile
        // when(strings.clear()).thenThrow(new IllegalArgumentException());
        
        // For void methods, use doThrow instead. Note the method call outside of the when clause.
        doThrow(new IllegalArgumentException()).when(strings).clear();
        assertThatThrownBy(() -> strings.clear()).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    public void unnecessaryStubbing() {
        when(strings.get(5)).thenReturn("hello");
        // Comment the following line to see mockito's unnecessary stubbing detection in action 
        strings.get(5);        
    }
}
