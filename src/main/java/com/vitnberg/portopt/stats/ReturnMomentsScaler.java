package com.vitnberg.portopt.stats;

import com.vitnberg.portopt.data.DataFrequency;
import com.vitnberg.portopt.math.MatrixUtils;

import java.util.Objects;

public final class ReturnMomentsScaler {

    private ReturnMomentsScaler(){
    }

    public static ReturnMoments scale(ReturnMoments moments, DataFrequency targetFrequency) {
        Objects.requireNonNull(moments);
        Objects.requireNonNull(targetFrequency);

        double scalingFactor = scalingFactor(moments.getFrequency(), targetFrequency);

        double[] scaledMeans = moments.getMeanReturns().clone();
        double[][] scaledCovariances = MatrixUtils.deepCopy(moments.getCovarianceMatrix());

        scaleVectorInPlace(scaledMeans, scalingFactor);
        scaleMatrixInPlace(scaledCovariances, scalingFactor);

        return new ReturnMoments(moments.getUniverse(), scaledMeans, scaledCovariances, targetFrequency);
    }

    private static double scalingFactor(DataFrequency originalFrequency, DataFrequency targetFrequency) {
        // Converts moments measured per 'from' period into moments per 'to' period
        return (double) originalFrequency.periodsPerYear() / targetFrequency.periodsPerYear();
    }


    private static void scaleVectorInPlace(double[] vector, double frequencyMultiplier) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] *= frequencyMultiplier;
        }
    }

    private static void scaleMatrixInPlace(double[][] matrix, double frequencyMultiplier) {
        for (double[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                row[i] *= frequencyMultiplier;
            }
        }
    }
}
