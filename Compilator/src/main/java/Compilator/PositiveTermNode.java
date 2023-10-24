package Compilator;

public class PositiveTermNode {
    private IntegerLiteralNode integerLiteralNode;
    private StringLiteralNode stringLiteralNode;
    private IdentifierNode identifierNode;
    private MethodCall methodCall;
    private InputStatement inputStatement;

    public PositiveTermNode(IntegerLiteralNode integerLiteralNode) {
        this.integerLiteralNode = integerLiteralNode;
    }

    public PositiveTermNode(StringLiteralNode stringLiteralNode) {
        this.stringLiteralNode = stringLiteralNode;
    }

    public PositiveTermNode(IdentifierNode identifierNode) {
        this.identifierNode = identifierNode;
    }


    public PositiveTermNode(InputStatement inputStatement) {
        this.inputStatement = inputStatement;
    }

    public PositiveTermNode(MethodCall methodCall) {
        this.methodCall = methodCall;
    }

    public IdentifierNode getIdentifierNode() {
        return identifierNode;
    }

    public IntegerLiteralNode getIntegerLiteralNode() {
        return integerLiteralNode;
    }

    public StringLiteralNode getStringLiteralNode() {
        return stringLiteralNode;
    }

    public MethodCall getMethodCall() {
        return methodCall;
    }

    public InputStatement getInput() {
        return inputStatement;
    }
    public void accept(CodeGeneratorVisitor visitor){
        visitor.visit(this);
    }
}
