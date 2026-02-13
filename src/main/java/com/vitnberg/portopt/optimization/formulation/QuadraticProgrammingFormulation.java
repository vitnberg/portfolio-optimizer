package com.vitnberg.portopt.optimization.formulation;

import java.util.List;
import java.util.Objects;

public final class QuadraticProgrammingFormulation {
    private final int numberOfVariables;
    private final Range[] variableRanges;
    private final ObjectiveFunction objectiveFunction;
    private final List<LinearConstraint> constraints;

    public QuadraticProgrammingFormulation(int numberOfVariables, Range[] variableRanges, ObjectiveFunction objectiveFunction, List<LinearConstraint> constraints) {
        this.numberOfVariables = numberOfVariables;
        this.variableRanges = Objects.requireNonNull(variableRanges).clone();
        this.objectiveFunction = Objects.requireNonNull(objectiveFunction);
        this.constraints = Objects.requireNonNull(constraints);

        validateParametersConsistency(numberOfVariables, variableRanges, objectiveFunction, constraints);
    }

    public int numberOfVariables() {
        return numberOfVariables;
    }

    public Range[] variableRanges() {
        return variableRanges.clone();
    }

    public ObjectiveFunction objectiveFunction() {
        return objectiveFunction;
    }

    public List<LinearConstraint> constraints() {
        return constraints;
    }

    private static void validateParametersConsistency(int numberOfVariables, Range[] variableRanges, ObjectiveFunction objectiveFunction, List<LinearConstraint> constraints) {
        if (numberOfVariables <= 0) {
            throw new IllegalArgumentException("Number of variables must be > 0");
        }

        if (numberOfVariables != variableRanges.length) {
            throw new IllegalArgumentException("Length of variableRanges must be equal numberOfVariables");
        }

        if (numberOfVariables != objectiveFunction.quadraticCoefficients().length) {
            throw new IllegalArgumentException("Objective quadratic coefficients dimension mismatch");
        }

        if (numberOfVariables != objectiveFunction.linearCoefficients().length) {
            throw new IllegalArgumentException("Linear coefficients dimension mismatch");
        }

        for (LinearConstraint constraint : constraints) {
            if (constraint == null) {
                throw new IllegalArgumentException("Constraint cannot be null");
            }
            if (numberOfVariables != constraint.coefficients().length) {
                throw new IllegalArgumentException("Constraint coefficients dimension mismatch");
            }
        }
    }

}
