package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {/*
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

        IdentifierNode identifierNode = new IdentifierNode("x");
        IntegerLiteralNode x = new IntegerLiteralNode(3);
        TermNode termX = new TermNode(x);
        IntegerLiteralNode y = new IntegerLiteralNode(4);
        TermNode termY = new TermNode(y);
        AdditionNode additionNode = new AdditionNode(termX,termY);
        ExpressionNode expressionNode = new ExpressionNode(additionNode);
        TermNode termNode = new TermNode(expressionNode);
        IntegerLiteralNode z = new IntegerLiteralNode(5);
        TermNode termZ = new TermNode(z);
        SubtractionNode subtractionNode = new SubtractionNode(termNode,termZ);
        ExpressionNode expressionNode1 = new ExpressionNode(subtractionNode);
        TermNode termNode1 = new TermNode(expressionNode1);
        IntegerLiteralNode b = new IntegerLiteralNode(6);
        TermNode termB = new TermNode(b);
        AdditionNode additionNode1 = new AdditionNode(termNode1,termB);
        ExpressionNode expressionNode2 = new ExpressionNode(additionNode1);
        TermNode termNode2 = new TermNode(expressionNode2);
        IntegerLiteralNode m = new IntegerLiteralNode(7);
        TermNode termM = new TermNode(m);
        SubtractionNode subtractionNode1 = new SubtractionNode(termNode2,termM);
        ExpressionNode expressionNode3 = new ExpressionNode(subtractionNode1);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode,expressionNode3);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        System.out.println(code_generator.generateCode(programNode));*/
    }
}