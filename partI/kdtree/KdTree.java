/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree {
    private Node root;
    private int size;

    private static class Node {
        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rb;
    }

    public KdTree() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (p == null) throw new java.lang.IllegalArgumentException("Null argument");

        if (isEmpty()) {
            root = new Node();
            root.p = p;
            root.rect = new RectHV(0.0, 0.0, 1.0, 1.0);
            size += 1;

            return;
        }

        if (contains(p)) return;

        Node ref = root;
        boolean red = true;
        boolean finish = false;

        while (!finish) {
            if (red) {
                if (p.x() < ref.p.x()) {
                    if (ref.lb != null) {
                        ref = ref.lb;
                    }
                    else {
                        Node node = new Node();
                        node.p = p;
                        node.rect = new RectHV(ref.rect.xmin(), ref.rect.ymin(), ref.p.x(),
                                               ref.rect.ymax());
                        size += 1;
                        ref.lb = node;
                        finish = true;
                    }
                }
                else {
                    if (ref.rb != null) {
                        ref = ref.rb;
                    }
                    else {
                        Node node = new Node();
                        node.p = p;
                        node.rect = new RectHV(ref.p.x(), ref.rect.ymin(), ref.rect.xmax(),
                                               ref.rect.ymax());
                        size += 1;
                        ref.rb = node;
                        finish = true;
                    }
                }
            }
            else {
                if (p.y() < ref.p.y()) {
                    if (ref.lb != null) {
                        ref = ref.lb;
                    }
                    else {
                        Node node = new Node();
                        node.p = p;
                        node.rect = new RectHV(ref.rect.xmin(), ref.rect.ymin(), ref.rect.xmax(),
                                               ref.p.y());
                        size += 1;
                        ref.lb = node;
                        finish = true;
                    }
                }
                else {
                    if (ref.rb != null) {
                        ref = ref.rb;
                    }
                    else {
                        Node node = new Node();
                        node.p = p;
                        node.rect = new RectHV(ref.rect.xmin(), ref.p.y(), ref.rect.xmax(),
                                               ref.rect.ymax());
                        size += 1;
                        ref.rb = node;
                        finish = true;
                    }
                }
            }

            red = !red;
        }

    }

    public boolean contains(Point2D p) {
        if (p == null) throw new java.lang.IllegalArgumentException("Null argument");

        if (isEmpty()) return false;

        if (root.p.equals(p)) return true;

        Node ref = root;
        boolean red = true;

        while (true) {
            if (red) {
                if (p.x() < ref.p.x()) {
                    if (ref.lb != null) {
                        ref = ref.lb;
                        if (ref.p.equals(p)) return true;
                    }
                    else return false;
                }
                else {
                    if (ref.rb != null) {
                        ref = ref.rb;
                        if (ref.p.equals(p)) return true;
                    }
                    else return false;
                }
            }
            else {
                if (p.y() < ref.p.y()) {
                    if (ref.lb != null) {
                        ref = ref.lb;
                        if (ref.p.equals(p)) return true;
                    }
                    else return false;
                }
                else {
                    if (ref.rb != null) {
                        ref = ref.rb;
                        if (ref.p.equals(p)) return true;
                    }
                    else return false;
                }
            }

            red = !red;
        }

    }

    public void draw() {
        if (isEmpty()) return;
        drawRecusive(root, true);
    }

    private void drawRecusive(Node node, boolean red) {
        if (red) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
            StdDraw.setPenRadius(0.01);
            StdDraw.setPenColor(StdDraw.BLACK);
            node.p.draw();
            StdDraw.setPenRadius();
        }
        else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
            StdDraw.setPenRadius(0.01);
            StdDraw.setPenColor(StdDraw.BLACK);
            node.p.draw();
            StdDraw.setPenRadius();
        }
        if (node.lb != null) {
            drawRecusive(node.lb, !red);
        }
        if (node.rb != null) {
            drawRecusive(node.rb, !red);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new java.lang.IllegalArgumentException("Null argument");

        if (isEmpty()) return null;

        ArrayList<Point2D> points = new ArrayList<Point2D>();
        searchPoint(root, rect, points);

        return points;
    }

    private void searchPoint(Node node, RectHV rect, ArrayList<Point2D> points) {
        if (rect.contains(node.p)) points.add(node.p);

        if (node.lb != null) {
            if (node.lb.rect.intersects(rect)) {
                searchPoint(node.lb, rect, points);
            }
        }

        if (node.rb != null) {
            if (node.rb.rect.intersects(rect)) {
                searchPoint(node.rb, rect, points);
            }
        }
    }

    private class Closest {
        Point2D point;
        double distance;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new java.lang.IllegalArgumentException("Null argument");

        if (isEmpty()) return null;

        Closest closest = new Closest();
        closest.point = root.p;
        closest.distance = root.p.distanceTo(p);

        searchClosest(root, p, closest);

        return closest.point;
    }

    private void searchClosest(Node node, Point2D p, Closest closest) {
        if (node.lb != null && node.rb != null) {
            if ((node.lb.rect.distanceTo(p)) < (node.rb.rect.distanceTo(p))) {
                searchClosest(node.lb, p, closest);
            }
            else {
                searchClosest(node.rb, p, closest);
            }
        }
        else if (node.lb != null) {
            searchClosest(node.lb, p, closest);
        }
        else if (node.rb != null) {
            searchClosest(node.rb, p, closest);
        }

        double newDistance = node.p.distanceTo(p);
        if (newDistance < closest.distance) {
            closest.point = node.p;
            closest.distance = newDistance;
        }

    }

    public static void main(String[] args) {
        Point2D p1 = new Point2D(0.7, 0.2);
        Point2D p2 = new Point2D(0.5, 0.4);
        Point2D p3 = new Point2D(0.2, 0.3);
        Point2D p4 = new Point2D(0.4, 0.7);
        Point2D p5 = new Point2D(0.9, 0.6);

        KdTree tree = new KdTree();
        tree.insert(p1);
        tree.insert(p2);
        tree.insert(p3);
        tree.insert(p4);
        tree.insert(p5);

        tree.draw();

        Point2D point = new Point2D(0.0, 1.0);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        point.draw();
        System.out.println(tree.nearest(point));
    }
}
