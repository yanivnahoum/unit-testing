package com.att.tlv.training.test.answers.assertions;

import com.att.tlv.training.test.exercises.assertions.Thrower;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ThrowerTestAnswer {
    
    @Test
    void addExactWithOverflow_shouldThrowException() {
        assertThatThrownBy(() -> Thrower.addExact(Integer.MAX_VALUE, 1))
                .isExactlyInstanceOf(ArithmeticException.class)
                .hasNoCause();
    }
    
    @Test
    void throwNestedException_shouldThrowExceptionWithCorrectMessageAndCause() {
        assertThatThrownBy(Thrower::throwNestedException)
            .isExactlyInstanceOf(RuntimeException.class)
            .hasMessage("Argument is not valid!")
            .hasRootCauseExactlyInstanceOf(IllegalArgumentException.class);
        
        // Or: 
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(Thrower::throwNestedException)
            .withMessage("Argument is not valid!")
            .withRootCauseExactlyInstanceOf(IllegalArgumentException.class);
    }
}
