package org.example;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class VisitorTest {
    @Test
    @DisplayName("Generate x = 32 from parse tree")
    void GenerateIntAssign() {
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=32",code_generator.generateCode(xInt()));
    }
    @Test
    @DisplayName("Generate x = \"this is a string\" from parse tree")
    void GenerateStringAssign(){
        StringLiteralNode stringLiteralNode = new StringLiteralNode("this is a string");
        TermNode termNode = new TermNode(stringLiteralNode);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        IdentifierNode identifierNode = new IdentifierNode("x");
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode,expressionNode);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);

        ArrayList<StatementNode> statements = new ArrayList<>();
        statements.add(statementNode);
        StatementListNode statementListNode = new StatementListNode();
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=\"this is a string\"",code_generator.generateCode(programNode));
    }
    @Test
    @DisplayName("Generate 12+20 (addition) from parse tree")
    void GenerateAddition(){
        IntegerLiteralNode x = new IntegerLiteralNode(12);
        TermNode termX = new TermNode(x);
        IntegerLiteralNode y = new IntegerLiteralNode(20);
        TermNode termY = new TermNode(y);
        AdditionNode additionNode = new AdditionNode(termX,termY);
        ExpressionNode expressionNode= new ExpressionNode(additionNode);
        ExpressionStatement expressionStatement = new ExpressionStatement(expressionNode);
        StatementNode statementNode = new StatementNode(expressionStatement);
        ArrayList<StatementNode> statementNodes = new ArrayList<>();
        statementNodes.add(statementNode);
        StatementListNode statementListNode = new StatementListNode();
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("12+20",code_generator.generateCode(programNode));
    }
    @Test
    @DisplayName("Generate 12-20 (subtraction) from parse tree")
    void GenerateSubtraction(){{

    }}
    private ProgramNode xInt(){
        IntegerLiteralNode integer = new IntegerLiteralNode(32);
        TermNode termNode = new TermNode(integer);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        IdentifierNode identifierNode = new IdentifierNode("x");
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode,expressionNode);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);

        ArrayList<StatementNode> statements = new ArrayList<>();
        statements.add(statementNode);
        StatementListNode statementListNode = new StatementListNode();
        ProgramNode programNode = new ProgramNode(statementListNode);
        return programNode;
    }

}
