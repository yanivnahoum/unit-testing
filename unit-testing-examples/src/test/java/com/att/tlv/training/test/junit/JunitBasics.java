package com.att.tlv.training.test.junit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// No need for a public class anymore!
class JunitBasics {
    
    // MUST be a public method!
    @Test
    void test1() {
        System.out.println("Running test1!");
    }
    
    @Test
    void test2() {
        System.out.println("Running test2!");
    }
    
    
    // Set up and tear down methods: all methods can be package private
    
    // MUST be a static method!
    @BeforeAll
    static void setUpClass() {
        System.out.println("Running set up before class...");
    }
    
    // MUST be a static method!
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
