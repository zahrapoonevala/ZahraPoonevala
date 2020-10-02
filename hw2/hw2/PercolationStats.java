package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int count;
    private int randomRow;
    private int randomCol;
    private double [] results;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        count = T;
        results = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation temp = pf.make(N);
            while (!temp.percolates()) {
                randomRow = StdRandom.uniform(N);
                randomCol = StdRandom.uniform(N);
                temp.open(randomRow, randomCol);
            }
            double total = (double) temp.numberOfOpenSites();
            results[i] = total / (N * N);

        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLow() {
        double numerator = 1.96 * stddev();
        double denominator = Math.sqrt(count);
        double answer = mean() - (numerator / denominator);
        return answer;
    }

    public double confidenceHigh() {
        double numerator = 1.96 * stddev();
        double denominator = Math.sqrt(count);
        double answer = mean() + (numerator / denominator);
        return answer;
    }

}
