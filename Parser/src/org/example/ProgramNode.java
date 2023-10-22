package org.example;

import java.util.ArrayList;

public class ProgramNode extends Node {
    private final StatementListNode statements;

    ProgramNode(StatementListNode statements){
        this.statements = statements;
    }

    public StatementListNode getStatements() {
        return statements;
    }
    public void accept(CodeGeneratorVisitor visitor){
        visitor.visit(this);
    }
}
