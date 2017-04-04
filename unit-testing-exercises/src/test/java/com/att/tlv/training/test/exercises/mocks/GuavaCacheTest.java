package com.att.tlv.training.test.exercises.mocks;

import static java.util.concurrent.TimeUnit.SECONDS;

import org.junit.Before;
import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;


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
        // TODO test cache.invalidate(key) with a valid key, before it expires.
        // Make sure the removal listener is notified with the correct key, value, and removal cause
    }
    
    @Test
    public void testInvalidateExpiredKey() {
        // TODO test cache.invalidate(key) with key, invalidating it after it expires.
        // Make sure the removal listener is notified with the correct key, value, and removal cause
    }
    
    @Test
    public void testReplaceKey() {
        // TODO test replacing an valid key by putting it again in the cache before it expires.
        // Make sure the removal listener is notified with the correct key, value, and removal cause
    }
    
    @Test
    public void testReplaceExpiredKey() {
        // TODO test replacing an expired key by putting it again in the cache after it expires.
        // Make sure the removal listener is notified with the correct key, value, and removal cause
    }
}