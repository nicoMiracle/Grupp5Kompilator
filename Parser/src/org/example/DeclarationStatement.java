package org.example;

public class DeclarationStatement extends Node {
    private final TypeNode typeNode;
    private IdentifierNode identifierNode;
    private AssignmentStatementNode assignmentStatementNode;
    public DeclarationStatement(TypeNode typeNode, IdentifierNode identifierNode){
        this.typeNode = typeNode;
        this.identifierNode = identifierNode;
    }
    public DeclarationStatement(TypeNode typeNode, AssignmentStatementNode assignmentStatementNode){
        this.typeNode = typeNode;
        this.assignmentStatementNode = assignmentStatementNode;
    }
    public TypeNode getType(){
        return typeNode;
    }

    public IdentifierNode getIdentifierNode() {
        return identifierNode;
    }
    public AssignmentStatementNode getAssignment(){
        return assignmentStatementNode;
    }

    @Override
    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
