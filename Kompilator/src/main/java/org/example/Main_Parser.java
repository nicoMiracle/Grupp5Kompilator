package org.example;

import java.util.List;
import java.util.ArrayList;

public class Main_Parser {
    public static void main(String[] args) {
        List<Token> tokens = new ArrayList<>();

        // Add tokens to the list
        tokens.add(new Token(TokenType.TYPE_INT, "int", 1));
        tokens.add(new Token(TokenType.IDENTIFIER, "x", 1));
        tokens.add(new Token(TokenType.ASSIGN, "=", 1));
        tokens.add(new Token(TokenType.INTEGER_LITERAL, "5", 1));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 1));
//
//        tokens.add(new Token(TokenType.TYPE_STRING, "String", 2));
//        tokens.add(new Token(TokenType.IDENTIFIER, "y", 2));
//        tokens.add(new Token(TokenType.ASSIGN, "=", 2));
//        tokens.add(new Token(TokenType.STRING_LITERAL, "\"Hello\"", 2));
//        tokens.add(new Token(TokenType.SEMICOLON, ";", 2));
//
//        tokens.add(new Token(TokenType.IDENTIFIER, "x", 3));
//        tokens.add(new Token(TokenType.ASSIGN, "=", 3));
//        tokens.add(new Token(TokenType.INTEGER_LITERAL, "10", 3));
//        tokens.add(new Token(TokenType.SEMICOLON, ";", 3));
//
//        tokens.add(new Token(TokenType.IDENTIFIER, "y", 4));
//        tokens.add(new Token(TokenType.ASSIGN, "=", 4));
//        tokens.add(new Token(TokenType.STRING_LITERAL, "\"World\"", 4));
//        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));

//        tokens.add(new Token(TokenType.KEYWORD_IF, "if", 5));
//        tokens.add(new Token(TokenType.LPAREN, "(", 5));
//        tokens.add(new Token(TokenType.IDENTIFIER, "x", 5));
//        tokens.add(new Token(TokenType.GREATER_EQUAL, ">", 5));
//        tokens.add(new Token(TokenType.INTEGER_LITERAL, "5", 5));
//        tokens.add(new Token(TokenType.RPAREN, ")", 5));
//        tokens.add(new Token(TokenType.LEFT_BRACE, "{", 5));
//        tokens.add(new Token(TokenType.IDENTIFIER, "y", 6));
//        tokens.add(new Token(TokenType.ASSIGN, "=", 6));
//        tokens.add(new Token(TokenType.STRING_LITERAL, "\"Goodbye\"", 6));
//        tokens.add(new Token(TokenType.SEMICOLON, ";", 6));
//        tokens.add(new Token(TokenType.RIGHT_BRACE, "}", 7));
//        tokens.add(new Token(TokenType.KEYWORD_ELSE, "else", 7));
//        tokens.add(new Token(TokenType.LEFT_BRACE, "{", 7));
//        tokens.add(new Token(TokenType.IDENTIFIER, "x", 8));
//        tokens.add(new Token(TokenType.ASSIGN, "=", 8));
//        tokens.add(new Token(TokenType.INTEGER_LITERAL, "2", 8));
//        tokens.add(new Token(TokenType.SEMICOLON, ";", 8));
//        tokens.add(new Token(TokenType.RIGHT_BRACE, "}", 9));

        // Create a parser and parse the tokens to build the AST
        Parser parser = new Parser(tokens);
        ProgramNode program = parser.parse();

        // Create a PrintVisitor to print the program structure
        PrintVisitor printVisitor = new PrintVisitor();
        program.accept(printVisitor);
    }
}
