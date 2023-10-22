package org.example;

public class ParameterNode extends Node{
    private final TypeNode typeNode;
    private final IdentifierNode identifierNode;
    public ParameterNode(TypeNode typeNode, IdentifierNode identifierNode){
        this.typeNode = typeNode;
        this.identifierNode = identifierNode;
    }
    public IdentifierNode getID(){
        return identifierNode;
    }
    public TypeNode getType(){
        return typeNode;
    }

    @Override
    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
