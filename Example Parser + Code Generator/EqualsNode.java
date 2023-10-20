package org.example;

public class EqualsNode extends Node {
    private final TermNode firstTerm;
    private final TermNode secondTerm;
    public EqualsNode(TermNode firstTerm,TermNode secondTerm){
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
    }

    public TermNode getFirstTerm(){
        return firstTerm;
    }

    public TermNode getSecondTerm() {
        return secondTerm;
    }
    public void accept(CodeGeneratorVisitor visitor){
        visitor.visit(this);
    }
}
