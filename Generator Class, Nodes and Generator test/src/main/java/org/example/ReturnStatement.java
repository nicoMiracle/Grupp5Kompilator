package org.example;

public class ReturnStatement extends Node {
    private final ExpressionNode expressionNode;

    public ReturnStatement(ExpressionNode expressionNode) {
        this.expressionNode = expressionNode;
    }

    public ExpressionNode getExpression() {
        return expressionNode;
    }

    @Override
    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
