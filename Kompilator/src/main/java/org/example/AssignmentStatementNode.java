package org.example;

public class AssignmentStatementNode {
    private final IdentifierNode identifier;
    private final ExpressionNode expression;

    public AssignmentStatementNode(IdentifierNode identifier, ExpressionNode expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    public IdentifierNode getIdentifier() {
        return identifier;
    }

    public ExpressionNode getExpression() {
        return expression;
    }

    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}