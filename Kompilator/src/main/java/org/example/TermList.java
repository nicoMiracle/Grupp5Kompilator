package org.example;

import java.util.ArrayList;

public class TermList {
    ArrayList<TermNode> terms ;
    public TermList(){
        terms = new ArrayList<>();
    }
    public void add(TermNode termNode){
        terms.add(termNode);
    }
    public void accept(CodeGeneratorVisitor visitor){
        visitor.visit(this);
    }
}
