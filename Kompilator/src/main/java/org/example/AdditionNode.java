package org.example;

public class AdditionNode {
    private final TermNode firstTermNode;
    private final TermNode secondTermNode;

    public AdditionNode(TermNode firstTermNode, TermNode secondExpressionNode) {
        this.firstTermNode = firstTermNode;
        this.secondTermNode = secondExpressionNode;
    }

    public TermNode getFirstTermNode() {
        return firstTermNode;
    }

    public TermNode getSecondTermNode() {
        return secondTermNode;
    }

    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
