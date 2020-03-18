/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.ArrayList;

public class PointSET {
    private SET<Point2D> pointSet;

    public PointSET() {
        pointSet = new SET<Point2D>();
    }

    public boolean isEmpty() {
        return pointSet.isEmpty();
    }

    public int size() {
        return pointSet.size();
    }

    public void insert(Point2D p) {
        if (p == null) throw new java.lang.IllegalArgumentException("Null argument");
        pointSet.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new java.lang.IllegalArgumentException("Null argument");
        return pointSet.contains(p);
    }

    public void draw() {
        for (Point2D point : pointSet) {
            point.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new java.lang.IllegalArgumentException("Null argument");
        ArrayList<Point2D> arrayPoints = new ArrayList<Point2D>();

        for (Point2D point : pointSet) {
            if (rect.contains(point)) {
                arrayPoints.add(point);
            }
        }

        return arrayPoints;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new java.lang.IllegalArgumentException("Null argument");
        if (pointSet.isEmpty()) return null;

        Point2D nearestPoint = null;
        double nearestDistance = Double.MAX_VALUE;

        for (Point2D point : pointSet) {
            double distance = p.distanceTo(point);
            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestPoint = point;
            }
        }

        return nearestPoint;
    }

    public static void main(String[] args) {

    }
}
