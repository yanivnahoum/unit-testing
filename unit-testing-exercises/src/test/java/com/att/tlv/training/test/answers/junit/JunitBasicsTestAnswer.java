package com.att.tlv.training.test.answers.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Add before / after methods so that a message is printed to the console ONCE
 * before the tests, and another message after EACH test. 
 * 
 */
class JunitBasicsTestAnswer {
    
    @BeforeAll
    static void beforeAll() {
        System.out.println("Running set up before class...");
    }
    
    @AfterEach
    void afterEach() {
        System.out.println("Running tear down after test...");
    }    
    
    @Test
    void test1() {
        System.out.println("Running test1!");
    }
    
    @Test
    void test2() {
        System.out.println("Running test2!");
    }
}
