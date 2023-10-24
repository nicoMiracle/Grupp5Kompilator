package org.example;

public class ProgramNode {
    private final StatementListNode statements;

    ProgramNode(StatementListNode statements) {
        this.statements = statements;
    }

    public StatementListNode getStatements() {
        return statements;
    }

    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
