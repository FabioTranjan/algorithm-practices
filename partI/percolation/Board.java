import java.util.ArrayList;

public class Board {
    private final int[][] tiles;
    private final int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null) throw new IllegalArgumentException("null argument");
        if (tiles.length <= 2 || tiles.length >= 128)
            throw new IllegalArgumentException("out of limits argument");
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
            boardStr += " ";
            for (int j = 0; j < n; j++) {
                boardStr += tiles[i][j] + " ";
            }
            boardStr += "\n";
        }
        return nStr + boardStr;
    }

    public int dimension() {
        return n;
    }

    // number of tiles out of place
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
                int expectedNum = 1 + j + i * n;
                int actualNum = tiles[i][j];
                if (actualNum != expectedNum) {
                    if (actualNum == 0) continue;
                    int actualRow = (actualNum - 1) / 3;
                    int actualColumn = (actualNum - 1) % 3;
                    int distance = Math.abs(actualRow - i) + Math.abs(actualColumn - j);
                    manhattanNum += distance;
                }
            }
        }
        return manhattanNum;
    }

    public boolean isGoal() {
        if (hamming() == 0) return true;
        else return false;
    }

    public boolean equals(Object y) {
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

    public Iterable<Board> neighbors() {
        int zeroRow = 0;
        int zeroColumn = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    zeroRow = i;
                    zeroColumn = j;
                }
            }
        }

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

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        return null;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] tiles = { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };
        Board board = new Board(tiles);

        System.out.println(board.toString());
        System.out.println("hamming: " + board.hamming());
        System.out.println("manhattan: " + board.manhattan());
        System.out.println("isGoal? " + board.isGoal());

        int[][] copyTiles = { { 8, 1, 3 }, { 4, 6, 2 }, { 7, 0, 5 } };
        Board copyBoard = new Board(copyTiles);

        for (Board otherBoard : board.neighbors()) {
            System.out.println("isEqual? " + (otherBoard.equals(copyBoard)));
        }
    }

}
