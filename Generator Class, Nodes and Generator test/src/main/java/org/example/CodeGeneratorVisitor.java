package org.example;

public interface CodeGeneratorVisitor {
    void visit(ProgramNode programNode);

    void visit(StatementListNode statementListNode);

    void visit(StatementNode statementNode);

    void visit(AssignmentStatementNode assignmentStatementNode);

    void visit(TermNode termNode);

    void visit(StringLiteralNode stringLiteralNode);

    void visit(IntegerLiteralNode integerLiteralNode);

    void visit(IdentifierNode identifierNode);

    void visit(ExpressionNode expressionNode);

    void visit(AdditionNode additionNode);

    void visit(ExpressionStatement expressionStatement);

    void visit(SubtractionNode subtractionNode);

    void visit(EqualsNode equalsNode);

    void visit(DeclarationStatement declarationStatement);

    void visit(BlockStatement blockStatement);

    void visit(WhileNode whileNode);

    void visit(ReturnStatement returnStatement);

    void visit(IfStatement ifStatement);

    void visit(ParameterListNode parameterListNode);

    void visit(ParameterNode parameterNode);

    void visit(MethodDeclarationStatement methodDeclarationStatement);

    void visit(MethodCall methodCall);

    void visit(InputStatement inputStatement);

    void visit(OutputStatement outputStatement);
}
