package com.att.tlv.training.test.exercises.mocks;

import org.junit.Before;
import org.junit.Test;

import com.att.tlv.training.test.exercises.data.Person;

public class ComplexCollectionTest {
    
    // Class under test:
    private ComplexCollection<Long, Person> complexCollection;
    
    @Before
    public void setUp() {
        // Production code typically creates an instance like this:
        // complexCollection = new ComplexCollection<>(ArrayList::new, HashMap::new, Person::getId);
        // TODO init
    }
    
    @Test
    public void testAdd() {
        // TODO test ComplexCollection.testAdd()
    }
    
    @Test
    public void testAddNCopies() {
        // TODO test ComplexCollection.testAddNCopies()
    }
    
    @Test
    public void testAddNCopiesWithInvalidNumber() {
        // TODO test ComplexCollection.testAddNCopies() specifying an invalid number of copies
    }
    
    @Test
    public void testRemove() {
        // TODO test ComplexCollection.remove()
    }
}