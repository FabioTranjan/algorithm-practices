/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] results;
    private final int t;
    private final double threshold;

    public PercolationStats(int n, int trials) {
        checkArguments(n, trials);

        t = trials;
        results = new double[t];
        threshold = 1.96;

        for (int c = 0; c < t; c++) {
            Percolation perc = new Percolation(n);

            while (!perc.percolates()) {
                int i = StdRandom.uniform(n) + 1;
                int j = StdRandom.uniform(n) + 1;
                if (!perc.isOpen(i, j))
                    perc.open(i, j);
            }

            results[c] = (double) perc.numberOfOpenSites() / (double) (n * n);
        }
    }

    private void checkArguments(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        else if (trials <= 0) {
            throw new IllegalArgumentException("trials must be positive");
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return mean() - (threshold * stddev() / Math.sqrt(t));
    }

    public double confidenceHi() {
        return mean() + (threshold * stddev() / Math.sqrt(t));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats perc = new PercolationStats(n, trials);
    }

}
