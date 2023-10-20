package org.example;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NodesTest {
    @Test
    @DisplayName("Test String Literal Constructor node")
    void testStringLiteralConstructor(){
        StringLiteralNode stringLiteralNode = new StringLiteralNode("test");
        assertEquals("test",stringLiteralNode.getValue(),"String literal did not get a value");
    }
    @Test
    @DisplayName("Test Integer Literal constructor node")
    void testIntegerLiteralConstructor(){
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(32);
        assertEquals(32,integerLiteralNode.getValue(),"could not get the correct result from the node");
    }
    @Test
    @DisplayName("Test the constructor of the identifier node")
    void testIdentifierConstructor(){
        IdentifierNode identifierNode = new IdentifierNode("test");
        assertEquals("test",identifierNode.getName());
    }
    @Test
    @DisplayName("Test term constructor with String Literal Node")
    void testStringTermConstructor(){
        StringLiteralNode stringLiteralNode = new StringLiteralNode("test");
        TermNode termNode = new TermNode(stringLiteralNode);
        assertEquals(stringLiteralNode,termNode.getStringLiteralNode(),"the incorrect object got brought");
    }
    @Test
    @DisplayName("Test term constructor with Integer Literal Node")
    void testIntegerTermConstructor(){
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        TermNode termNode = new TermNode(integerLiteralNode);
        assertEquals(integerLiteralNode,termNode.getIntegerLiteralNode(),"the incorrect object got brought");
    }
    @Test
    @DisplayName("Test term constructor with Identifier node")
    void testIdentifierTermConstructor(){
        IdentifierNode identifierNode = new IdentifierNode("number");
        TermNode termNode = new TermNode(identifierNode);
        assertEquals(identifierNode,termNode.getIdentifierNode(),"the incorrect object got brought");
    }
    @Test
    @DisplayName("Test expression constructor with term node")
    void testExpressionWithTerm(){
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        TermNode termNode = new TermNode(integerLiteralNode);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        assertEquals(termNode,expressionNode.getTermNode(),"the incorrect object got brought");
    }
    @Test
    @DisplayName("Test first term of the addition node")
    void testAdditionFirstTerm(){
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        TermNode termNode = new TermNode(integerLiteralNode);
        IntegerLiteralNode integerLiteralNode1 = new IntegerLiteralNode(50);
        TermNode termNode1 = new TermNode(integerLiteralNode1);
        AdditionNode additionNode = new AdditionNode(termNode,termNode1);
        assertEquals(termNode,additionNode.getFirstTermNode(),"incorrect object");
    }
    @Test
    @DisplayName("Test second term of the addition node")
    void testAdditionSecondTerm(){
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        TermNode termNode = new TermNode(integerLiteralNode);
        IntegerLiteralNode integerLiteralNode1 = new IntegerLiteralNode(50);
        TermNode termNode1 = new TermNode(integerLiteralNode1);
        AdditionNode additionNode = new AdditionNode(termNode,termNode1);
        assertEquals(termNode1,additionNode.getSecondTermNode(),"incorrect object");
    }
    @Test
    @DisplayName("Test expression constructor with addition node")
    void testExpressionWithAddition(){
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        TermNode termNode = new TermNode(integerLiteralNode);
        IntegerLiteralNode integerLiteralNode1 = new IntegerLiteralNode(50);
        TermNode termNode1 = new TermNode(integerLiteralNode1);
        AdditionNode additionNode = new AdditionNode(termNode,termNode1);
        ExpressionNode expressionNode = new ExpressionNode(additionNode);
        assertEquals(additionNode,expressionNode.getAdditionNode(),"did not get addition node");
    }
    @Test
    @DisplayName("Test first term of subtraction node")
    void testSubtractionFirstTerm(){
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        TermNode termNode = new TermNode(integerLiteralNode);
        IntegerLiteralNode integerLiteralNode1 = new IntegerLiteralNode(50);
        TermNode termNode1 = new TermNode(integerLiteralNode1);
        SubtractionNode subtractionNode = new SubtractionNode(termNode,termNode1);
        assertEquals(termNode,subtractionNode.getFirstTermNode(),"wrongful object got");
    }
    @Test
    @DisplayName("Test second term of subtraction node")
    void testSubtractionSecondTerm(){
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        TermNode termNode = new TermNode(integerLiteralNode);
        IntegerLiteralNode integerLiteralNode1 = new IntegerLiteralNode(50);
        TermNode termNode1 = new TermNode(integerLiteralNode1);
        SubtractionNode subtractionNode = new SubtractionNode(termNode,termNode1);
        assertEquals(termNode1,subtractionNode.getSecondTermNode(),"wrongful object got");
    }
    @Test
    @DisplayName("Test subtraction in expression")
    void testSubtractionInExpression(){
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        TermNode termNode = new TermNode(integerLiteralNode);
        IntegerLiteralNode integerLiteralNode1 = new IntegerLiteralNode(50);
        TermNode termNode1 = new TermNode(integerLiteralNode1);
        SubtractionNode subtractionNode = new SubtractionNode(termNode,termNode1);
        ExpressionNode expressionNode = new ExpressionNode(subtractionNode);
        assertEquals(subtractionNode,expressionNode.getSubtractionNode(),"something went wrong");
    }
    @Test
    @DisplayName("Test first term of equals node")
    void testFirstTermEquals(){
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        TermNode termNode = new TermNode(integerLiteralNode);
        IntegerLiteralNode integerLiteralNode1 = new IntegerLiteralNode(50);
        TermNode termNode1 = new TermNode(integerLiteralNode1);
        EqualsNode equalsNode = new EqualsNode(termNode,termNode1);
        assertEquals(termNode,equalsNode.getFirstTerm(),"got the wrong object");
    }
    @Test
    @DisplayName("Test Second term of equals node")
    void testSecondTermEquals(){
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        TermNode termNode = new TermNode(integerLiteralNode);
        IntegerLiteralNode integerLiteralNode1 = new IntegerLiteralNode(50);
        TermNode termNode1 = new TermNode(integerLiteralNode1);
        EqualsNode equalsNode = new EqualsNode(termNode,termNode1);
        assertEquals(termNode1,equalsNode.getSecondTerm(),"got the wrong object");
    }
    @Test
    @DisplayName("Test term constructor with an expression")
    void testTermWithExpression(){
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        TermNode termNode = new TermNode(integerLiteralNode);
        IntegerLiteralNode integerLiteralNode1 = new IntegerLiteralNode(50);
        TermNode termNode1 = new TermNode(integerLiteralNode1);
        AdditionNode additionNode = new AdditionNode(termNode,termNode1);
        ExpressionNode expressionNode = new ExpressionNode(additionNode);
        TermNode termNodeExpression = new TermNode(expressionNode);
        assertEquals(expressionNode,termNodeExpression.getExpressionNode(),"something went wrong");
    }
    @Test
    @DisplayName("Test Assign Statements Identifier")
    void testAssignIdentifier(){
        IdentifierNode identifierNode = new IdentifierNode("variableName");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        TermNode termNode = new TermNode(integerLiteralNode);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode,expressionNode);
        assertEquals(identifierNode,assignmentStatementNode.getIdentifier(),"something went wrong");
    }
    @Test
    @DisplayName("Test Assign Statements Expression")
    void testAssignExpression(){
        IdentifierNode identifierNode = new IdentifierNode("variableName");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        TermNode termNode = new TermNode(integerLiteralNode);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode,expressionNode);
        assertEquals(expressionNode,assignmentStatementNode.getExpression(),"something went wrong");
    }
    @Test
    @DisplayName("Test expression statement Node")
    void testExpressionStatement(){
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        TermNode termNode = new TermNode(integerLiteralNode);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        ExpressionStatement expressionStatement = new ExpressionStatement(expressionNode);
        assertEquals(expressionNode,expressionStatement.getExpression(),"something went wrong");
    }
    @Test
    @DisplayName("Test type")
        void testTypeNode(){
            TypeNode typeNode = new TypeNode("String");
            assertEquals("String",typeNode.getType(),"something went wrong");
    }
    @Test
    @DisplayName("Test Declaration Statement type ")
    void testTypeDeclaration(){
        TypeNode typeNode = new TypeNode("void");
        IdentifierNode identifierNode = new IdentifierNode("x");
        DeclarationStatement declarationStatement = new DeclarationStatement(typeNode,identifierNode);
        assertEquals(typeNode,declarationStatement.getType(),"something went wrong");
    }
    @Test
    @DisplayName("Test Declaration Statement ID")
    void testTypeIdentificationDeclaration(){
        TypeNode typeNode = new TypeNode("void");
        IdentifierNode identifierNode = new IdentifierNode("x");
        DeclarationStatement declarationStatement = new DeclarationStatement(typeNode,identifierNode);
        assertEquals(identifierNode,declarationStatement.getIdentifierNode(),"something went wrong");
    }
    @Test
    @DisplayName("Test declaration statement but with an assignment statement")
    void testDeclarationWithAssignment(){
        TypeNode typeNode = new TypeNode("void");
        IdentifierNode identifierNode = new IdentifierNode("x");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(5);
        TermNode termNode = new TermNode(integerLiteralNode);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode,expressionNode);
        DeclarationStatement declarationStatement = new DeclarationStatement(typeNode, assignmentStatementNode);
        assertEquals(assignmentStatementNode,declarationStatement.getAssignment(),"something went wrong");
    }
    @Test
    @DisplayName("Test Expression statement")
    void testExpressionStatementNode(){
        IdentifierNode identifierNode = new IdentifierNode("thirty");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(30);
        TermNode termNode = new TermNode(integerLiteralNode);
        TermNode termNode1 = new TermNode(identifierNode);
        EqualsNode equalsNode = new EqualsNode(termNode,termNode1);
        ExpressionNode expressionNode = new ExpressionNode(equalsNode);
        ExpressionStatement expressionStatement = new ExpressionStatement(expressionNode);
        assertEquals(expressionNode,expressionStatement.getExpression(),"something went wrong");
    }
    @Test
    @DisplayName("Test Statement Node with AssignStatement")
    void testStatementNode(){
        IdentifierNode identifierNode = new IdentifierNode("variableName");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        TermNode termNode = new TermNode(integerLiteralNode);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode,expressionNode);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        assertEquals(assignmentStatementNode,statementNode.getAssign(),"something went wrong");
    }
    @Test
    @DisplayName("Test Statement Node with Expression Statement")
    void testExpressionStatementInStatementNode(){
        IdentifierNode identifierNode = new IdentifierNode("thirty");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(30);
        TermNode termNode = new TermNode(integerLiteralNode);
        TermNode termNode1 = new TermNode(identifierNode);
        EqualsNode equalsNode = new EqualsNode(termNode,termNode1);
        ExpressionNode expressionNode = new ExpressionNode(equalsNode);
        ExpressionStatement expressionStatement = new ExpressionStatement(expressionNode);
        StatementNode statementNode = new StatementNode(expressionStatement);
        assertEquals(expressionStatement,statementNode.getExpressionStatement(),"something went wrong");
    }
    @Test
    @DisplayName("Test statement list with a statement")
    void testStatementListOneEntry(){
        IdentifierNode identifierNode = new IdentifierNode("variableName");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        TermNode termNode = new TermNode(integerLiteralNode);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode,expressionNode);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        assertTrue(statementListNode.getStatementNodes().contains(statementNode), "something went wrong");
    }
    @Test
    @DisplayName("Test Program Node with a List")
    void testProgramNode(){
        IdentifierNode identifierNode = new IdentifierNode("variableName");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        TermNode termNode = new TermNode(integerLiteralNode);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode,expressionNode);
        StatementNode statementNode = new StatementNode(assignmentStatementNode);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNode);
        ProgramNode programNode = new ProgramNode(statementListNode);
        assertEquals(statementListNode,programNode.getStatements());
    }
    @Test
    @DisplayName("Test a while statements expression")
    void testWhileStatement(){
        IdentifierNode identifierNode = new IdentifierNode("thirty");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(30);
        TermNode termNode = new TermNode(integerLiteralNode);
        TermNode termNode1 = new TermNode(identifierNode);
        EqualsNode equalsNode = new EqualsNode(termNode,termNode1);
        ExpressionNode expressionNode = new ExpressionNode(equalsNode);
        StatementListNode statementListNode = new StatementListNode();
        BlockStatement blockStatement = new BlockStatement(statementListNode);
        WhileNode whileNode = new WhileNode(expressionNode,blockStatement);
        assertEquals(expressionNode, whileNode.getExpression(),"something went wrong");
    }
    @Test
    @DisplayName("Test a while statements block")
    void testWhileBlock(){
        IdentifierNode identifierNode = new IdentifierNode("thirty");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(30);
        TermNode termNode = new TermNode(integerLiteralNode);
        TermNode termNode1 = new TermNode(identifierNode);
        EqualsNode equalsNode = new EqualsNode(termNode,termNode1);
        ExpressionNode expressionNode = new ExpressionNode(equalsNode);
        StatementListNode statementListNode = new StatementListNode();
        BlockStatement blockStatement = new BlockStatement(statementListNode);
        WhileNode whileNode = new WhileNode(expressionNode,blockStatement);
        assertEquals(blockStatement,whileNode.getBlockStatement(),"something went wrong");
    }
    @Test
    @DisplayName("Test a while statement in statement Node")
    void testWhileStatementInStatement(){
        IdentifierNode identifierNode = new IdentifierNode("thirty");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(30);
        TermNode termNode = new TermNode(integerLiteralNode);
        TermNode termNode1 = new TermNode(identifierNode);
        EqualsNode equalsNode = new EqualsNode(termNode,termNode1);
        ExpressionNode expressionNode = new ExpressionNode(equalsNode);
        StatementListNode statementListNode = new StatementListNode();
        BlockStatement blockStatement = new BlockStatement(statementListNode);
        WhileNode whileNode = new WhileNode(expressionNode,blockStatement);
        StatementNode statementNode = new StatementNode(whileNode);
        assertEquals(whileNode,statementNode.getWhile(),"something went wrong");
    }
    @Test
    @DisplayName("Test the expression value of If statement node")
    void testIfStatementExpression(){
        IdentifierNode identifierNode = new IdentifierNode("thirty");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(30);
        TermNode termNode = new TermNode(integerLiteralNode);
        TermNode termNode1 = new TermNode(identifierNode);
        EqualsNode equalsNode = new EqualsNode(termNode,termNode1);
        ExpressionNode expressionNode = new ExpressionNode(equalsNode);
        StatementListNode statementListNode = new StatementListNode();
        BlockStatement blockStatement = new BlockStatement(statementListNode);
        IfStatement ifStatement = new IfStatement(expressionNode,blockStatement);
        assertEquals(expressionNode,ifStatement.getExpression(),"something went wrong");
    }
    @Test
    @DisplayName("Test the block object  of If statement node")
    void testBlockInIf(){
        IdentifierNode identifierNode = new IdentifierNode("thirty");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(30);
        TermNode termNode = new TermNode(integerLiteralNode);
        TermNode termNode1 = new TermNode(identifierNode);
        EqualsNode equalsNode = new EqualsNode(termNode,termNode1);
        ExpressionNode expressionNode = new ExpressionNode(equalsNode);
        StatementListNode statementListNode = new StatementListNode();
        BlockStatement blockStatement = new BlockStatement(statementListNode);
        IfStatement ifStatement = new IfStatement(expressionNode,blockStatement);
        assertEquals(blockStatement,ifStatement.getBlockStatement(),"something went wrong");
    }
    @Test
    @DisplayName("Test if Statement in Statement Node")
    void testIfInStatementNode(){
        IdentifierNode identifierNode = new IdentifierNode("thirty");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(30);
        TermNode termNode = new TermNode(integerLiteralNode);
        TermNode termNode1 = new TermNode(identifierNode);
        EqualsNode equalsNode = new EqualsNode(termNode,termNode1);
        ExpressionNode expressionNode = new ExpressionNode(equalsNode);
        StatementListNode statementListNode = new StatementListNode();
        BlockStatement blockStatement = new BlockStatement(statementListNode);
        IfStatement ifStatement = new IfStatement(expressionNode,blockStatement);
        StatementNode statementNode = new StatementNode(ifStatement);
        assertEquals(ifStatement,statementNode.getIfStatement(),"something went wrong");
    }
    @Test
    @DisplayName("Test return statement")
    void testReturn(){
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        TermNode termNode = new TermNode(integerLiteralNode);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        ReturnStatement returnStatement = new ReturnStatement(expressionNode);
        assertEquals(expressionNode,returnStatement.getExpression(),"something went wrong");
    }
    @Test
    @DisplayName("Test return statement in Statement node")
    void testReturnInStatement(){
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
        TermNode termNode = new TermNode(integerLiteralNode);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        ReturnStatement returnStatement = new ReturnStatement(expressionNode);
        StatementNode statementNode= new StatementNode(returnStatement);
        assertEquals(returnStatement,statementNode.getReturn(),"something went wrong");
    }
    @Test
    @DisplayName("Test Method Declaration Statements type")
    void testMethodDeclarationType(){
        TypeNode typeNode = new TypeNode("String");
        IdentifierNode identifierNode = new IdentifierNode("getSomething");
        ParameterListNode parameterList = new ParameterListNode();
        StatementListNode statementListNode = new StatementListNode();
        BlockStatement blockStatement = new BlockStatement(statementListNode);
        MethodDeclarationStatement methodDeclaration = new MethodDeclarationStatement(typeNode,identifierNode,parameterList,blockStatement);
        assertEquals(typeNode,methodDeclaration.getType(),"something went wrong");
    }
    @Test
    @DisplayName("Test Method Declaration Statements ID")
    void testMethodDeclarationID(){
        TypeNode typeNode = new TypeNode("String");
        IdentifierNode identifierNode = new IdentifierNode("getSomething");
        ParameterListNode parameterList = new ParameterListNode();
        StatementListNode statementListNode = new StatementListNode();
        BlockStatement blockStatement = new BlockStatement(statementListNode);
        MethodDeclarationStatement methodDeclaration = new MethodDeclarationStatement(typeNode,identifierNode,parameterList,blockStatement);
        assertEquals(identifierNode,methodDeclaration.getID(),"something went wrong");
    }
    @Test
    @DisplayName("Test Method Declaration Statements ParameterList")
    void testMethodDeclarationParameterList(){
        TypeNode typeNode = new TypeNode("String");
        IdentifierNode identifierNode = new IdentifierNode("getSomething");
        ParameterListNode parameterList = new ParameterListNode();
        StatementListNode statementListNode = new StatementListNode();
        BlockStatement blockStatement = new BlockStatement(statementListNode);
        MethodDeclarationStatement methodDeclaration = new MethodDeclarationStatement(typeNode,identifierNode,parameterList,blockStatement);
        assertEquals(parameterList,methodDeclaration.getParameters(),"something went wrong");
    }

    @Test
    @DisplayName("Test Method Declaration Statements Block Statement")
    void testMethodDeclarationBlockStatement(){
        TypeNode typeNode = new TypeNode("String");
        IdentifierNode identifierNode = new IdentifierNode("getSomething");
        ParameterListNode parameterList = new ParameterListNode();
        StatementListNode statementListNode = new StatementListNode();
        BlockStatement blockStatement = new BlockStatement(statementListNode);
        MethodDeclarationStatement methodDeclaration = new MethodDeclarationStatement(typeNode,identifierNode,parameterList,blockStatement);
        assertEquals(blockStatement,methodDeclaration.getBlock(),"something went wrong");
    }
    @Test
    @DisplayName("Test the parameter nodes Type")
    void testParameterNodeType(){
        TypeNode typeNode = new TypeNode("int");
        IdentifierNode identifierNode = new IdentifierNode("number");
        ParameterNode parameterNode = new ParameterNode(typeNode,identifierNode);
        assertEquals(typeNode,parameterNode.getType(),"something went wrong");
    }
    @Test
    @DisplayName("Test the parameter nodes ID")
    void testParameterNodeID(){
        TypeNode typeNode = new TypeNode("int");
        IdentifierNode identifierNode = new IdentifierNode("number");
        ParameterNode parameterNode = new ParameterNode(typeNode,identifierNode);
        assertEquals(identifierNode,parameterNode.getID(),"something went wrong");
    }
    @Test
    @DisplayName("Test parameter list node with one parameter")
    void testParameterListWithParameter(){
        TypeNode typeNode = new TypeNode("int");
        IdentifierNode identifierNode = new IdentifierNode("number");
        ParameterNode parameterNode = new ParameterNode(typeNode,identifierNode);
        ParameterListNode parameterListNode= new ParameterListNode();
        parameterListNode.addParameter(parameterNode);
        assertTrue(parameterListNode.getParameterNodes().contains(parameterNode), "something went wrong");
    }
    @Test
    @DisplayName("Test method calls method ID")
    void testMethodCall(){
        IdentifierNode identifierNode = new IdentifierNode("getname");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(30);
        TermNode termNode = new TermNode(integerLiteralNode);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        MethodCall methodCall = new MethodCall(identifierNode,expressionNode);
        assertEquals(identifierNode,methodCall.getMethodID(),"something went wrong");
    }
    @Test
    @DisplayName("Test method calls method Expression")
    void testMethodCallExpression(){
        IdentifierNode identifierNode = new IdentifierNode("getname");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(30);
        TermNode termNode = new TermNode(integerLiteralNode);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        MethodCall methodCall = new MethodCall(identifierNode,expressionNode);
        assertEquals(expressionNode,methodCall.getExpression(),"something went wrong");
    }
    @Test
    @DisplayName("Test method call from another identifier")
    void testMethodCallFromIdentifier(){
        IdentifierNode stringID = new IdentifierNode("this is a string variable ID");
        IdentifierNode identifierMethod = new IdentifierNode("trim()");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(30);
        TermNode termNode = new TermNode(integerLiteralNode);
        ExpressionNode expressionNode = new ExpressionNode(termNode);
        MethodCall methodCall = new MethodCall(stringID,identifierMethod,expressionNode);
        assertEquals(stringID,methodCall.getOptionalID(),"something went wrong");
    }
    @Test
    @DisplayName("Test declaration statement in Statement Node")
    void testDeclarationInStatement(){
        TypeNode typeNode = new TypeNode("void");
        IdentifierNode identifierNode = new IdentifierNode("x");
        DeclarationStatement declarationStatement = new DeclarationStatement(typeNode,identifierNode);
        StatementNode statementNode = new StatementNode(declarationStatement);
        assertEquals(declarationStatement,statementNode.getDeclaration(),"something went wrong");
    }
    @Test
    @DisplayName("Test Method Declaration in Statement Node")
    void testMethodDeclarationInStatementNode(){
        TypeNode typeNode = new TypeNode("String");
        IdentifierNode identifierNode = new IdentifierNode("getSomething");
        ParameterListNode parameterList = new ParameterListNode();
        StatementListNode statementListNode = new StatementListNode();
        BlockStatement blockStatement = new BlockStatement(statementListNode);
        MethodDeclarationStatement methodDeclaration = new MethodDeclarationStatement(typeNode,identifierNode,parameterList,blockStatement);
        StatementNode statementNode = new StatementNode(methodDeclaration);
        assertEquals(methodDeclaration,statementNode.getMethodDeclaration(),"something went wrong");
    }
    @Test
    @DisplayName("Test block statement in Statement Node")
    void testBlockInStatementNode(){
        IdentifierNode identifierNode = new IdentifierNode("thirty");
        IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(30);
        TermNode termNode = new TermNode(integerLiteralNode);
        TermNode termNode1 = new TermNode(identifierNode);
        EqualsNode equalsNode = new EqualsNode(termNode,termNode1);
        ExpressionNode expressionNode = new ExpressionNode(equalsNode);
        ExpressionStatement expressionStatement = new ExpressionStatement(expressionNode);
        StatementNode statementNodeExpression = new StatementNode(expressionStatement);
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(statementNodeExpression);
        BlockStatement blockStatement = new BlockStatement(statementListNode);
        StatementNode statementNode = new StatementNode(blockStatement);
        assertEquals(blockStatement,statementNode.getBlock(),"something went wrong");
    }
    @Test
    @DisplayName("Test method call statement in Statement node")
        void testMethodCallInStatement(){
            IdentifierNode identifierNode = new IdentifierNode("getname");
            IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(30);
            TermNode termNode = new TermNode(integerLiteralNode);
            ExpressionNode expressionNode = new ExpressionNode(termNode);
            MethodCall methodCall = new MethodCall(identifierNode,expressionNode);
            StatementNode statementNode = new StatementNode(methodCall);
            assertEquals(methodCall,statementNode.getMethodCall(),"something went wrong");
        }
        @Test
    @DisplayName("Test Input Statement in Statement node")
    void testInputStatementInStatement(){
        InputStatement inputStatement = new InputStatement();
        StatementNode statementNode = new StatementNode(inputStatement);
        assertEquals(inputStatement,statementNode.getInput(),"something went wrong");
        }
        @Test
    @DisplayName("Test output Statement")
    void testOutputStatement(){
            IdentifierNode identifierNode = new IdentifierNode("getname");
            TermNode termNode = new TermNode(identifierNode);
            ExpressionNode expressionNode = new ExpressionNode(termNode);
            OutputStatement outputStatement = new OutputStatement(expressionNode);
            assertEquals(expressionNode,outputStatement.getExpression(),"something went wrong");
        }
        @Test
        @DisplayName("test getting the Statement list node of Block statement")
        void testBlockGetStatements(){
            IdentifierNode identifierNode = new IdentifierNode("variableName");
            IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(34);
            TermNode termNode = new TermNode(integerLiteralNode);
            ExpressionNode expressionNode = new ExpressionNode(termNode);
            AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode,expressionNode);
            StatementNode statementNode = new StatementNode(assignmentStatementNode);
            StatementListNode statementListNode = new StatementListNode();
            statementListNode.addStatement(statementNode);
            BlockStatement blockStatement = new BlockStatement(statementListNode);
            assertEquals(statementListNode,blockStatement.getStatements(),"something went wrong");
        }
        @Test
    @DisplayName("Test getting an equals expresions from Expression Node")
    void testGetEqualsInExpression(){
            IdentifierNode identifierNode = new IdentifierNode("thirty");
            IntegerLiteralNode integerLiteralNode = new IntegerLiteralNode(30);
            TermNode termNode = new TermNode(integerLiteralNode);
            TermNode termNode1 = new TermNode(identifierNode);
            EqualsNode equalsNode = new EqualsNode(termNode,termNode1);
            ExpressionNode expressionNode = new ExpressionNode(equalsNode);
            assertEquals(equalsNode,expressionNode.getEqualsNode(),"something went wrong");
        }
        @Test
    @DisplayName("Test output statement in Statement node")
    void testStatementNodeOutput(){
            IdentifierNode identifierNode = new IdentifierNode("getname");
            TermNode termNode = new TermNode(identifierNode);
            ExpressionNode expressionNode = new ExpressionNode(termNode);
            OutputStatement outputStatement = new OutputStatement(expressionNode);
            StatementNode statementNode = new StatementNode(outputStatement);
            assertEquals(outputStatement,statementNode.getOutput(),"something went wrong");
        }
    }
