package compilator;

public class ParameterNode {
    private final IdentifierNode identifierNode;

    public ParameterNode( IdentifierNode identifierNode) {
        this.identifierNode = identifierNode;
    }

    public IdentifierNode getID() {
        return identifierNode;
    }

    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
