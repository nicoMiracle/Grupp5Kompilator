package org.example;

import java.util.ArrayList;
import java.util.List;

public class ProgramNode extends ASTNode {
    private List<VariableDeclarationNode> declarations;
    private List<StatementNode> statements;
    private List<ExpressionNode> expressions;

    public ProgramNode() {
        declarations = new ArrayList<>();
        statements = new ArrayList<>();
        expressions = new ArrayList<>();
    }

    public List<VariableDeclarationNode> getDeclarations() {
        return declarations;
    }

    public List<StatementNode> getStatements() {
        return statements;
    }

    public List<ExpressionNode> getExpressions() {
        return expressions;
    }

    public void addDeclaration(VariableDeclarationNode declaration) {
        declarations.add(declaration);
    }

    public void addStatement(StatementNode statement) {
        statements.add(statement);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
        for (VariableDeclarationNode declaration : declarations) {
            declaration.accept(visitor);
        }
        for (StatementNode statement : statements) {
            statement.accept(visitor);
        }

        for (ExpressionNode expression : expressions) {
            expression.accept(visitor);
        }
    }
}


