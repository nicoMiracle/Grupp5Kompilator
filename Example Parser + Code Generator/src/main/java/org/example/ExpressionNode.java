package org.example;

public class ExpressionNode extends Node {
    private TermNode termNode;
    private AdditionNode additionNode;
    private SubtractionNode subtractionNode;
    private EqualsNode equalsNode;
    public ExpressionNode(TermNode termNode){
        this.termNode=termNode;
    }
    public ExpressionNode(AdditionNode additionNode){
        this.additionNode = additionNode;
    }
    public ExpressionNode(SubtractionNode subtractionNode){
        this.subtractionNode = subtractionNode;
    }
    public ExpressionNode(EqualsNode equalsNode){
        this.equalsNode = equalsNode;
    }

    public SubtractionNode getSubtractionNode() {
        return subtractionNode;
    }

    public TermNode getTermNode() {
        return termNode;
    }

    public AdditionNode getAdditionNode() {
        return additionNode;
    }
    public EqualsNode getEqualsNode(){
        return equalsNode;
    }

    public void accept(CodeGeneratorVisitor visitor){
        visitor.visit(this);
    }
}
