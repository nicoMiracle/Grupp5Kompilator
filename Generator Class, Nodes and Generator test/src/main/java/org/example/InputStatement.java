package org.example;

public class InputStatement extends Node {
    public InputStatement() {
    }

    @Override
    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
