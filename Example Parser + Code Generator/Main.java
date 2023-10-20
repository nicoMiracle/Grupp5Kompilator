package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.IDENTIFIER,"number",1));
        tokens.add(new Token(TokenType.ASSIGN,"=",1));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"32",1));
        tokens.add(new Token(TokenType.SEMICOLON,";",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"number",1));
        tokens.add(new Token(TokenType.ASSIGN,"=",1));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"300",1));
        tokens.add(new Token(TokenType.SEMICOLON,";",1));
        tokens.add(new Token(TokenType.IDENTIFIER,"number",1));
        tokens.add(new Token(TokenType.ASSIGN,"=",1));
        tokens.add(new Token(TokenType.INTEGER_LITERAL,"2674",1));
        tokens.add(new Token(TokenType.SEMICOLON,";",1));
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