/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private ArrayList<LineSegment> lineSegments;

    public BruteCollinearPoints(Point[] points) {
        checkArgs(points);

        lineSegments = new ArrayList<LineSegment>();

        Point[] pts = points.clone();

        int n = pts.length;
        Arrays.sort(pts);

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int w = k + 1; w < n; w++) {
                        double firstSlope = pts[i].slopeTo(pts[j]);
                        double secondSlope = pts[i].slopeTo(pts[k]);
                        double thirdSlope = pts[i].slopeTo(pts[w]);

                        if (firstSlope == secondSlope &&
                                firstSlope == thirdSlope) {
                            LineSegment lineSegment = new LineSegment(pts[i], pts[w]);
                            lineSegments.add(lineSegment);
                        }
                    }
                }
            }
        }

    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] lineSegs = new LineSegment[lineSegments.size()];
        return lineSegments.toArray(lineSegs);
    }

    private void checkArgs(Point[] points) {
        if (points == null) throw new java.lang.IllegalArgumentException();

        if (points.length < 4) throw new java.lang.IllegalArgumentException();

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new java.lang.IllegalArgumentException();
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    throw new java.lang.IllegalArgumentException();
            }
        }
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
