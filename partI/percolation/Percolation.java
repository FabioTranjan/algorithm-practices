/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF openQuickFindUF;
    private final WeightedQuickUnionUF fullnessQuickFindUF;

    private final boolean[] openSites;
    private int numberOpenSites;
    private final int totalCols;
    private final int totalSquares;

    private final int virtualTop;
    private final int virtualBottom;

    public Percolation(int n) {
        totalCols = n;
        totalSquares = n * totalCols + 2;
        numberOpenSites = 0;
        openSites = new boolean[totalSquares];

        virtualTop = 0;
        virtualBottom = n * n + 1;

        openSites[virtualTop] = true;
        openSites[virtualBottom] = true;

        openQuickFindUF = new WeightedQuickUnionUF(totalSquares);
        fullnessQuickFindUF = new WeightedQuickUnionUF(totalSquares - 1);

        for (int i = 1; i <= totalCols; i++) {
            openQuickFindUF.union(virtualTop, i);
        }

        for (int i = 1; i <= totalCols; i++) {
            fullnessQuickFindUF.union(virtualTop, i);
        }

        for (int i = virtualBottom - n; i <= n * n; i++) {
            openQuickFindUF.union(virtualBottom, i);
        }
    }

    private void checkParameters(int row, int col) {
        if (row <= 0) {
            throw new IllegalArgumentException("row must be positive");
        }
        else if (col <= 0) {
            throw new IllegalArgumentException("col must be positive");
        }
    }

    private int rowColToPos(int row, int col, String pos) {
        int offsetRow = row - 1;
        int offsetCol = col - 1;

        if (pos.equals("pos")) {
            return 1 + offsetCol + totalCols * offsetRow;
        }
        else if (pos.equals("top")) {
            return 1 + offsetCol + (totalCols * (offsetRow - 1));
        }
        else if (pos.equals("left")) {
            return 1 + (offsetCol - 1) + totalCols * offsetRow;
        }
        else if (pos.equals("bottom")) {
            return 1 + offsetCol + (totalCols * (offsetRow + 1));
        }
        else if (pos.equals("right")) {
            return 1 + (offsetCol + 1) + totalCols * offsetRow;
        }

        return 0;
    }

    public void open(int row, int col) {
        checkParameters(row, col);

        int p = rowColToPos(row, col, "pos");
        int top = rowColToPos(row, col, "top");
        int left = rowColToPos(row, col, "left");
        int bottom = rowColToPos(row, col, "bottom");
        int right = rowColToPos(row, col, "right");

        if (isOpen(row, col)) {
            return;
        }

        openSites[p] = true;
        numberOpenSites++;

        if ((row - 1) > 0 && isOpen(row - 1, col)) {
            openQuickFindUF.union(p, top);
            if ((row - 1) <= totalCols && col <= totalCols)
                fullnessQuickFindUF.union(p, top);
        }

        if ((col - 1) > 0 && isOpen(row, col - 1)) {
            openQuickFindUF.union(p, left);
            if (row <= totalCols && (col - 1) <= totalCols)
                fullnessQuickFindUF.union(p, left);
        }

        if ((row + 1) <= totalCols && isOpen(row + 1, col)) {
            openQuickFindUF.union(p, bottom);
            if ((row + 1) <= totalCols && col <= totalCols)
                fullnessQuickFindUF.union(p, bottom);
        }

        if ((col + 1) <= totalCols && isOpen(row, col + 1)) {
            openQuickFindUF.union(p, right);
            if (row <= totalCols && (col + 1) <= totalCols)
                fullnessQuickFindUF.union(p, right);
        }

    }

    public boolean isOpen(int row, int col) {
        checkParameters(row, col);

        int p = rowColToPos(row, col, "pos");

        if (p < 0 || p >= totalSquares) {
            return false;
        }

        return openSites[p];
    }

    public boolean isFull(int row, int col) {
        checkParameters(row, col);

        int p = rowColToPos(row, col, "pos");
        return isOpen(row, col) && fullnessQuickFindUF.connected(virtualTop, p);
    }

    public int numberOfOpenSites() {
        return numberOpenSites;
    }

    public boolean percolates() {
        return openQuickFindUF.connected(virtualTop, virtualBottom);
    }

}
