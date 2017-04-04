package com.att.tlv.training.test.answers.junit;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Add before / after methods so that a message is printed to the console ONCE
 * before the tests, and another message after EACH test. 
 * 
 */
public class JunitBasicsTestAnswer {
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Running set up before class...");
    }
    
    @After
    public void tearDown() {
        System.out.println("Running tear down after test...");
    }    
    
    @Test
    public void test1() {
        System.out.println("Running test1!");
    }
    
    @Test
    public void test2() {
        System.out.println("Running test2!");
    }
}
