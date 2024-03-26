package Solver.Expr;

import java.util.Map;

public class VariableExpression implements Expression {
    private final String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public double execute(Map<String, Double> params) {
        if (!params.containsKey(name)) {
            throw new IllegalArgumentException("No parameter provided for variable: " + name);
        }
        return params.get(name);
    }
}
