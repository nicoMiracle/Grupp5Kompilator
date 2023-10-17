package org.example;

public class PrintVisitor implements ASTVisitor {
    private int depth = 0;

    @Override
    public void visit(IntegerLiteralNode node) {
        printIndented("Integer Literal: " + node.getValue());
    }

    public void visit(StatementNode node) {
        printIndented("Statement Node");
        depth++;
        // Handle the specific type of statement (e.g., AssignmentStatementNode, IfStatementNode) here
        if (node instanceof AssignmentStatementNode) {
            visit((AssignmentStatementNode) node);
        } else if (node instanceof IfStatementNode) {
            visit((IfStatementNode) node);
        } else if (node instanceof WhileStatementNode) {
            visit((WhileStatementNode) node);
        }else  if (node instanceof BlockStatementNode) {
            visit((BlockStatementNode) node);
        }else{
            System.out.println("No such thing!");
        }
        depth--;
    }

    @Override
    public void visit(StringLiteralNode node) {
        printIndented("String Literal: " + node.getValue());
    }

    @Override
    public void visit(VariableNode node) {
        printIndented("Variable: " + node.getName());
    }

    @Override
    public void visit(BinaryExpressionNode node) {
        printIndented("Binary Expression: " + node.getOperator());
        depth++;
        node.getLeft().accept(this);
        node.getRight().accept(this);
        depth--;
    }

    @Override
    public void visit(FunctionCallNode node) {

    }

    @Override
    public void visit(ProgramNode node) {
        System.out.println("Program tree:");
        depth++;

        for (VariableDeclarationNode declaration : node.getDeclarations()) {
            declaration.accept(this);
        }

        for (StatementNode statement : node.getStatements()) {
            statement.accept(this);
        }

        for (ExpressionNode exp : node.getExpressions()) {
            exp.accept(this);
        }

        depth--;
    }


    @Override
    public void visit(VariableDeclarationNode node) {
        printIndented("Variable Declaration: " + node.getVariableName());
        depth++;
        if (node.getInitialValue() != null) {
            node.getInitialValue().accept(this);
        }
        depth--;
    }

    @Override
    public void visit(AssignmentStatementNode node) {

    }


    @Override
    public void visit(IfStatementNode node) {
        printIndented("If statement: " + node.getCondition());
        depth++;
        node.getCondition().accept(this);
        if (node.getElseBlock() != null) {
            printIndented("Else:");
            node.getElseBlock().accept(this);
        }
        depth--;
    }

    @Override
    public void visit(BlockStatementNode node) {

    }

    @Override
    public void visit(WhileStatementNode node) {

    }

    @Override
    public void visit(ExpressionNode node) {

    }

    // Implement visit methods for other node types

    private void printIndented(String message) {
        String indent = "  ".repeat(depth);
        System.out.println(indent + message);
    }
}



