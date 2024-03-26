package Solver;

import Solver.Expr.*;
import Solver.Expr.Unary.*;
import Solver.Expr.Binary.*;

import java.util.*;
import java.util.regex.*;
// https://ru.wikipedia.org/wiki/Алгоритм_сортировочной_станции

public class ExpressionParser {

    // функция возвращает массив из токенов в постфиксном формате
    public Deque<TokenLexeme> tokenize(String expr){
        Deque<TokenLexeme> tokens = new ArrayDeque<>();
        Deque<TokenLexeme> stack = new ArrayDeque<>();
        Matcher m = Token.getTokenPattern().matcher(expr);
        while (m.find()) {
            String token = m.group().trim();
//            System.out.print(token);

            if (token.matches(Token.Const.pattern)) {
                tokens.push(new TokenLexeme(Token.Const, token));
//                System.out.println(" - const - to T");
            } else if (token.matches(Token.PostfixFunction.pattern)) {
                tokens.push(new TokenLexeme(Token.PostfixFunction, token));
//                System.out.println(" - postfix - to T");
            } else if (token.matches(Token.UnaryFunction.pattern)) {
                stack.push(new TokenLexeme(Token.UnaryFunction, token));
//                System.out.println(" - Func - to S");
            } else if (token.matches(Token.Operator.pattern)) {
                while (!stack.isEmpty() && stack.peek().token == Token.Operator) {
                    tokens.push(stack.pop());
                }
                stack.push(new TokenLexeme(Token.Operator, token));
//                System.out.println(" - Oper - to S");
            } else if (token.equals("(")) {
                stack.push(new TokenLexeme(Token.Branch, token));
//                System.out.println(" - Lbranch - to S");
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !(stack.peek().token == Token.Branch)) {
                    tokens.push(stack.pop());
                }
                stack.pop();
//                System.out.println(" - Rbranch - Del LB");
                if (!stack.isEmpty() && stack.peek().token == Token.UnaryFunction) {
                    tokens.push(stack.pop());
                }
            } else if (token.matches(Token.Variable.pattern)) {
                tokens.push(new TokenLexeme(Token.Variable, token));
//                System.out.println(" - Var");
            } else {
                System.out.println("IDK WTF: "+token);
            }
        }
        while (!stack.isEmpty()) {
            tokens.push(stack.pop());
        }


        return tokens;
    }

    /*
    * in > 2 + 3 - sin(4*5)
    * out < [   ... в обратном порядке
    *       [c, 2]  ... это конец списка
    *       [c, 3]
    *       [op, +]
    *       [c, 4]
    *       [c, 5]
    *       [op, *]
    *       [uf, sin]
    *       [op, -]
    *   ]
    */


    public Expression parse(String expr) {
        Deque<TokenLexeme> ReversePolish = this.tokenize(expr);  // пример входных и выходных выше
        Deque<Expression> Expressions = new ArrayDeque<>();
        Iterator<TokenLexeme> itr = ReversePolish.descendingIterator();

        while (itr.hasNext()) {
            TokenLexeme token = itr.next();
//            System.out.println(token);
            switch (token.token){
                case Const:
                    Expressions.push(new NumberExpression(Double.parseDouble(token.lexeme)));
                    break;
                case Variable:
                    Expressions.push(new VariableExpression(token.lexeme));
                    break;
                case Operator:
                    Expression Rvalue = Expressions.pop();
                    Expression Lvalue = Expressions.pop();
                    switch (token.lexeme){
                        case "+":
                            Expressions.push(new AddExpression(Lvalue,Rvalue));
                            break;
                        case "-":
                            Expressions.push(new SubtractExpression(Lvalue,Rvalue));
                            break;
                        case "*":
                            Expressions.push(new MultiplyExpression(Lvalue, Rvalue));
                            break;
                        case "/":
                            Expressions.push(new DivideExpression(Lvalue, Rvalue));
                            break;
                        default:
                            System.out.println("No matching operator found: "+token.lexeme);
                            break;
                    }
                    break;
                case UnaryFunction:
                    switch (token.lexeme) {
                        case "sin":
                            Expressions.push(new SinExpression(Expressions.pop()));
                            break;
                        case "cos":
                            Expressions.push(new CosExpression(Expressions.pop()));
                            break;
                        case "tan":
                            Expressions.push(new TanExpression(Expressions.pop()));
                            break;
                        default:
                            System.out.println("No matching UnaryFunction found: "+token.lexeme);
                            break;
                    }
                    break;
                case PostfixFunction:
                    switch (token.lexeme) {
                        case "!":
                            Expressions.push(new FactorialExpression(Expressions.pop()));
                            break;
                        default:
                            System.out.println("No matching PostfixFunction found: "+token.lexeme);
                            break;
                    }
                    break;
            }
        }

        return Expressions.pop();
    }
}
