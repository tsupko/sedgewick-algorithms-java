package beyond;

/******************************************************************************
 *  Compilation:  javac GaussianEliminationLite.java
 *  Execution:    java GaussianEliminationLite
 *  Dependencies: StdOut.java
 * 
 *  Gaussian elimination with partial pivoting.
 *
 *  % java GaussianEliminationLite
 *  -1.0
 *  2.0
 *  2.0
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdOut;

/**
 *  The {@code GaussianEliminationLite} class provides a static method to
 *  solve a linear system of equations <em>Ax</em> = <em>b</em>,
 *  where <em>A</em> is a nonsingular <em>N</em>-by-<em>N</em> matrix
 *  and <em>b</em> is a length <em>N</em> vector.
 *  <p>
 *  This is a bare-bones implementation that uses Gaussian elimination
 *  with partial pivoting. See {@link GaussianElimination} for a version
 *  that also handles singular systems (in which there are either
 *  infinitely many solutions or no solutions).
 *  See {@link GaussJordanElimination} for an alternate implementation
 *  that uses Gauss-Jordan elimination.
 *  For an industrial-strength numerical linear algebra library,
 *  see <a href = "http://math.nist.gov/javanumerics/jama/">JAMA</a>. 
 *  <p>
 *  For additional documentation, see
 *  <a href="http://algs4.cs.princeton.edu/99scientific">Section 9.9</a>
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class GaussianEliminationLite {
    private static final double EPSILON = 1e-10;

    // Do not instantiate.
    private GaussianEliminationLite() { }

    /**
     * Returns the unique solution <em>x</em> to the specified linear system of
     * equation <em>Ax</em> = <em>b</em>, where <em>A</em> is a nonsingular matrix.
     * Warning: this implementation mutates the arguments <em>A</em> and <em>b</em>.
     *
     * @param  A the nonsingular <em>N</em>-by-<em>N</em> constraint matrix
     * @param  b the length <em>N</em> right-hand-side vector
     * @return the unique solution <em>x</em> to the linear system of equations
     *         <em>Ax</em> = <em>b</em>
     * @throws ArithmeticException if the matrix is singular or nearly singular
     */
    public static double[] lsolve(double[][] A, double[] b) {
        int N  = b.length;

        for (int p = 0; p < N; p++) {

            // find pivot row and swap
            int max = p;
            for (int i = p + 1; i < N; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }

            swap(A, p, max);
            swap(b, p, max);

            // singular or nearly singular
            if (Math.abs(A[p][p]) <= EPSILON) {
                throw new ArithmeticException("Matrix is singular or nearly singular");
            }

            // pivot within A and b
            for (int i = p + 1; i < N; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < N; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        // back substitution
        double[] x = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }

    // swap entries a[i] and a[j] in array a[]
    private static void swap(double[] a, int i, int j) {
        double temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // swap rows A[i][] and A[j][] in 2D array A[][]
    private static void swap(double[][] A, int i, int j) {
        double[] temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    /**
     * Unit tests the {@code GaussianElimination} class.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int N = 3;

        double[][] A = {
            { 0, 1,  1 },
            { 2, 4, -2 },
            { 0, 3, 15 }
        };
        double[] b = { 4, 2, 36 };

        double[] x = lsolve(A, b);

        // print results
        for (int i = 0; i < N; i++) {
            StdOut.println(x[i]);
        }

    }

}
