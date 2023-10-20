package org.example;

public class TypeNode extends Node{
    private final String type;
    public TypeNode(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
