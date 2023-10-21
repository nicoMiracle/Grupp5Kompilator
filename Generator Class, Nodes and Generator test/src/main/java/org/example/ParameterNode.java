package org.example;

public class ParameterNode extends Node {
    private final IdentifierNode identifierNode;

    public ParameterNode( IdentifierNode identifierNode) {
        this.identifierNode = identifierNode;
    }

    public IdentifierNode getID() {
        return identifierNode;
    }

    @Override
    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
