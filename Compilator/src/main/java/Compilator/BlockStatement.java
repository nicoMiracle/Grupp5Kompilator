package Compilator;

public class BlockStatement {
    private final StatementListNode statementListNode;

    public BlockStatement(StatementListNode statementListNode) {
        this.statementListNode = statementListNode;
    }

    public StatementListNode getStatements() {
        return statementListNode;
    }

    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
