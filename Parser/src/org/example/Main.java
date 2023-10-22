package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Token> tokens = new ArrayList<>();

        //number=32;
        tokens.add(new Token(TokenType.IDENTIFIER,"number",1));
        tokens.add(new Token(TokenType.ASSIGN,"=",1));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"32",1));
        tokens.add(new Token(TokenType.SEMICOLON,";",1));

//        //x=300;
        tokens.add(new Token(TokenType.IDENTIFIER,"x",2));
        tokens.add(new Token(TokenType.ASSIGN,"=",2));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"300",2));
        tokens.add(new Token(TokenType.SEMICOLON,";",2));

        //y="World";
        tokens.add(new Token(TokenType.IDENTIFIER, "y", 3));
        tokens.add(new Token(TokenType.ASSIGN, "=", 3));
        tokens.add(new Token(TokenType.STRING_LITERAL, "\"World\"", 3));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 3));

        //string="Hello";
        tokens.add(new Token(TokenType.IDENTIFIER, "string", 4));
        tokens.add(new Token(TokenType.ASSIGN, "=", 4));
        tokens.add(new Token(TokenType.STRING_LITERAL, "\"Hello\"", 4));
        tokens.add(new Token(TokenType.SEMICOLON, ";", 4));

//        //int number = 2674;
        tokens.add(new Token(TokenType.TYPE_INT,"int",5));
        tokens.add(new Token(TokenType.IDENTIFIER,"number",5));
        tokens.add(new Token(TokenType.ASSIGN,"=",6));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"2674",5));
        tokens.add(new Token(TokenType.SEMICOLON,";",5));
//
        //int x;
        tokens.add(new Token(TokenType.TYPE_INT,"int",6));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",6));
        tokens.add(new Token(TokenType.SEMICOLON,";",6));

        //int x = 2674;
        tokens.add(new Token(TokenType.TYPE_INT,"int",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",1));
        tokens.add(new Token(TokenType.ASSIGN,"=",1));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"2674",1));
        tokens.add(new Token(TokenType.SEMICOLON,";",1));

        //String str = "Hello";
        tokens.add(new Token(TokenType.TYPE_STRING,"String",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"str",1));
        tokens.add(new Token(TokenType.ASSIGN,"=",1));
        tokens.add(new Token(TokenType.STRING_LITERAL,"\"Hello\"",1));
        tokens.add(new Token(TokenType.SEMICOLON,";",1));

        //String s;
        tokens.add(new Token(TokenType.TYPE_STRING,"String",2));
        tokens.add(new Token(TokenType.IDENTIFIER,"s",2));
        tokens.add(new Token(TokenType.SEMICOLON,";",2));

        //String s = "Hello";
        tokens.add(new Token(TokenType.TYPE_STRING,"String",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"s",1));
        tokens.add(new Token(TokenType.ASSIGN,"=",1));
        tokens.add(new Token(TokenType.STRING_LITERAL,"\"Hello\"",1));
        tokens.add(new Token(TokenType.SEMICOLON,";",1));

        //int x;
        tokens.add(new Token(TokenType.TYPE_INT,"int",2));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",2));
        tokens.add(new Token(TokenType.SEMICOLON,";",2));
//
//        //int number;
        tokens.add(new Token(TokenType.TYPE_INT,"int",2));
        tokens.add(new Token(TokenType.IDENTIFIER,"number",2));
        tokens.add(new Token(TokenType.SEMICOLON,";",2));
//
//
        //String str;
        tokens.add(new Token(TokenType.TYPE_STRING,"String",2));
        tokens.add(new Token(TokenType.IDENTIFIER,"str",2));
        tokens.add(new Token(TokenType.SEMICOLON,";",2));

        //if(x == 5){y="GoodBye";}

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

        /*
        while(x == 5){
        int x;
        String str;
        x=300;
        }
         */
        tokens.add(new Token(TokenType.KEYWORD_WHILE, "while", 5));
        tokens.add(new Token(TokenType.LPAREN, "(", 5));
        tokens.add(new Token(TokenType.IDENTIFIER, "x", 5));
        tokens.add(new Token(TokenType.EQUAL, "==", 5));
        tokens.add(new Token(TokenType.INTEGER_LITERAL, "5", 5));
        tokens.add(new Token(TokenType.RPAREN, ")", 5));
        tokens.add(new Token(TokenType.LEFT_BRACE, "{", 5));
        tokens.add(new Token(TokenType.TYPE_INT,"int",2));
        tokens.add(new Token(TokenType.IDENTIFIER,"number",2));
        tokens.add(new Token(TokenType.SEMICOLON,";",2));
        tokens.add(new Token(TokenType.TYPE_STRING,"String",2));
        tokens.add(new Token(TokenType.IDENTIFIER,"str",2));
        tokens.add(new Token(TokenType.SEMICOLON,";",2));
        tokens.add(new Token(TokenType.IDENTIFIER,"x",2));
        tokens.add(new Token(TokenType.ASSIGN,"=",2));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"300",2));
        tokens.add(new Token(TokenType.SEMICOLON,";",2));
        tokens.add(new Token(TokenType.RIGHT_BRACE, "}", 7));

        //2+3;
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"2",2));
        tokens.add(new Token(TokenType.PLUS,"+",2));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"300",2));
        tokens.add(new Token(TokenType.SEMICOLON,";",2));

        //2-3;
        tokens.add(new Token(TokenType.IDENTIFIER,"x",2));
        tokens.add(new Token(TokenType.ASSIGN,"=",2));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"2",2));
        tokens.add(new Token(TokenType.MINUS,"-",2));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"300",2));
        tokens.add(new Token(TokenType.SEMICOLON,";",2));

        //2+300+300-300
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

        Parser parser = new Parser(tokens);
        Code_Generator code_generator = new Code_Generator();
        System.out.println(code_generator.generateCode(parser.parseProgram()));



        /*
        IntegerLiteralNode x = new IntegerLiteralNode(12);
        TermNode termX = new TermNode(x);
        IntegerLiteralNode z = new IntegerLiteralNode(12);
        TermNode termZ = new TermNode(z);
        AdditionNode additionNode2 = new AdditionNode(termX,termZ);
        ExpressionNode ex = new ExpressionNode(additionNode2);
        TermNode exE = new TermNode(ex);

        IntegerLiteralNode y = new IntegerLiteralNode(12);
        TermNode termY = new TermNode(y);
        SubtractionNode subtractionNode = new SubtractionNode(termY,exE);
        ExpressionNode expressionNode= new ExpressionNode(subtractionNode);
        ExpressionStatement expressionStatement = new ExpressionStatement(expressionNode);
        StatementNode statementNode = new StatementNode(expressionStatement);
        ArrayList<StatementNode> statementNodes = new ArrayList<>();
        statementNodes.add(statementNode);
        StatementListNode statementListNode = new StatementListNode();
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        System.out.println(code_generator.generateCode(programNode));

         */
    }
}