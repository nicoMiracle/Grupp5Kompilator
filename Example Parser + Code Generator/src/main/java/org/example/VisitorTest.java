package org.example;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class VisitorTest {
    @Test
    @DisplayName("Generate a program with no Statements in it")
    void generateEmptyProgram() {
        Code_Generator code_generator = new Code_Generator();
        StatementListNode statementListNode = new StatementListNode();
        ProgramNode programNode = new ProgramNode(statementListNode);
        assertEquals("",code_generator.generateCode(programNode),"did not generate nothing");
    }
    @Test
    @DisplayName("Generate assignment statement x = 32 from tree")
    void generateAssignmentStatementInt(){
        Code_Generator code_generator = new Code_Generator();
        IdentifierNode identifierNode = new IdentifierNode("x");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(32);
        TermNode termNode = new TermNode(integerLiteralNode);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode,expressionNode);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        assertEquals("x=32",code_generator.generateCode(programNode),"it did not generate x=32");
    }
    @Test
    @DisplayName("Generate x = \"this is a string\" from parse tree")
    void generateStringAssign(){
        StringLiteralNode stringLiteralNode = new StringLiteralNode("this is a string");
        TermNode termNode = new TermNode(stringLiteralNode);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        IdentifierNode identifierNode = new IdentifierNode("x");
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode,expressionNode);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=\"this is a string\"",code_generator.generateCode(programNode));
    }
    @Test
    @DisplayName("Generate x=12+20 (addition) from parse tree")
    void generateAddition(){
        IdentifierNode identifierNode = new IdentifierNode("x");
        IntegerLiteralNode x = new IntegerLiteralNode(12);
        TermNode termX = new TermNode(x);
        IntegerLiteralNode y = new IntegerLiteralNode(20);
        TermNode termY = new TermNode(y);
        AdditionNode additionNode = new AdditionNode(termX,termY);
        ExpressionNode expressionNode= new ExpressionNode(additionNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode,expressionNode);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=12+20",code_generator.generateCode(programNode));
    }
    @Test
    @DisplayName("Generate x=12-20 (subtraction) from parse tree")
    void generateSubtraction(){{
        IdentifierNode identifierNode = new IdentifierNode("x");
        IntegerLiteralNode x = new IntegerLiteralNode(12);
        TermNode termX = new TermNode(x);
        IntegerLiteralNode y = new IntegerLiteralNode(20);
        TermNode termY = new TermNode(y);
        SubtractionNode subtractionNode = new SubtractionNode(termX,termY);
        ExpressionNode expressionNode= new ExpressionNode(subtractionNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode,expressionNode);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=12-20",code_generator.generateCode(programNode),"did not generate x=12-20");
    }}
    @Test
    @DisplayName("Generate x=y (assignment with another identifier) from parse tree" )
    void testAssignmentIdentifier(){
        IdentifierNode identifierNode = new IdentifierNode("x");
        IdentifierNode identifierNode1 = new IdentifierNode("y");
        TermNode termNode = new TermNode(identifierNode1);
        ExpressionNode expressionNode= new ExpressionNode(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode,expressionNode);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=y",code_generator.generateCode(programNode),"did not get x=y");
    }
    @Test
    @DisplayName("Generate x=3+4-5+6-7 from parser tree (many expressions")
    void testAssignmentMany(){
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
        assertEquals("x=3+4-5+6-7",code_generator.generateCode(programNode),"it went wrong somewhere");
    }
    @Test
    @DisplayName("Test making a method call statement with only two parameters")
    void generateWhileLoopWithOneStatement(){

    }
}
