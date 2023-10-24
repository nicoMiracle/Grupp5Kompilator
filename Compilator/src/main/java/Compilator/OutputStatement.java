package Compilator;

public class OutputStatement {
    private final ExpressionNode expressionNode;

    public OutputStatement(ExpressionNode expressionNode) {
        this.expressionNode = expressionNode;
    }

    public ExpressionNode getExpression() {
        return expressionNode;
    }

    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
