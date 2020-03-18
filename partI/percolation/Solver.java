import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private int moves;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        moves = 0;

        int priority = moves + initial.manhattan();
        MinPQ<Board> minPQ = new MinPQ<Board>();

        minPQ.insert(initial);

        while (priority != 0) {
            Iterable<Board> neightbords = initial.neighbors();

            for (Board neightbord : neightbords) {
                minPQ.insert(neightbord);
            }

            Board minBoard = minPQ.delMin();

            moves++;
            priority = moves + minBoard.manhattan();
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return true;
    }

    // min number of moves to solve initial board
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        return null;
    }

    // test client (see below)
    public static void main(String[] args) {
        int[][] tiles = { { 0, 1, 3 }, { 4, 2, 5 }, { 7, 8, 6 } };
        Board board = new Board(tiles);

        Solver solver = new Solver(board);
        solver.moves();
    }

}
