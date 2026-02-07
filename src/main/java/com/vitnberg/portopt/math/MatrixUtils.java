package com.vitnberg.portopt.math;

public final class MatrixUtils {

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

    public static double[][] deepCopy(double[][] matrix) {
        double[][] copy = new double[matrix.length][];

        for (int i = 0; i < matrix.length; i++) {
            copy[i] = matrix[i].clone();
        }
        return copy;
    }

}
