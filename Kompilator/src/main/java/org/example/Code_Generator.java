package org.example;

import com.sun.source.tree.*;

public class Code_Generator {
    StatementTree Stmtnode = new StatementTree() {
        @Override
        public Kind getKind() {
            return null;
        }

        @Override
        public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
            return null;
        }
    };
    public void visit(StatementTree node){
    }
    public void visit(IdentifierTree node){

    }
    public void visit(ExpressionTree node){

    }
    public void visit(LiteralTree node){

    }
    public void visit(UnaryTree node){

    }
}