package org.example;

public class BinaryExpressionNode extends ExpressionNode {
    private String operator;
    private ExpressionNode left;
    private ExpressionNode right;

    public BinaryExpressionNode(String operator, ExpressionNode left, ExpressionNode right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public String getOperator() {
        return operator;
    }

    public ExpressionNode getLeft() {
        return left;
    }

    public ExpressionNode getRight() {
        return right;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
