package com.att.tlv.training.test.exercises.assertions;

import java.util.Collection;
import java.util.List;

import com.att.tlv.training.test.exercises.data.Point;
import com.google.common.collect.ImmutableList;

public abstract class PointList {

    /**
     *  Returns an empty immutable list.
     */
    public static List<Point> empty() {
        return ImmutableList.of();
    }
    
    /**
     * Returns an immutable list containing a single point. 
     *
     * @throws NullPointerException if {@code p} is null
     */
    public static List<Point> of(Point p) {
        return ImmutableList.of(p);
    }

    /**
     * Returns an immutable list containing the given points, in order.
     *
     * @throws NullPointerException if any element is null
     */
    public static List<Point> of(Point p1, Point p2) {
        return ImmutableList.of(p1, p2);
    }
    
    /**
     * Returns an immutable list containing the given points, in order.
     *
     * @throws NullPointerException if any element is null
     */
    public static List<Point> of(Point p1, Point p2, Point p3) {
        return ImmutableList.of(p1, p2, p3);
    }
    
    /**
     * Returns an immutable list containing a single point with the given coordinates
     */    
    public static List<Point> of(int aX, int aY) {
        Point a = new Point(aX, aY);
        return ImmutableList.of(a);
    }    
    
    /**
     * Returns an immutable list containing 2 points with the given coordinates, in order.
     */    
    public static List<Point> of(int aX, int aY, int bX, int bY) {
        Point a = new Point(aX, aY);
        Point b = new Point(bX, bY);
        return ImmutableList.of(a, b);
    }   
    
    /**
     * Returns an immutable list containing 3 points with the given coordinates, in order.
     */    
    public static List<Point> of(int aX, int aY, int bX, int bY, int cX, int cY) {
        Point a = new Point(aX, aY);
        Point b = new Point(bX, bY);
        Point c = new Point(cX, cY);
        return ImmutableList.of(a, b, c);
    }    
    
    /**
     * Returns an immutable list containing the points in the specified collection, in order.
     *
     * @throws NullPointerException if any of {@code elements} is null
     */
    public static List<Point> copyOf(Collection<? extends Point> points) {
        return ImmutableList.copyOf(points);
    }
}
