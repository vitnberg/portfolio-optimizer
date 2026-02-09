package com.vitnberg.portopt.math;

public final class MatrixUtils {

    private MatrixUtils() {
    }

    public static void validateSquareMatrix(double[][] matrix, int size) {
        if (matrix.length != size) {
            throw new IllegalArgumentException("Matrix dimension mismatch");
        }
        for (int i = 0; i < size; i++) {
            if (matrix[i].length != size) {
                throw new IllegalArgumentException("Matrix should be square");
            }
        }
    }

    public static void validateMatrixShape(double[][] matrix) {
        if (matrix.length == 0) {
            throw new IllegalArgumentException("Matrix contains no rows");
        }

        if (matrix[0].length == 0) {
            throw new IllegalArgumentException("Matrix contains no cols");
        }

        int cols = matrix[0].length;
        for (double[] row : matrix) {
            if (row.length != cols) {
                throw new IllegalArgumentException("Two-dimensional array is not a matrix");
            }
        }

    }

    public static double[][] deepCopy(double[][] matrix) {
        double[][] copy = new double[matrix.length][];

        for (int i = 0; i < matrix.length; i++) {
            copy[i] = matrix[i].clone();
        }
        return copy;
    }

}
