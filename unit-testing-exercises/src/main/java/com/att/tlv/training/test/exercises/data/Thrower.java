package com.att.tlv.training.test.exercises.data;

public class Thrower {
    
    /**
     * Returns the sum of its arguments,
     * throwing an exception if the result overflows an {@code int}.
     *
     * @param x the first value
     * @param y the second value
     * @return the result
     * @throws ArithmeticException if the result overflows an int
     */    
    public static int addExact(int x, int y) {
        return Math.addExact(x, y);
    }
    
    /**
     * Throws an {@code IllegalArgumentException} wrapped in a {@code RuntimeException} with a specific message.
     */
    public static void throwNestedException() {
        throw new RuntimeException("Argument is not valid!", new IllegalArgumentException());
    }
}