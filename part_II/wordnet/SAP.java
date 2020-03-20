import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class SAP {
    private Digraph G;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException();
        this.G = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        checkVertices(v, w);

        DeluxeBFS bfs_v = new DeluxeBFS(G, v);
        DeluxeBFS bfs_w = new DeluxeBFS(G, w);

        return length(bfs_v, bfs_w);
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        checkVertices(v, w);

        DeluxeBFS bfs_v = new DeluxeBFS(G, v);
        DeluxeBFS bfs_w = new DeluxeBFS(G, w);

        return ancestor(bfs_v, bfs_w);
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        checkVertex(v);
        checkVertex(w);

        DeluxeBFS bfs_v = new DeluxeBFS(G, v);
        DeluxeBFS bfs_w = new DeluxeBFS(G, w);

        return length(bfs_v, bfs_w);
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        checkVertex(v);
        checkVertex(w);

        DeluxeBFS bfs_v = new DeluxeBFS(G, v);
        DeluxeBFS bfs_w = new DeluxeBFS(G, w);

        return ancestor(bfs_v, bfs_w);
    }

    private int length(DeluxeBFS bfs_v, DeluxeBFS bfs_w) {
        return minorAncestor(bfs_v, bfs_w).getFirst();
    }

    private int ancestor(DeluxeBFS bfs_v, DeluxeBFS bfs_w) {
        return minorAncestor(bfs_v, bfs_w).getSecond();
    }

    private Pair<Integer, Integer> minorAncestor(DeluxeBFS bfs_v, DeluxeBFS bfs_w) {
        Pair<Integer, Integer> calc_v = calcAncestor(bfs_v, bfs_w);
        Pair<Integer, Integer> calc_w = calcAncestor(bfs_w, bfs_v);

        if (calc_v.getFirst() >= calc_w.getFirst()) return calc_w;
        else return calc_v;
    }

    private Pair<Integer, Integer> calcAncestor(DeluxeBFS bfs_v, DeluxeBFS bfs_w) {
        Iterable<Integer> ancestors = bfs_v.ancestors();
        Iterator<Integer> it = ancestors.iterator();

        while (it.hasNext()) {
            int anc = it.next();
            if (bfs_w.hasPathTo(anc)) {
                int dist = bfs_v.distTo(anc) + bfs_w.distTo(anc);
                return new Pair<Integer, Integer>(dist, anc);
            }
        }

        return new Pair<Integer, Integer>(-1, -1);
    }

    private void checkVertices(int v, int w) {
        if (v < 0 || w < 0 || v >= G.V() || w >= G.V())
            throw new IllegalArgumentException();
    }

    private void checkVertex(Iterable<Integer> vertex) {
        Iterator<Integer> it = vertex.iterator();

        while (it.hasNext()) {
            int v = it.next();
            if (v < 0 || v >= G.V())
                throw new IllegalArgumentException();
        }
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        int length = sap.length(1, 3);
        int ancestor = sap.ancestor(1, 3);
        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
    }
}
