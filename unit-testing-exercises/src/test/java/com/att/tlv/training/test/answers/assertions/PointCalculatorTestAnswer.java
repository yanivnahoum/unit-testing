package com.att.tlv.training.test.answers.assertions;

import com.att.tlv.training.test.exercises.assertions.PointCalculator;
import com.att.tlv.training.test.exercises.data.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PointCalculatorTestAnswer {
    
    private PointCalculator pointCalculator;

    @BeforeEach
    void setUp() {
        pointCalculator = new PointCalculator();
    }

    @Test
    void add_nonOriginPoints_shouldReturnSum() {
        // arrange
        int aX = 0, aY = 2;
        int bX = 3, bY = 0;
        Point a = new Point(aX, aY);
        Point b = new Point(bX, bY);
        // act
        Point result = pointCalculator.add(a, b);
        // assert
        int expectedX = aX + bX;
        int expectedY = aY + bY;
        assertThat(result.getX()).isEqualTo(expectedX);
        assertThat(result.getY()).isEqualTo(expectedY);
        
        // Or...        
        Point expected = new Point(expectedX, expectedY);
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
    
    @Test
    @DisplayName("Given a non-origin Point p and an origin point, the non-origin should be returned")
    void add_pointAndOrigin_shouldReturnPoint() {
        Point a = new Point(1, 2);
        Point origin = new Point(0, 0);

        Point result = pointCalculator.add(a, origin);

        assertThat(result).isSameAs(a);
    }
    
    @Test
    void add_originAndPoint_shouldReturnPoint() {
        Point a = new Point(1, 2);
        Point origin = new Point(0, 0);
        
        Point result = pointCalculator.add(origin, a);
        
        assertThat(result).isSameAs(a);
    }
}
