package com.vitnberg.portopt.metrics;

import com.vitnberg.portopt.portfolio.AssetUniverse;
import com.vitnberg.portopt.portfolio.Portfolio;
import com.vitnberg.portopt.stats.ReturnMoments;

public final class PortfolioAnalytics {

    private final AssetUniverse universe;
    private final double[] meanReturns;
    private final double[][] covarianceMatrix;

    public PortfolioAnalytics(ReturnMoments returnMoments) {
        universe = returnMoments.getUniverse();
        meanReturns = returnMoments.getMeanReturns();
        covarianceMatrix = returnMoments.getCovarianceMatrix();
    }

    public double expectedReturn(Portfolio p) {
        requireSameUniverse(p);
        return weightedSum(p.getWeights(), meanReturns);
    }

    public double variance(Portfolio p) {
        requireSameUniverse(p);
        return quadraticForm(covarianceMatrix, p.getWeights());
    }

    public double volatility(Portfolio p) {
        return Math.sqrt(variance(p));
    }

    private void requireSameUniverse(Portfolio p) {
        if (!p.getUniverse().equals(universe)) {
            throw new IllegalArgumentException("Portfolio universe does not match ReturnMoments universe");
        }
    }

    private double weightedSum(double[] weights, double[] values) {
        if (weights.length != values.length) {
            throw new IllegalArgumentException("Dimension mismatch");
        }
        double sum = 0d;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * values[i];
        }
        return sum;
    }

    // TODO: faster implementation for symmetric matrices
    private double quadraticForm(double[][] coefficients, double[] values) {
        if (coefficients.length != values.length) {
            throw new IllegalArgumentException("Dimension mismatch");

        }
        int size = coefficients.length;
        double result = 0d;
        for (int i = 0; i < size; i++) {
            result += values[i] * weightedSum(values, coefficients[i]);
        }
        return result;
    }

}
