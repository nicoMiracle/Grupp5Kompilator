package org.example;

public class DeclarationStatement extends Node {
    private final TypeNode typeNode;
    private AssignmentStatementNode assignmentStatementNode;
    public DeclarationStatement(TypeNode typeNode){
        this.typeNode = typeNode;
    }
    public DeclarationStatement(TypeNode typeNode, AssignmentStatementNode assignmentStatementNode){
        this.typeNode = typeNode;
        this.assignmentStatementNode = assignmentStatementNode;
    }
    public TypeNode getType(){
        return typeNode;
    }

    public AssignmentStatementNode getAssignment(){
        return assignmentStatementNode;
    }

    @Override
    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
