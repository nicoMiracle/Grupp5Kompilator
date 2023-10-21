package org.example;

public class TermNode extends Node {
    private IntegerLiteralNode integerLiteralNode;
    private StringLiteralNode stringLiteralNode;
    private IdentifierNode identifierNode;
    private ExpressionNode expressionNode;
    private MethodCall methodCall;

    public TermNode(IntegerLiteralNode integerLiteralNode){
        this.integerLiteralNode = integerLiteralNode;
    }
    public TermNode(StringLiteralNode stringLiteralNode){
        this.stringLiteralNode = stringLiteralNode;
    }
    public TermNode(IdentifierNode identifierNode){
        this.identifierNode = identifierNode;
    }
    public TermNode(ExpressionNode expressionNode){
        this.expressionNode = expressionNode;
    }
    public TermNode(MethodCall methodCall){
        this.methodCall = methodCall;
    }

    public IdentifierNode getIdentifierNode() {
        return identifierNode;
    }

    public ExpressionNode getExpressionNode() {
        return expressionNode;
    }

    public IntegerLiteralNode getIntegerLiteralNode() {
        return integerLiteralNode;
    }
    public StringLiteralNode getStringLiteralNode(){
        return stringLiteralNode;
    }
    public MethodCall getMethodCall(){
        return methodCall;
    }
    public void accept(CodeGeneratorVisitor visitor){
        visitor.visit(this);
    }
}
