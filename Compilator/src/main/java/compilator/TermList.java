package compilator;

import java.util.ArrayList;

public class TermList {
    private final ArrayList<TermNode> terms ;
    public TermList(){
        terms = new ArrayList<>();
    }
    public void add(TermNode termNode){
        terms.add(termNode);
    }
    public ArrayList<TermNode> getTerms(){
        return terms;
    }
    public void accept(CodeGeneratorVisitor visitor){
        visitor.visit(this);
    }
}
