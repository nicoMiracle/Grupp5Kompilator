package org.example;

public class MethodDeclarationStatement extends Node {
    private final TypeNode typeNode;
    private final IdentifierNode identifierNode;
    private final ParameterListNode parameterListNode;
    private final BlockStatement blockStatement;
    public MethodDeclarationStatement(TypeNode typeNode,IdentifierNode identifierNode,ParameterListNode parameterListNode,BlockStatement blockStatement){
        this.typeNode = typeNode;
        this.identifierNode = identifierNode;
        this.parameterListNode = parameterListNode;
        this.blockStatement = blockStatement;
    }
    public TypeNode getType(){
        return typeNode;
    }
    public IdentifierNode getID(){
        return identifierNode;
    }
    public ParameterListNode getParameters(){
        return parameterListNode;
    }
    public BlockStatement getBlock(){
        return blockStatement;
    }

    @Override
    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
