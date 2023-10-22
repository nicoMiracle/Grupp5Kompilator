package org.example;

public class StatementNode extends Node {

    private AssignmentStatementNode assign;
    private ExpressionStatement expressionStatement;
    private WhileNode whileNode;
    private IfStatement ifStatement;
    private ReturnStatement returnStatement;
    private DeclarationStatement declarationStatement;
    private MethodDeclarationStatement methodDeclarationStatement;
    private BlockStatement blockStatement;
    private MethodCall methodCall;
    private InputStatement inputStatement;
    private OutputStatement outputStatement;
    public StatementNode(MethodCall methodCall){
        this.methodCall = methodCall;
    }
    public StatementNode(BlockStatement blockStatement){
        this.blockStatement = blockStatement;
    }
    public StatementNode(MethodDeclarationStatement methodDeclarationStatement){
        this.methodDeclarationStatement = methodDeclarationStatement;
    }
    public StatementNode(DeclarationStatement declarationStatement){
        this.declarationStatement = declarationStatement;
    }
    public StatementNode(AssignmentStatementNode assign){
        this.assign = assign;
    }
    public StatementNode(ExpressionStatement expressionStatement){
        this.expressionStatement = expressionStatement;
    }
    public StatementNode(WhileNode whileNode){
        this.whileNode = whileNode;
    }
    public StatementNode(IfStatement ifStatement){
        this.ifStatement = ifStatement;
    }
    public StatementNode(ReturnStatement returnStatement){
        this.returnStatement = returnStatement;
    }
    public StatementNode(InputStatement inputStatement){
        this.inputStatement = inputStatement;
    }
    public StatementNode(OutputStatement outputStatement){
        this.outputStatement = outputStatement;
    }
    public AssignmentStatementNode getAssign(){
        return assign;
    }

    public ExpressionStatement getExpressionStatement() {
        return expressionStatement;
    }
    public WhileNode getWhile(){
        return whileNode;
    }
    public IfStatement getIfStatement(){
        return ifStatement;
    }
    public ReturnStatement getReturn(){
        return returnStatement;
    }
    public DeclarationStatement getDeclaration(){
        return declarationStatement;
    }
    public MethodDeclarationStatement getMethodDeclaration(){
        return methodDeclarationStatement;
    }
    public BlockStatement getBlock(){
        return blockStatement;
    }
    public MethodCall getMethodCall(){
        return methodCall;
    }
    public InputStatement getInput(){
        return inputStatement;
    }
    public OutputStatement getOutput(){
        return outputStatement;
    }

    public void accept(CodeGeneratorVisitor visitor){
        visitor.visit(this);
    }
}
