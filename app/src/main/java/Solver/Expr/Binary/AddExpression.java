package Solver.Expr.Binary;

import Solver.Expr.Expression;
import java.util.Map;

public class AddExpression extends BinaryExpression {
    public AddExpression(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    public double execute(Map<String, Double> params) {
        return leftExpression.execute(params) + rightExpression.execute(params);
    }
}
