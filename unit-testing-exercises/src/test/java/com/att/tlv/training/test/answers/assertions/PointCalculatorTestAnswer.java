package com.att.tlv.training.test.answers.assertions;

import com.att.tlv.training.test.exercises.assertions.PointCalculator;
import com.att.tlv.training.test.exercises.data.Point;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PointCalculatorTestAnswer {
    
    private PointCalculator pointCalculator;

    @Before
    public void setUp() {
        pointCalculator = new PointCalculator();
    }

    @Test
    public void add_nonOriginPoints_shouldReturnSum() {
        int aX = 0, aY = 2;
        int bX = 3, bY = 0;
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
    public void add_pointAndOrigin_shouldReturnPoint() {
        Point a = new Point(1, 2);
        Point origin = new Point(0, 0);
        
        Point result = pointCalculator.add(a, origin);
        
        assertThat(result).isSameAs(a);
    }
    
    @Test
    public void add_originAndPoint_shouldReturnPoint() {
        Point a = new Point(1, 2);
        Point origin = new Point(0, 0);
        
        Point result = pointCalculator.add(origin, a);
        
        assertThat(result).isSameAs(a);
    }
}
