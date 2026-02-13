package com.vitnberg.portopt.optimization.formulation;

import java.util.Objects;

public final class LinearConstraint {
    private final double[] coefficients;
    private final Range range;

    public LinearConstraint(double[] coefficients, Range range) {
        Objects.requireNonNull(coefficients, "coefficients");
        Objects.requireNonNull(range, "range");

        this.range = range;

        validateCoefficients(coefficients);
        this.coefficients = coefficients.clone();
    }

    public double[] coefficients() {
        return this.coefficients.clone();
    }

    public Range range() {
        return this.range;
    }

    private static void validateCoefficients(double[] coefficients) {
        if (coefficients.length == 0) {
            throw new IllegalArgumentException("coefficients must be not empty");
        }
    }

}
