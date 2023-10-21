package org.example;

import java.util.ArrayList;

public class ParameterListNode extends Node {
    private final ArrayList<ParameterNode> parameterNodes;

    public ParameterListNode() {
        this.parameterNodes = new ArrayList<>();
    }

    public ArrayList<ParameterNode> getParameterNodes() {
        return parameterNodes;
    }

    public void addParameter(ParameterNode parameterNode) {
        parameterNodes.add(parameterNode);
    }

    @Override
    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
