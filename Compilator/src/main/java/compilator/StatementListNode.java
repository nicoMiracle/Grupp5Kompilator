package compilator;

import java.util.ArrayList;

public class StatementListNode {
    private final ArrayList<StatementNode> statementNodes;

    public StatementListNode() {
        statementNodes = new ArrayList<>();
    }

    public void addStatement(StatementNode statementNode) {
        statementNodes.add(statementNode);
    }

    public ArrayList<StatementNode> getStatementNodes() {
        return statementNodes;
    }

    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
