package org.example;

public class TypeNode extends Node{
    public TypeNode(){
    }

    @Override
    public void accept(CodeGeneratorVisitor visitor) {
        visitor.visit(this);
    }
}
