package Solver.Expr.Binary;

import Solver.Expr.Expression;
import java.util.Map;

public class MultiplyExpression extends BinaryExpression {

    public MultiplyExpression(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    public double execute(Map<String, Double> params) {
        return leftExpression.execute(params) * rightExpression.execute(params);
    }
}
