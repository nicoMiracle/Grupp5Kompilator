package Compilator;

public class ReturnStatement {
    private final ExpressionNode expressionNode;

    public ReturnStatement(ExpressionNode expressionNode) {
        this.expressionNode = expressionNode;
    }

    public ExpressionNode getExpression() {
        return expressionNode;
    }

    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
