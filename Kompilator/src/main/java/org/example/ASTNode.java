package org.example;

public abstract class ASTNode {
    // Properties or methods common to all AST nodes can be defined here.

    // For example, you might define methods for accepting visitors, toString, or other common operations.
    public abstract void accept(ASTVisitor visitor);


    // You can also include properties or methods to track the line and position in the source code.
    protected int line;
    protected int position;

    public int getLine() {
        return line;
    }

    public int getPosition() {
        return position;
    }
}
