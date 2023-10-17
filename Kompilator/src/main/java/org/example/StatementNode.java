package org.example;

public abstract class StatementNode extends ASTNode {
    // Common properties or methods for all statement nodes can be defined here.

    // For example, you might define a method for executing the statement (if your language supports runtime execution):


    // You can also add other common methods or properties as needed by your language's grammar.

    // If statements have a line or position in the source code, you can also store that information.
    protected int line;
    protected int position;

    public int getLine() {
        return line;
    }

    public int getPosition() {
        return position;
    }
}
