package compilator;

public class NegativeTerm {
    private IntegerLiteralNode integerLiteralNode;
    private StringLiteralNode stringLiteralNode;
    private IdentifierNode identifierNode;
    private MethodCall methodCall;
    private InputStatement inputStatement;

    public NegativeTerm(IntegerLiteralNode integerLiteralNode) {
        this.integerLiteralNode = integerLiteralNode;
    }

    public NegativeTerm(StringLiteralNode stringLiteralNode) {
        this.stringLiteralNode = stringLiteralNode;
    }

    public NegativeTerm(IdentifierNode identifierNode) {
        this.identifierNode = identifierNode;
    }


    public NegativeTerm(InputStatement inputStatement) {
        this.inputStatement = inputStatement;
    }

    public NegativeTerm(MethodCall methodCall) {
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
