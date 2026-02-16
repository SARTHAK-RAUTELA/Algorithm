import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int n;
    private final boolean[] openSites;
    private int openCount;

    private final WeightedQuickUnionUF uf;        // for percolation
    private final WeightedQuickUnionUF ufFull;    // for fullness check

    private final int virtualTop;
    private final int virtualBottom;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be > 0");

        this.n = n;
        openSites = new boolean[n * n];
        openCount = 0;

        uf = new WeightedQuickUnionUF(n * n + 2);
        ufFull = new WeightedQuickUnionUF(n * n + 1);

        virtualTop = n * n;
        virtualBottom = n * n + 1;
    }

    private void validate(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("index out of bounds");
    }

    private int index(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    public void open(int row, int col) {
        validate(row, col);

        int idx = index(row, col);

        if (openSites[idx]) return;

        openSites[idx] = true;
        openCount++;

        // connect to virtual top
        if (row == 1) {
            uf.union(idx, virtualTop);
            ufFull.union(idx, virtualTop);
        }

        // connect to virtual bottom (only in uf)
        if (row == n) {
            uf.union(idx, virtualBottom);
        }

        // connect neighbors
        connectIfOpen(row, col, row - 1, col);
        connectIfOpen(row, col, row + 1, col);
        connectIfOpen(row, col, row, col - 1);
        connectIfOpen(row, col, row, col + 1);
    }

    private void connectIfOpen(int r1, int c1, int r2, int c2) {
        if (r2 < 1 || r2 > n || c2 < 1 || c2 > n) return;

        if (isOpen(r2, c2)) {
            int p = index(r1, c1);
            int q = index(r2, c2);

            uf.union(p, q);
            ufFull.union(p, q);
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return openSites[index(row, col)];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return isOpen(row, col) &&
               ufFull.connected(index(row, col), virtualTop);
    }

    public int numberOfOpenSites() {
        return openCount;
    }

    public boolean percolates() {
        return uf.connected(virtualTop, virtualBottom);
    }
}
