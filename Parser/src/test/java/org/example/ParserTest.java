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

        //x=10;
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
    @DisplayName("Test if it throws Parser exception in Assignment statement node with no value")
    void testIfParseAssignmentStatementThrowsException() {

        //x=;
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.IDENTIFIER, "x", 3));
        tokens.add(new Token(TokenType.ASSIGN, "=", 3));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 3));

        parser = new Parser(tokens);
        assertThrows( ParseException.class, () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test if it throws Parser exception in statement node with no value")
    void testIfParseStatementNodeThrowsException() {

        //x;
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.IDENTIFIER, "x", 3));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 3));

        parser = new Parser(tokens);
        assertThrows( ParseException.class, () -> {
            parser.parseStatementNode();
        });
    }


    @Test
    @DisplayName("Test Assignment statement node with string value")
    void testParseAssignmentStatementWithStringValue() {

        List<Token> tokens = new ArrayList<>();

        //y="world";
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
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"300",3));
        tokens.add(new Token(TokenType.SEMICOLON,";",2));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test Assignment statement node with multiple negative term")
    void testParseAssignmentStatementWithNegativeTerm() {

        List<Token> tokens = new ArrayList<>();
        // x= x-z-z-z-z-z-z-z;
        tokens.add(new Token(TokenType.IDENTIFIER,"x",2));
        tokens.add(new Token(TokenType.ASSIGN,"=",2));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",2));
        tokens.add(new Token(TokenType.MINUS,"-",2));
        tokens.add(new Token(TokenType.IDENTIFIER,"z",3));
        tokens.add(new Token(TokenType.MINUS,"-",2));
        tokens.add(new Token(TokenType.IDENTIFIER,"z",3));
        tokens.add(new Token(TokenType.MINUS,"-",2));
        tokens.add(new Token(TokenType.IDENTIFIER,"z",3));
        tokens.add(new Token(TokenType.MINUS,"-",2));
        tokens.add(new Token(TokenType.IDENTIFIER,"z",3));
        tokens.add(new Token(TokenType.SEMICOLON,";",2));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test Assignment statement node with multiple negative and possitive term")
    void testParseAssignmentStatementWithNegativeAndPositiveTerm() {

        List<Token> tokens = new ArrayList<>();
        // x= x+z-z+z-z+z-z;
        tokens.add(new Token(TokenType.IDENTIFIER, "x", 2));
        tokens.add(new Token(TokenType.ASSIGN, "=", 2));
        tokens.add(new Token(TokenType.IDENTIFIER, "x", 2));
        tokens.add(new Token(TokenType.PLUS, "+", 2));
        tokens.add(new Token(TokenType.IDENTIFIER, "z", 3));
        tokens.add(new Token(TokenType.MINUS, "-", 2));
        tokens.add(new Token(TokenType.IDENTIFIER, "z", 3));
        tokens.add(new Token(TokenType.PLUS, "+", 2));
        tokens.add(new Token(TokenType.IDENTIFIER, "z", 3));
        tokens.add(new Token(TokenType.MINUS, "-", 2));
        tokens.add(new Token(TokenType.IDENTIFIER, "z", 3));
        tokens.add(new Token(TokenType.MINUS, "-", 2));
        tokens.add(new Token(TokenType.IDENTIFIER, "z", 3));
        tokens.add(new Token(TokenType.PLUS, "+", 2));
        tokens.add(new Token(TokenType.IDENTIFIER, "z", 3));
        tokens.add(new Token(TokenType.MINUS, "-", 2));
        tokens.add(new Token(TokenType.IDENTIFIER, "z", 3));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 2));

        parser = new Parser(tokens);
        assertDoesNotThrow(() -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test Assignment statement node with invalid assignment")
    void testIfParseAssignmentStatementThrowsExceptionWithInvalidAssign() {

        List<Token> tokens = new ArrayList<>();
        // x= x+;
        tokens.add(new Token(TokenType.IDENTIFIER, "x", 2));
        tokens.add(new Token(TokenType.ASSIGN, "=", 2));
        tokens.add(new Token(TokenType.IDENTIFIER, "x", 2));
        tokens.add(new Token(TokenType.PLUS, "+", 2));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 2));

        parser = new Parser(tokens);
        assertThrows( ParseException.class, () -> {
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
    @DisplayName("Test Assignment statement node with method call value")
    void testParseAssignmentStatementWithMethodCallValue() {

        List<Token> tokens = new ArrayList<>();

        // y =  get(x);

        tokens.add(new Token(TokenType.IDENTIFIER,"y",2));
        tokens.add(new Token(TokenType.ASSIGN,"=",2));
        tokens.add(new Token(TokenType.IDENTIFIER, "get", 4));
        tokens.add(new Token(TokenType.LPAREN, "(", 5));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",8));
        tokens.add(new Token(TokenType.RPAREN, ")", 5));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test ParseAssignmentStatement and String concatenation using parseAdditionSubtractionExpression method")
    public void testParseAssignmentStatementAndStringConcatenation() {

        List<Token> tokens = new ArrayList<>();
        /*
        String str = "Hello" + "Furti" ;
         */

        tokens.add(new Token(TokenType.TYPE_STRING, "String", 1));
        tokens.add(new Token(TokenType.IDENTIFIER, "str", 2));
        tokens.add(new Token(TokenType.ASSIGN, "=", 3));
        tokens.add(new Token(TokenType.STRING_LITERAL, "\"Hello\"", 4));
        tokens.add(new Token(TokenType.PLUS, "+", 5));
        tokens.add(new Token(TokenType.STRING_LITERAL, "\"Furti\"", 6));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 7));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }


    @Test
    @DisplayName("Test Declaration Statement node with string type and with out initial value")
    void testParseDeclarationStatementWithOutInitalValue() {

        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.TYPE_INT,"int",6));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",6));
        tokens.add(new Token(TokenType.SEMICOLON,";",6));

        parser = new Parser(tokens);
        assertThrows( ParseException.class, () -> {
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
    @DisplayName("Test Declaration statement node with method call value")
    void testParseDeclarationStatementWithMethodCallValue() {

        List<Token> tokens = new ArrayList<>();

        //int x=  get(x);
        tokens.add(new Token(TokenType.TYPE_INT,"int",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",2));
        tokens.add(new Token(TokenType.ASSIGN,"=",2));
        tokens.add(new Token(TokenType.IDENTIFIER, "get", 4));
        tokens.add(new Token(TokenType.LPAREN, "(", 6));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",8));
        tokens.add(new Token(TokenType.RPAREN, ")", 5));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));

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

        tokens.add(new Token(TokenType.CONTROL_IF, "if", 5));
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

        tokens.add(new Token(TokenType.CONTROL_IF, "if", 5));
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

        tokens.add(new Token(TokenType.CONTROL_WHILE, "while", 5));
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

        tokens.add(new Token(TokenType.CONTROL_WHILE, "while", 5));
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
    @DisplayName("Test MethodDeclarationStatement with int type, one statement in block and one parameter")
    public void testParseMethodDeclarationStatementWithOneParameter() {

        List<Token> tokens = new ArrayList<>();

        //int x(int x){int x = 2674;}
        tokens.add(new Token(TokenType.TYPE_INT,"int",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",1));
        tokens.add(new Token(TokenType.LPAREN, "(", 5));
        tokens.add(new Token(TokenType.TYPE_INT,"int",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",8));
        tokens.add(new Token(TokenType.RPAREN, ")", 5));
        tokens.add(new Token(TokenType.LEFT_BRACE, "{", 5));
        tokens.add(new Token(TokenType.TYPE_INT,"int",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",1));
        tokens.add(new Token(TokenType.ASSIGN,"=",1));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"2674",1));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));
        tokens.add(new Token(TokenType.RIGHT_BRACE, "}", 7));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test MethodDeclarationStatement with string type, multiple statement in block and two parameter")
    public void testParseMethodDeclarationStatementWithStringType() {

        List<Token> tokens = new ArrayList<>();

        //int x(int x, String y ){ x = 2674; y = "Hello";}
        tokens.add(new Token(TokenType.TYPE_STRING,"String",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"getString",1));
        tokens.add(new Token(TokenType.LPAREN, "(", 5));
        tokens.add(new Token(TokenType.TYPE_INT,"int",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",8));
        tokens.add(new Token(TokenType.COMMA, ",", 4));
        tokens.add(new Token(TokenType.TYPE_STRING, "String", 5));
        tokens.add(new Token(TokenType.IDENTIFIER,"y",1));
        tokens.add(new Token(TokenType.RPAREN, ")", 5));
        tokens.add(new Token(TokenType.LEFT_BRACE, "{", 5));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",1));
        tokens.add(new Token(TokenType.ASSIGN,"=",1));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"2674",1));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));
        tokens.add(new Token(TokenType.IDENTIFIER, "y", 4));
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
    @DisplayName("Test MethodDeclarationStatement with multiple statement in block and two parameter")
    public void testParseMethodDeclarationStatementWithTwoParameter() {

        List<Token> tokens = new ArrayList<>();

        //int x(int x, String y ){ x = 2674; y = "Hello";}
        tokens.add(new Token(TokenType.TYPE_INT,"int",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",1));
        tokens.add(new Token(TokenType.LPAREN, "(", 5));
        tokens.add(new Token(TokenType.TYPE_INT,"int",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",8));
        tokens.add(new Token(TokenType.COMMA, ",", 4));
        tokens.add(new Token(TokenType.TYPE_STRING, "String", 5));
        tokens.add(new Token(TokenType.IDENTIFIER,"y",1));
        tokens.add(new Token(TokenType.RPAREN, ")", 5));
        tokens.add(new Token(TokenType.LEFT_BRACE, "{", 5));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",1));
        tokens.add(new Token(TokenType.ASSIGN,"=",1));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"2674",1));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));
        tokens.add(new Token(TokenType.IDENTIFIER, "y", 4));
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
    @DisplayName("Test ReturnStatement ")
    public void testReturnStatement() {

        List<Token> tokens = new ArrayList<>();

        /*
        if(x==5){
        if(x==7){
        while(x==7){
        return "Hello";}}}
         */
        tokens.add(new Token(TokenType.CONTROL_IF, "if", 5));
        tokens.add(new Token(TokenType.LPAREN, "(", 5));
        tokens.add(new Token(TokenType.IDENTIFIER, "x", 5));
        tokens.add(new Token(TokenType.EQUAL, "==", 5));
        tokens.add(new Token(TokenType.INTEGER_LITERAL, "5", 5));
        tokens.add(new Token(TokenType.RPAREN, ")", 5));
        tokens.add(new Token(TokenType.LEFT_BRACE, "{", 5));
        tokens.add(new Token(TokenType.CONTROL_IF, "if", 5));
        tokens.add(new Token(TokenType.LPAREN, "(", 5));
        tokens.add(new Token(TokenType.IDENTIFIER, "x", 5));
        tokens.add(new Token(TokenType.EQUAL, "==", 5));
        tokens.add(new Token(TokenType.INTEGER_LITERAL, "7", 5));
        tokens.add(new Token(TokenType.RPAREN, ")", 5));
        tokens.add(new Token(TokenType.LEFT_BRACE, "{", 5));
        tokens.add(new Token(TokenType.CONTROL_WHILE, "while", 5));
        tokens.add(new Token(TokenType.LPAREN, "(", 5));
        tokens.add(new Token(TokenType.IDENTIFIER, "x", 5));
        tokens.add(new Token(TokenType.EQUAL, "==", 5));
        tokens.add(new Token(TokenType.INTEGER_LITERAL, "7", 5));
        tokens.add(new Token(TokenType.RPAREN, ")", 5));
        tokens.add(new Token(TokenType.LEFT_BRACE, "{", 5));
        tokens.add(new Token(TokenType.FUNCTION_RETURN, "return", 4));
        tokens.add(new Token(TokenType.STRING_LITERAL, "\"Hello\"", 4));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));
        tokens.add(new Token(TokenType.RIGHT_BRACE, "}", 7));
        tokens.add(new Token(TokenType.RIGHT_BRACE, "}", 7));
        tokens.add(new Token(TokenType.RIGHT_BRACE, "}", 7));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test ReturnStatement int return value ")
    public void testReturnStatementWithIntValue() {

        List<Token> tokens = new ArrayList<>();
        /*
        return 5 ;
         */

        tokens.add(new Token(TokenType.FUNCTION_RETURN, "return", 4));
        tokens.add(new Token(TokenType.INTEGER_LITERAL, "5", 4));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test ReturnStatement, variable as return value ")
    public void testReturnStatementWithVariableValue() {

        List<Token> tokens = new ArrayList<>();
        /*
        return x ;
         */

        tokens.add(new Token(TokenType.FUNCTION_RETURN, "return", 4));
        tokens.add(new Token(TokenType.IDENTIFIER, "x", 4));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test ParserException, variable as return value ")
    public void testParserException() {

        List<Token> tokens = new ArrayList<>();
        /*
        return  ;
         */

        tokens.add(new Token(TokenType.FUNCTION_RETURN, "return", 4));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));

        parser = new Parser(tokens);
        assertThrows( ParseException.class, () -> {
            parser.parseStatementNode();
        });
    }
    @Test
    @DisplayName("Test ReturnStatement, concatenation of string as return value ")
    public void testReturnStatementWithConcatenatedValue() {

        List<Token> tokens = new ArrayList<>();
        /*
        return "Hello" + "Furti" ;
         */
        tokens.add(new Token(TokenType.FUNCTION_RETURN, "return", 4));
        tokens.add(new Token(TokenType.STRING_LITERAL, "\"Hello\"", 4));
        tokens.add(new Token(TokenType.PLUS, "+", 7));
        tokens.add(new Token(TokenType.STRING_LITERAL, "\"Furti\"", 4));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test MethodCall ")
    public void testMethodCall() {

        List<Token> tokens = new ArrayList<>();
        /*
        return get(x);
         */
        tokens.add(new Token(TokenType.FUNCTION_RETURN, "return", 4));
        tokens.add(new Token(TokenType.IDENTIFIER, "get", 4));
        tokens.add(new Token(TokenType.LPAREN, "(", 5));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",8));
        tokens.add(new Token(TokenType.RPAREN, ")", 5));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }
    @Test
    @DisplayName("Test Output statement which prints ID")
    public void testParseOutputStatement() {

        List<Token> tokens = new ArrayList<>();
        /*
        System.out.println(x);
         */

        tokens.add(new Token(TokenType.SYSTEM, "System", 4));
        tokens.add(new Token(TokenType.DOT, ".", 4));
        tokens.add(new Token(TokenType.OUT, "out", 4));
        tokens.add(new Token(TokenType.DOT, ".", 4));
        tokens.add(new Token(TokenType.PRINT, "println", 4));
        tokens.add(new Token(TokenType.LPAREN, "(", 5));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",8));
        tokens.add(new Token(TokenType.RPAREN, ")", 5));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test Output statement which prints string literal")
    public void testParseOutputStatementWithStringOutPut() {

        List<Token> tokens = new ArrayList<>();
        /*
        System.out.println("Hello");
         */

        tokens.add(new Token(TokenType.SYSTEM, "System", 4));
        tokens.add(new Token(TokenType.DOT, ".", 4));
        tokens.add(new Token(TokenType.OUT, "out", 4));
        tokens.add(new Token(TokenType.DOT, ".", 4));
        tokens.add(new Token(TokenType.PRINT, "println", 4));
        tokens.add(new Token(TokenType.LPAREN, "(", 5));
        tokens.add(new Token(TokenType.STRING_LITERAL, "\"Hello\"", 4));
        tokens.add(new Token(TokenType.RPAREN, ")", 5));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test Output statement which prints integer literal")
    public void testParseOutputStatementWithIntOutPut() {

        List<Token> tokens = new ArrayList<>();
        /*
        System.out.println(1);
         */

        tokens.add(new Token(TokenType.SYSTEM, "System", 4));
        tokens.add(new Token(TokenType.DOT, ".", 4));
        tokens.add(new Token(TokenType.OUT, "out", 4));
        tokens.add(new Token(TokenType.DOT, ".", 4));
        tokens.add(new Token(TokenType.PRINT, "println", 4));
        tokens.add(new Token(TokenType.LPAREN, "(", 5));
        tokens.add(new Token(TokenType.INTEGER_LITERAL, "1", 4));
        tokens.add(new Token(TokenType.RPAREN, ")", 5));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));

        parser = new Parser(tokens);
        assertDoesNotThrow( () -> {
            parser.parseStatementNode();
        });
    }

    @Test
    @DisplayName("Test Input statement which assigns to variable")
    public void testParseInputStatement() {

        List<Token> tokens = new ArrayList<>();
        /*
       x = input.nextLine();
         */

        tokens.add(new Token(TokenType.IDENTIFIER, "x", 4));
        tokens.add(new Token(TokenType.ASSIGN, "=", 4));
        tokens.add(new Token(TokenType.INPUT, "input", 4));
        tokens.add(new Token(TokenType.DOT, ".", 4));
        tokens.add(new Token(TokenType.NEXTLINE, "nextLine", 4));
        tokens.add(new Token(TokenType.LPAREN, "(", 5));
        tokens.add(new Token(TokenType.RPAREN, ")", 5));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));

        parser = new Parser(tokens);
        assertDoesNotThrow(() -> {
            parser.parseStatementNode();
        });
    }
}
