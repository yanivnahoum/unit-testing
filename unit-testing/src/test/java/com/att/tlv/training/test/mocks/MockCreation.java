package com.att.tlv.training.test.mocks;

import static org.mockito.Mockito.mock;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MockCreation {
    
    @SuppressWarnings("unused")
    @Test
    public void create() {
        // We can mock interfaces
        List<?> list = mock(List.class);   
        // We can mock concrete classes as well
        ArrayList<?> arrayList = mock(ArrayList.class);   
    }
    
    @SuppressWarnings("unused")
    @Test
    public void cannotCreate() {
        // There are a few types we can't create:
        // Final classes:
        String str = mock(String.class);    
        // Enums:
        DayOfWeek dayOfWeek = mock(DayOfWeek.class); 
        
        // Actually, starting from Mockito v2.1.0 it IS possible, using a different mock maker that the default one.
        // But it is rarely needed.
    }
}