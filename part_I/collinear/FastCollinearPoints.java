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
import java.util.Comparator;

public class FastCollinearPoints {

    private LineSegment[] lineSegments;

    public FastCollinearPoints(
            Point[] points) {
        checkArgs(points);

        Point[] pts = points.clone();
        Point[] sortedPoints = points.clone();
        int n = points.length;

        ArrayList<LineSegment> storedSegments = new ArrayList<LineSegment>();

        for (Point point : pts) {
            sort(sortedPoints, point.slopeOrder());

            int count = 1;
            double tmpSlope = point.slopeTo(sortedPoints[0]);

            int i;
            for (i = 1; i < n; i++) {
                if (point.slopeTo(sortedPoints[i]) == tmpSlope) {
                    count++;
                }
                else {
                    if (count >= 3) {
                        Arrays.sort(sortedPoints, i - count, i);
                        if (point.compareTo(sortedPoints[i - count]) < 0) {
                            storedSegments.add(new LineSegment(point, sortedPoints[i - 1]));
                        }
                    }
                    tmpSlope = point.slopeTo(sortedPoints[i]);
                    count = 1;
                }
            }

            if (count >= 3) {
                Arrays.sort(sortedPoints, i - count, i);
                if (point.compareTo(sortedPoints[i - count]) < 0) {
                    storedSegments.add(new LineSegment(point, sortedPoints[i - 1]));
                }
            }
        }

        lineSegments = new LineSegment[storedSegments.size()];
        storedSegments.toArray(lineSegments);
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments.clone();
    }

    private static void sort(Point[] a, Comparator<Point> comparator) {
        int n = a.length;
        for (int i = 0; i < n; i++)
            for (int j = i; j > 0 && less(comparator, a[j], a[j - 1]); j--) exch(a, j, j - 1);
    }

    private static boolean less(Comparator<Point> c, Point v, Point w) {
        return c.compare(v, w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
