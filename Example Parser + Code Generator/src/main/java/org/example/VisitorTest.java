package org.example;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("generate if statement with one expression and one statmeent inside")
    void generateIfStatementWithOneStatement(){
        IdentifierNode identifierNode = new IdentifierNode("x");
        TermNode termNodeIdentifier = new TermNode(identifierNode);
        StringLiteralNode stringLiteralNode = new StringLiteralNode("true");
        TermNode termNodeString = new TermNode(stringLiteralNode);
        EqualsNode equalsNode = new EqualsNode(termNodeIdentifier,termNodeString);
        ExpressionNode expressionNode = new ExpressionNode(equalsNode);
        IdentifierNode identifierNode1 = new IdentifierNode("y");
        StringLiteralNode stringLiteralNode1 = new StringLiteralNode("false");
        TermNode termNode1 = new TermNode(stringLiteralNode1);
        ExpressionNode expressionNode1 = new ExpressionNode(termNode1);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode1,expressionNode1);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNodeBlock = new StatementListNode();
        statementListNodeBlock.addStatement(statementNode);
        BlockStatement blockStatement = new BlockStatement(statementListNodeBlock);
        IfStatement ifStatement = new IfStatement(expressionNode,blockStatement);
        StatementListNode statementListNode = new StatementListNode();
        StatementNode statementNode1 = new StatementNode(ifStatement);
        statementListNode.addStatement(statementNode1);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("if(x==\"true\"):\n   y=\"false\"",code_generator.generateCode(programNode),"the result was not the one expexted");
    }
    @Test
    @DisplayName("Generate a while loop with one expression and 3 statements inside, as well as another statement outside after")
    void testWhileMultipleStatements(){
        IdentifierNode identifierNode = new IdentifierNode("x");
        TermNode termNodeIdentifier = new TermNode(identifierNode);
        StringLiteralNode stringLiteralNode = new StringLiteralNode("true");
        TermNode termNodeString = new TermNode(stringLiteralNode);
        EqualsNode equalsNode = new EqualsNode(termNodeIdentifier,termNodeString);
        ExpressionNode expressionNode = new ExpressionNode(equalsNode);
        IdentifierNode identifierNode1 = new IdentifierNode("y");
        StringLiteralNode stringLiteralNode1 = new StringLiteralNode("false");
        TermNode termNode1 = new TermNode(stringLiteralNode1);
        ExpressionNode expressionNode1 = new ExpressionNode(termNode1);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode1,expressionNode1);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNodeBlock = new StatementListNode();

        IdentifierNode identifierNodeZ = new IdentifierNode("z");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(32);
        TermNode termNodeInt = new TermNode(integerLiteralNode);
        ExpressionNode expressionNode2 = new ExpressionNode(termNodeInt);
        AssignmentStatementNode assignmentStatementNode1 = new AssignmentStatementNode(identifierNodeZ, expressionNode2);
        StatementNode statementNode1 = new StatementNode(assignmentStatementNode1);

        IdentifierNode identifierNodeB = new IdentifierNode("b");
        IntegerLiteralNode integerLiteralNode1 = new IntegerLiteralNode(32);
        TermNode termNodeInt2 = new TermNode(integerLiteralNode1);
        IntegerLiteralNode integerLiteralNode2 = new IntegerLiteralNode(6);
        TermNode termNodeInt3 = new TermNode(integerLiteralNode2);
        ExpressionNode expressionNode3 = new ExpressionNode(termNodeInt2);
        ExpressionNode expressionNode4 = new ExpressionNode(termNodeInt3);
        TermNode termNodeExpression = new TermNode(expressionNode3);
        TermNode termNodeExpression2= new TermNode(expressionNode4);
        AdditionNode additionNode = new AdditionNode(termNodeExpression,termNodeExpression2);
        ExpressionNode expressionNode5 = new ExpressionNode(additionNode);
        AssignmentStatementNode assignmentStatementNode2 = new AssignmentStatementNode(identifierNodeB,expressionNode5);
        StatementNode statementNode2 = new StatementNode(assignmentStatementNode2);

        statementListNodeBlock.addStatement(statementNode);
        statementListNodeBlock.addStatement(statementNode1);
        statementListNodeBlock.addStatement(statementNode2);
        BlockStatement blockStatement = new BlockStatement(statementListNodeBlock);
        WhileNode whileNode = new WhileNode(expressionNode,blockStatement);
        StatementListNode statementListNode = new StatementListNode();
        IdentifierNode identifierNodeOut = new IdentifierNode("x");
        IntegerLiteralNode integerLiteralNodeOut = new IntegerLiteralNode(32);
        TermNode termNode = new TermNode(integerLiteralNodeOut);
        ExpressionNode expressionNodeOut = new ExpressionNode(termNode);
        AssignmentStatementNode assignmentStatementNodeOut = new AssignmentStatementNode(identifierNodeOut,expressionNodeOut);
        StatementNode statementNodeOut = new StatementNode(assignmentStatementNodeOut);
        StatementNode statementNode3 = new StatementNode(whileNode);
        statementListNode.addStatement(statementNode3);
        statementListNode.addStatement(statementNodeOut);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("while(x==\"true\"):\n   y=\"false\"\n   z=32\n   b=32+6\nx=32",code_generator.generateCode(programNode),"the result was not the one expexted");
    }
    @Test
    @DisplayName("Generate an assignment statement where a empty method call is used")
    void testGenerateAssignmentMethodCall(){
        IdentifierNode identifierNodeOut = new IdentifierNode("x");
        IdentifierNode identifierNode = new IdentifierNode("getName");
        MethodCall methodCall = new MethodCall(identifierNode);
        TermNode termNode = new TermNode(methodCall);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNodeOut,expressionNode);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=getName()",code_generator.generateCode(programNode),"did not get expected results");
    }
    @Test
    @DisplayName("Generate an assignment where an empty method call from another object is used")
    void testGenerateAssignmentObjectFunctionCall(){
        IdentifierNode identifierNodeOut = new IdentifierNode("x");
        IdentifierNode identifierNode = new IdentifierNode("getName");
        IdentifierNode identifierNode1 = new IdentifierNode("Object");
        MethodCall methodCall = new MethodCall(identifierNode1,identifierNode);
        TermNode termNode = new TermNode(methodCall);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNodeOut,expressionNode);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=Object.getName()",code_generator.generateCode(programNode),"did not get expected results");
    }
    @Test
    @DisplayName("Generate an assignment where one parameters are used in a method call from an object")
    void testGenerateAssignmentParametersMethodCall(){
        IdentifierNode identifierNode3 = new IdentifierNode("x");
        IdentifierNode identifierNode = new IdentifierNode("x");
        IdentifierNode identifierNode1 = new IdentifierNode("y");
        TermNode termNode = new TermNode(identifierNode);
        TermNode termNode1 = new TermNode(identifierNode1);
        AdditionNode additionNode = new AdditionNode(termNode,termNode1);
        ExpressionNode expressionNode = new ExpressionNode(additionNode);
        IdentifierNode identifierNode2 = new IdentifierNode("Math");
        IdentifierNode identifierNode4 = new IdentifierNode("getAbsolute");
        MethodCall methodCall = new MethodCall(identifierNode2,identifierNode4,expressionNode);
        TermNode termNode2 = new TermNode(methodCall);
        ExpressionNode expressionNode1 = new ExpressionNode(termNode2);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode3,expressionNode1);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=Math.getAbsolute(x+y)",code_generator.generateCode(programNode),"did not get expected results");
    }
    @Test
    @DisplayName("Generate an assignment with a method with a parameter but not from an object")
    void testGenerateAssignmentNoObject(){
        IdentifierNode identifierNode3 = new IdentifierNode("x");
        IdentifierNode identifierNode = new IdentifierNode("x");
        IdentifierNode identifierNode1 = new IdentifierNode("y");
        TermNode termNode = new TermNode(identifierNode);
        TermNode termNode1 = new TermNode(identifierNode1);
        AdditionNode additionNode = new AdditionNode(termNode,termNode1);
        ExpressionNode expressionNode = new ExpressionNode(additionNode);
        IdentifierNode identifierNode4 = new IdentifierNode("getAbsolute");
        MethodCall methodCall = new MethodCall(identifierNode4,expressionNode);
        TermNode termNode2 = new TermNode(methodCall);
        ExpressionNode expressionNode1 = new ExpressionNode(termNode2);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode3,expressionNode1);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=getAbsolute(x+y)",code_generator.generateCode(programNode),"did not get expected results");
    }
    @Test
    @DisplayName("Generate nothing if a declaration does not have an assignment")
    void testDeclarationNoAssignment(){
        TypeNode typeNode = new TypeNode();
        DeclarationStatement declarationStatement = new DeclarationStatement(typeNode);
        StatementNode statementNode = new StatementNode(declarationStatement);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("",code_generator.generateCode(programNode),"did not get expected results");
    }
    @Test
    @DisplayName("Generate assignment if a declaration has an assignment")
    void testDeclarationAssignment(){
        TypeNode typeNode = new TypeNode();
        IdentifierNode identifierNode = new IdentifierNode("x");
        StringLiteralNode stringLiteralNode = new StringLiteralNode("correct");
        TermNode termNode = new TermNode(stringLiteralNode);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode,expressionNode);
        DeclarationStatement declarationStatement = new DeclarationStatement(typeNode,assignmentStatementNode);
        StatementNode statementNode = new StatementNode(declarationStatement);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=\"correct\"",code_generator.generateCode(programNode),"did not get expected results");
    }
    @Test
    @DisplayName("generate an assignment where x is equal with the input of the user")
    void testAssignmentInput(){
        IdentifierNode identifierNode = new IdentifierNode("x");
        InputStatement inputStatement = new InputStatement();
        TermNode termNode = new TermNode(inputStatement);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode,expressionNode);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=input()",code_generator.generateCode(programNode),"did not get expected thing");
    }
    @Test
    @DisplayName("generate a single expression statement 34+5")
    void testExpressionStatement(){
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        IntegerLiteralNode integerLiteralNode1 = new IntegerLiteralNode(5);
        TermNode termNode = new TermNode(integerLiteralNode);
        TermNode termNode1 = new TermNode(integerLiteralNode1);
        AdditionNode additionNode = new AdditionNode(termNode,termNode1);
        ExpressionNode expressionNode = new ExpressionNode(additionNode);
        ExpressionStatement expressionStatement = new ExpressionStatement(expressionNode);
        StatementNode statementNode = new StatementNode(expressionStatement);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("34+5",code_generator.generateCode(programNode),"did not get expected code");
    }
    @Test
    @DisplayName("Generate a method with no parameters with a return statement at the end")
    void testGenerateMethodWithReturn(){
        TypeNode typeNode = new TypeNode();
        IdentifierNode identifierNode = new IdentifierNode("getAdditionResult");
        StatementListNode statementListNodeBlock = new StatementListNode();
        IdentifierNode result = new IdentifierNode("result");
        IdentifierNode firstTerm = new IdentifierNode("x");
        IdentifierNode secondTerm = new IdentifierNode("y");
        TermNode termNode = new TermNode(firstTerm);
        TermNode termNode1 = new TermNode(secondTerm);
        AdditionNode additionNode = new AdditionNode(termNode,termNode1);
        ExpressionNode expressionNode = new ExpressionNode(additionNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(result,expressionNode);
        StatementNode statementNode1 = new StatementNode(assignmentStatementNode);
        statementListNodeBlock.addStatement(statementNode1);
        TermNode termNode2 = new TermNode(result);
        ExpressionNode expressionNode1 = new ExpressionNode(termNode2);
        ReturnStatement returnStatement = new ReturnStatement(expressionNode1);
        StatementNode statementNode = new StatementNode(returnStatement);
        statementListNodeBlock.addStatement(statementNode);
        BlockStatement blockStatement = new BlockStatement(statementListNodeBlock);

        MethodDeclarationStatement methodDeclarationStatement = new MethodDeclarationStatement(typeNode,identifierNode,blockStatement);
        StatementNode statementNode2 = new StatementNode(methodDeclarationStatement);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode2);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("def getAdditionResult():\n   result=x+y\n   return result",code_generator.generateCode(programNode),"did not get expected result");
    }
    @Test
    @DisplayName("Generate a method declaration with one parameter")
    void testGenerateMethodDeclarationOneParameter(){
        TypeNode typeNode = new TypeNode();
        IdentifierNode identifierNode = new IdentifierNode("getAdditionResult");
        StatementListNode statementListNodeBlock = new StatementListNode();
        IdentifierNode result = new IdentifierNode("result");
        IdentifierNode firstTerm = new IdentifierNode("x");
        IdentifierNode secondTerm = new IdentifierNode("y");
        TermNode termNode = new TermNode(firstTerm);
        TermNode termNode1 = new TermNode(secondTerm);
        AdditionNode additionNode = new AdditionNode(termNode,termNode1);
        ExpressionNode expressionNode = new ExpressionNode(additionNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(result,expressionNode);
        StatementNode statementNode1 = new StatementNode(assignmentStatementNode);
        statementListNodeBlock.addStatement(statementNode1);
        TermNode termNode2 = new TermNode(result);
        ExpressionNode expressionNode1 = new ExpressionNode(termNode2);
        ReturnStatement returnStatement = new ReturnStatement(expressionNode1);
        StatementNode statementNode = new StatementNode(returnStatement);
        statementListNodeBlock.addStatement(statementNode);
        BlockStatement blockStatement = new BlockStatement(statementListNodeBlock);
        ParameterNode parameterNode = new ParameterNode(new TypeNode(),new IdentifierNode("x"));
        ParameterListNode parameterListNode = new ParameterListNode();
        parameterListNode.addParameter(parameterNode);
        MethodDeclarationStatement methodDeclarationStatement = new MethodDeclarationStatement(typeNode,identifierNode,parameterListNode,blockStatement);
        StatementNode statementNode2 = new StatementNode(methodDeclarationStatement);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode2);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("def getAdditionResult(x):\n   result=x+y\n   return result",code_generator.generateCode(programNode),"did not get expected result");
    }
    @Test
    @DisplayName("generate an output statement that prints Hello World")
    void testOutputHelloWorld(){
        OutputStatement outputStatement = new OutputStatement(new ExpressionNode(new TermNode(new StringLiteralNode("Hello World"))));
        StatementNode statementNode = new StatementNode(outputStatement);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("print(\"Hello World\")",code_generator.generateCode(programNode));
    }
}
