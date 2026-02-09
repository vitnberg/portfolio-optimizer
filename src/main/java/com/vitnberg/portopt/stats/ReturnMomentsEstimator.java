package com.vitnberg.portopt.stats;

import com.vitnberg.portopt.math.MatrixUtils;
import com.vitnberg.portopt.portfolio.AssetUniverse;

import java.util.Objects;

public final class ReturnMomentsEstimator {

    private ReturnMomentsEstimator() {
    }

    public static ReturnMoments returnMoments(AssetUniverse universe, double[][] dailyValues) {
        Objects.requireNonNull(universe, "universe");
        MatrixUtils.validateMatrixShape(dailyValues);

        if (dailyValues.length < 2) {
            throw new IllegalArgumentException("Need at least two observations");
        }

        if (dailyValues[0].length != universe.size()) {
            throw new IllegalArgumentException("Return row dimension mismatch");
        }

        double[] means = means(dailyValues);
        return new ReturnMoments(universe, means, covarianceMatrix(dailyValues, means));
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
