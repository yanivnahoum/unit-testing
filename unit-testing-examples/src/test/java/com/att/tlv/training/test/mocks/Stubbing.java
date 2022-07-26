package com.att.tlv.training.test.mocks;

import com.att.tlv.training.test.data.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = WARN)
class Stubbing {

    @Mock
    private List<String> strings;
    @Mock
    private List<Person> persons;
    
    @Test
    void basicStubbing() {
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
    
    @Test
    void stubbingWithArgumentMatchers() {
        // We can stub with argument matchers
        when(strings.get(anyInt())).thenReturn("Shalom");
        String value = strings.get(1024);        
        assertThat(value).isEqualTo("Shalom");
        
        // Last stub wins, but this one's scope is smaller - so it overrides only one specific case
        when(strings.get(1024)).thenReturn("Ciao");
        assertThat(strings.get(1024)).isEqualTo("Ciao");
        assertThat(strings.get(1)).isEqualTo("Shalom");
        
        // Last stub wins, and this one's scope is larger - so it overrides all previous stubbings
        when(strings.get(anyInt())).thenReturn("Shalom");
        assertThat(strings.get(1024)).isEqualTo("Shalom");
        
        // More complex ones argument matchers
        when(strings.get(intThat(i -> i % 2 == 0))).thenReturn("Even");
        when(strings.get(intThat(i -> i % 2 != 0))).thenReturn("Odd");
        String value1 = strings.get(20);        
        assertThat(value1).isEqualTo("Even");
        String value2 = strings.get(21);        
        assertThat(value2).isEqualTo("Odd");
        
        // argThat works with any reference type
        when(persons.add(argThat(this::isJohn))).thenReturn(true);
        Person john = new Person(123, "John", 30, 1.88);
        assertThat(persons.add(john)).isTrue();
        
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
    
    private boolean isJohn(Person person) {
        return "John".equals(person.getName());
    }
    
    @Test
    void stubbingWithExceptions() {
        // Stub to throw exception:
        when(strings.get(5)).thenThrow(new IllegalArgumentException("Exception thrown by mock"));
        // Assert
        assertThatIllegalArgumentException().isThrownBy(() -> strings.get(5));
    }
    
    @Test
    void stubbingConsecutiveCalls() {
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
        assertThatNullPointerException().isThrownBy(() -> strings.get(4));
        assertThatNullPointerException().isThrownBy(() -> strings.get(4));
    }
    
    // Why would we need to stub void methods?
    @Test
    void stubbingVoidMethods() {
        // Unfortunately, this doesn't compile
//         when(strings.clear()).thenThrow(new IllegalArgumentException());
        
        // For void methods, use doThrow instead. Note the method call outside of the when clause.
        doThrow(new IllegalArgumentException()).when(strings).clear();
        assertThatIllegalArgumentException().isThrownBy(() -> strings.clear());
    }
    
    @Test
    void stubbingConsecutiveVoidMethods() {
        doThrow(new IllegalArgumentException())
                // This call allows us to cancel the previous behavior
                .doNothing()
                .when(strings).clear();
        assertThatIllegalArgumentException().isThrownBy(() -> strings.clear());
        assertThatCode(strings::clear).doesNotThrowAnyException();
    }

    @Test
    void unnecessaryStubbing() {
        when(strings.get(5)).thenReturn("hello");
        // Comment the following line to see mockito's unnecessary stubbing detection in action
        strings.get(5);
        // This can be bypassed, at the stub (lenient().when()...), mock (@Mock(strictness = Mock.Strictness.LENIENT) or class (@MockSettings(strictness = LENIENT)) level.
        // Even if we do call strings.get() but use a different argument (like 100 below) we still get a warning or StrictStubbingException
        // strings.get(100);
    }
}
