package com.vitnberg.portopt.optimization.problem;

import com.vitnberg.portopt.math.Constants;
import com.vitnberg.portopt.portfolio.PortfolioConstraints;
import com.vitnberg.portopt.stats.ReturnMoments;

public final class MeanVarianceTradeoffProblem implements PortfolioOptimizationProblem {

    private final ReturnMoments returnMoments;
    private final PortfolioConstraints constraints;
    private final double riskAversion;

    public MeanVarianceTradeoffProblem(ReturnMoments returnMoments, PortfolioConstraints constraints, double riskAversion) {
        this.returnMoments = returnMoments;
        this.constraints = constraints;

        verifyRiskAversion(riskAversion);
        this.riskAversion = riskAversion;
    }

    @Override
    public ReturnMoments returnMoments() {
        return returnMoments;
    }

    @Override
    public PortfolioConstraints constraints() {
        return constraints;
    }

    public double riskAversion() {
        return this.riskAversion;
    }

    private static void verifyRiskAversion(double riskAversion) {
        if (riskAversion < Constants.EPS) {
            throw new IllegalArgumentException("Risk aversion must be positive");
        }
    }

}
