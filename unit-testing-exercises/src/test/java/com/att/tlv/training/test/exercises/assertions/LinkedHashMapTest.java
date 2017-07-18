package com.att.tlv.training.test.exercises.assertions;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.att.tlv.training.test.exercises.data.Person;

public class LinkedHashMapTest {
    
    private static final Person ALICE = new Person(100L, "Alice");
    private static final Person BOB = new Person(200L, "Bob");
    private static final Person CARL = new Person(300L, "Carl");
    private Map<Long, Person> map;
    
    @Before
    public void setUp() {
        map = new LinkedHashMap<>();
    }
    
    @Test
    public void testNewMapIsEmpty() {
        // TODO test
    }
    
    @Test
    public void testPutTwoEntries() {
        // TODO test calling map.put() twice
    }
    
    @Test
    public void testGetExistingEntry() {
        // TODO test map.get(id) for an existing person
    }
    
    @Test
    public void testGetMissingEntry() {
        // TODO test map.get(id) for a missing person
    }
    
    @Test
    public void testConstructorWithMap() {
        // TODO test new LinkedHashMap<>(sourceMap`)
    }
    
    @Test
    public void testRemoveOneOfTwoEntries() {
        // TODO test put two entries and remove one of them
    }
    
    @Test
    public void testRemoveMissingEntry() {
        // TODO test put two entries and try to remove a third (missing)
    }
}
