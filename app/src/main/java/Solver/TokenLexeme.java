package Solver;

import java.util.regex.Pattern;

enum Token {
    Const("\\d+"),
    Variable("\\w+"),
    Operator("[-+*\\/]"),
    UnaryFunction("sin|cos|tan"),
    PostfixFunction("!"),
    Branch("[()]");

    public final String pattern;

    Token(String pattern) {
        this.pattern = pattern;
    }

    public static Pattern getTokenPattern(){
        StringBuilder patternString = new StringBuilder();
        for (Token token : Token.values()) {
            patternString.append(token.pattern).append("|");
        }
        String pattern = patternString.substring(0, patternString.length() - 1); // последняя палочка лишняя

        return Pattern.compile("\\s*("+pattern+")\\s*");
    }
}

public class TokenLexeme {
    public Token token;
    public String lexeme;

    public TokenLexeme(Token token, String lexeme) {
        this.token = token;
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return "{" + token + ": '" + lexeme + "'}";
    }
}
