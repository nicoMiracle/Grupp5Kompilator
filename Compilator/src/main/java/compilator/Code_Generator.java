package compilator;

public class Code_Generator implements CodeGeneratorVisitor {
    private final StringBuilder pythonCode = new StringBuilder();
    private int block = -1;

    public String generateCode(ProgramNode node) {
        node.accept(this);
        return pythonCode.toString().strip();
    }

    @Override
    public void visit(ProgramNode programNode) {
        programNode.getStatements().accept(this);
    }

    @Override
    public void visit(StatementListNode statementListNode) {
        int i = 0;
        block++;
        for (StatementNode statementNode : statementListNode.getStatementNodes()) {
            pythonCode.append("   ".repeat(Math.max(0, block)));
            statementNode.accept(this);
            if (i != statementListNode.getStatementNodes().size() - 1) {
                pythonCode.append("\n");
            }
            i++;
        }
        block--;
    }

    @Override
    public void visit(StatementNode statementNode) {
        if (statementNode.getAssign() != null) {
            statementNode.getAssign().accept(this);
        } else if (statementNode.getIfStatement() != null) {
            statementNode.getIfStatement().accept(this);
        } else if (statementNode.getWhile() != null) {
            statementNode.getWhile().accept(this);
        } else if (statementNode.getDeclaration() != null) {
            statementNode.getDeclaration().accept(this);
        } else if (statementNode.getMethodDeclaration() != null) {
            statementNode.getMethodDeclaration().accept(this);
        } else if (statementNode.getReturn() != null) {
            statementNode.getReturn().accept(this);
        } else if (statementNode.getOutput() != null) {
            statementNode.getOutput().accept(this);
        }
        else if(statementNode.getMethod()!=null){
            statementNode.getMethod().accept(this);
        }
    }

    @Override
    public void visit(AssignmentStatementNode assignmentStatementNode) {
        assignmentStatementNode.getIdentifier().accept(this);
        pythonCode.append("=");
        assignmentStatementNode.getExpression().accept(this);
    }

    @Override
    public void visit(ExpressionNode expressionNode) {
        if (expressionNode.getTermList() != null) {
            expressionNode.getTermList().accept(this);
        } else if (expressionNode.getEqualsNode() != null) {
            expressionNode.getEqualsNode().accept(this);
        }
    }

    @Override
    public void visit(EqualsNode equalsNode) {
        equalsNode.getFirstTerm().accept(this);
        pythonCode.append("==");
        equalsNode.getSecondTerm().accept(this);
    }

    @Override
    public void visit(DeclarationStatement declarationStatement) {
        declarationStatement.getAssignment().accept(this);
    }

    @Override
    public void visit(BlockStatement blockStatement) {
        blockStatement.getStatements().accept(this);
    }

    @Override
    public void visit(WhileNode whileNode) {
        pythonCode.append("while(");
        whileNode.getExpression().accept(this);
        pythonCode.append("):\n");
        whileNode.getBlockStatement().accept(this);
    }

    @Override
    public void visit(ReturnStatement returnStatement) {
        pythonCode.append("return ");
        returnStatement.getExpression().accept(this);
    }

    @Override
    public void visit(IfStatement ifStatement) {
        pythonCode.append("if(");
        ifStatement.getExpression().accept(this);
        pythonCode.append("):\n");
        ifStatement.getBlockStatement().accept(this);
    }

    @Override
    public void visit(ParameterListNode parameterListNode) {
        for (ParameterNode parameter : parameterListNode.getParameterNodes()) {
            parameter.accept(this);
        }
    }

    @Override
    public void visit(ParameterNode parameterNode) {
        parameterNode.getID().accept(this);
    }

    @Override
    public void visit(MethodDeclarationStatement methodDeclarationStatement) {
        if (methodDeclarationStatement.getParameters() == null) {
            pythonCode.append("def ");
            methodDeclarationStatement.getID().accept(this);
            pythonCode.append("():\n");
            methodDeclarationStatement.getBlock().accept(this);
        } else {
            pythonCode.append("def ");
            methodDeclarationStatement.getID().accept(this);
            pythonCode.append("(");
            methodDeclarationStatement.getParameters().accept(this);
            pythonCode.append("):\n");
            methodDeclarationStatement.getBlock().accept(this);
        }
    }

