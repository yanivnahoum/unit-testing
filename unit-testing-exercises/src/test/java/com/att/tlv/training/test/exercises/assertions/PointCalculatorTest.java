package com.att.tlv.training.test.exercises.assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Note the name & location of this test:
 * It's called the same as the class under test with the suffix 'Test'.
 * It's located in the same package as the class under test, under src/test/java
 */
class PointCalculatorTest {
    
    private PointCalculator pointCalculator;

    @BeforeEach
    void setUp() {
        pointCalculator = new PointCalculator();
    }

    /**
     * Make sure PointCalculator::add returns the expected result for non-origin Points.
     */
    @Test
    void add_nonOriginPoints_shouldReturnSum() {
        // TODO test PointCalculator.add.
    }
    
    @Test
    void add_pointAndOrigin_shouldReturnPoint() {
        // TODO test PointCalculator.add. Make sure it
        // returns the non-origin Point p when p and an origin point are added (in that order).
    }
    
    @Test
    void add_originAndPoint_shouldReturnPoint() {
        // TODO test PointCalculator.add. Make sure it
        // returns the non-origin Point p when origin point and p are added (in that order).
    }
}
