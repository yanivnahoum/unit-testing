package com.att.tlv.training.test.exercises.junit;

import org.junit.Test;

/**
 * Add before / after methods so that a message is printed to the console ONCE
 * before the tests, and another message after EACH test. 
 * 
 */
public class JunitBasicsTest {
    
    @Test
    public void test1() {
        System.out.println("Running test1!");
    }
    
    @Test
    public void test2() {
        System.out.println("Running test2!");
    }
}