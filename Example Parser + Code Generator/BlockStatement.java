package org.example;

public class BlockStatement extends Node {
    private final StatementListNode statementListNode;
    public BlockStatement(StatementListNode statementListNode){
        this.statementListNode = statementListNode;
    }
    public StatementListNode getStatements(){
        return statementListNode;
    }

    @Override
    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
