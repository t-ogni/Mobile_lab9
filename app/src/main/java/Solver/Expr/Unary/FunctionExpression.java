package Solver.Expr.Unary;

import Solver.Expr.Expression;

import java.util.Map;

public abstract class FunctionExpression implements Expression {
    private final Expression expression;

    public FunctionExpression(Expression Expression, String name) {
        this.expression = Expression;
    }
}
