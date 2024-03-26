package Solver.Expr;

import java.util.*;

public interface Expression {
    double execute(Map<String, Double> params);
}
