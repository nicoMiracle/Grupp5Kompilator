package org.example;

import java.util.List;

public class FunctionCallNode extends ExpressionNode {
    private String functionName;
    private List<ExpressionNode> arguments;

    public FunctionCallNode(String functionName, List<ExpressionNode> arguments) {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<ExpressionNode> getArguments() {
        return arguments;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
