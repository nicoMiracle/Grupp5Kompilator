package org.example;

public class VariableDeclarationNode extends ASTNode {
    private String variableName;
    private ExpressionNode initialValue;
    private String type;

    public VariableDeclarationNode(String type, String variableName, ExpressionNode initialValue) {
        this.variableName = variableName;
        this.initialValue = initialValue;
        this.type = type;
    }

    public String getVariableName() {
        return variableName;
    }
    public String getType() {
        return type;
    }

    public ExpressionNode getInitialValue() {
        return initialValue;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
        initialValue.accept(visitor);
    }
}
