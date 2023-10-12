package org.example;

public class Token {
    private TokenType type; // type of the token
    private String lexeme; // The value of token
    private int line; // The line number where the token was found

    public Token(TokenType type, String lexeme) {
        this.type = type;
        this.lexeme = lexeme;
    }

    public TokenType getType() {
        return type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public int getLine() {
        return line;
    }

    @Override
    public String toString() {
        return String.format("Token(type=%s, lexeme='%s', line=%d)", type, lexeme, line);
    }
}
