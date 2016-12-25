package beyond;

/******************************************************************************
 *  Compilation:  javac -classpath .:jama.jar SVD.java
 *  Execution:    java  -classpath .:jama.jar SVD
 *  Dependencies: jama.jar
 *  
 *  Test client for computing singular values of a matrix.
 *  
 *       http://math.nist.gov/javanumerics/jama/
 *       http://math.nist.gov/javanumerics/jama/Jama-1.0.1.jar
 *
 ******************************************************************************/

import Jama.Matrix;
import Jama.SingularValueDecomposition;
import edu.princeton.cs.algs4.StdOut;

public class SVD {
    public static void main(String[] args) { 

        // create M-by-N matrix that doesn't have full rank
        int M = 8, N = 5;
        Matrix B = Matrix.random(5, 3);
        Matrix A = Matrix.random(M, N).times(B).times(B.transpose());
        StdOut.print("A = ");
        A.print(9, 6);

        // compute the singular vallue decomposition
        StdOut.println("A = U S V^T");
        StdOut.println();
        SingularValueDecomposition s = A.svd();
        StdOut.print("U = ");
        Matrix U = s.getU();
        U.print(9, 6);
        StdOut.print("Sigma = ");
        Matrix S = s.getS();
        S.print(9, 6);
        StdOut.print("V = ");
        Matrix V = s.getV();
        V.print(9, 6);
        StdOut.println("rank = " + s.rank());
        StdOut.println("condition number = " + s.cond());
        StdOut.println("2-norm = " + s.norm2());

        // print out singular values
        StdOut.print("singular values = ");
        Matrix svalues = new Matrix(s.getSingularValues(), 1);
        svalues.print(9, 6);
    }

}
