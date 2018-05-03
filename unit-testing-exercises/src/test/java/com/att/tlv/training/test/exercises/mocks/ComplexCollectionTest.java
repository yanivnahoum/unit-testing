package com.att.tlv.training.test.exercises.mocks;

import com.att.tlv.training.test.exercises.data.Person;
import org.junit.Before;
import org.junit.Test;

/**
 * Unlike most collections, this collection does not expose methods for getting elements,
 * only for adding or removing them. This was done intentionally so that the only way to test
 * it would be via verifying mock behaviors.
 * See {@link ComplexCollection}
 */
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
        // TODO test ComplexCollection.add()
    }
    
    @Test
    public void testAddNCopies() {
        // TODO test ComplexCollection.addNCopies()
    }
    
    @Test
    public void testAddNCopiesWithInvalidNumber() {
        // TODO test ComplexCollection.addNCopies() specifying an invalid number of copies
    }
    
    @Test
    public void testRemove() {
        // TODO test ComplexCollection.remove()
    }
}