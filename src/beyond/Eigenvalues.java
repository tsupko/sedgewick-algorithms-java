package beyond;

/******************************************************************************
 *  Compilation:  javac -classpath .:jama.jar Eigenvalues.java
 *  Execution:    java -classpath .:jama.jar Eigenvalues
 *  Dependencies: jama.jar
 *  
 *  Test client for computing eigenvalues and eigenvectors of a real
 *  symmetric matrix A = V D V^T.
 *  
 *       http://math.nist.gov/javanumerics/jama/
 *       http://math.nist.gov/javanumerics/jama/Jama-1.0.1.jar
 *
 ******************************************************************************/

import Jama.EigenvalueDecomposition;
import Jama.Matrix;
import edu.princeton.cs.algs4.StdOut;

public class Eigenvalues {
    public static void main(String[] args) { 
        int N = 5;

        // create a symmetric positive definite matrix
        Matrix A = Matrix.random(N, N);
        A = A.transpose().times(A);

        // compute the spectral decomposition
        EigenvalueDecomposition e = A.eig();
        Matrix V = e.getV();
        Matrix D = e.getD();

        StdOut.print("A =");
        A.print(9, 6);
        StdOut.print("D =");
        D.print(9, 6);
        StdOut.print("V =");
        V.print(9, 6);

        // check that V is orthogonal
        StdOut.print("||V * V^T - I|| = ");
        StdOut.println(V.times(V.transpose()).minus(Matrix.identity(N, N)).normInf());

        // check that A V = D V
        StdOut.print("||AV - DV|| = ");
        StdOut.println(A.times(V).minus(V.times(D)).normInf());
    }

}
