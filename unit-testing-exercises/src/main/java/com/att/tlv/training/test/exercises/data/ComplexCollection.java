package com.att.tlv.training.test.exercises.data;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class ComplexCollection<K, V> {
    
    private final List<V> elements;
    private final Map<K, V> indexedElements;
    private final Function<? super V, ? extends K> keyExtractor;
    
    /**
     * Constructs a {@code ComplexCollection} using the specified listGenerator, mapGenerator, and keyExtractor functions. 
     * @param listGenerator a {@link Supplier} that generates a list.
     * @param mapGenerator a {@link Supplier} that generates a map.
     * @param keyExtractor a {@link Function} from value to key to be used when working with the map specified.
     */
    public ComplexCollection(Supplier<List<V>> listGenerator, Supplier<Map<K, V>> mapGenerator, Function<? super V, ? extends K> keyExtractor) {
        this.elements = listGenerator.get();
        this.indexedElements = mapGenerator.get();
        this.keyExtractor = Objects.requireNonNull(keyExtractor);
    }
    
    /**
     * Adds the specified element to this {@code ComplexCollection}.
     * @param element to be added.
     */
    public void add(V element) {
        addNCopies(element, 1);
    }

    /**
     * Adds the specified element to this {@code ComplexCollection}, n times.
     * @param element to be added.
     * @param n the number of copies to be added.
     */
    public void addNCopies(V element, int n) {
        checkArgument(n > 0, "Number of copies must be greater than 0");
        for (int i = 0; i < n; i++) {
            addToList(element);
        }
        addToMap(element);
    }

    /**
     * Removes the specified element from this {@code ComplexCollection}
     * @param element to be removed.
     */
    public void remove(V element) {
        elements.remove(element);
        K key = getKey(element);
        indexedElements.remove(key);
    }
    
    private void addToMap(V element) {
        K key = getKey(element);
        indexedElements.put(key, element);
    }

    private void addToList(V element) {
        elements.add(element);
    }    
    
    private K getKey(V element) {
        return keyExtractor.apply(element);
    }
}