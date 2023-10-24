package org.example;

public class ExpressionNode {
    private TermList termList;
    private EqualsNode equalsNode;

    public ExpressionNode(TermList termList) {
        this.termList = termList;
    }

    public ExpressionNode(EqualsNode equalsNode) {
        this.equalsNode = equalsNode;
    }

    public TermList getTermList() {
        return termList;
    }

    public EqualsNode getEqualsNode() {
        return equalsNode;
    }


    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
