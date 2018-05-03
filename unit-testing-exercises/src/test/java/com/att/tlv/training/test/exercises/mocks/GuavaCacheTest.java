package com.att.tlv.training.test.exercises.mocks;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.Before;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.SECONDS;


/**
 * Read about GuavaCache in general here: https://github.com/google/guava/wiki/CachesExplained 
 * We'd like to test a time-based eviction policy called expireAfterWrite.
 * You can read about it here: https://github.com/google/guava/wiki/CachesExplained#eviction
 */
public class GuavaCacheTest {

    private static final long EXPIRATION_INTERVAL_SEC = 30; 
    // Class under test
    private Cache<Integer, Integer> cache;

    @Before
    public void setUp() {
        // TODO add a removalListener via the builder.
        // What else is needed?
        // Hint - how can you control key expiration in your tests?
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRATION_INTERVAL_SEC, SECONDS)
                .build();
    }

    @Test
    public void testInvalidateKey() {
        // Test cache.invalidate(key) with a valid key, BEFORE it expires:
        int key = 10;
        int value = 100;
        
        // When we call put, the cache will save the current time with the specified key.
        cache.put(key, value);
        // When we call invalidate(key), the key is removed and a removal notification is fired. 
        // The removal cause depends on whether the key is still valid, or has already expired.
        cache.invalidate(key);
        
        // TODO Make sure the removal listener is notified with the correct key, value, and removal cause
    }
    
    @Test
    public void testInvalidateExpiredKey() {
        // TODO Just like in the previous test, test cache.invalidate(key) with key, invalidating it AFTER it expires.
        // Make sure the removal listener is notified with the correct key, value, and removal cause
    }
    
    @Test
    public void testReplaceKey() {
        // Test replacing a key by putting it again in the cache BEFORE it expires.
        
        int key = 10;
        int value1 = 100;
        int value2 = 101;
        // When we call put, the cache will save the current time with the specified key.
        cache.put(key, value1);
        // When we call put again with the same key, the cache replaces the previous key-value pair
        // and a removal notification is fired.
        cache.put(key, value2);
        
        // TODO Make sure the removal listener is notified with the correct key, value, and removal cause
    }
    
    @Test
    public void testReplaceExpiredKey() {
        // TODO Just like in the previous test, test replacing a key by putting it again in the cache AFTER it expires.
        // Make sure the removal listener is notified with the correct key, value, and removal cause
    }
}