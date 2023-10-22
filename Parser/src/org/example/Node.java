package org.example;

public abstract class Node {
    public abstract void accept(CodeGeneratorVisitor visitor);
}
