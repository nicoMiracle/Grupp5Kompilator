package org.example;

public class IntegerLiteralNode extends ExpressionNode {
    private int value;

    public IntegerLiteralNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
