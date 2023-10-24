package org.example;

public class DeclarationStatement {
    private final AssignmentStatementNode assignmentStatementNode;

    public DeclarationStatement( AssignmentStatementNode assignmentStatementNode) {

        this.assignmentStatementNode = assignmentStatementNode;
    }


    public AssignmentStatementNode getAssignment() {
        return assignmentStatementNode;
    }

    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
