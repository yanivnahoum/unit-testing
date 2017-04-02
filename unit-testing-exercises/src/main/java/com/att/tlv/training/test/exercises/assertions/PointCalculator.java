package com.att.tlv.training.test.exercises.assertions;

import com.att.tlv.training.test.exercises.data.Point;

public class PointCalculator {
    
    public Point add(Point a, Point b) {
        if (isOrigin(a)) {
            return b;
        }
        
        if (isOrigin(b)) {
            return a;
        }
        
        int x = a.getX() + b.getX();
        int y = a.getY() + b.getY();
        return new Point(x, y);
    }
    
    private boolean isOrigin(Point p) {
        return p.getX() == 0 && p.getY() == 0;
    }
}
