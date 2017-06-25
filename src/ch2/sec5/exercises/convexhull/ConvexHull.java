package ch2.sec5.exercises.convexhull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

/**
 * @author Alexander Tsupko (tsupko.alexander@yandex.ru)
 *         Copyright (c) 2016. All rights reserved.
 */
public class ConvexHull {
    private static final Deque<Point2D> hull = new ArrayDeque<>();

    public ConvexHull() {

    }

    private static class Point2D implements Comparable<Point2D> {
        private final double x;
        private final double y;

        Point2D(double x, double y) {
            if (Double.isInfinite(x) || Double.isInfinite(y)) {
                throw new IllegalArgumentException("Coordinates must be finite");
            }
            if (Double.isNaN(x) || Double.isNaN(y)) {
                throw new IllegalArgumentException("Coordinates cannot be NaN");
            }
            if (x == 0.0) this.x = 0.0;
            else this.x = x;
            if (y == 0.0) this.y = 0.0;
            else this.y = y;
        }

        @Override
        public int compareTo(Point2D that) {
            if (this.y < that.y) return -1;
            if (this.y > that.y) return +1;
            if (this.x < that.x) return -1;
            if (this.x > that.x) return +1;
            return 0;
        }

        private static int ccw(Point2D a, Point2D b, Point2D c) {
            double area2 = area2(a, b, c);
            if (area2 < 0) return -1;
            else if (area2 > 0) return +1;
            else return 0;
        }

        private static double area2(Point2D a, Point2D b, Point2D c) {
            return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        }
    }

    static {
        Random random = new Random();
        int n = 10;
        while (n-- > 0) {
            hull.add(new Point2D(random.nextDouble(), random.nextDouble()));
        }
    }

    public static void main(String[] args) {

    }
}
