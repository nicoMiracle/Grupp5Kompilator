package compilator;

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
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(assignmentIntTree());
        ProgramNode programNode = new ProgramNode(statementListNode);
        assertEquals("x=32", code_generator.generateCode(programNode), "it did not generate x=32");
    }
    private StatementNode assignmentIntTree(){
        TermList termList = new TermList();
        TermNode termNode = new TermNode(new IntegerLiteralNode(32));
        termList.add(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(new IdentifierNode("x"),new ExpressionNode(termList));
        return new StatementNode(assignmentStatementNode);
    }

    @Test
    @DisplayName("Generate x = \"this is a string\" from parse tree")
    void generateStringAssign() {
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(assignmentStringTree());
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=\"this is a string\"", code_generator.generateCode(programNode));
    }
    private StatementNode assignmentStringTree(){
        TermNode termNode = new TermNode(new StringLiteralNode("this is a string"));
        TermList termList = new TermList();
        termList.add(termNode);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(new IdentifierNode("x"),new ExpressionNode(termList));
        return new StatementNode(assignmentStatementNode);
    }
    @Test
    @DisplayName("Generate x=y (assignment with another identifier) from parse tree")
    void testAssignmentIdentifier() {
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(assignmentIdentifierTree());
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=y", code_generator.generateCode(programNode), "did not get x=y");
    }
    private StatementNode assignmentIdentifierTree(){
        IdentifierNode identifierNode = new IdentifierNode("x");
        IdentifierNode identifierNode1 = new IdentifierNode("y");
        TermNode termNode = new TermNode(identifierNode1);
        TermList termList = new TermList();
        termList.add(termNode);
        ExpressionNode expressionNode = new ExpressionNode(termList);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode, expressionNode);
        return new StatementNode(assignmentStatementNode);
    }

    @Test
    @DisplayName("Generate x=12+20 (addition) from parse tree")
    void generateAddition() {
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(additionTree());
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=12+20", code_generator.generateCode(programNode));
    }
    private StatementNode additionTree(){
        TermNode termNode = new TermNode(new IntegerLiteralNode(12));
        TermNode termNode1 = new TermNode(new PositiveTermNode(new IntegerLiteralNode(20)));
        TermList termList = new TermList();
        termList.add(termNode);
        termList.add(termNode1);
        IdentifierNode identifierNode = new IdentifierNode("x");
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode,new ExpressionNode(termList));
        return new StatementNode(assignmentStatementNode);
    }

    @Test
    @DisplayName("Generate x=12-20 (subtraction) from parse tree")
    void generateSubtraction() {
        {
            StatementListNode statementListNode = new StatementListNode();
            statementListNode.addStatement(subtractionTree());
            ProgramNode programNode = new ProgramNode(statementListNode);
            Code_Generator code_generator = new Code_Generator();
            assertEquals("x=12-20", code_generator.generateCode(programNode), "did not generate x=12-20");
        }
    }
    private StatementNode subtractionTree(){
        IdentifierNode identifierNode = new IdentifierNode("x");
        TermNode termNode = new TermNode(new IntegerLiteralNode(12));
        TermNode termNode1 = new TermNode(new NegativeTerm(new IntegerLiteralNode(20)));
        TermList termList = new TermList();
        termList.add(termNode);
        termList.add(termNode1);
        ExpressionNode expressionNode = new ExpressionNode(termList);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode, expressionNode);
        return new StatementNode(assignmentStatementNode);
    }

    @Test
    @DisplayName("Generate x=3+4-5+6-7 from parser tree (many expressions")
    void testAssignmentMany() {
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(mixedExpressionTree());
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=3+4-5+6-7", code_generator.generateCode(programNode), "it went wrong somewhere");
    }
    private StatementNode mixedExpressionTree(){
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
        return new StatementNode(assignmentStatementNode);
    }
    @Test
    @DisplayName("generate if statement with one expression and one statement inside")
    void generateIfStatementWithOneStatement() {
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(ifStatementTree());
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("if(x==\"true\"):\n   y=\"false\"", code_generator.generateCode(programNode), "the result was not the one expected");
    }
    private StatementNode ifStatementTree(){
        StatementListNode statementListNodeIf = new StatementListNode();
        BlockStatement blockStatementIf = new BlockStatement(statementListNodeIf);
        IfStatement ifStatement = new IfStatement(new ExpressionNode(new EqualsNode(new TermNode(new IdentifierNode("x")),new TermNode(new StringLiteralNode("true")))),blockStatementIf);
        TermNode termNode = new TermNode(new StringLiteralNode("false"));
        TermList termList = new TermList();
        termList.add(termNode);
        ExpressionNode expressionNode = new ExpressionNode(termList);
        StatementNode statementNode = new StatementNode(new AssignmentStatementNode(new IdentifierNode("y"),expressionNode));
        statementListNodeIf.addStatement(statementNode);
        return new StatementNode(ifStatement);
    }

    @Test
    @DisplayName("Generate a while loop with one expression and 3 statements inside, as well as another statement outside after")
    void testWhileMultipleStatements() {
        StatementListNode statementListNode = new StatementListNode();
        ProgramNode programNode = new ProgramNode(statementListNode);
        statementListNode.addStatement(whileMultipleStatementsTree());
        TermList termList3 = new TermList();
        termList3.add(new TermNode(new IntegerLiteralNode(32)));
        ExpressionNode expressionNode3 = new ExpressionNode(termList3);
        StatementNode statementNode3 = new StatementNode(new AssignmentStatementNode(new IdentifierNode("x"), expressionNode3));
        statementListNode.addStatement(statementNode3);

        Code_Generator code_generator = new Code_Generator();
        assertEquals("while(x==\"true\"):\n   y=\"false\"\n   z=32\n   b=32+6\nx=32", code_generator.generateCode(programNode), "the result was not the one expected");
    }
