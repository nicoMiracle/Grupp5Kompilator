package org.example;

public class WhileStatementNode extends StatementNode {
    private ExpressionNode condition;
    private BlockStatementNode body;

    public WhileStatementNode(ExpressionNode condition, BlockStatementNode body) {
        this.condition = condition;
        this.body = body;
    }

    public ExpressionNode getCondition() {
        return condition;
    }

    public BlockStatementNode getBody() {
        return body;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
