package com.att.tlv.training.test.junit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledOnJre;

import static org.junit.jupiter.api.condition.JRE.JAVA_8;

// No need for a public class anymore!
class JunitBasics {
    
    // MUST not be a private method!
    @Test
    void test1() {
        System.out.println("Running test1!");
    }
    
    @Test
    @DisplayName("Test #2")
    void test2() {
        System.out.println("Running test2!");
    }

    @Test
    @DisplayName("Test #3")
    @Disabled
    @DisabledOnJre(JAVA_8)
    @DisabledIfSystemProperty(named = "env-type", matches = "ci")
    void test3() {
        System.out.println("Running test3!");
    }
    
    
    // Set up and tear down methods: all methods can be package private
    
    // MUST be a static non-private method!
    @BeforeAll
    static void setUpClass() {
        System.out.println("Running set up before class...");
    }
    
    // MUST be a static non-private method!
    @AfterAll
    static void tearDownClass() {
        System.out.println("Running tear down after class...");
    }

    // Multiple methods are supported but order is not guaranteed - so use only one!
    @BeforeEach
    void setUp() {
        System.out.println("Running set up before test...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Running tear down after test...");
    }
}
