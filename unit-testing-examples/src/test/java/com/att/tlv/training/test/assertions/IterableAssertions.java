package com.att.tlv.training.test.assertions;

import com.att.tlv.training.test.data.Person;
import com.att.tlv.training.test.data.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static java.util.Comparator.comparingInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;
import static org.assertj.core.api.Assertions.tuple;

class IterableAssertions {

    @Test
    void iterableBasicAssertions() {
        Iterable<String> animals = List.of("dog", "cat", "pig");
        assertThat(animals).isNotEmpty()
                .hasSize(3);
        assertThat(animals).hasSameSizeAs(List.of(1, 2, 3));
        assertThat(animals).contains("cat", "pig")
                .doesNotContain("tiger");

        assertThat(animals).doesNotContainNull()
                .doesNotHaveDuplicates();

        // Special check for null, empty collection or both
        assertThat(Arrays.asList("Hello", null, "World")).containsNull()
                .isNotEmpty();

        List<Object> newArrayList = new ArrayList<>();
        assertThat(newArrayList).doesNotContainNull()
                .isEmpty();
        assertThat(newArrayList).isNullOrEmpty();

        newArrayList = null;
        assertThat(newArrayList).isNullOrEmpty();

        // you can also check the start or end of your collection/iterable
        Iterable<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        assertThat(numbers).startsWith(1, 2)
                .endsWith(5, 6)
                // Same order
                .containsSequence(3, 4, 5)
                // Same order, possibly with other elements in between
                .containsSubsequence(1, 3, 6)
                // No order, all elements in the source iterable must be contained in the target
                .isSubsetOf(6, 5, 4, 3, 2, 1, 0, -1)
                // No order
                .containsAll(List.of(6, 3, 1))
                .containsAll(List.of(6, 3, 1, 1, 1));
    }

    @Test
    void test_containsOnly() {
        Iterable<String> animals = List.of("dog", "cat", "pig");
        Iterable<String> duplicatedAnimals = List.of("dog", "cat", "pig", "dog", "cat", "pig");

        // With containsOnly, all the distinct elements must be present, and nothing else (but the order is not important)
        assertThat(duplicatedAnimals).containsOnly("dog", "cat", "pig")
                .containsOnly("pig", "dog", "cat");
        assertThat(animals).hasSameElementsAs(duplicatedAnimals);
    }

    @Test
    void testIterable_containsExactly() {
        Iterable<String> animals = List.of("cat", "dog", "pig", "pig");

        // Same elements, same order (same size too, i.e. duplicates included), and nothing else
        assertThat(animals).containsExactly("cat", "dog", "pig", "pig")
                .containsOnly("pig", "cat", "dog")
                // including duplicates, in any order
                .containsExactlyInAnyOrder("pig", "pig", "cat", "dog");

        // It works with collections that have a consistent iteration order (i.e. NOT HashSet)
        SortedSet<String> sortedAnimals = new TreeSet<>();
        sortedAnimals.add("dog");
        sortedAnimals.add("cat");
        sortedAnimals.add("pig");
        assertThat(sortedAnimals).containsExactly("cat", "dog", "pig");

        // Expected values can be given by another Iterable. Must be in the same order (unlike hasSameElementsAs)
        assertThat(sortedAnimals).containsExactlyElementsOf(List.of("cat", "dog", "pig"));
    }

    @Test
    void testIterableWithCustomComparator() {
        Person jim = new Person(444, "Jim", 30, 1.85d);
        Person carl = new Person(666, "Carl", 20, 1.95d);
        Iterable<Person> people = List.of(jim, carl);

        Person alice = new Person(555, "Alice", 30, 1.65d);
        // By default, equals() is used, in Person's case - comparing ids
        assertThat(people).doesNotContain(alice);

        // But if we specify a custom comparator that compares ages:
        assertThat(people).usingElementComparator(comparingInt(Person::getAge))
                .contains(alice);
        // This goes for all methods we saw - containAll/Only/Exactly etc.
    }

    @Test
    void testIterableWithExtractedValues() {
        Person jim = new Person(444, "Jim", 30, 1.85d);
        Person carl = new Person(666, "Carl", 20, 1.95d);
        Iterable<Person> people = List.of(jim, carl);

        // Sort of like Stream::map
        assertThat(people).extracting(Person::getAge)
                .containsOnly(20, 30);

        assertThat(people).extracting(Person::getAge, Person::getName)
                .containsExactlyInAnyOrder(tuple(20, "Carl"), tuple(30, "Jim"));
    }

    @Test
    void testIterableWithFieldByFieldComparison() {
        Point oneTwo = new Point(1, 2);
        Point oneThree = new Point(1, 3);
        Iterable<Point> points = List.of(oneTwo, oneThree);

        Point oneTwoClone = new Point(1, 2);
        // By default equals() is used, in Point's case - Object.equals() (reference equality)
        assertThat(points).doesNotContain(oneTwoClone);
        // But if we specify a field by field comparator:
        assertThat(points).usingRecursiveFieldByFieldElementComparator()
                .contains(oneTwoClone);

    }

    @Test
    void testIndexedAccess() {
        Person jim = new Person(444, "Jim", 30, 1.85d);
        Person carl = new Person(666, "Carl", 20, 1.95d);

        // For lists, you can check element at a given index (we use Assertions.atIndex(int) syntactic sugar for better readability):
        List<Person> people = List.of(jim, carl);
        assertThat(people).contains(jim, atIndex(0))
                .contains(carl, atIndex(1));

        // And the same goes for arrays:
        Person[] peopleArray = {jim, carl};
        assertThat(peopleArray).contains(jim, atIndex(0))
                .contains(carl, atIndex(1));

    }

    @Test
    void testWithPredicate() {
        Person jim = new Person(444, "Jim", 30, 1.85d);
        Person carl = new Person(666, "Carl", 20, 1.95d);
        List<Person> people = List.of(jim, carl);

        assertThat(people).allMatch(p -> p.getAge() > 18);
        assertThat(people).anyMatch(p -> p.getId() == 666);
        assertThat(people).noneMatch(p -> p.getAge() < 18);

        assertThat(people).satisfies(p -> assertThat(p.getAge()).isEqualTo(20), atIndex(1));
    }


    @Test
    void testIterablesOfDifferentTypes() {
        List<A> as = List.of(new A("one"), new A("two"));
        List<B> bs = List.of(new B("one"), new B("two"));

        assertThat(as).usingRecursiveComparison()
                .isEqualTo(bs);

        try {
            // This fails:
            assertThat(as).usingRecursiveComparison()
                    .withStrictTypeChecking()
                    .isEqualTo(bs);
        } catch (AssertionError e) {
            e.printStackTrace();
        }
    }

    static class A {
        private final String name;

        A(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName() + ": " + name;
        }
    }

    static class B {
        private final String name;

        B(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName() + ": " + name;
        }
    }
}
