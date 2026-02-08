package com.vitnberg.portopt.portfolio;

import com.vitnberg.portopt.metrics.PortfolioAnalytics;
import com.vitnberg.portopt.stats.ReturnMoments;

import java.util.Objects;

public final class MeanVarianceObjective {

    private final PortfolioAnalytics portfolioAnalytics;

    public MeanVarianceObjective(ReturnMoments returnMoments) {
        portfolioAnalytics = new PortfolioAnalytics(Objects.requireNonNull(returnMoments));
    }

    public double objectiveValue(double[] weights, double riskAversion) {
        return 0.5 * portfolioAnalytics.variance(weights) - riskAversion * portfolioAnalytics.expectedReturn(weights);
    }
}
