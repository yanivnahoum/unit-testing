package com.att.tlv.training.test.exercises.mocks;

import com.att.tlv.training.test.exercises.data.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unlike most collections, this collection does not expose methods for getting elements,
 * only for adding or removing them. This was done intentionally so that the only way to test
 * it would be via verifying mock behaviors.
 * See {@link ComplexCollection}
 */
class ComplexCollectionTest {
    
    // Class under test:
    private ComplexCollection<Long, Person> complexCollection;
    
    @BeforeEach
    void setUp() {
        // Production code typically creates an instance like this:
        // complexCollection = new ComplexCollection<>(ArrayList::new, HashMap::new, Person::getId);
        // TODO init
    }
    
    @Test
    void testAdd() {
        // TODO test ComplexCollection.add()
    }
    
    @Test
    void testAddNCopies() {
        // TODO test ComplexCollection.addNCopies()
    }
    
    @Test
    void testAddNCopiesWithInvalidNumber() {
        // TODO test ComplexCollection.addNCopies() specifying an invalid number of copies
    }
    
    @Test
    void testRemove() {
        // TODO test ComplexCollection.remove()
    }
}