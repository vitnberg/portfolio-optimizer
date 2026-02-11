package com.vitnberg.portopt.optimization.problem;

import com.vitnberg.portopt.portfolio.PortfolioConstraints;
import com.vitnberg.portopt.stats.ReturnMoments;

public final class MinVarianceProblem implements PortfolioOptimizationProblem {

    private final ReturnMoments returnMoments;
    private final PortfolioConstraints constraints;

    public MinVarianceProblem(ReturnMoments returnMoments, PortfolioConstraints constraints) {
        this.returnMoments = returnMoments;
        this.constraints = constraints;
    }

    @Override
    public ReturnMoments returnMoments() {
        return returnMoments;
    }

    @Override
    public PortfolioConstraints constraints() {
        return constraints;
    }
}
