package com.att.tlv.training.test.answers.mocks;

import com.att.tlv.training.test.exercises.data.Person;
import com.att.tlv.training.test.exercises.mocks.ComplexCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Unlike most collections, this collection does not expose methods for getting elements,
 * only for adding or removing them. This was done intentionally so that the only way to test
 * it would be via verifying mock behaviors.
 * See {@link ComplexCollection}
 */
@ExtendWith(MockitoExtension.class)
class ComplexCollectionTestAnswer {
    
    private static final Person ALICE = new Person(500, "Alice");
    private ComplexCollection<Long, Person> complexCollection;
    @Mock private List<Person> persons;
    @Mock private Map<Long, Person> idToPerson;
    
    @BeforeEach
    void setUp() {
        complexCollection = new ComplexCollection<>(() -> persons, () -> idToPerson, Person::getId);
    }
    
    @Test
    void testAdd() {
        complexCollection.add(ALICE);
        
        verify(persons).add(ALICE);
        verify(idToPerson).put(ALICE.getId(), ALICE);
    }
    
    @Test
    void testAddNCopies() {
        int numOfCopies = 50;
        complexCollection.addNCopies(ALICE, numOfCopies);
        
        verify(persons, times(numOfCopies)).add(ALICE);
        verify(idToPerson).put(ALICE.getId(), ALICE);
    }
    
    @Test
    void testAddNCopiesWithInvalidNumber() {
        assertThatIllegalArgumentException().isThrownBy(() -> complexCollection.addNCopies(ALICE, -1))
                .withMessage("Number of copies must be greater than 0");

        verify(persons, never()).add(ALICE);
        verify(idToPerson, never()).put(ALICE.getId(), ALICE);
        
        // Or
        verify(persons, never()).add(any());
        verify(idToPerson, never()).put(anyLong(), any());
        
        // Or
        verifyZeroInteractions(persons, idToPerson);
    }
    
    @Test
    void testRemove() {
        complexCollection.remove(ALICE);
        
        verify(persons).remove(ALICE);
        verify(idToPerson).remove(ALICE.getId());
    }
}