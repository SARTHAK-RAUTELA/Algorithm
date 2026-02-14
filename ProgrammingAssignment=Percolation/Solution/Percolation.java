//It checks whether water can flow from the top of a grid to the bottom through open cells.

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class percolation{
    private final int n;
    private final boolean[] openSites;
    private int openCount;
    pprivate final WeighhtedQuickUionUF uf;
    public percolation(int n){
        if (n <= 0) {
            throw new IllegalArgumentException("Grid size must be greater than 0");
        }
        this.n = n;
        this.openSites = new boolean[n * n];
        this.openCount = 0;
        this.uf = new WeightedQuickUnionUF(n * n + 2); // +2 for virtual top and bottom
    }
}