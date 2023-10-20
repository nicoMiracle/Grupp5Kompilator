package org.example;

public class WhileNode extends Node{
    private final ExpressionNode expressionNode;
    private final BlockStatement blockStatement;
    public WhileNode(ExpressionNode expressionNode,BlockStatement blockStatement){
        this.expressionNode = expressionNode;
        this.blockStatement = blockStatement;
    }
    public ExpressionNode getExpression(){
        return expressionNode ;
    }
    public BlockStatement getBlockStatement(){
        return blockStatement;
    }

    @Override
    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
