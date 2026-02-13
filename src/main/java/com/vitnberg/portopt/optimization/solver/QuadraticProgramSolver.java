package com.vitnberg.portopt.optimization.solver;

import com.vitnberg.portopt.optimization.formulation.QuadraticProgrammingFormulation;

public interface QuadraticProgramSolver {
    SolveResult<double[]> solve(QuadraticProgrammingFormulation formulation);
}
