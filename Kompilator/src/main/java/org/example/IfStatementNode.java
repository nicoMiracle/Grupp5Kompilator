package org.example;

public class IfStatementNode extends StatementNode {
    private ExpressionNode condition;
    private BlockStatementNode ifBlock;
    private BlockStatementNode elseBlock;

    public IfStatementNode(ExpressionNode condition, BlockStatementNode ifBlock, BlockStatementNode elseBlock) {
        this.condition = condition;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
    }

    public ExpressionNode getCondition() {
        return condition;
    }

    public BlockStatementNode getIfBlock() {
        return ifBlock;
    }

    public BlockStatementNode getElseBlock() {
        return elseBlock;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
        condition.accept(visitor);
        ifBlock.accept(visitor);
        if (elseBlock != null) {
            elseBlock.accept(visitor);
        }
    }
}
