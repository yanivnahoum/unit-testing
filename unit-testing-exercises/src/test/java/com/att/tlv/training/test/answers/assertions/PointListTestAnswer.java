package com.att.tlv.training.test.answers.assertions;

import com.att.tlv.training.test.exercises.assertions.PointList;
import com.att.tlv.training.test.exercises.data.Point;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;

class PointListTestAnswer {

    private static final Point P1 = new Point(1, 2);
    private static final Point P2 = new Point(3, 4);
    private static final Point P3 = new Point(5, 6);

    @Test
    void empty_shouldReturnEmptyList() {
        List<Point> list = PointList.empty();
        assertThat(list).isEmpty();
    }

    @Test
    void of_withSinglePoint_shouldReturnListOfPoint() {
        List<Point> list = PointList.of(P1);
        assertThat(list).containsExactly(P1);
    }

    @Test
    void of_withTwoPoints_shouldReturnListOfOrderedPoints() {
        List<Point> list = PointList.of(P1, P2);
        assertThat(list).containsExactly(P1, P2);
    }

    @Test
    void of_withThreePoints_shouldReturnListOfOrderedPoints() {
        List<Point> list = PointList.of(P1, P2, P3);
        assertThat(list).containsExactly(P1, P2, P3);
    }

    @Test
    void of_withSinglePointCoordinates_shouldReturnListOfPoint() {
        List<Point> list = PointList.of(P1.getX(), P1.getY());
        assertThat(list).usingRecursiveFieldByFieldElementComparator()
                .containsExactly(P1);
    }

    @Test
    void of_withTwoPointCoordinates_shouldReturnListOfPoints() {
        List<Point> list = PointList.of(P1.getX(), P1.getY(), P2.getX(), P2.getY());
        assertThat(list).usingRecursiveFieldByFieldElementComparator()
                .containsExactly(P1, P2);
    }

    @Test
    void of_withThreePointCoordinates_shouldReturnListOfPoints() {
        List<Point> list = PointList.of(P1.getX(), P1.getY(), P2.getX(), P2.getY(), P3.getX(), P3.getY());
        assertThat(list).usingRecursiveFieldByFieldElementComparator()
                .containsExactly(P1, P2, P3);
    }

    @Test
    void testSize() {
        List<Point> list = PointList.of(P1, P2, P3);
        assertThat(list).hasSize(3);
    }

    @Test
    void copyOfList_shouldReturnNewIdenticalList() {
        List<Point> source = List.of(P1, P2, P3);
        List<Point> copy = PointList.copyOf(source);
        assertThat(copy).containsExactlyElementsOf(source);
    }

    @Test
    void copyOfHashSet_shouldReturnNewList_inAnyOrder() {
        Set<Point> source = newHashSet(P1, P2, P3);
        List<Point> copy = PointList.copyOf(source);

        assertThat(copy).hasSameElementsAs(source)
                        .hasSameSizeAs(source);
        // or even better:
        assertThat(copy).containsExactlyInAnyOrder(P1, P2, P3);
        // or this:
        assertThat(copy).containsExactlyInAnyOrderElementsOf(source);
    }
}
