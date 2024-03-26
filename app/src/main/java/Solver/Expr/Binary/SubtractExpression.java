package Solver.Expr.Binary;

import Solver.Expr.Expression;

import java.util.Map;

public class SubtractExpression extends BinaryExpression {
    public SubtractExpression(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    public double execute(Map<String, Double> params) {
        return leftExpression.execute(params) - rightExpression.execute(params);
    }
}
