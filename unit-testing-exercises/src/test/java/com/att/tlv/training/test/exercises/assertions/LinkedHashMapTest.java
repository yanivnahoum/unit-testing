package com.att.tlv.training.test.exercises.assertions;

import com.att.tlv.training.test.exercises.data.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

class LinkedHashMapTest {
    
    private static final Person ALICE = new Person(100L, "Alice");
    private static final Person BOB = new Person(200L, "Bob");
    private static final Person CARL = new Person(300L, "Carl");
    private Map<Long, Person> map;
    
    @BeforeEach
    void setUp() {
        map = new LinkedHashMap<>();
    }
    
    @Test
    void testNewMapIsEmpty() {
        // TODO test
    }
    
    @Test
    void testPutTwoEntries() {
        // TODO test calling map.put() twice
    }
    
    @Test
    void testGetExistingEntry() {
        // TODO test map.get(id) for an existing person
    }
    
    @Test
    void testGetMissingEntry() {
        // TODO test map.get(id) for a missing person
    }
    
    @Test
    void testConstructorWithMap() {
        // TODO test new LinkedHashMap<>(sourceMap)
    }
    
    @Test
    void testRemoveOneOfTwoEntries() {
        // TODO test put two entries and remove one of them
    }
    
    @Test
    void testRemoveMissingEntry() {
        // TODO test put two entries and try to remove a third (missing)
    }
}
