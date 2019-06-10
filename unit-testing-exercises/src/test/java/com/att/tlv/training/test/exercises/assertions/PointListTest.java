package com.att.tlv.training.test.exercises.assertions;

import com.att.tlv.training.test.exercises.data.Point;
import org.junit.jupiter.api.Test;

/**
 * Go over all the public methods of {@link PointList}.
 * Below you'll find a test for each one of them.
 */
class PointListTest {

    private static final Point P1 = new Point(1, 2);
    private static final Point P2 = new Point(3, 4);
    private static final Point P3 = new Point(5, 6);
    
    @Test
    void empty_shouldReturnEmptyList() {
        // TODO test
    }

    @Test
    void of_withSinglePoint_shouldReturnListOfPoint() {
        // TODO test PointList.of(Point p)
    }

    @Test
    void of_withTwoPoints_shouldReturnListOfOrderedPoints() {
        // TODO test
    }

    @Test
    void of_withThreePoints_shouldReturnListOfOrderedPoints() {
        // TODO test
    }

    @Test
    void of_withSinglePointCoordinates_shouldReturnListOfPoint() {
        // TODO test PointList.of(int x, int y)
    }

    @Test
    void of_withTwoPointCoordinates_shouldReturnListOfPoints() {
        // TODO test
    }

    @Test
    void of_withThreePointCoordinates_shouldReturnListOfPoints() {
    }

    @Test
    void testSize() {
        // TODO after obtaining a list with 3 arguments, test its size.
    }

    @Test
    void copyOfList_shouldReturnNewIdenticalList() {
        // TODO test
    }

    @Test
    void copyOfHashSet_shouldReturnNewList_inAnyOrder() {
        // TODO test
    }
}
