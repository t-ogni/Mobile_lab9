package Solver.Expr.Unary;

import Solver.Expr.Expression;
import java.util.Map;

public class FactorialExpression implements Expression {
    private final Expression expression;

    public FactorialExpression(Expression Expression) {
        this.expression = Expression;
    }

    @Override
    public double execute(Map<String, Double> params) {
        double x = expression.execute(params), y = 1;
        for (int i = 2; i <= x; i++)
            y *= i;
        return y;
    }
}