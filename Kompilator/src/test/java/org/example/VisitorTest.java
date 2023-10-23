package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitorTest {
    @Test
    @DisplayName("Generate a program with no Statements in it")
    void generateEmptyProgram() {
        Code_Generator code_generator = new Code_Generator();
        StatementListNode statementListNode = new StatementListNode();
        ProgramNode programNode = new ProgramNode(statementListNode);
        assertEquals("", code_generator.generateCode(programNode), "did not generate nothing");
    }

    @Test
    @DisplayName("Generate assignment statement x = 32 from tree")
    void generateAssignmentStatementInt() {
        Code_Generator code_generator = new Code_Generator();
        TermList termList = new TermList();
        TermNode termNode = new TermNode(new IntegerLiteralNode(32));
        termList.add(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(new IdentifierNode("x"),new ExpressionNode(termList));
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        assertEquals("x=32", code_generator.generateCode(programNode), "it did not generate x=32");
    }

    @Test
    @DisplayName("Generate x = \"this is a string\" from parse tree")
    void generateStringAssign() {
        TermNode termNode = new TermNode(new StringLiteralNode("this is a string"));
        TermList termList = new TermList();
        termList.add(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(new IdentifierNode("x"),new ExpressionNode(termList));
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=\"this is a string\"", code_generator.generateCode(programNode));
    }

    @Test
    @DisplayName("Generate x=12+20 (addition) from parse tree")
    void generateAddition() {
        TermNode termNode = new TermNode(new IntegerLiteralNode(12));
        TermNode termNode1 = new TermNode(new PositiveTermNode(new IntegerLiteralNode(20)));
        TermList termList = new TermList();
        termList.add(termNode);
        termList.add(termNode1);
        IdentifierNode identifierNode = new IdentifierNode("x");
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode,new ExpressionNode(termList));
        StatementListNode statementListNode = new StatementListNode();
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=12+20", code_generator.generateCode(programNode));
    }

    @Test
    @DisplayName("Generate x=12-20 (subtraction) from parse tree")
    void generateSubtraction() {
        {
            IdentifierNode identifierNode = new IdentifierNode("x");
            TermNode termNode = new TermNode(new IntegerLiteralNode(12));
            TermNode termNode1 = new TermNode(new NegativeTerm(new IntegerLiteralNode(20)));
            TermList termList = new TermList();
            termList.add(termNode);
            termList.add(termNode1);
            ExpressionNode expressionNode = new ExpressionNode(termList);
            AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode, expressionNode);
            StatementNode statementNode = new StatementNode(assignmentStatementNode);
            StatementListNode statementListNode = new StatementListNode();
            statementListNode.addStatement(statementNode);
            ProgramNode programNode = new ProgramNode(statementListNode);
            Code_Generator code_generator = new Code_Generator();
            assertEquals("x=12-20", code_generator.generateCode(programNode), "did not generate x=12-20");
        }
    }

    @Test
    @DisplayName("Generate x=y (assignment with another identifier) from parse tree")
    void testAssignmentIdentifier() {
        IdentifierNode identifierNode = new IdentifierNode("x");
        IdentifierNode identifierNode1 = new IdentifierNode("y");
        TermNode termNode = new TermNode(identifierNode1);
        TermList termList = new TermList();
        termList.add(termNode);
        ExpressionNode expressionNode = new ExpressionNode(termList);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode, expressionNode);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=y", code_generator.generateCode(programNode), "did not get x=y");
    }

    @Test
    @DisplayName("Generate x=3+4-5+6-7 from parser tree (many expressions")
    void testAssignmentMany() {
        IdentifierNode identifierNode = new IdentifierNode("x");
        TermList termList = new TermList();
        TermNode termNode = new TermNode(new IntegerLiteralNode(3));
        termList.add(termNode);
        TermNode termNode1 = new TermNode(new PositiveTermNode(new IntegerLiteralNode(4)));
        termList.add(termNode1);
        TermNode termNode2 = new TermNode(new NegativeTerm(new IntegerLiteralNode(5)));
        termList.add(termNode2);
        TermNode termNode3 = new TermNode(new PositiveTermNode(new IntegerLiteralNode(6)));
        termList.add(termNode3);
        TermNode termNode4 = new TermNode(new NegativeTerm(new IntegerLiteralNode(7)));
        termList.add(termNode4);
        ExpressionNode expressionNode3 = new ExpressionNode(termList);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode, expressionNode3);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=3+4-5+6-7", code_generator.generateCode(programNode), "it went wrong somewhere");
    }
    @Test
    @DisplayName("generate if statement with one expression and one statement inside")
    void generateIfStatementWithOneStatement() {
        IdentifierNode identifierNode = new IdentifierNode("x");
        TermNode termNodeIdentifier = new TermNode(identifierNode);
        StringLiteralNode stringLiteralNode = new StringLiteralNode("true");
        TermNode termNodeString = new TermNode(stringLiteralNode);
        EqualsNode equalsNode = new EqualsNode(termNodeIdentifier, termNodeString);
        ExpressionNode expressionNode = new ExpressionNode(equalsNode);
        IdentifierNode identifierNode1 = new IdentifierNode("y");
        StringLiteralNode stringLiteralNode1 = new StringLiteralNode("false");
        TermNode termNode1 = new TermNode(stringLiteralNode1);
        ExpressionNode expressionNode1 = new ExpressionNode(termNode1);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode1, expressionNode1);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNodeBlock = new StatementListNode();
        statementListNodeBlock.addStatement(statementNode);
        BlockStatement blockStatement = new BlockStatement(statementListNodeBlock);
        IfStatement ifStatement = new IfStatement(expressionNode, blockStatement);
        StatementListNode statementListNode = new StatementListNode();
        StatementNode statementNode1 = new StatementNode(ifStatement);
        statementListNode.addStatement(statementNode1);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("if(x==\"true\"):\n   y=\"false\"", code_generator.generateCode(programNode), "the result was not the one expected");
    }/*


    @Test
    @DisplayName("Generate a while loop with one expression and 3 statements inside, as well as another statement outside after")
    void testWhileMultipleStatements() {
        IdentifierNode identifierNode = new IdentifierNode("x");
        TermNode termNodeIdentifier = new TermNode(identifierNode);
        StringLiteralNode stringLiteralNode = new StringLiteralNode("true");
        TermNode termNodeString = new TermNode(stringLiteralNode);
        EqualsNode equalsNode = new EqualsNode(termNodeIdentifier, termNodeString);
        ExpressionNode expressionNode = new ExpressionNode(equalsNode);
        IdentifierNode identifierNode1 = new IdentifierNode("y");
        StringLiteralNode stringLiteralNode1 = new StringLiteralNode("false");
        TermNode termNode1 = new TermNode(stringLiteralNode1);
        ExpressionNode expressionNode1 = new ExpressionNode(termNode1);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode1, expressionNode1);
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
        TermNode termNodeExpression2 = new TermNode(expressionNode4);
        AdditionNode additionNode = new AdditionNode(termNodeExpression, termNodeExpression2);
        ExpressionNode expressionNode5 = new ExpressionNode(additionNode);
        AssignmentStatementNode assignmentStatementNode2 = new AssignmentStatementNode(identifierNodeB, expressionNode5);
        StatementNode statementNode2 = new StatementNode(assignmentStatementNode2);

        statementListNodeBlock.addStatement(statementNode);
        statementListNodeBlock.addStatement(statementNode1);
        statementListNodeBlock.addStatement(statementNode2);
        BlockStatement blockStatement = new BlockStatement(statementListNodeBlock);
        WhileNode whileNode = new WhileNode(expressionNode, blockStatement);
        StatementListNode statementListNode = new StatementListNode();
        IdentifierNode identifierNodeOut = new IdentifierNode("x");
        IntegerLiteralNode integerLiteralNodeOut = new IntegerLiteralNode(32);
        TermNode termNode = new TermNode(integerLiteralNodeOut);
        ExpressionNode expressionNodeOut = new ExpressionNode(termNode);
        AssignmentStatementNode assignmentStatementNodeOut = new AssignmentStatementNode(identifierNodeOut, expressionNodeOut);
        StatementNode statementNodeOut = new StatementNode(assignmentStatementNodeOut);
        StatementNode statementNode3 = new StatementNode(whileNode);
        statementListNode.addStatement(statementNode3);
        statementListNode.addStatement(statementNodeOut);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("while(x==\"true\"):\n   y=\"false\"\n   z=32\n   b=32+6\nx=32", code_generator.generateCode(programNode), "the result was not the one expected");
    }

    @Test
    @DisplayName("Generate an assignment statement where a empty method call is used")
    void testGenerateAssignmentMethodCall() {
        IdentifierNode identifierNodeOut = new IdentifierNode("x");
        IdentifierNode identifierNode = new IdentifierNode("getName");
        MethodCall methodCall = new MethodCall(identifierNode);
        TermNode termNode = new TermNode(methodCall);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNodeOut, expressionNode);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=getName()", code_generator.generateCode(programNode), "did not get expected results");
    }

    @Test
    @DisplayName("Generate an assignment where an empty method call from another object is used")
    void testGenerateAssignmentObjectFunctionCall() {
        IdentifierNode identifierNodeOut = new IdentifierNode("x");
        IdentifierNode identifierNode = new IdentifierNode("getName");
        IdentifierNode identifierNode1 = new IdentifierNode("Object");
        MethodCall methodCall = new MethodCall(identifierNode1, identifierNode);
        TermNode termNode = new TermNode(methodCall);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNodeOut, expressionNode);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=Object.getName()", code_generator.generateCode(programNode), "did not get expected results");
    }

    @Test
    @DisplayName("Generate an assignment where one parameters are used in a method call from an object")
    void testGenerateAssignmentParametersMethodCall() {
        IdentifierNode identifierNode3 = new IdentifierNode("x");
        IdentifierNode identifierNode = new IdentifierNode("x");
        IdentifierNode identifierNode1 = new IdentifierNode("y");
        TermNode termNode = new TermNode(identifierNode);
        TermNode termNode1 = new TermNode(identifierNode1);
        AdditionNode additionNode = new AdditionNode(termNode, termNode1);
        ExpressionNode expressionNode = new ExpressionNode(additionNode);
        IdentifierNode identifierNode2 = new IdentifierNode("Math");
        IdentifierNode identifierNode4 = new IdentifierNode("getAbsolute");
        MethodCall methodCall = new MethodCall(identifierNode2, identifierNode4, expressionNode);
        TermNode termNode2 = new TermNode(methodCall);
        ExpressionNode expressionNode1 = new ExpressionNode(termNode2);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode3, expressionNode1);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=Math.getAbsolute(x+y)", code_generator.generateCode(programNode), "did not get expected results");
    }

    @Test
    @DisplayName("Generate an assignment with a method with a parameter but not from an object")
    void testGenerateAssignmentNoObject() {
        IdentifierNode identifierNode3 = new IdentifierNode("x");
        IdentifierNode identifierNode = new IdentifierNode("x");
        IdentifierNode identifierNode1 = new IdentifierNode("y");
        TermNode termNode = new TermNode(identifierNode);
        TermNode termNode1 = new TermNode(identifierNode1);
        AdditionNode additionNode = new AdditionNode(termNode, termNode1);
        ExpressionNode expressionNode = new ExpressionNode(additionNode);
        IdentifierNode identifierNode4 = new IdentifierNode("getAbsolute");
        MethodCall methodCall = new MethodCall(identifierNode4, expressionNode);
        TermNode termNode2 = new TermNode(methodCall);
        ExpressionNode expressionNode1 = new ExpressionNode(termNode2);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode3, expressionNode1);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=getAbsolute(x+y)", code_generator.generateCode(programNode), "did not get expected results");
    }


    @Test
    @DisplayName("Generate assignment if a declaration has an assignment")
    void testDeclarationAssignment() {
        IdentifierNode identifierNode = new IdentifierNode("x");
        StringLiteralNode stringLiteralNode = new StringLiteralNode("correct");
        TermNode termNode = new TermNode(stringLiteralNode);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode, expressionNode);
        DeclarationStatement declarationStatement = new DeclarationStatement(assignmentStatementNode);
        StatementNode statementNode = new StatementNode(declarationStatement);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=\"correct\"", code_generator.generateCode(programNode), "did not get expected results");
    }

    @Test
    @DisplayName("generate an assignment where x is equal with the input of the user")
    void testAssignmentInput() {
        IdentifierNode identifierNode = new IdentifierNode("x");
        InputStatement inputStatement = new InputStatement();
        TermNode termNode = new TermNode(inputStatement);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode, expressionNode);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=input()", code_generator.generateCode(programNode), "did not get expected thing");
    }

    @Test
    @DisplayName("Generate a method with no parameters with a return statement at the end")
    void testGenerateMethodWithReturn() {
        IdentifierNode identifierNode = new IdentifierNode("getAdditionResult");
        StatementListNode statementListNodeBlock = new StatementListNode();
        IdentifierNode result = new IdentifierNode("result");
        IdentifierNode firstTerm = new IdentifierNode("x");
        IdentifierNode secondTerm = new IdentifierNode("y");
        TermNode termNode = new TermNode(firstTerm);
        TermNode termNode1 = new TermNode(secondTerm);
        AdditionNode additionNode = new AdditionNode(termNode, termNode1);
        ExpressionNode expressionNode = new ExpressionNode(additionNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(result, expressionNode);
        StatementNode statementNode1 = new StatementNode(assignmentStatementNode);
        statementListNodeBlock.addStatement(statementNode1);
        TermNode termNode2 = new TermNode(result);
        ExpressionNode expressionNode1 = new ExpressionNode(termNode2);
        ReturnStatement returnStatement = new ReturnStatement(expressionNode1);
        StatementNode statementNode = new StatementNode(returnStatement);
        statementListNodeBlock.addStatement(statementNode);
        BlockStatement blockStatement = new BlockStatement(statementListNodeBlock);

        MethodDeclarationStatement methodDeclarationStatement = new MethodDeclarationStatement(identifierNode, blockStatement);
        StatementNode statementNode2 = new StatementNode(methodDeclarationStatement);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode2);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("def getAdditionResult():\n   result=x+y\n   return result", code_generator.generateCode(programNode), "did not get expected result");
    }

    @Test
    @DisplayName("Generate a method declaration with one parameter")
    void testGenerateMethodDeclarationOneParameter() {
        IdentifierNode identifierNode = new IdentifierNode("getAdditionResult");
        StatementListNode statementListNodeBlock = new StatementListNode();
        IdentifierNode result = new IdentifierNode("result");
        IdentifierNode firstTerm = new IdentifierNode("x");
        IdentifierNode secondTerm = new IdentifierNode("y");
        TermNode termNode = new TermNode(firstTerm);
        TermNode termNode1 = new TermNode(secondTerm);
        AdditionNode additionNode = new AdditionNode(termNode, termNode1);
        ExpressionNode expressionNode = new ExpressionNode(additionNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(result, expressionNode);
        StatementNode statementNode1 = new StatementNode(assignmentStatementNode);
        statementListNodeBlock.addStatement(statementNode1);
        TermNode termNode2 = new TermNode(result);
        ExpressionNode expressionNode1 = new ExpressionNode(termNode2);
        ReturnStatement returnStatement = new ReturnStatement(expressionNode1);
        StatementNode statementNode = new StatementNode(returnStatement);
        statementListNodeBlock.addStatement(statementNode);
        BlockStatement blockStatement = new BlockStatement(statementListNodeBlock);
        ParameterNode parameterNode = new ParameterNode( new IdentifierNode("x"));
        ParameterListNode parameterListNode = new ParameterListNode();
        parameterListNode.addParameter(parameterNode);
        MethodDeclarationStatement methodDeclarationStatement = new MethodDeclarationStatement( identifierNode, parameterListNode, blockStatement);
        StatementNode statementNode2 = new StatementNode(methodDeclarationStatement);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode2);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("def getAdditionResult(x):\n   result=x+y\n   return result", code_generator.generateCode(programNode), "did not get expected result");
    }

    @Test
    @DisplayName("generate an output statement that prints Hello World")
    void testOutputHelloWorld() {
        OutputStatement outputStatement = new OutputStatement(new ExpressionNode(new TermNode(new StringLiteralNode("Hello World"))));
        StatementNode statementNode = new StatementNode(outputStatement);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("print(\"Hello World\")", code_generator.generateCode(programNode));
    }
    @Test
    @DisplayName("Generate a if inside a while loop inside a function")
    void testGenerateTripleNested(){
        StatementListNode statementListNodeIf = new StatementListNode();
        BlockStatement blockStatementIf = new BlockStatement(statementListNodeIf);
        IfStatement ifStatement = new IfStatement(new ExpressionNode(new TermNode(new IdentifierNode("yes"))),blockStatementIf);
        StatementListNode statementListNodeWhile = new StatementListNode();
        StatementNode statementNodeIf = new StatementNode(ifStatement);
        statementListNodeWhile.addStatement(statementNodeIf);
        BlockStatement blockStatementWhile = new BlockStatement(statementListNodeWhile);
        WhileNode whileNode = new WhileNode(new ExpressionNode(new EqualsNode(new TermNode(new IdentifierNode("X")),new TermNode(new IntegerLiteralNode(5)))),blockStatementWhile);
        StatementListNode statementListNodeFunction = new StatementListNode();
        StatementNode statementNodeWhile= new StatementNode(whileNode);
        statementListNodeFunction.addStatement(statementNodeWhile);
        BlockStatement blockStatement = new BlockStatement(statementListNodeFunction);
        MethodDeclarationStatement methodDeclarationStatement = new MethodDeclarationStatement(new IdentifierNode("function"),blockStatement);
        StatementListNode statementListNode = new StatementListNode();
        StatementNode statementNode = new StatementNode(methodDeclarationStatement);
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("def function():\n   while(X==5):\n      if(yes):",code_generator.generateCode(programNode),"did not get expected");
    }
    @Test
    @DisplayName("Generate every single type of node in one test using state machine, 100% coverage")
    void testGenerateEverythingOneTest(){
        StatementListNode statementListNodeProgram = new StatementListNode();
        //Generate Declaration with assignment, first line
        ExpressionNode expressionNode = new ExpressionNode(new TermNode(new IdentifierNode("true")));
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(new IdentifierNode("yes"),expressionNode);
        DeclarationStatement declarationStatement = new DeclarationStatement(assignmentStatementNode);
        StatementNode statementNodeDeclaration = new StatementNode(declarationStatement);
        statementListNodeProgram.addStatement(statementNodeDeclaration);
        //Generate lone Assignment with int, first line
        ExpressionNode expressionNode1 = new ExpressionNode(new TermNode(new IntegerLiteralNode(5)));
        StatementNode statementNodeAssignment1 = new StatementNode(new AssignmentStatementNode(new IdentifierNode("five"),expressionNode1));
        statementListNodeProgram.addStatement(statementNodeAssignment1);
        //Generate assignment with string, line 2
        ExpressionNode expressionNode2 = new ExpressionNode(new TermNode(new StringLiteralNode("Zoe")));
        StatementNode statementNode2 = new StatementNode(new AssignmentStatementNode(new IdentifierNode("name"),expressionNode2));
        statementListNodeProgram.addStatement(statementNode2);
        //Generate assignment with addition and subtraction, line 3
        ExpressionNode expressionNode3 = new ExpressionNode(new AdditionNode(new TermNode(new IntegerLiteralNode(5)),new TermNode(new ExpressionNode(new SubtractionNode(new TermNode(new IntegerLiteralNode(6)),new TermNode(new IntegerLiteralNode(7)))))));
        StatementNode statementNode3 = new StatementNode(new AssignmentStatementNode(new IdentifierNode("plusNminus"),expressionNode3));
        statementListNodeProgram.addStatement(statementNode3);
        //Generate assignment with input , line 4
        StatementNode statementNode4 = new StatementNode(new AssignmentStatementNode(new IdentifierNode("say_hi"),new ExpressionNode(new TermNode(new InputStatement()))));
        statementListNodeProgram.addStatement(statementNode4);
        //Generate an assignment with a method call statement, only one identifier, line 5
        MethodCall methodCall = new MethodCall(new IdentifierNode("getSix"));
        StatementNode statementNode = new StatementNode(new AssignmentStatementNode(new IdentifierNode("six"),new ExpressionNode(new TermNode(methodCall))));
        statementListNodeProgram.addStatement(statementNode);
        //Generate an assignment with a method call, two identifiers, line 6
        ExpressionNode expressionNode4 = new ExpressionNode(new TermNode(new MethodCall(new IdentifierNode("Math"),new IdentifierNode("getSeven"))));
        StatementNode statementNode1 = new StatementNode(new AssignmentStatementNode(new IdentifierNode("seven"),expressionNode4));
        statementListNodeProgram.addStatement(statementNode1);
        //Generate an assignment with a method call, one identifier, one parameter, line 7
        ExpressionNode expressionNode6 = new ExpressionNode(new TermNode(new IntegerLiteralNode(5)));
        ExpressionNode expressionNode5 = new ExpressionNode(new TermNode(new MethodCall(new IdentifierNode("getEight"),expressionNode6)));
        StatementNode statementNode5 = new StatementNode(new AssignmentStatementNode(new IdentifierNode("eight"),expressionNode5));
        statementListNodeProgram.addStatement(statementNode5);
        //Generate an assignment with a method call, two identifier, one parameter, line 8
        ExpressionNode expressionNode8 = new ExpressionNode(new TermNode(new IntegerLiteralNode(0)));
        ExpressionNode expressionNode7 = new ExpressionNode(new TermNode(new MethodCall(new IdentifierNode("Math"),new IdentifierNode("getMaxInt"),expressionNode8)));
        StatementNode statementNode6 = new StatementNode(new AssignmentStatementNode(new IdentifierNode("maxInt"),expressionNode7));
        statementListNodeProgram.addStatement(statementNode6);
        //Generate a print statement, line 9
        StatementNode statementNode7 = new StatementNode(new OutputStatement(new ExpressionNode(new TermNode(new StringLiteralNode("Hello World")))));
        statementListNodeProgram.addStatement(statementNode7);
        //Generate a method declaration with a parameter, with a while loop, and an if, and a return
        StatementListNode statementListNodeMethod = new StatementListNode();
        BlockStatement blockStatementMethod = new BlockStatement(statementListNodeMethod);
        ParameterNode parameterNode = new ParameterNode(new IdentifierNode("number"));
        ParameterListNode parameterListNode = new ParameterListNode();
        parameterListNode.addParameter(parameterNode);

        EqualsNode equalsNode = new EqualsNode(new TermNode(new IdentifierNode("name")),new TermNode(new StringLiteralNode("Zoe")));
        StatementListNode statementListNodeWhile = new StatementListNode();
        BlockStatement blockStatementWhile = new BlockStatement(statementListNodeWhile);
        StatementNode statementNode9 = new StatementNode(new WhileNode(new ExpressionNode(equalsNode),blockStatementWhile));
        statementListNodeMethod.addStatement(statementNode9);

        StatementListNode statementListNodeIf = new StatementListNode();
        BlockStatement blockStatementIf = new BlockStatement(statementListNodeIf);
        StatementNode statementNode10 = new StatementNode(new IfStatement(new ExpressionNode(new TermNode(new IdentifierNode("changeName"))),blockStatementIf));
        ExpressionNode expressionNode9 = new ExpressionNode(new TermNode(new StringLiteralNode("Maria")));
        StatementNode statementNode11 = new StatementNode(new AssignmentStatementNode(new IdentifierNode("name"),expressionNode9));
        statementListNodeIf.addStatement(statementNode11);
        statementListNodeWhile.addStatement(statementNode10);
        StatementNode statementNode8 = new StatementNode(new MethodDeclarationStatement(new IdentifierNode("method"),parameterListNode,blockStatementMethod));
        StatementNode statementNode12 = new StatementNode(new ReturnStatement(new ExpressionNode(new TermNode(new IdentifierNode("name")))));
        statementListNodeProgram.addStatement(statementNode8);
        statementListNodeIf.addStatement(statementNode12);
        //Generate a simple method with no parameter, with an output inside
        StatementListNode statementListNodeHello = new StatementListNode();
        BlockStatement blockStatementHello = new BlockStatement(statementListNodeHello);
        StatementNode statementNode13 = new StatementNode(new MethodDeclarationStatement(new IdentifierNode("hello"),blockStatementHello));
        StatementNode statementNode14 = new StatementNode(new OutputStatement(new ExpressionNode(new TermNode(new StringLiteralNode("Hello!")))));
        statementListNodeHello.addStatement(statementNode14);
        statementListNodeProgram.addStatement(statementNode13);
        //Generate a method call as a lone statement
        StatementNode statementNode15 = new StatementNode(new MethodCall(new IdentifierNode("hello")));
        statementListNodeProgram.addStatement(statementNode15);
        ProgramNode programNode = new ProgramNode(statementListNodeProgram);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("""
                yes=true
                five=5
                name="Zoe"
                plusNminus=5+6-7
                say_hi=input()
                six=getSix()
                seven=Math.getSeven()
                eight=getEight(5)
                maxInt=Math.getMaxInt(0)
                print("Hello World")
                def method(number):
                   while(name=="Zoe"):
                      if(changeName):
                         name="Maria"
                         return name
                def hello():
                   print("Hello!")
                hello()""",code_generator.generateCode(programNode),"did not get expected");
    }
}*/}
