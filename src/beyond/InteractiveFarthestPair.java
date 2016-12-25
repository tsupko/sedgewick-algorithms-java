package beyond;

import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

/******************************************************************************
 *  Compilation:  javac InteractiveFarthestPair.java
 *  Execution:    java InteractiveFarthestPair
 *  Dependencies: FarthestPair.java Point2D.java StdDraw.java SET.java
 *
 *  Interactive farthest pair visualization.
 ******************************************************************************/

public class InteractiveFarthestPair {

    public static void main(String[] args) {
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 10000);
        StdDraw.setYscale(0, 10000);
//        StdDraw.enableDoubleBuffering();

        SET<Point2D> set = new SET<>();

        while (true) {
            if (StdDraw.mousePressed()) {
                // mouse pressed so add point to list of points
                int x = (int) (Math.round(StdDraw.mouseX()));
                int y = (int) (Math.round(StdDraw.mouseY()));
                set.add(new Point2D(x, y));

                // extract array of points
                int N = set.size();
                Point2D[] points = new Point2D[N];
                int n = 0;
                for (Point2D p : set) {
                    points[n++] = p;
                }

                // compute farthest pair
//                FarthestPair      farthest = new FarthestPair(points);
//                FarthestPairBrute brute    = new FarthestPairBrute(points);


                StdDraw.clear();

                // draw the points in black
                StdDraw.setPenRadius(.01);
                StdDraw.setPenColor(StdDraw.BLACK);
                for (int i = 0; i < N; i++)
                    points[i].draw();

                // draw the farthest pair in red
                StdDraw.setPenColor(StdDraw.RED);
//                farthest.either().draw();
//                farthest.other().draw();

                StdDraw.setPenRadius();
                StdDraw.setPenColor(StdDraw.BLUE);
//                brute.either().drawTo(brute.other());
            }
            StdDraw.show();
//            StdDraw.pause(20);
        }

    }

}

