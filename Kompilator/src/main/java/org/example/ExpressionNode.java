package org.example;

public abstract class ExpressionNode extends ASTNode {
    // You can define common properties or methods for all expression nodes here
    public abstract void accept(ASTVisitor visitor);
}
