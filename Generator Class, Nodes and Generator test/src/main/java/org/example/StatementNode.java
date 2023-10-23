package org.example;

public class StatementNode {
    private AssignmentStatementNode assign;
    private WhileNode whileNode;
    private IfStatement ifStatement;
    private ReturnStatement returnStatement;
    private DeclarationStatement declarationStatement;
    private MethodDeclarationStatement methodDeclarationStatement;
    private OutputStatement outputStatement;
    private MethodCall methodCall;

    public StatementNode(MethodDeclarationStatement methodDeclarationStatement) {
        this.methodDeclarationStatement = methodDeclarationStatement;
    }

    public StatementNode(DeclarationStatement declarationStatement) {
        this.declarationStatement = declarationStatement;
    }

    public StatementNode(AssignmentStatementNode assign) {
        this.assign = assign;
    }

    public StatementNode(WhileNode whileNode) {
        this.whileNode = whileNode;
    }

    public StatementNode(IfStatement ifStatement) {
        this.ifStatement = ifStatement;
    }

    public StatementNode(ReturnStatement returnStatement) {
        this.returnStatement = returnStatement;
    }

    public StatementNode(OutputStatement outputStatement) {
        this.outputStatement = outputStatement;
    }

    public StatementNode(MethodCall methodCall) {
        this.methodCall = methodCall;
    }

    public AssignmentStatementNode getAssign() {
        return assign;
    }


    public WhileNode getWhile() {
        return whileNode;
    }

    public IfStatement getIfStatement() {
        return ifStatement;
    }

    public ReturnStatement getReturn() {
        return returnStatement;
    }

    public DeclarationStatement getDeclaration() {
        return declarationStatement;
    }

    public MethodDeclarationStatement getMethodDeclaration() {
        return methodDeclarationStatement;
    }

    public OutputStatement getOutput() {
        return outputStatement;
    }
    public MethodCall getMethod(){
        return methodCall;
    }

    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