private StatementNode whileMultipleStatementsTree(){
    StatementListNode statementListNodeWhile = new StatementListNode();
    BlockStatement blockStatementWhile = new BlockStatement(statementListNodeWhile);
    WhileNode whileNode = new WhileNode(new ExpressionNode(new EqualsNode(new TermNode(new IdentifierNode("x")),new TermNode(new StringLiteralNode("true")))),blockStatementWhile);

    TermList termList = new TermList();
    termList.add(new TermNode(new StringLiteralNode("false")));
    ExpressionNode expressionNode = new ExpressionNode(termList);
    StatementNode statementNode = new StatementNode(new AssignmentStatementNode(new IdentifierNode("y"),expressionNode));
    statementListNodeWhile.addStatement(statementNode);

    TermList termList1 = new TermList();
    termList1.add(new TermNode(new IntegerLiteralNode(32)));
    ExpressionNode expressionNode1 = new ExpressionNode(termList1);
    StatementNode statementNode1 = new StatementNode(new AssignmentStatementNode(new IdentifierNode("z"),expressionNode1));
    statementListNodeWhile.addStatement(statementNode1);

    TermList termList2 = new TermList();
    termList2.add(new TermNode(new IntegerLiteralNode(32)));
    termList2.add(new TermNode(new PositiveTermNode(new IntegerLiteralNode(6))));
    ExpressionNode expressionNode2 = new ExpressionNode(termList2);
    StatementNode statementNode2 = new StatementNode(new AssignmentStatementNode(new IdentifierNode("b"), expressionNode2));
    statementListNodeWhile.addStatement(statementNode2);
    return new StatementNode(whileNode);
}
    @Test
    @DisplayName("Generate an assignment statement where a empty method call is used")
    void testGenerateAssignmentMethodCall() {
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(emptyMethodCallTree());
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=getName()", code_generator.generateCode(programNode), "did not get expected results");
    }
    private StatementNode emptyMethodCallTree(){
        IdentifierNode identifierNodeOut = new IdentifierNode("x");
        IdentifierNode identifierNode = new IdentifierNode("getName");
        MethodCall methodCall = new MethodCall(identifierNode);
        TermNode termNode = new TermNode(methodCall);
        TermList termList = new TermList();
        termList.add(termNode);
        ExpressionNode expressionNode = new ExpressionNode(termList);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNodeOut, expressionNode);
        return new StatementNode(assignmentStatementNode);
    }

    @Test
    @DisplayName("Generate an assignment where an empty method call from another object is used")
    void testGenerateAssignmentObjectFunctionCall() {

        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(methodCallObjectTree());
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=Object.getName()", code_generator.generateCode(programNode), "did not get expected results");
    }
    private StatementNode methodCallObjectTree(){
        IdentifierNode identifierNodeOut = new IdentifierNode("x");
        IdentifierNode identifierNode = new IdentifierNode("getName");
        IdentifierNode identifierNode1 = new IdentifierNode("Object");
        MethodCall methodCall = new MethodCall(identifierNode1, identifierNode);
        TermNode termNode = new TermNode(methodCall);
        TermList termList = new TermList();
        termList.add(termNode);
        ExpressionNode expressionNode = new ExpressionNode(termList);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNodeOut, expressionNode);
        return new StatementNode(assignmentStatementNode);
    }

    @Test
    @DisplayName("Generate an assignment where one parameters are used in a method call from an object")
    void testGenerateAssignmentParametersMethodCall() {
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(methodCallObjectParameter());
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=Math.getAbsolute(x+y)", code_generator.generateCode(programNode), "did not get expected results");
    }
    private StatementNode methodCallObjectParameter(){
        IdentifierNode identifierNode3 = new IdentifierNode("x");
        IdentifierNode identifierNode = new IdentifierNode("x");
        IdentifierNode identifierNode1 = new IdentifierNode("y");
        TermNode termNode = new TermNode(identifierNode);
        TermNode termNode1 = new TermNode(new PositiveTermNode(identifierNode1));
        TermList termList = new TermList();
        termList.add(termNode);
        termList.add(termNode1);
        ExpressionNode expressionNode = new ExpressionNode(termList);
        IdentifierNode identifierNode2 = new IdentifierNode("Math");
        IdentifierNode identifierNode4 = new IdentifierNode("getAbsolute");
        MethodCall methodCall = new MethodCall(identifierNode2, identifierNode4, expressionNode);
        TermNode termNode2 = new TermNode(methodCall);
        TermList termList1 = new TermList();
        termList1.add(termNode2);
        ExpressionNode expressionNode1 = new ExpressionNode(termList1);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode3, expressionNode1);
        return new StatementNode(assignmentStatementNode);
    }

    @Test
    @DisplayName("Generate an assignment with a method with a parameter but not from an object")
    void testGenerateAssignmentNoObject() {
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(methodCallParameter());
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=getAbsolute(x+y)", code_generator.generateCode(programNode), "did not get expected results");
    }
    private StatementNode methodCallParameter(){
        IdentifierNode identifierNode3 = new IdentifierNode("x");
        IdentifierNode identifierNode = new IdentifierNode("x");
        IdentifierNode identifierNode1 = new IdentifierNode("y");
        TermNode termNode = new TermNode(identifierNode);
        TermNode termNode1 = new TermNode(new PositiveTermNode(identifierNode1));

        TermList termList = new TermList();
        termList.add(termNode);
        termList.add(termNode1);
        ExpressionNode expressionNode = new ExpressionNode(termList);
        IdentifierNode identifierNode4 = new IdentifierNode("getAbsolute");
        MethodCall methodCall = new MethodCall(identifierNode4, expressionNode);
        TermNode termNode2 = new TermNode(methodCall);
        TermList termList1 = new TermList();
        termList1.add(termNode2);
        ExpressionNode expressionNode1 = new ExpressionNode(termList1);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode3, expressionNode1);
        return new StatementNode(assignmentStatementNode);
    }

    @Test
    @DisplayName("Generate assignment if a declaration has an assignment")
    void testDeclarationAssignment() {
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(declarationAssignmentTree());
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=\"correct\"", code_generator.generateCode(programNode), "did not get expected results");
    }
    private StatementNode declarationAssignmentTree(){
        IdentifierNode identifierNode = new IdentifierNode("x");
        StringLiteralNode stringLiteralNode = new StringLiteralNode("correct");
        TermNode termNode = new TermNode(stringLiteralNode);
        TermList termList = new TermList();
        termList.add(termNode);
        ExpressionNode expressionNode = new ExpressionNode(termList);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode, expressionNode);
        DeclarationStatement declarationStatement = new DeclarationStatement(assignmentStatementNode);
        return new StatementNode(declarationStatement);
    }

    @Test
    @DisplayName("generate an assignment where x is equal with the input of the user")
    void testAssignmentInput() {
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(assignmentInputTree());
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=input()", code_generator.generateCode(programNode), "did not get expected thing");
    }
    private StatementNode assignmentInputTree(){
        IdentifierNode identifierNode = new IdentifierNode("x");
        InputStatement inputStatement = new InputStatement();
        TermNode termNode = new TermNode(inputStatement);
        TermList termList = new TermList();
        termList.add(termNode);
        ExpressionNode expressionNode = new ExpressionNode(termList);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(identifierNode, expressionNode);
        return new StatementNode(assignmentStatementNode);
    }
    @Test
    @DisplayName("Generate an assignment where all types of positive")
    void testAllPositive(){
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(allPositiveTree());
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=1+2+\"text\"+ID+input()+method()",code_generator.generateCode(programNode),"got wrong result");
    }
    private StatementNode allPositiveTree(){
        TermList termList = new TermList();
        termList.add(new TermNode(new IntegerLiteralNode(1)));
        termList.add(new TermNode(new PositiveTermNode(new IntegerLiteralNode(2))));
        termList.add(new TermNode(new PositiveTermNode(new StringLiteralNode("text"))));
        termList.add(new TermNode(new PositiveTermNode(new IdentifierNode("ID"))));
        termList.add(new TermNode(new PositiveTermNode(new InputStatement())));
        termList.add(new TermNode(new PositiveTermNode(new MethodCall(new IdentifierNode("method")))));
        ExpressionNode expressionNode = new ExpressionNode(termList);
        return new StatementNode(new AssignmentStatementNode(new IdentifierNode("x"),expressionNode));
    }
    @Test
    @DisplayName("Generate an assignment where all types of negatives")
    void testAllNegatives(){
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(allNegativeTree());
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("x=1-2-\"text\"-ID-input()-method()",code_generator.generateCode(programNode),"got wrong result");
    }
    private StatementNode allNegativeTree(){
        TermList termList = new TermList();
        termList.add(new TermNode(new IntegerLiteralNode(1)));
        termList.add(new TermNode(new NegativeTerm(new IntegerLiteralNode(2))));
        termList.add(new TermNode(new NegativeTerm(new StringLiteralNode("text"))));
        termList.add(new TermNode(new NegativeTerm(new IdentifierNode("ID"))));
        termList.add(new TermNode(new NegativeTerm(new InputStatement())));
        termList.add(new TermNode(new NegativeTerm(new MethodCall(new IdentifierNode("method")))));
        ExpressionNode expressionNode = new ExpressionNode(termList);
        return new StatementNode(new AssignmentStatementNode(new IdentifierNode("x"),expressionNode));
    }
    @Test
    @DisplayName("Generate a method with no parameters with a return statement at the end")
    void testGenerateMethodWithReturn() {
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(methodReturnTree());
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("def getAdditionResult():\n   result=x+y\n   return result", code_generator.generateCode(programNode), "did not get expected result");
    }
    private StatementNode methodReturnTree(){
        IdentifierNode identifierNode = new IdentifierNode("getAdditionResult");
        StatementListNode statementListNodeBlock = new StatementListNode();
        IdentifierNode result = new IdentifierNode("result");
        IdentifierNode firstTerm = new IdentifierNode("x");
        IdentifierNode secondTerm = new IdentifierNode("y");
        TermList termList = new TermList();
        termList.add(new TermNode(firstTerm));
        termList.add(new TermNode(new PositiveTermNode(secondTerm)));
        ExpressionNode expressionNode = new ExpressionNode(termList);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(result, expressionNode);
        StatementNode statementNode1 = new StatementNode(assignmentStatementNode);
        statementListNodeBlock.addStatement(statementNode1);

        TermList termList1 = new TermList();
        termList1.add(new TermNode(result));
        ExpressionNode expressionNode1 = new ExpressionNode(termList1);
        ReturnStatement returnStatement = new ReturnStatement(expressionNode1);
        StatementNode statementNode = new StatementNode(returnStatement);
        statementListNodeBlock.addStatement(statementNode);
        BlockStatement blockStatement = new BlockStatement(statementListNodeBlock);

        MethodDeclarationStatement methodDeclarationStatement = new MethodDeclarationStatement(identifierNode, blockStatement);
        return new StatementNode(methodDeclarationStatement);
    }

    @Test
    @DisplayName("Generate a method declaration with one parameter")
    void testGenerateMethodDeclarationOneParameter() {
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(methodParameter());
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("def getAdditionResult(x):\n   result=x+y\n   return result", code_generator.generateCode(programNode), "did not get expected result");
    }
    private StatementNode methodParameter(){
        IdentifierNode identifierNode = new IdentifierNode("getAdditionResult");
        StatementListNode statementListNodeBlock = new StatementListNode();
        IdentifierNode result = new IdentifierNode("result");
        IdentifierNode firstTerm = new IdentifierNode("x");
        IdentifierNode secondTerm = new IdentifierNode("y");
        TermList termList1 = new TermList();
        termList1.add(new TermNode(firstTerm));
        termList1.add(new TermNode(new PositiveTermNode(secondTerm)));
        ExpressionNode expressionNode = new ExpressionNode(termList1);
        AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode(result, expressionNode);
        StatementNode statementNode1 = new StatementNode(assignmentStatementNode);
        statementListNodeBlock.addStatement(statementNode1);
        TermList termList = new TermList();
        termList.add(new TermNode(result));
        ExpressionNode expressionNode1 = new ExpressionNode(termList);
        ReturnStatement returnStatement = new ReturnStatement(expressionNode1);
        StatementNode statementNode = new StatementNode(returnStatement);
        statementListNodeBlock.addStatement(statementNode);
        BlockStatement blockStatement = new BlockStatement(statementListNodeBlock);
        ParameterNode parameterNode = new ParameterNode( new IdentifierNode("x"));
        ParameterListNode parameterListNode = new ParameterListNode();
        parameterListNode.addParameter(parameterNode);
        MethodDeclarationStatement methodDeclarationStatement = new MethodDeclarationStatement( identifierNode, parameterListNode, blockStatement);
        return new StatementNode(methodDeclarationStatement);
    }

    @Test
    @DisplayName("generate an output statement that prints Hello World")
    void testOutputHelloWorld() {
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(outputTree());
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("print(\"Hello World\")", code_generator.generateCode(programNode));
    }
    private StatementNode outputTree(){
        TermList termList = new TermList();
        termList.add(new TermNode(new StringLiteralNode("Hello World")));
        OutputStatement outputStatement = new OutputStatement(new ExpressionNode(termList));
        return new StatementNode(outputStatement);
    }

    @Test
    @DisplayName("Generate a if inside a while loop inside a function")
    void testGenerateTripleNested(){
        StatementListNode statementListNode = new StatementListNode();
        statementListNode.addStatement(nestedTree());
        ProgramNode programNode = new ProgramNode(statementListNode);
        Code_Generator code_generator = new Code_Generator();
        assertEquals("def function():\n   while(X==5):\n      if(yes):",code_generator.generateCode(programNode),"did not get expected");
    }
    private StatementNode nestedTree(){
        StatementListNode statementListNodeIf = new StatementListNode();
        BlockStatement blockStatementIf = new BlockStatement(statementListNodeIf);
        TermList termList = new TermList();
        termList.add(new TermNode(new IdentifierNode("yes")));
        IfStatement ifStatement = new IfStatement(new ExpressionNode(termList),blockStatementIf);
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
        return new StatementNode(methodDeclarationStatement);
    }
    @Test
    @DisplayName("Generate every single type of node in one test using state machine, 100% coverage")
    void testGenerateEverythingOneTest(){
        StatementListNode statementListNodeProgram = new StatementListNode();
        ProgramNode programNode = new ProgramNode(statementListNodeProgram);
        //All positives
        statementListNodeProgram.addStatement(allPositives());
        //All negatives
        statementListNodeProgram.addStatement(allNegatives());
        //Declaration Assignment with int
        statementListNodeProgram.addStatement(declarationIntStatement());
        //Assignment with String
        statementListNodeProgram.addStatement(assignmentStringStatement());
        //Assignment with input
        statementListNodeProgram.addStatement(assignmentInputStatement());
        //Assignment with method call, one identifier
        statementListNodeProgram.addStatement(assignmentMethodCallStatement());
        //Assignment with method call, two identifier
        statementListNodeProgram.addStatement(objectMethodCallStatement());
        //Assignment with method call, one identifier, one parameter
        statementListNodeProgram.addStatement(methodCallParameterStatement());
        //Assignment with method call, two identifier, one parameter
        statementListNodeProgram.addStatement(objectMethodCallParameterStatement());
        //One line of output
        statementListNodeProgram.addStatement(OutputStatement());
        //One method with a parameter, one while inside that, one if inside, one variable and return
        statementListNodeProgram.addStatement(nestedMethodWhileIfReturnStatement());
        //method declaration, no parameters, one output inside
        statementListNodeProgram.addStatement(methodDeclarationStatement());
        //one method call as statement
        statementListNodeProgram.addStatement(new StatementNode(new MethodCall(new IdentifierNode("hello"))));
        Code_Generator code_generator = new Code_Generator();
        assertEquals(expectedLongString(),code_generator.generateCode(programNode),"did not get expected");
    }
    private String expectedLongString(){
        return """
                x=1+2+"text"+ID+input()+method()
                x=1-2-"text"-ID-input()-method()
                int=5
                string="Zoe"
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
                hello()""";
    }
    private StatementNode allPositives(){
        TermList termList = new TermList();
        termList.add(new TermNode(new IntegerLiteralNode(1)));
        termList.add(new TermNode(new PositiveTermNode(new IntegerLiteralNode(2))));
        termList.add(new TermNode(new PositiveTermNode(new StringLiteralNode("text"))));
        termList.add(new TermNode(new PositiveTermNode(new IdentifierNode("ID"))));
        termList.add(new TermNode(new PositiveTermNode(new InputStatement())));
        termList.add(new TermNode(new PositiveTermNode(new MethodCall(new IdentifierNode("method")))));
        ExpressionNode expressionNode = new ExpressionNode(termList);
        return new StatementNode(new AssignmentStatementNode(new IdentifierNode("x"),expressionNode));
    }
    private StatementNode allNegatives(){
        TermList termList1 = new TermList();
        termList1.add(new TermNode(new IntegerLiteralNode(1)));
        termList1.add(new TermNode(new NegativeTerm(new IntegerLiteralNode(2))));
        termList1.add(new TermNode(new NegativeTerm(new StringLiteralNode("text"))));
        termList1.add(new TermNode(new NegativeTerm(new IdentifierNode("ID"))));
        termList1.add(new TermNode(new NegativeTerm(new InputStatement())));
        termList1.add(new TermNode(new NegativeTerm(new MethodCall(new IdentifierNode("method")))));
        ExpressionNode expressionNode1 = new ExpressionNode(termList1);
        return new StatementNode(new AssignmentStatementNode(new IdentifierNode("x"), expressionNode1));
    }
    private StatementNode declarationIntStatement(){
        TermList termList2 = new TermList();
        termList2.add(new TermNode(new IntegerLiteralNode(5)));
        ExpressionNode expressionNode2 = new ExpressionNode(termList2);
        return new StatementNode(new DeclarationStatement(new AssignmentStatementNode(new IdentifierNode("int"),expressionNode2)));
    }
    private StatementNode assignmentStringStatement(){
        TermList termList3 = new TermList();
        termList3.add(new TermNode(new StringLiteralNode("Zoe")));
        ExpressionNode expressionNode3 = new ExpressionNode(termList3);
        return new StatementNode(new AssignmentStatementNode(new IdentifierNode("string"), expressionNode3));
    }
    private StatementNode assignmentInputStatement(){
        TermList termList4 = new TermList();
        termList4.add(new TermNode(new InputStatement()));
        ExpressionNode expressionNode4 = new ExpressionNode(termList4);
        return new StatementNode(new AssignmentStatementNode(new IdentifierNode("say_hi"), expressionNode4));
    }
    private StatementNode assignmentMethodCallStatement(){
        TermList termList5 = new TermList();
        termList5.add(new TermNode(new MethodCall(new IdentifierNode("getSix"))));
        ExpressionNode expressionNode5 = new ExpressionNode(termList5);
        return new StatementNode(new AssignmentStatementNode(new IdentifierNode("six"),expressionNode5));
    }
    private StatementNode objectMethodCallStatement(){
        TermList termList6 = new TermList();
        termList6.add(new TermNode(new MethodCall(new IdentifierNode("Math"),new IdentifierNode("getSeven"))));
        ExpressionNode expressionNode6 = new ExpressionNode(termList6);
        return new StatementNode(new AssignmentStatementNode(new IdentifierNode("seven"), expressionNode6));
    }
    private StatementNode methodCallParameterStatement(){
        TermList termList7 = new TermList();
        TermList termList8 = new TermList();
        termList8.add(new TermNode(new IntegerLiteralNode(5)));
        termList7.add(new TermNode(new MethodCall(new IdentifierNode("getEight"),new ExpressionNode(termList8))));
        ExpressionNode expressionNode7 = new ExpressionNode(termList7);
        return new StatementNode(new AssignmentStatementNode(new IdentifierNode("eight"), expressionNode7));
    }
    private StatementNode objectMethodCallParameterStatement(){
        TermList termList9 = new TermList();
        TermList termList10 = new TermList();
        termList10.add(new TermNode(new IntegerLiteralNode(0)));
        termList9.add(new TermNode(new MethodCall(new IdentifierNode("Math"),new IdentifierNode("getMaxInt"),new ExpressionNode(termList10))));
        ExpressionNode expressionNode8 = new ExpressionNode(termList9);
        return new StatementNode(new AssignmentStatementNode(new IdentifierNode("maxInt"), expressionNode8));
    }
    private StatementNode OutputStatement(){
        TermList termList11 = new TermList();
        termList11.add(new TermNode(new StringLiteralNode("Hello World")));
        OutputStatement outputStatement = new OutputStatement(new ExpressionNode(termList11));
        return new StatementNode(outputStatement);
    }
    private StatementNode nestedMethodWhileIfReturnStatement(){
        ParameterListNode parameterListNode = new ParameterListNode();
        parameterListNode.addParameter(new ParameterNode(new IdentifierNode("number")));
        StatementListNode statementListNode1 = new StatementListNode();
        BlockStatement blockStatementMethod = new BlockStatement(statementListNode1);
        StatementListNode statementListNode2 = new StatementListNode();
        TermList termList12 = new TermList();
        termList12.add(new TermNode(new IdentifierNode("changeName")));
        StatementListNode statementListNode3 = new StatementListNode();
        TermList termList13 = new TermList();
        termList13.add(new TermNode(new StringLiteralNode("Maria")));
        ExpressionNode expressionNode9 = new ExpressionNode(termList13);
        statementListNode3.addStatement(new StatementNode(new AssignmentStatementNode(new IdentifierNode("name"),expressionNode9)));
        BlockStatement blockStatementIf = new BlockStatement(statementListNode3);
        StatementNode statementNode3 = new StatementNode(new IfStatement(new ExpressionNode(termList12),blockStatementIf));
        statementListNode2.addStatement(statementNode3);
        TermList termList14 = new TermList();
        termList14.add(new TermNode(new IdentifierNode("name")));
        ExpressionNode expressionNode10 = new ExpressionNode(termList14);
        statementListNode3.addStatement(new StatementNode(new ReturnStatement(expressionNode10)));
        BlockStatement blockStatement = new BlockStatement(statementListNode2);
        StatementNode statementNode = new StatementNode(new WhileNode(new ExpressionNode(new EqualsNode(new TermNode(new IdentifierNode("name")),new TermNode(new StringLiteralNode("Zoe")))),blockStatement));
        statementListNode1.addStatement(statementNode);
        MethodDeclarationStatement methodDeclarationStatement = new MethodDeclarationStatement(new IdentifierNode("method"),parameterListNode,blockStatementMethod);
        return new StatementNode(methodDeclarationStatement);
    }
    private StatementNode methodDeclarationStatement(){
        StatementListNode statementListNode4 = new StatementListNode();
        TermList termList15 = new TermList();
        termList15.add(new TermNode(new StringLiteralNode("Hello!")));
        statementListNode4.addStatement(new StatementNode(new OutputStatement(new ExpressionNode(termList15))));
        BlockStatement blockStatement1 = new BlockStatement(statementListNode4);
        return new StatementNode(new MethodDeclarationStatement(new IdentifierNode("hello"),blockStatement1));
    }
}
