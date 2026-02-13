package com.vitnberg.portopt.optimization.formulation;

import java.util.Optional;

public final class Range {
    private final Double lowerBound;
    private final Double upperBound;

    private Range(Double lowerBound, Double upperBound) {
        validateBounds(lowerBound, upperBound);
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public Optional<Double> lowerBound() {
        return Optional.ofNullable(lowerBound);
    }

    public Optional<Double> upperBound() {
        return Optional.ofNullable(upperBound);
    }

    public static Range equal(double value) {
        return new Range(value, value);
    }

    public static Range atMost(double upperBound) {
        return new Range(null, upperBound);
    }

    public static Range atLeast(double lowerBound) {
        return new Range(lowerBound, null);
    }

    public static Range between(double lowerBound, double upperBound) {
        return new Range(lowerBound, upperBound);
    }

    private static void validateBounds(Double lowerBound, Double upperBound) {
        if (lowerBound == null && upperBound == null) {
            throw new IllegalArgumentException("Both lowerBound and upperBound cannot be null");
        }

        if (lowerBound != null && upperBound != null && lowerBound > upperBound) {
            throw new IllegalArgumentException("lowerBound must be <= upper bound");
        }
    }
}
