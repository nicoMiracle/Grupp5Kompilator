package Compilator;

public class MethodDeclarationStatement {
    private final IdentifierNode identifierNode;
    private ParameterListNode parameterListNode;
    private final BlockStatement blockStatement;

    public MethodDeclarationStatement( IdentifierNode identifierNode, ParameterListNode parameterListNode, BlockStatement blockStatement) {
        this.identifierNode = identifierNode;
        this.parameterListNode = parameterListNode;
        this.blockStatement = blockStatement;
    }

    public MethodDeclarationStatement( IdentifierNode identifierNode, BlockStatement blockStatement) {
        this.identifierNode = identifierNode;
        this.blockStatement = blockStatement;
    }

    public IdentifierNode getID() {
        return identifierNode;
    }

    public ParameterListNode getParameters() {
        return parameterListNode;
    }

    public BlockStatement getBlock() {
        return blockStatement;
    }

    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
