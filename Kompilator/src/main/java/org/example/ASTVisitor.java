package org.example;

public interface ASTVisitor {
    void visit(ProgramNode node);

    void visit(VariableDeclarationNode node);

    void visit(AssignmentStatementNode node);

    void visit(IfStatementNode node);

    void visit(BlockStatementNode node);

    void visit(WhileStatementNode node);

    void visit(ExpressionNode node);

    void visit(IntegerLiteralNode node);

    void visit(StringLiteralNode node);

    void visit(VariableNode node);

    void visit(BinaryExpressionNode node);

    void visit(FunctionCallNode node);
    // Add additional visit methods for other node types as needed
}
