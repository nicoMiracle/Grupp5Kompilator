package compilator;

import java.util.ArrayList;

public class ParameterListNode {
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

    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
