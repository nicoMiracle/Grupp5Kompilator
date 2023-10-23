package org.example;

public class SubtractionNode {
    private final TermNode firstTermNode;
    private final TermNode secondTermNode;

    public SubtractionNode(TermNode firstTermNode, TermNode secondExpressionNode) {
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
