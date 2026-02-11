package com.vitnberg.portopt.optimization.solver;

import java.util.Optional;

public final class SolveResult<T> {
    private final SolveStatus status;
    private final T solution;

    private SolveResult(SolveStatus status) {
        this.status = status;
        this.solution = null;
    }

    private SolveResult(SolveStatus status, T solution) {
        this.status = status;
        this.solution = solution;
    }

    public SolveStatus status() {
        return status;
    }

    public Optional<T> solution() {
        return Optional.ofNullable(solution);
    }

    public static <T> SolveResult<T> withoutSolution(SolveStatus status) {
        validateWithoutSolutionStatus(status);
        return new SolveResult<>(status);
    }

    public static <T> SolveResult<T> optimalSolution(T solution) {
        validateSolution(solution);
        return new SolveResult<>(SolveStatus.OPTIMAL, solution);
    }

    public static <T> SolveResult<T> feasibleSolution(T solution) {
        validateSolution(solution);
        return new SolveResult<>(SolveStatus.FEASIBLE, solution);
    }

    private static void validateWithoutSolutionStatus(SolveStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("SolveStatus cannot be null");
        }
        if (status.hasSolution()) {
            throw new IllegalArgumentException("A solution must be present");
        }
    }

    private static <T> void validateSolution(T solution) {
        if (solution == null) {
            throw new IllegalArgumentException("Solution cannot be null");
        }
    }
}
