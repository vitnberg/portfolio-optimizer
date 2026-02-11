package com.vitnberg.portopt.optimization.problem;

import com.vitnberg.portopt.portfolio.PortfolioConstraints;
import com.vitnberg.portopt.stats.ReturnMoments;

import java.util.Objects;

public sealed interface PortfolioOptimizationProblem permits MinVarianceProblem, MeanVarianceTradeoffProblem {
    ReturnMoments returnMoments();
    PortfolioConstraints constraints();
}
