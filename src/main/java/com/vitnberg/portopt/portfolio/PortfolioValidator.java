package com.vitnberg.portopt.portfolio;

public final class PortfolioValidator {

    private static final double EPS = 1e-12;

    private PortfolioValidator() {
    }

    public static void validateFullyInvested(Portfolio p) {
        double sum = 0d;
        for (double wt : p.getWeights()) {
            sum += wt;
        }

        if (Math.abs(sum - 1) > EPS) {
            throw new IllegalArgumentException(String.format("Portfolio weights must sum to 1. Sum = %.2f", sum));
        }
    }

    public static void validateLongOnly(Portfolio p) {
        for (double wt : p.getWeights()) {
            if (wt < -EPS) {
                throw new IllegalArgumentException("Short positions are not allowed");
            }
        }
    }
}
