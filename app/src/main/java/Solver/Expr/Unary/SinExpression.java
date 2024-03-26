package Solver.Expr.Unary;

import Solver.Expr.Expression;

import java.util.Map;

public class SinExpression implements Expression {
    private final Expression expression;

    public SinExpression(Expression Expression) {
        this.expression = Expression;
    }

    @Override
    public double execute(Map<String, Double> params) {
        return Math.sin(expression.execute(params));
    }
}