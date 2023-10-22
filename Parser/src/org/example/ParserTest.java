package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;


public class ParserTest {

    private Parser parser;

    @Test
    @DisplayName("Test Parser constructor")
    public void testParserConstructor() {
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.IDENTIFIER, "x", 3));
        tokens.add(new Token(TokenType.ASSIGN, "=", 3));
        tokens.add(new Token(TokenType.INTEGER_LITERAL, "10", 3));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 3));
         parser = new Parser(tokens);
        assertEquals(tokens, parser.getTokens(), "Parser should have the same tokens as input list");
        assertEquals(0, parser.getPosition(), "Parser should start with position 0");
    }

//
    @Test
    @DisplayName("Test Assignment statement node with integervalue")
    void testParseAssignmentStatementWithIntValue() {

        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.IDENTIFIER, "x", 3));
        tokens.add(new Token(TokenType.ASSIGN, "=", 3));
        tokens.add(new Token(TokenType.INTEGER_LITERAL, "10", 3));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 3));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }


    @Test
    @DisplayName("Test Assignment statement node with string value")
    void testParseAssignmentStatementWithStringValue() {

        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.IDENTIFIER, "y", 3));
        tokens.add(new Token(TokenType.ASSIGN, "=", 3));
        tokens.add(new Token(TokenType.STRING_LITERAL, "\"World\"", 3));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 3));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }


    @Test
    @DisplayName("Test Assignment statement node with one binary expression value")
    void testParseAssignmentStatementWithBinaryExpressionValue() {

        List<Token> tokens = new ArrayList<>();
        // x= 2-300;
        tokens.add(new Token(TokenType.IDENTIFIER,"x",2));
        tokens.add(new Token(TokenType.ASSIGN,"=",2));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"2",2));
        tokens.add(new Token(TokenType.MINUS,"-",2));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"300",2));
        tokens.add(new Token(TokenType.SEMICOLON,";",2));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test Assignment statement node with multiple binary expression value")
    void testParseAssignmentStatementWithMultipleBinaryExpressionValue() {

        List<Token> tokens = new ArrayList<>();
        // x=  2 + 300 + 300 -300;
        tokens.add(new Token(TokenType.IDENTIFIER,"x",2));
        tokens.add(new Token(TokenType.ASSIGN,"=",2));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"2",2));
        tokens.add(new Token(TokenType.PLUS,"+",2));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"300",2));
        tokens.add(new Token(TokenType.PLUS,"+",2));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"300",2));
        tokens.add(new Token(TokenType.MINUS,"-",2));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"300",2));
        tokens.add(new Token(TokenType.SEMICOLON,";",2));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }


    @Test
    @DisplayName("Test Declaration Statement node with string type and with out initial value")
    void testParseDeclarationStatementWithStringType() {

        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.TYPE_INT,"int",6));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",6));
        tokens.add(new Token(TokenType.SEMICOLON,";",6));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test Declaration Statement node with string type and with out initial value")
    void testParseDeclarationStatementWithIntType() {

        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.IDENTIFIER, "string", 4));
        tokens.add(new Token(TokenType.ASSIGN, "=", 4));
        tokens.add(new Token(TokenType.STRING_LITERAL, "\"Hello\"", 4));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test Declaration Statement node with string type and initial value")
    void testParseDeclarationStatementWithStringvalue() {

        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.TYPE_STRING,"String",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"str",1));
        tokens.add(new Token(TokenType.ASSIGN,"=",1));
        tokens.add(new Token(TokenType.STRING_LITERAL,"\"Hello\"",1));
        tokens.add(new Token(TokenType.SEMICOLON,";",1));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test Declaration Statement node with int type and initial value")
    void testParseDeclarationStatementWithIntvalue() {

        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.TYPE_INT,"int",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",1));
        tokens.add(new Token(TokenType.ASSIGN,"=",1));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"2674",1));
        tokens.add(new Token(TokenType.SEMICOLON,";",1));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test Assignment statement node with multiple binary expression value")
    void testParseDeclarationStatementWithMultipleBinaryExpressionValue() {

        List<Token> tokens = new ArrayList<>();

        //int x=  2 + 300 + 300 -300;
        tokens.add(new Token(TokenType.TYPE_INT,"int",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",2));
        tokens.add(new Token(TokenType.ASSIGN,"=",2));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"2",2));
        tokens.add(new Token(TokenType.PLUS,"+",2));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"300",2));
        tokens.add(new Token(TokenType.PLUS,"+",2));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"300",2));
        tokens.add(new Token(TokenType.MINUS,"-",2));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"300",2));
        tokens.add(new Token(TokenType.SEMICOLON,";",2));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test if Statement with one statement i block")
    public void testParseIfStatementWithOneStatement() {
        // Create a list of tokens for an if statement
        List<Token> tokens = new ArrayList<>();

        tokens.add(new Token(TokenType.KEYWORD_IF, "if", 5));
        tokens.add(new Token(TokenType.LPAREN, "(", 5));
        tokens.add(new Token(TokenType.IDENTIFIER, "x", 5));
        tokens.add(new Token(TokenType.EQUAL, "==", 5));
        tokens.add(new Token(TokenType.INTEGER_LITERAL, "5", 5));
        tokens.add(new Token(TokenType.RPAREN, ")", 5));
        tokens.add(new Token(TokenType.LEFT_BRACE, "{", 5));
        tokens.add(new Token(TokenType.IDENTIFIER, "y", 6));
        tokens.add(new Token(TokenType.ASSIGN, "=", 6));
        tokens.add(new Token(TokenType.STRING_LITERAL, "\"Goodbye\"", 6));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 6));
        tokens.add(new Token(TokenType.RIGHT_BRACE, "}", 7));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test if Statement with multiple statement i block")
    public void testParseIfStatementWithMultipleStatement() {
        // Create a list of tokens for an if statement
        List<Token> tokens = new ArrayList<>();

        tokens.add(new Token(TokenType.KEYWORD_IF, "if", 5));
        tokens.add(new Token(TokenType.LPAREN, "(", 5));
        tokens.add(new Token(TokenType.IDENTIFIER, "x", 5));
        tokens.add(new Token(TokenType.EQUAL, "==", 5));
        tokens.add(new Token(TokenType.INTEGER_LITERAL, "5", 5));
        tokens.add(new Token(TokenType.RPAREN, ")", 5));
        tokens.add(new Token(TokenType.LEFT_BRACE, "{", 5));
        tokens.add(new Token(TokenType.TYPE_INT,"int",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",1));
        tokens.add(new Token(TokenType.ASSIGN,"=",1));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"2674",1));
        tokens.add(new Token(TokenType.SEMICOLON,";",1));
        tokens.add(new Token(TokenType.TYPE_STRING,"String",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"str",1));
        tokens.add(new Token(TokenType.ASSIGN,"=",1));
        tokens.add(new Token(TokenType.STRING_LITERAL,"\"Hello\"",1));
        tokens.add(new Token(TokenType.SEMICOLON,";",1));
        tokens.add(new Token(TokenType.IDENTIFIER, "y", 6));
        tokens.add(new Token(TokenType.ASSIGN, "=", 6));
        tokens.add(new Token(TokenType.STRING_LITERAL, "\"Goodbye\"", 6));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 6));
        tokens.add(new Token(TokenType.IDENTIFIER, "string", 4));
        tokens.add(new Token(TokenType.ASSIGN, "=", 4));
        tokens.add(new Token(TokenType.STRING_LITERAL, "\"Hello\"", 4));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));
        tokens.add(new Token(TokenType.RIGHT_BRACE, "}", 7));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test while Statement with one statement in the body")
    public void testParseWhileStatementWithOneStatement() {
        // Create a list of tokens for an if statement
        List<Token> tokens = new ArrayList<>();

        tokens.add(new Token(TokenType.KEYWORD_WHILE, "while", 5));
        tokens.add(new Token(TokenType.LPAREN, "(", 5));
        tokens.add(new Token(TokenType.IDENTIFIER, "x", 5));
        tokens.add(new Token(TokenType.EQUAL, "==", 5));
        tokens.add(new Token(TokenType.INTEGER_LITERAL, "5", 5));
        tokens.add(new Token(TokenType.RPAREN, ")", 5));
        tokens.add(new Token(TokenType.LEFT_BRACE, "{", 5));
        tokens.add(new Token(TokenType.IDENTIFIER, "y", 6));
        tokens.add(new Token(TokenType.ASSIGN, "=", 6));
        tokens.add(new Token(TokenType.STRING_LITERAL, "\"Goodbye\"", 6));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 6));
        tokens.add(new Token(TokenType.RIGHT_BRACE, "}", 7));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test while Statement with multiple statement i block")
    public void testParseWhileStatementWithMultipleStatement() {
        // Create a list of tokens for an if statement
        List<Token> tokens = new ArrayList<>();

        tokens.add(new Token(TokenType.KEYWORD_WHILE, "while", 5));
        tokens.add(new Token(TokenType.LPAREN, "(", 5));
        tokens.add(new Token(TokenType.IDENTIFIER, "x", 5));
        tokens.add(new Token(TokenType.EQUAL, "==", 5));
        tokens.add(new Token(TokenType.INTEGER_LITERAL, "5", 5));
        tokens.add(new Token(TokenType.RPAREN, ")", 5));
        tokens.add(new Token(TokenType.LEFT_BRACE, "{", 5));
        tokens.add(new Token(TokenType.TYPE_INT,"int",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",1));
        tokens.add(new Token(TokenType.ASSIGN,"=",1));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"2674",1));
        tokens.add(new Token(TokenType.SEMICOLON,";",1));
        tokens.add(new Token(TokenType.TYPE_STRING,"String",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"str",1));
        tokens.add(new Token(TokenType.ASSIGN,"=",1));
        tokens.add(new Token(TokenType.STRING_LITERAL,"\"Hello\"",1));
        tokens.add(new Token(TokenType.SEMICOLON,";",1));
        tokens.add(new Token(TokenType.IDENTIFIER, "y", 6));
        tokens.add(new Token(TokenType.ASSIGN, "=", 6));
        tokens.add(new Token(TokenType.STRING_LITERAL, "\"Goodbye\"", 6));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 6));
        tokens.add(new Token(TokenType.IDENTIFIER, "string", 4));
        tokens.add(new Token(TokenType.ASSIGN, "=", 4));
        tokens.add(new Token(TokenType.STRING_LITERAL, "\"Hello\"", 4));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));
        tokens.add(new Token(TokenType.RIGHT_BRACE, "}", 7));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }
}
