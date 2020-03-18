import java.util.ArrayList;

public class Board {
    private final int[][] tiles;
    private final int n;

    public Board(int[][] tiles) {
        if (tiles == null) throw new IllegalArgumentException("null argument");
        this.n = tiles.length;
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }

    }

    public String toString() {
        String nStr = Integer.toString(dimension());
        String boardStr = "\n";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boardStr += String.format("%2d ", tiles[i][j]);
            }
            boardStr += "\n";
        }
        return nStr + boardStr;
    }

    public int dimension() {
        return n;
    }

    public int hamming() {
        int hammingNum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int expectedNum = 1 + j + i * n;
                int actualNum = tiles[i][j];
                if (actualNum != expectedNum) {
                    if (actualNum == 0) continue;
                    hammingNum++;
                }
            }
        }
        return hammingNum;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattanNum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int actualNum = tiles[i][j];
                if (actualNum == 0) continue;

                int expectedRow = (actualNum - 1) / n;
                int expectedColumn = (actualNum - 1) % n;

                int distance = Math.abs(expectedRow - i) + Math.abs(expectedColumn - j);
                manhattanNum += distance;
            }
        }
        return manhattanNum;
    }

    public boolean isGoal() {
        if (hamming() == 0) return true;
        else return false;
    }

    public boolean equals(Object y) {
        if (y == null) return false;
        if (!y.getClass().getName().equals("Board")) return false;

        Board otherBoard = (Board) y;

        if (otherBoard.dimension() != this.dimension()) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (otherBoard.tiles[i][j] != this.tiles[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private int[] findZero() {
        int[] zero = new int[2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    zero[0] = i;
                    zero[1] = j;
                }
            }
        }

        return zero;
    }

    public Iterable<Board> neighbors() {
        int[] zero = findZero();
        int zeroRow = zero[0];
        int zeroColumn = zero[1];

        ArrayList<Board> boardList = new ArrayList<Board>();

        if (zeroRow == 0 && zeroColumn == 0) {
            Board firstBoard = new Board(tiles);
            firstBoard.tiles[zeroRow][zeroColumn] = firstBoard.tiles[zeroRow][zeroColumn + 1];
            firstBoard.tiles[zeroRow][zeroColumn + 1] = 0;
            boardList.add(firstBoard);

            Board secondBoard = new Board(tiles);
            secondBoard.tiles[zeroRow][zeroColumn] = secondBoard.tiles[zeroRow + 1][zeroColumn];
            secondBoard.tiles[zeroRow + 1][zeroColumn] = 0;
            boardList.add(secondBoard);
        }
        else if (zeroRow == (n - 1) && zeroColumn == 0) {
            Board firstBoard = new Board(tiles);
            firstBoard.tiles[zeroRow][zeroColumn] = firstBoard.tiles[zeroRow][zeroColumn + 1];
            firstBoard.tiles[zeroRow][zeroColumn + 1] = 0;
            boardList.add(firstBoard);

            Board secondBoard = new Board(tiles);
            secondBoard.tiles[zeroRow][zeroColumn] = secondBoard.tiles[zeroRow - 1][zeroColumn];
            secondBoard.tiles[zeroRow - 1][zeroColumn] = 0;
            boardList.add(secondBoard);
        }
        else if (zeroRow == 0 && zeroColumn == (n - 1)) {
            Board firstBoard = new Board(tiles);
            firstBoard.tiles[zeroRow][zeroColumn] = firstBoard.tiles[zeroRow][zeroColumn - 1];
            firstBoard.tiles[zeroRow][zeroColumn - 1] = 0;
            boardList.add(firstBoard);

            Board secondBoard = new Board(tiles);
            secondBoard.tiles[zeroRow][zeroColumn] = secondBoard.tiles[zeroRow + 1][zeroColumn];
            secondBoard.tiles[zeroRow + 1][zeroColumn] = 0;
            boardList.add(secondBoard);
        }
        else if (zeroRow == (n - 1) && zeroColumn == (n - 1)) {
            Board firstBoard = new Board(tiles);
            firstBoard.tiles[zeroRow][zeroColumn] = firstBoard.tiles[zeroRow][zeroColumn - 1];
            firstBoard.tiles[zeroRow][zeroColumn - 1] = 0;
            boardList.add(firstBoard);

            Board secondBoard = new Board(tiles);
            secondBoard.tiles[zeroRow][zeroColumn] = secondBoard.tiles[zeroRow - 1][zeroColumn];
            secondBoard.tiles[zeroRow - 1][zeroColumn] = 0;
            boardList.add(secondBoard);
        }
        else if (zeroRow == (n - 1) && zeroColumn == (n - 1)) {
            Board firstBoard = new Board(tiles);
            firstBoard.tiles[zeroRow][zeroColumn] = firstBoard.tiles[zeroRow][zeroColumn - 1];
            firstBoard.tiles[zeroRow][zeroColumn - 1] = 0;
            boardList.add(firstBoard);

            Board secondBoard = new Board(tiles);
            secondBoard.tiles[zeroRow][zeroColumn] = secondBoard.tiles[zeroRow - 1][zeroColumn];
            secondBoard.tiles[zeroRow - 1][zeroColumn] = 0;
            boardList.add(secondBoard);
        }
        else if (zeroRow == 0) {
            Board firstBoard = new Board(tiles);
            firstBoard.tiles[zeroRow][zeroColumn] = firstBoard.tiles[zeroRow][zeroColumn + 1];
            firstBoard.tiles[zeroRow][zeroColumn + 1] = 0;
            boardList.add(firstBoard);

            Board secondBoard = new Board(tiles);
            secondBoard.tiles[zeroRow][zeroColumn] = secondBoard.tiles[zeroRow][zeroColumn - 1];
            secondBoard.tiles[zeroRow][zeroColumn - 1] = 0;
            boardList.add(secondBoard);

            Board thirdBoard = new Board(tiles);
            thirdBoard.tiles[zeroRow][zeroColumn] = thirdBoard.tiles[zeroRow + 1][zeroColumn];
            thirdBoard.tiles[zeroRow + 1][zeroColumn] = 0;
            boardList.add(thirdBoard);
        }
        else if (zeroRow == (n - 1)) {
            Board firstBoard = new Board(tiles);
            firstBoard.tiles[zeroRow][zeroColumn] = firstBoard.tiles[zeroRow][zeroColumn + 1];
            firstBoard.tiles[zeroRow][zeroColumn + 1] = 0;
            boardList.add(firstBoard);

            Board secondBoard = new Board(tiles);
            secondBoard.tiles[zeroRow][zeroColumn] = secondBoard.tiles[zeroRow][zeroColumn - 1];
            secondBoard.tiles[zeroRow][zeroColumn - 1] = 0;
            boardList.add(secondBoard);

            Board thirdBoard = new Board(tiles);
            thirdBoard.tiles[zeroRow][zeroColumn] = thirdBoard.tiles[zeroRow - 1][zeroColumn];
            thirdBoard.tiles[zeroRow - 1][zeroColumn] = 0;
            boardList.add(thirdBoard);
        }
        else if (zeroColumn == 0) {
            Board firstBoard = new Board(tiles);
            firstBoard.tiles[zeroRow][zeroColumn] = firstBoard.tiles[zeroRow][zeroColumn + 1];
            firstBoard.tiles[zeroRow][zeroColumn + 1] = 0;
            boardList.add(firstBoard);

            Board secondBoard = new Board(tiles);
            secondBoard.tiles[zeroRow][zeroColumn] = secondBoard.tiles[zeroRow + 1][zeroColumn];
            secondBoard.tiles[zeroRow + 1][zeroColumn] = 0;
            boardList.add(secondBoard);

            Board thirdBoard = new Board(tiles);
            thirdBoard.tiles[zeroRow][zeroColumn] = thirdBoard.tiles[zeroRow - 1][zeroColumn];
            thirdBoard.tiles[zeroRow - 1][zeroColumn] = 0;
            boardList.add(thirdBoard);
        }
        else if (zeroColumn == (n - 1)) {
            Board firstBoard = new Board(tiles);
            firstBoard.tiles[zeroRow][zeroColumn] = firstBoard.tiles[zeroRow][zeroColumn - 1];
            firstBoard.tiles[zeroRow][zeroColumn - 1] = 0;
            boardList.add(firstBoard);

            Board secondBoard = new Board(tiles);
            secondBoard.tiles[zeroRow][zeroColumn] = secondBoard.tiles[zeroRow + 1][zeroColumn];
            secondBoard.tiles[zeroRow + 1][zeroColumn] = 0;
            boardList.add(secondBoard);

            Board thirdBoard = new Board(tiles);
            thirdBoard.tiles[zeroRow][zeroColumn] = thirdBoard.tiles[zeroRow - 1][zeroColumn];
            thirdBoard.tiles[zeroRow - 1][zeroColumn] = 0;
            boardList.add(thirdBoard);
        }
        else {
            Board firstBoard = new Board(tiles);
            firstBoard.tiles[zeroRow][zeroColumn] = firstBoard.tiles[zeroRow][zeroColumn - 1];
            firstBoard.tiles[zeroRow][zeroColumn - 1] = 0;
            boardList.add(firstBoard);

            Board secondBoard = new Board(tiles);
            secondBoard.tiles[zeroRow][zeroColumn] = secondBoard.tiles[zeroRow][zeroColumn + 1];
            secondBoard.tiles[zeroRow][zeroColumn + 1] = 0;
            boardList.add(secondBoard);

            Board thirdBoard = new Board(tiles);
            thirdBoard.tiles[zeroRow][zeroColumn] = thirdBoard.tiles[zeroRow - 1][zeroColumn];
            thirdBoard.tiles[zeroRow - 1][zeroColumn] = 0;
            boardList.add(thirdBoard);

            Board fourthBoard = new Board(tiles);
            fourthBoard.tiles[zeroRow][zeroColumn] = thirdBoard.tiles[zeroRow + 1][zeroColumn];
            fourthBoard.tiles[zeroRow + 1][zeroColumn] = 0;
            boardList.add(fourthBoard);
        }

        return boardList;
    }

    public Board twin() {
        Board board = new Board(tiles);

        if (tiles[0][0] != 0 && tiles[0][1] != 0) {
            int tmp = board.tiles[0][0];
            board.tiles[0][0] = board.tiles[0][1];
            board.tiles[0][1] = tmp;
        }
        else {
            int tmp = board.tiles[1][0];
            board.tiles[1][0] = board.tiles[1][1];
            board.tiles[1][1] = tmp;
        }

        return board;

    }

    public static void main(String[] args) {
        int[][] tiles = { { 1, 0 }, { 2, 3 } };
        Board board = new Board(tiles);

        System.out.println(board.toString());
        System.out.println("hamming: " + board.hamming());
        System.out.println("manhattan: " + board.manhattan());
        System.out.println("isGoal? " + board.isGoal());

        Board twin = board.twin();
        System.out.println("twinBoard: " + twin.toString());
    }

}
