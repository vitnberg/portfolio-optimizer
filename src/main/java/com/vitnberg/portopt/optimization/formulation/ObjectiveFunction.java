package com.vitnberg.portopt.optimization.formulation;

import com.vitnberg.portopt.math.MatrixUtils;

import java.util.Objects;

public final class ObjectiveFunction {
    private final double[][] quadraticCoefficients;
    private final double[] linearCoefficients;

    public ObjectiveFunction(double[][] quadraticCoefficients, double[] linearCoefficients) {
        Objects.requireNonNull(quadraticCoefficients, "quadraticCoefficients");
        Objects.requireNonNull(linearCoefficients, "linearCoefficients");

        validateDimensions(quadraticCoefficients, linearCoefficients);

        this.quadraticCoefficients = MatrixUtils.deepCopy(quadraticCoefficients);
        this.linearCoefficients = linearCoefficients.clone();
    }

    public double[][] quadraticCoefficients() {
        return this.quadraticCoefficients;
    }

    public double[] linearCoefficients() {
        return this.linearCoefficients;
    }

    private static void validateDimensions(double[][] quadraticForm, double[] linearForm) {
        MatrixUtils.validateSquareMatrix(quadraticForm, linearForm.length);
    }
}
