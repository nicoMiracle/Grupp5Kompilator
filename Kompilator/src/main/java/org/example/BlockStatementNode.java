package org.example;

import java.util.ArrayList;
import java.util.List;

public class BlockStatementNode extends StatementNode {
    private List<StatementNode> statements;

    public BlockStatementNode(List<StatementNode> statements) {
        this.statements = statements;
    }

    public List<StatementNode> getStatements() {
        return statements;
    }

    public void addStatement(StatementNode statement) {
        statements.add(statement);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
        for (StatementNode statement : statements) {
            statement.accept(visitor);
        }
    }
}
