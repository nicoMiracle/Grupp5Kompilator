package org.example;

public class DeclarationStatement extends Node {
    private final AssignmentStatementNode assignmentStatementNode;

    public DeclarationStatement( AssignmentStatementNode assignmentStatementNode) {

        this.assignmentStatementNode = assignmentStatementNode;
    }


    public AssignmentStatementNode getAssignment() {
        return assignmentStatementNode;
    }

    @Override
    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
