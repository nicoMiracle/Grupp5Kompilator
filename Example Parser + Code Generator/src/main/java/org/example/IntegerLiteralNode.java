package org.example;

public class IntegerLiteralNode extends Node {
    private final int value;

    public IntegerLiteralNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
