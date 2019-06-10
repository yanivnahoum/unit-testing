package com.att.tlv.training.test.mocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArgumentCaptorsAndMatchers {
    
    private Adder adder;
    @Mock private IntPairAdder intPairAdder;
    @Captor private ArgumentCaptor<IntPair> pairCaptor;
    
    @BeforeEach
    void setUp() {
        adder = new Adder(intPairAdder);
    }
    
    @Test
    void testSum() {
        // This is just a simple test to serve as a starting point.
        // There's no delegation of the work here, everything is done by the Calculator itself.
        int x = 5;
        int y = 3;
        int value = adder.sum(x, y);
        assertThat(value).isEqualTo(x + y);
    }
    
    @Test
    void testAddUsingCaptor() {
        int expected = 101;
        // When stubbing, we obviously don't have the IntPair instance that will be specified to calculateSum()
        // so we use the ArgumentMatcher any().
        when(intPairAdder.calculateSum(any())).thenReturn(expected);
        
        int x = 5;
        int y = 3;
        int value = adder.add(x, y);
        
        // assert that the value obtained is actually the one returned
        assertThat(value).isEqualTo(expected);
        
        // But how can we obtain the argument passed to intPairAdder.calculateSum()
        // and run some assertions on it?
        // Let's use the ArgumentCaptor we defined at the beginning:
        // Regular verification, but call the capture() method of the captor.
        verify(intPairAdder).calculateSum(pairCaptor.capture());
        // Now that we've captured the value, let's get it
        IntPair intPair = pairCaptor.getValue();
        // And run some assertions on it
        assertThat(intPair).isEqualToComparingFieldByField(new IntPair(x, y));
    }
    
    @Test
    void testAddUsingMatcher() {
        int x = 5;
        int y = 3;
        int expected = 101;
//        when(intPairAdder.calculateSum(argThat(pair -> pair.getFirst() == x && pair.getSecond() == y))).thenReturn(expected);
        when(intPairAdder.calculateSum(argThat(isIntPairOf(x, y)))).thenReturn(expected);
        int value = adder.add(x, y);
        
        // assert that the value obtained is actually the one returned
        assertThat(value).isEqualTo(expected);
        // Note that there's no need to verify calculateSum was called, since there's no
        // other way we could have received the expected result
    }
    
    private static ArgumentMatcher<IntPair> isIntPairOf(int x, int y) {
        return pair -> pair.getFirst() == x && pair.getSecond() == y;
    }
}

class Adder {
    
    private final IntPairAdder intPairAdder;

    Adder(IntPairAdder intPairAdder) {
        this.intPairAdder = Objects.requireNonNull(intPairAdder);
    }

    int sum(int x, int y) {
        return x + y;
    }
    
    int add(int x, int y) {
        IntPair intPair = new IntPair(x, y);
        return intPairAdder.calculateSum(intPair);
    }
}

class IntPairAdder {
    
    int calculateSum(IntPair intPair) {
        return intPair.getFirst() + intPair.getSecond();
    }
}

class IntPair {

    private final int first;
    private final int second;

    IntPair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    int getFirst() {
        return first;
    }

    int getSecond() {
        return second;
    }
}
