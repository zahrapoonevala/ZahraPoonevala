package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF full;
    private WeightedQuickUnionUF percolation;
    private boolean [][] gridPercolation; // true = open, false = blocked
    private int openSites;
    private int size;
    private int virtualTop;
    private int virtualBottom;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        this.virtualTop = N * N;
        this.virtualBottom = N * N + 1;

        this.full = new WeightedQuickUnionUF(N * N + 1); //includes virtual top only to check if full
        this.percolation = new WeightedQuickUnionUF(N * N + 2); //includes both virtuals

        this.openSites = 0;
        this.size = N;
        this.gridPercolation = new boolean[N][N];

    }
    private int xyTo1dHelper(int r, int c) {
        return ((r * size) + c);
    }


    public void open(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException();
        }

        int tempIndex = xyTo1dHelper(row, col);
        if (!gridPercolation[row][col]) {
            gridPercolation[row][col] = true;
            openSites += 1;
        }

        if (row == 0) {
            percolation.union(tempIndex, virtualTop);
            full.union(tempIndex, virtualTop);
        }

        if (row == size - 1) {
            percolation.union(tempIndex, virtualBottom);
        }

        if (isOpen(row - 1, col)) { //checking neighbor above
            int aboveIndex = xyTo1dHelper(row - 1, col);
            openHelper(tempIndex, aboveIndex);
        }

        if (isOpen(row + 1, col)) { //checking neighbor below
            int belowIndex = xyTo1dHelper(row + 1, col);
            openHelper(tempIndex, belowIndex);
        }

        if (isOpen(row, col - 1)) { //checking neighbor left
            int leftIndex = xyTo1dHelper(row, col - 1);
            openHelper(tempIndex, leftIndex);
        }

        if (isOpen(row, col + 1)) { //checking neighbor right
            int rightIndex = xyTo1dHelper(row, col + 1);
            openHelper(tempIndex, rightIndex);
        }

    }

    private void openHelper(int generalIndex, int currentIndex) {
        percolation.union(generalIndex, currentIndex);
        full.union(generalIndex, currentIndex);
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return false;
        }

        return gridPercolation[row][col];

    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException();
        }

        int tempIndex = xyTo1dHelper(row, col);
        if (full.connected(tempIndex, virtualTop) && percolation.connected(tempIndex, virtualTop)) {
            return true;
        }
        return false;
    }

    public int numberOfOpenSites(){
        return openSites;
    }

    public boolean percolates(){
        if (percolation.connected(virtualTop, virtualBottom)){
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        Percolation p = new Percolation(5);
        p.open(0,0);
        p.isOpen(0,0);

    }

}
