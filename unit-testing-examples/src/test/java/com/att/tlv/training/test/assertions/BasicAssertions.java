package com.att.tlv.training.test.assertions;

import com.att.tlv.training.test.data.Person;
import com.att.tlv.training.test.data.Point;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Comparator.comparingInt;
import static org.assertj.core.api.Assertions.assertThat;

class BasicAssertions {

    private final Person john = new Person(333, "John", 30, 1.85d);

    @Test
    void test_isEqualTo_isNotEqualTo() {
        // The most simple assertion
        assertThat(john.getAge()).isEqualTo(30);
        assertThat(john.getName()).isEqualTo("John")
                .isNotEqualTo("Johnny");

        // isEqualTo takes an object (just like Object.equals())
        Object johnAsObject = john;
        assertThat(john).isEqualTo(johnAsObject);
    }

    @Test
    void test_isSame_isNotSame() {
        // isSame compares objects reference
        Object sameJohn = john;
        // is equal to john but not the same
        Object johnClone = new Person(333, "John", 30, 1.85d);

        assertThat(john).isSameAs(sameJohn)
                .isEqualTo(johnClone)
                // but...
                .isNotSameAs(johnClone);
    }

    @Test
    void test_isIn_isNotIn() {
        List<Integer> ints = List.of(10, 20, 30);

        assertThat(20).isIn(ints);
        assertThat(40).isNotIn(ints);

        assertThat(20).isIn(10, 20, 30);
        assertThat(40).isNotIn(10, 20, 30);
    }

    @Test
    void test_isNull_isNotNull() {
        Object nullObject = null;
        assertThat(nullObject).isNull();
        Object nonNullObject = new Object();
        assertThat(nonNullObject).isNotNull();
    }

    @Test
    void isInstanceOf_assertions_examples() {
        assertThat(john).isInstanceOf(Person.class)
                .isInstanceOfAny(Object.class, Person.class);
        assertThat(john).isNotInstanceOf(HashMap.class)
                .isNotInstanceOfAny(HashMap.class, String.class);
    }

    @Test
    void testEqualityWithCustomComparator() {
        Person jim = new Person(444, "Jim", 30, 1.85d);
        Person alice = new Person(555, "Alice", 30, 1.65d);

        // Default equality is determined by the equals method (or reference equality if equals has not been overridden)
        // Jim and Alice have different ids
        assertThat(jim).isNotEqualTo(alice);

        // But they're both 30, so let's compare by age only.
        assertThat(jim).usingComparator(comparingInt(Person::getAge))
                .isEqualTo(alice);

        // isIn/isNotIn also uses the comparator
        Person carl = new Person(666, "Carl", 20, 1.95d);
        Person bob = new Person(666, "Bob", 60, 1.75d);
        assertThat(jim).usingComparator(comparingInt(Person::getAge))
                .isIn(alice, carl, bob);
    }

    @Test
    void test_usingRecursiveComparison_withComparedFields() {
        Point oneTwo = new Point(1, 2);
        Point oneTwoClone = new Point(1, 2);

        // Obviously the two objects are not equal (default equality compares references)...
        var comparisonConfiguration = RecursiveComparisonConfiguration.builder()
                .withComparedFields("x", "y")
                .build();
        assertThat(oneTwo).isNotEqualTo(oneTwoClone)
                // ...but the fields 'x' and 'y' are equal
                .usingRecursiveComparison(comparisonConfiguration)
                .isEqualTo(oneTwoClone);

        // If we just want to go over all fields / properties:
        assertThat(oneTwo).usingRecursiveComparison().isEqualTo(oneTwoClone);
    }

    @Test
    void test_satisfies() {
        Person jim = new Person(444, "Jim", 30, 1.85d);
        Person alice = new Person(555, "Alice", 25, 1.65d);

        Consumer<Person> requirements = p -> {
            assertThat(p.getAge()).isBetween(20, 30);
            assertThat(p.getName()).contains("i");
        };

        assertThat(jim).satisfies(requirements);
        assertThat(alice).satisfies(requirements);
    }

    @Test
    void test_assertionMessages() {
        try {
            // You can specify a test description with as() method or describedAs(), it supports String format args
            assertThat(john.getAge()).as("check %s's age", john.getName())
                    .isEqualTo(55);
        } catch (AssertionError e) {
            assertThat(e).hasMessageContaining("[check John's age]");
        }

        try {
            int age = john.getAge();
            // You can even specify your own error message
            assertThat(age).overridingErrorMessage("Expected %s's age to be 55, but it was %d instead!", john.getName(), age)
                    .isEqualTo(55);
        } catch (AssertionError e) {
            assertThat(e).hasMessage("Expected John's age to be 55, but it was 30 instead!");
        }
    }
}
