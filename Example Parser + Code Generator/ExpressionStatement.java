package org.example;

public class ExpressionStatement extends Node {
    private final ExpressionNode expression;
    public ExpressionStatement(ExpressionNode expression){
        this.expression = expression;
    }
    public ExpressionNode getExpression() {
        return expression;
    }
    public void accept(CodeGeneratorVisitor visitor){
        visitor.visit(this);
    }
}
