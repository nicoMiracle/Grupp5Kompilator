package org.example;

public record Token(TokenType type, String lexem, int line){}

//package org.example;
//
//import java.util.Objects;
//
//public class Token {
//    private TokenType type; // type of the token
//    private String lexeme; // The value of token
//    private int line; // The line number where the token was found
//
//    public Token(TokenType type, String lexeme, int line) {
//        this.type = type;
//        this.lexeme = lexeme;
//        this.line = line;
//    }
//
//    public TokenType getType() {
//        return type;
//    }
//
//    public String getLexeme() {
//        return lexeme;
//    }
//
//    public int getLine() {
//        return line;
//    }
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null || getClass() != obj.getClass()) {
//            return false;
//        }
//        Token other = (Token) obj;
//        return type == other.type && Objects.equals(lexeme, other.lexeme) && line == other.line;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(type, lexeme, line);
//    }
//
//    @Override
//    public String toString() {
//        return String.format("Token(type=%s, lexeme='%s', line=%d)", type, lexeme, line);
//    }
//}
