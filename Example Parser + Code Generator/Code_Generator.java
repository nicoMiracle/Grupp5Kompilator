package org.example;

public class Code_Generator implements CodeGeneratorVisitor {
    StringBuilder pythonCode = new StringBuilder();
    public String generateCode(ProgramNode node){
        node.accept(this);
        return pythonCode.toString();
    }

    @Override
    public void visit(ProgramNode programNode) {
        /*
        programNode.getStatements().accept(this);

         */
    }

    @Override
    public void visit(StatementListNode statementListNode) {
        /*
        int i = 0;
        for (StatementNode statementNode : statementListNode.getStatementNodes()) {
            statementNode.accept(this);
            if(i!=statementListNode.getStatementNodes().size()-1){
                pythonCode.append("\n");
            }
        }

         */
    }

    @Override
    public void visit(StatementNode statementNode) {
        /*
        if (statementNode.getAssign() != null) {
            statementNode.getAssign().accept(this);
        }
        else if (statementNode.getExpressionStatement()!=null){
            statementNode.getExpressionStatement().accept(this);
        }

         */
    }

    @Override
    public void visit(AssignmentStatementNode assignmentStatementNode) {
        /*
        assignmentStatementNode.getIdentifier().accept(this);
        pythonCode.append("=");
        assignmentStatementNode.getExpression().accept(this);

         */
    }
    @Override
    public void visit(ExpressionNode expressionNode) {
        /*
        if(expressionNode.getTermNode()!=null){
            expressionNode.getTermNode().accept(this);
        }
        else if(expressionNode.getAdditionNode()!= null){
            expressionNode.getAdditionNode().accept(this);
        }
        else if(expressionNode.getSubtractionNode()!=null){
            expressionNode.getSubtractionNode().accept(this);
        }
         */
    }

    @Override
    public void visit(AdditionNode additionNode) {
        /*
        additionNode.getFirstTermNode().accept(this);
        pythonCode.append("+");
        additionNode.getSecondTermNode().accept(this);

         */
    }

    @Override
    public void visit(ExpressionStatement expressionStatement) {
        /*
        expressionStatement.getExpression().accept(this);

         */
    }

    @Override
    public void visit(SubtractionNode subtractionNode) {
        /*
        subtractionNode.getFirstTermNode().accept(this);
        pythonCode.append("-");
        subtractionNode.getSecondTermNode().accept(this);

         */
    }

    @Override
    public void visit(EqualsNode equalsNode) {

    }

    @Override
    public void visit(TypeNode typeNode) {

    }

    @Override
    public void visit(DeclarationStatement declarationStatement) {

    }

    @Override
    public void visit(BlockStatement blockStatement) {

    }

    @Override
    public void visit(WhileNode whileNode) {

    }

    @Override
    public void visit(ReturnStatement returnStatement) {

    }

    @Override
    public void visit(IfStatement ifStatement) {

    }

    @Override
    public void visit(ParameterListNode parameterListNode) {

    }

    @Override
    public void visit(ParameterNode parameterNode) {

    }

    @Override
    public void visit(MethodDeclarationStatement methodDeclarationStatement) {

    }

    @Override
    public void visit(MethodCall methodCall) {

    }

    @Override
    public void visit(InputStatement inputStatement) {

    }

    @Override
    public void visit(OutputStatement outputStatement) {

    }

    @Override
    public void visit(TermNode termNode) {
        /*
        if(termNode.getIntegerLiteralNode()!=null){
            termNode.getIntegerLiteralNode().accept(this);
        }
        else if(termNode.getStringLiteralNode()!=null){
            termNode.getStringLiteralNode().accept(this);
        }
        else if(termNode.getExpressionNode()!= null){
            termNode.getExpressionNode().accept(this);
        }
        else if(termNode.getIdentifierNode()!=null){
            termNode.getIdentifierNode().accept(this);
        }*/
    }
    @Override
    public void visit(StringLiteralNode stringLiteralNode) {
        /*
        pythonCode.append("\"");
        pythonCode.append(stringLiteralNode.getValue());
        pythonCode.append("\"");

         */
    }

    @Override
    public void visit(IntegerLiteralNode integerLiteralNode) {
        /*
        pythonCode.append(integerLiteralNode.getValue());

         */
    }
    @Override
    public void visit(IdentifierNode identifierNode) {
        /*
        pythonCode.append(identifierNode.getName());

         */
    }

}