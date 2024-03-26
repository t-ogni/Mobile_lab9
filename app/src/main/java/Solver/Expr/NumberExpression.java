package Solver.Expr;

import java.util.Map;

public class NumberExpression implements Expression {
    private final double number;

    public NumberExpression(double number) {
        this.number = number;
    }

    @Override
    public double execute(Map<String, Double> params) {
        return number;
    }
}
