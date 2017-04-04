package com.att.tlv.training.test.answers.mocks;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.att.tlv.training.test.exercises.data.ComplexCollection;
import com.att.tlv.training.test.exercises.data.Person;

@RunWith(MockitoJUnitRunner.class)
public class VerificationTestAnswer {
    
    private static final Person ALICE = new Person(500, "Alice");
    private ComplexCollection<Long, Person> complexCollection;
    @Mock private List<Person> persons;
    @Mock private Map<Long, Person> idToPerson;
    
    @Before
    public void setUp() {
        complexCollection = new ComplexCollection<>(() -> persons, () -> idToPerson, Person::getId);
    }
    
    @Test
    public void testAdd() {
        complexCollection.add(ALICE);
        
        verify(persons).add(ALICE);
        verify(idToPerson).put(ALICE.getId(), ALICE);
    }
    
    @Test
    public void testAddNCopies() {
        int numOfCopies = 50;
        complexCollection.addNCopies(ALICE, numOfCopies);
        
        verify(persons, times(numOfCopies)).add(ALICE);
        verify(idToPerson).put(ALICE.getId(), ALICE);
    }
    
    @Test
    public void testAddNCopiesWithInvalidNumber() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> complexCollection.addNCopies(ALICE, -1))
                .withMessage("Number of copies must be greater than 0");

        verify(persons, never()).add(ALICE);
        verify(idToPerson, never()).put(ALICE.getId(), ALICE);
        
        // Or
        verify(persons, never()).add(any());
        verify(idToPerson, never()).put(anyLong(), any());
        
        // Or
        verifyZeroInteractions(persons);
        verifyZeroInteractions(idToPerson);
    }
    
    @Test
    public void testRemove() {
        complexCollection.remove(ALICE);
        
        verify(persons).remove(ALICE);
        verify(idToPerson).remove(ALICE.getId());
    }
}