package com.att.tlv.training.test.exercises.assertions;

import org.junit.Before;
import org.junit.Test;

/**
 * Note the name & location of this test:
 * It's called the same as the class under test with the suffix 'Test'.
 * It's located in the same package as the class under test, under src/test/java
 */
public class PointCalculatorTest {
    
    private PointCalculator pointCalculator;

    @Before
    public void setUp() {
        pointCalculator = new PointCalculator();
    }

    /**
     * Make sure PointCalculator::add returns the expected result for non-origin Points.
     */
    @Test
    public void testAdd() {
        // TODO test PointCalculator.add.
    }
    
    @Test
    public void testAddPointAndOrigin() {
        // TODO test PointCalculator.add. Make sure it
        // returns the non-origin Point p when p and an origin point are added (in that order).
    }
    
    @Test
    public void testAddOriginAndPoint() {
        // TODO test PointCalculator.add. Make sure it
        // returns the non-origin Point p when origin point and p are added (in that order).
    }
}
