package com.vitnberg.portopt.optimization.solver;

public enum SolveStatus {
    OPTIMAL,
    FEASIBLE,
    INFEASIBLE,
    UNBOUNDED,
    FAILURE;

    public boolean hasSolution() {
        return (this == OPTIMAL) || (this == FEASIBLE);
    }
}
