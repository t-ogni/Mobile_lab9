package Solver.Expr.Binary;

import Solver.Expr.Expression;
import java.util.Map;

public abstract class BinaryExpression implements Expression {
    protected final Expression leftExpression;
    protected final Expression rightExpression;

    public BinaryExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }
}
