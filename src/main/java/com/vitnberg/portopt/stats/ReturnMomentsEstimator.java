package com.vitnberg.portopt.stats;

import com.vitnberg.portopt.data.ReturnHistory;
import com.vitnberg.portopt.math.MatrixUtils;

import java.util.Objects;

public final class ReturnMomentsEstimator {

    private ReturnMomentsEstimator() {
    }

    public static ReturnMoments returnMoments(ReturnHistory history) {
        Objects.requireNonNull(history, "history");
        double[][] returns = history.returns();
        MatrixUtils.validateMatrixShape(returns);

        if (returns.length < 2) {
            throw new IllegalArgumentException("Need at least two observations");
        }

        if (returns[0].length != history.universe().size()) {
            throw new IllegalArgumentException("Return row dimension mismatch");
        }

        double[] means = means(returns);
        double[][] covarianceMatrix = covarianceMatrix(returns, means);

        return new ReturnMoments(history.universe(), means, covarianceMatrix, history.frequency());
    }

    private static double[] means(double[][] dailyValues) {
        int assetsNumber = dailyValues[0].length;
        int observationsNumber = dailyValues.length;

        double[] result = new double[assetsNumber];
        for (double[] row : dailyValues) {
            for (int i = 0; i < row.length; i++) {
                result[i] += row[i];
            }
        }

        for (int i = 0; i < assetsNumber; i++) {
            result[i] /= observationsNumber;
        }
        return result;
    }

    private static double[][] covarianceMatrix(double[][] dailyValues, double[] means) {
        int assetNumber = means.length;
        double[][] result = new double[assetNumber][assetNumber];
        for (double[] row : dailyValues) {
            for (int asset1 = 0; asset1 < assetNumber; asset1++) {
                double delta = row[asset1] - means[asset1];
                for (int asset2 = asset1; asset2 < assetNumber; asset2++) {
                    result[asset1][asset2] += delta * (row[asset2] - means[asset2]);
                }
            }
        }

        double denominator = dailyValues.length - 1.0;
        for (int asset1 = 0; asset1 < assetNumber; asset1++) {
            for (int asset2 = asset1; asset2 < assetNumber; asset2++) {
                result[asset1][asset2] /= denominator;
            }
        }

        for (int asset1 = 0; asset1 < assetNumber; asset1++) {
            for (int asset2 = 0; asset2 < asset1; asset2++) {
                result[asset1][asset2] = result[asset2][asset1];
            }
        }
        return result;
    }

}
