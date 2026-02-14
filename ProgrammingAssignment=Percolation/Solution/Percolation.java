//It checks whether water can flow from the top of a grid to the bottom through open cells.

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class percolation{
    private final int n;
    private final boolean[] openSites;
    private int openCount;
    private final WeightedQuickUnionUF uf;
    private final WeightedQickUnioUF ufFull;

    private final int virtualTop;
    private final int virtualBottom;
    
}