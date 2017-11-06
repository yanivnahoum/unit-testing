package com.att.tlv.training.test.answers.assertions;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.att.tlv.training.test.exercises.data.Person;

public class LinkedHashMapTestAnswer {
    
    private static final Person ALICE = new Person(100L, "Alice");
    private static final Person BOB = new Person(200L, "Bob");
    private static final Person CARL = new Person(300L, "Carl");
    private Map<Long, Person> map;
    
    @Before
    public void setUp() {
        map = new LinkedHashMap<>();
    }
    
    @Test
    public void testEmpty() {
        assertThat(map).isEmpty();
    }
    
    @Test
    public void testPutTwoEntries() {
        map.put(ALICE.getId(), ALICE);
        map.put(BOB.getId(), BOB);
        
        assertThat(map).containsExactly(entry(ALICE.getId(), ALICE), entry(BOB.getId(), BOB));
    }
    
    @Test
    public void testGetExistingEntry() {
        map.put(ALICE.getId(), ALICE);
        
        Person alice = map.get(ALICE.getId());
        
        assertThat(alice).isSameAs(ALICE);
    }
    
    @Test
    public void testGetMissingEntry() {
        Person person = map.get(400L);
        
        assertThat(person).isNull();
    }
    
    @Test
    public void testConstructorWithMap() {
        Map<Long, Person> source = Stream.of(ALICE, BOB, CARL)
                .collect(toMap(Person::getId, Function.identity()));
        
        Map<Long, Person> copy = new LinkedHashMap<>(source);
        
        assertThat(copy).containsAllEntriesOf(source)
                // ...and nothing else
                .hasSameSizeAs(source);
        
        // or:
        assertThat(copy).containsOnly(entry(ALICE.getId(), ALICE), entry(BOB.getId(), BOB), entry(CARL.getId(), CARL));
    }
    
    @Test
    public void testRemoveOneOfTwoEntries() {
        map.put(ALICE.getId(), ALICE);
        map.put(BOB.getId(), BOB);
        
        Person removed = map.remove(ALICE.getId());
        
        assertThat(removed).isSameAs(ALICE);
        assertThat(map).containsEntry(BOB.getId(), BOB)
                .doesNotContainEntry(ALICE.getId(), ALICE);
    }
    
    @Test
    public void testRemoveMissingEntry() {
        map.put(ALICE.getId(), ALICE);
        map.put(BOB.getId(), BOB);
        
        Person removed = map.remove(CARL.getId());
        
        assertThat(removed).isNull();
        assertThat(map).containsExactly(entry(ALICE.getId(), ALICE), entry(BOB.getId(), BOB));
    }
}
