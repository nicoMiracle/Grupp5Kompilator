package Compilator;

public class IfStatement {
    private final ExpressionNode expressionNode;
    private final BlockStatement blockStatement;

    public IfStatement(ExpressionNode expressionNode, BlockStatement blockStatement) {
        this.expressionNode = expressionNode;
        this.blockStatement = blockStatement;
    }

    public ExpressionNode getExpression() {
        return expressionNode;
    }

    public BlockStatement getBlockStatement() {
        return blockStatement;
    }

    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
