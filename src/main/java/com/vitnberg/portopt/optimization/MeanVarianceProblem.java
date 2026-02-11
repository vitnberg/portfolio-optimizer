package com.vitnberg.portopt.optimization;

import com.vitnberg.portopt.portfolio.PortfolioConstraints;
import com.vitnberg.portopt.stats.ReturnMoments;

import java.util.Objects;

public abstract class MeanVarianceProblem {

    private final ReturnMoments returnMoments;
    private final MeanVarianceProblemType problemType;
    private final PortfolioConstraints constraints;

    public MeanVarianceProblem(ReturnMoments returnMoments, MeanVarianceProblemType problemType, PortfolioConstraints constraints) {
        this.returnMoments = Objects.requireNonNull(returnMoments);
        this.problemType = Objects.requireNonNull(problemType);
        this.constraints = Objects.requireNonNull(constraints);
    }

    public ReturnMoments getReturnMoments() {
        return returnMoments;
    }

    public MeanVarianceProblemType getProblemType() {
        return problemType;
    }

    public PortfolioConstraints getConstraints() {
        return constraints;
    }
}
