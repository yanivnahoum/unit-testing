package com.att.tlv.training.test.mocks;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultReturnValues {
    
    @Mock
    private Demo demo;
    
    @Test
    public void assertThatReturnedCharIsZero() {
        assertThat(demo.getChar()).isEqualTo('\0');
        assertThat(demo.getChar()).isEqualTo('\u0000');
        assertThat(demo.getChar()).isEqualTo(Character.MIN_VALUE);
        assertThat(demo.getBoxedChar()).isEqualTo('\u0000');
    }
    
    @Test
    public void assertThatReturnedByteIsZero() {
        assertThat(demo.getByte()).isZero();
        assertThat(demo.getBoxedByte()).isZero();
    }
    
    @Test
    public void assertThatReturnedShortIsZero() {
        assertThat(demo.getShort()).isZero();
        assertThat(demo.getBoxedShort()).isZero();
    }
    
    @Test
    public void assertThatReturnedIntIsZero() {
        assertThat(demo.getInt()).isZero();
        assertThat(demo.getBoxedInt()).isZero();
    }
    
    @Test
    public void assertThatReturnedLongIsZero() {
        assertThat(demo.getLong()).isZero();
        assertThat(demo.getBoxedLong()).isZero();
    }
    
    @Test
    public void assertThatReturnedFloatIsZero() {
        assertThat(demo.getFloat()).isZero();
        assertThat(demo.getBoxedFloat()).isZero();
    }
    
    @Test
    public void assertThatReturnedDoubleIsZero() {
        assertThat(demo.getDouble()).isZero();
        assertThat(demo.getBoxedDouble()).isZero();
    }
    
    @Test
    public void assertThatReturnedBooleanIsFalse() {
        assertThat(demo.getBoolean()).isFalse();
        assertThat(demo.getBoxedBoolean()).isFalse();
    }
    
    @Test
    public void assertThatObjectReturnsNull() {
        assertThat(demo.getObject()).isNull();
    }
    
    @Test
    public void assertThatArrayReturnsNull() {
        assertThat(demo.getObjectArray()).isNull();
    }
    
    @Test
    public void assertThatCollectionReturnsEmptyCollection() {
        assertThat(demo.getCollection()).isEmpty();
        assertThat(demo.getList()).isEmpty();
        assertThat(demo.getSet()).isEmpty();
    }
    
    @Test
    public void assertThatMapReturnsEmptyMap() {
        assertThat(demo.getStream()).isEmpty();
    }
    
    @Test
    public void assertThatStreamReturnsEmptyStream() {
        assertThat(demo.getStream()).isEmpty();
    }
}

interface Demo {
    
    char getChar();
    Character getBoxedChar();
    
    byte getByte();
    Byte getBoxedByte();
    
    short getShort();
    Short getBoxedShort();
    
    int getInt();
    Integer getBoxedInt();
    
    long getLong();
    Long getBoxedLong();
    
    float getFloat();
    Float getBoxedFloat();
    
    double getDouble();
    Double getBoxedDouble();
    
    boolean getBoolean();
    Boolean getBoxedBoolean();
    
    Object getObject();
    
    Object[] getObjectArray();
    
    Collection<Object> getCollection();
    List<Object> getList();
    Set<Object> getSet();
    
    Map<Object, Object> getMap();
    
    Stream<Object> getStream();
}