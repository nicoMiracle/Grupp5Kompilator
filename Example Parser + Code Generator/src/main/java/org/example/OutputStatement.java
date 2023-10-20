package org.example;

public class OutputStatement extends Node{
    private final ExpressionNode expressionNode;
    public OutputStatement(ExpressionNode expressionNode){
        this.expressionNode = expressionNode;
    }
    public ExpressionNode getExpression(){
        return expressionNode;
    }
    @Override
    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