    @Override
    public void visit(MethodCall methodCall) {
        if (methodCall.getExpression() == null && methodCall.getOptionalID() == null) {
            methodCall.getMethodID().accept(this);
            pythonCode.append("()");
        } else if (methodCall.getExpression() == null) {
            methodCall.getOptionalID().accept(this);
            pythonCode.append(".");
            methodCall.getMethodID().accept(this);
            pythonCode.append("()");
        } else if (methodCall.getExpression() != null && methodCall.getOptionalID() != null) {
            methodCall.getOptionalID().accept(this);
            pythonCode.append(".");
            methodCall.getMethodID().accept(this);
            pythonCode.append("(");
            methodCall.getExpression().accept(this);
            pythonCode.append(")");
        } else if (methodCall.getExpression() != null && methodCall.getOptionalID() == null) {
            methodCall.getMethodID().accept(this);
            pythonCode.append("(");
            methodCall.getExpression().accept(this);
            pythonCode.append(")");
        }
    }
    public void visit(InputStatement ignoredInputStatement) {
        pythonCode.append("input()");
    }

    @Override
    public void visit(OutputStatement outputStatement) {
        pythonCode.append("print(");
        outputStatement.getExpression().accept(this);
        pythonCode.append(")");
    }

    @Override
    public void visit(TermList termList) {
        for(TermNode termNode:termList.getTerms()){
            termNode.accept(this);
        }
    }

    @Override
    public void visit(PositiveTermNode positiveTermNode) {
        pythonCode.append("+");
        if(positiveTermNode.getIntegerLiteralNode()!=null){
            positiveTermNode.getIntegerLiteralNode().accept(this);
        }
        else if(positiveTermNode.getIdentifierNode()!=null){
            positiveTermNode.getIdentifierNode().accept(this);
        }
        else if(positiveTermNode.getStringLiteralNode()!=null){
            positiveTermNode.getStringLiteralNode().accept(this);
        }
        else if(positiveTermNode.getInput()!=null){
            positiveTermNode.getInput().accept(this);
        }
        else if(positiveTermNode.getMethodCall()!=null){
            positiveTermNode.getMethodCall().accept(this);
        }
    }

    @Override
    public void visit(NegativeTerm negativeTerm) {
        pythonCode.append("-");
        if(negativeTerm.getIntegerLiteralNode()!=null){
            negativeTerm.getIntegerLiteralNode().accept(this);
        }
        else if(negativeTerm.getIdentifierNode()!=null){
            negativeTerm.getIdentifierNode().accept(this);
        }
        else if(negativeTerm.getInput()!=null){
            negativeTerm.getInput().accept(this);
        }
        else if(negativeTerm.getMethodCall()!=null){
            negativeTerm.getMethodCall().accept(this);
        }
        else if(negativeTerm.getStringLiteralNode()!=null){
            negativeTerm.getStringLiteralNode().accept(this);
        }
    }

    @Override
    public void visit(TermNode termNode) {
        if (termNode.getIntegerLiteralNode() != null) {
            termNode.getIntegerLiteralNode().accept(this);
        } else if (termNode.getStringLiteralNode() != null) {
            termNode.getStringLiteralNode().accept(this);
        } else if (termNode.getIdentifierNode() != null) {
            termNode.getIdentifierNode().accept(this);
        } else if (termNode.getMethodCall() != null) {
            termNode.getMethodCall().accept(this);
        } else if (termNode.getInput() != null) {
            termNode.getInput().accept(this);
        }else if(termNode.getPositiveTermNode()!=null){
            termNode.getPositiveTermNode().accept(this);
        }else if(termNode.getNegativeTerm()!=null){
            termNode.getNegativeTerm().accept(this);
        }
    }

    @Override
    public void visit(StringLiteralNode stringLiteralNode) {
        pythonCode.append("\"");
        pythonCode.append(stringLiteralNode.getValue());
        pythonCode.append("\"");
    }

    @Override
    public void visit(IntegerLiteralNode integerLiteralNode) {
        pythonCode.append(integerLiteralNode.getValue());
    }

    @Override
    public void visit(IdentifierNode identifierNode) {
        pythonCode.append(identifierNode.getName());
    }
}