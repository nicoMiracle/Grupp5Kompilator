package org.example;

public class AssignmentStatementNode extends StatementNode {
    private String variableName;
    private ExpressionNode expression;

    public AssignmentStatementNode(String variableName, ExpressionNode expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    public String getVariableName() {
        return variableName;
    }

    public ExpressionNode getExpression() {
        return expression;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
        expression.accept(visitor);
    }
}
