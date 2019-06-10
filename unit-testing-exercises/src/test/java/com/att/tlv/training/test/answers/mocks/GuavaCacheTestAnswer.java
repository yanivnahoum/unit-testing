package com.att.tlv.training.test.answers.mocks;

import com.google.common.base.Ticker;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuavaCacheTestAnswer {

    private static final long EXPIRATION_INTERVAL_SEC = 30;    
    private static final long EXPIRED_INTERVAL_NS = SECONDS.toNanos(EXPIRATION_INTERVAL_SEC + 1);    
    private Cache<Integer, Integer> cache;
    @Mock
    private Ticker ticker;
    @Mock
    private RemovalListener<Integer, Integer> removalListener;
    @Captor
    private ArgumentCaptor<RemovalNotification<Integer, Integer>> removalNotificationCaptor;

    @BeforeEach
    void setup() {
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRATION_INTERVAL_SEC, SECONDS)
                .removalListener(removalListener)
                .ticker(ticker)
                .build();
    }

    @Test
    void testInvalidateKey() {
        int key = 10;
        int value = 100;
        cache.put(key, value);
        cache.invalidate(key);
        
        verifyRemovalNotification(key, value, RemovalCause.EXPLICIT);
    }
    
    @Test
    void testInvalidateExpiredKey() {
        when(ticker.read()).thenReturn(0L, EXPIRED_INTERVAL_NS);
        
        int key = 10;
        int value = 100;
        cache.put(key, value);
        cache.invalidate(key);
        
        verifyRemovalNotification(key, value, RemovalCause.EXPIRED);
    }
    
    @Test
    void testReplaceKey() {
        int key = 10;
        int value1 = 100;
        int value2 = 101;
        cache.put(key, value1);
        cache.put(key, value2);
        
        verifyRemovalNotification(key, value1, RemovalCause.REPLACED);
    }
    
    @Test
    void testReplaceExpiredKey() {
        when(ticker.read()).thenReturn(0L, EXPIRED_INTERVAL_NS);
        
        int key = 10;
        int value1 = 100;
        int value2 = 101;
        cache.put(key, value1);
        cache.put(key, value2);
        
        verifyRemovalNotification(key, value1, RemovalCause.EXPIRED);
    }
    

    private void verifyRemovalNotification(int key, int value, RemovalCause removalCause) {
        verify(removalListener).onRemoval(removalNotificationCaptor.capture());
        RemovalNotification<Integer, Integer> removalNotification = removalNotificationCaptor.getValue();
        assertThat(removalNotification.getKey()).isEqualTo(key);
        assertThat(removalNotification.getValue()).isEqualTo(value);
        assertThat(removalNotification.getCause()).isEqualTo(removalCause);
    }    
}
