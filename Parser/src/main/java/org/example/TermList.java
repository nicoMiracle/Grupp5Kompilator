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

    public boolean isTermsEmpty() {
        return terms.isEmpty();
    }


    public void accept(CodeGeneratorVisitor visitor){
        visitor.visit(this);
    }
}
