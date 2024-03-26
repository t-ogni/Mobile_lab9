package Solver.Expr.Unary;

import Solver.Expr.Expression;
import java.util.Map;

public class CosExpression implements Expression {
    private final Expression expression;

    public CosExpression(Expression Expression) {
        this.expression = Expression;
    }

    @Override
    public double execute(Map<String, Double> params) {
        return Math.cos(expression.execute(params));
    }
}