package Compilator;

public class InputStatement {
    public InputStatement() {
    }

    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
