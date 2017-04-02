package com.att.tlv.training.test.exercises.assertions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.att.tlv.training.test.exercises.data.Point;

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
        int aX = 1, aY = 2;
        int bX = 3, bY = 4;
        Point a = new Point(aX, aY);
        Point b = new Point(bX, bY);
        
        Point result = pointCalculator.add(a, b);
        
        int expectedX = aX + bX;
        int expectedY = aY + bY;
        assertThat(result.getX()).isEqualTo(expectedX);
        assertThat(result.getY()).isEqualTo(expectedY);
        
        // Or...        
        Point expected = new Point(expectedX, expectedY);
        assertThat(result).isEqualToComparingFieldByField(expected);
    }
    
    @Test
    public void testAddPointAndOrigin() {
        Point a = new Point(1, 2);
        Point origin = new Point(0, 0);
        
        Point result = pointCalculator.add(a, origin);
        
        assertThat(result).isSameAs(a);
    }
    
    @Test
    public void testAddOriginAndPoint() {
        Point a = new Point(1, 2);
        Point origin = new Point(0, 0);
        
        Point result = pointCalculator.add(origin, a);
        
        assertThat(result).isSameAs(a);
    }
}
