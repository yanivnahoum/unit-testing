package com.att.tlv.training.test.mocks;

import com.att.tlv.training.test.data.Demo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DefaultReturnValues {
    
    @Mock
    private Demo demo;
    
    @Test
    void assertThatReturnedCharIsZero() {
        assertThat(demo.getChar()).isEqualTo('\0');
        assertThat(demo.getChar()).isEqualTo('\u0000');
        assertThat(demo.getChar()).isEqualTo(Character.MIN_VALUE);
        assertThat(demo.getBoxedChar()).isEqualTo('\u0000');
    }
    
    @Test
    void assertThatReturnedByteIsZero() {
        assertThat(demo.getByte()).isZero();
        assertThat(demo.getBoxedByte()).isZero();
    }
    
    @Test
    void assertThatReturnedShortIsZero() {
        assertThat(demo.getShort()).isZero();
        assertThat(demo.getBoxedShort()).isZero();
    }
    
    @Test
    void assertThatReturnedIntIsZero() {
        assertThat(demo.getInt()).isZero();
        assertThat(demo.getBoxedInt()).isZero();
    }
    
    @Test
    void assertThatReturnedLongIsZero() {
        assertThat(demo.getLong()).isZero();
        assertThat(demo.getBoxedLong()).isZero();
    }
    
    @Test
    void assertThatReturnedFloatIsZero() {
        assertThat(demo.getFloat()).isZero();
        assertThat(demo.getBoxedFloat()).isZero();
    }
    
    @Test
    void assertThatReturnedDoubleIsZero() {
        assertThat(demo.getDouble()).isZero();
        assertThat(demo.getBoxedDouble()).isZero();
    }
    
    @Test
    void assertThatReturnedBooleanIsFalse() {
        assertThat(demo.getBoolean()).isFalse();
        assertThat(demo.getBoxedBoolean()).isFalse();
    }
    
    @Test
    void assertThatObjectReturnsNull() {
        assertThat(demo.getObject()).isNull();
    }
    
    @Test
    void assertThatStringReturnsNull() {
        assertThat(demo.getString()).isNull();
    }
    
    @Test
    void assertThatArrayReturnsNull() {
        assertThat(demo.getObjectArray()).isNull();
    }
    
    @Test
    void assertThatCollectionReturnsEmptyCollection() {
        assertThat(demo.getCollection()).isEmpty();
        assertThat(demo.getList()).isEmpty();
        assertThat(demo.getSet()).isEmpty();
    }
    
    @Test
    void assertThatMapReturnsEmptyMap() {
        assertThat(demo.getMap()).isEmpty();
    }
    
    @Test
    void assertThatStreamReturnsEmptyStream() {
        assertThat(demo.getStream()).isEmpty();
    }
    
    @Test
    void assertThatOptionalReturnsEmptyOptional() {
        assertThat(demo.getOptional()).isEmpty();
        assertThat(demo.getOptionalInt()).isEmpty();
        assertThat(demo.getOptionalLong()).isEmpty();
        assertThat(demo.getOptionalDouble()).isEmpty();
    }
    
    @Test
    void assertThatMockedTypeReturnsNull() {
        assertThat(demo.getMe()).isNull();
    }
    
    @Test
    void assertThatMockedTypeReturnsItself(@Mock(answer = Answers.RETURNS_SELF) Demo fluentDemo) {
        assertThat(fluentDemo.getMe()).isSameAs(fluentDemo);
    }

    @Test
    void assertThatGenericParameterTypesAreTreatedAsObject(@Mock List<Integer> ints) {
        // Although method get returns an Integer, it actually returns T, whose type is erased.
        // So we get the behavior of List<Object> - which returns null by default.
        Integer value = ints.get(0);
        assertThat(value).isNull();
    }
}