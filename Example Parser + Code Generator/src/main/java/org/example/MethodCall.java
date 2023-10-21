package org.example;

public class MethodCall extends Node {
    private IdentifierNode optionalIdentifier;
    private final IdentifierNode methodIdentifier;
    private ExpressionNode expressionNode;
    public MethodCall(IdentifierNode identifierNode){
        this.methodIdentifier = identifierNode;
    }
    public MethodCall(IdentifierNode optionalIdentifier,IdentifierNode methodIdentifier){
        this.optionalIdentifier = optionalIdentifier;
        this.methodIdentifier = methodIdentifier;
    }
    public MethodCall(IdentifierNode methodIdentifier,ExpressionNode expressionNode){
        this.methodIdentifier = methodIdentifier;
        this.expressionNode = expressionNode;
    }
    public MethodCall(IdentifierNode optionalIdentifier,IdentifierNode methodIdentifier,ExpressionNode expressionNode){
        this.optionalIdentifier = optionalIdentifier;
        this.methodIdentifier = methodIdentifier;
        this.expressionNode = expressionNode;
    }
    public IdentifierNode getOptionalID(){
        return optionalIdentifier;
    }
    public IdentifierNode getMethodID(){
        return methodIdentifier;
    }
    public ExpressionNode getExpression(){
        return expressionNode;
    }

    @Override
    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
