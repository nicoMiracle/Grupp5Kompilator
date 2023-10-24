package compilator;

public class TermNode {
    private IntegerLiteralNode integerLiteralNode;
    private StringLiteralNode stringLiteralNode;
    private IdentifierNode identifierNode;
    private MethodCall methodCall;
    private InputStatement inputStatement;
    private NegativeTerm negativeTerm;
    private PositiveTermNode positiveTermNode;

    public TermNode(IntegerLiteralNode integerLiteralNode) {
        this.integerLiteralNode = integerLiteralNode;
    }

    public TermNode(StringLiteralNode stringLiteralNode) {
        this.stringLiteralNode = stringLiteralNode;
    }

    public TermNode(IdentifierNode identifierNode) {
        this.identifierNode = identifierNode;
    }


    public TermNode(InputStatement inputStatement) {
        this.inputStatement = inputStatement;
    }

    public TermNode(MethodCall methodCall) {
        this.methodCall = methodCall;
    }
    public TermNode(PositiveTermNode termNode){
        this.positiveTermNode = termNode;
    }
    public TermNode(NegativeTerm termNode){
        this.negativeTerm = termNode;
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
    public PositiveTermNode getPositiveTermNode(){
        return positiveTermNode;
    }
    public NegativeTerm getNegativeTerm(){
        return negativeTerm;
    }

    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
