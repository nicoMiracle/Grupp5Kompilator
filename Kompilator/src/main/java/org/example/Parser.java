package org.example;

import java.util.List;
import java.util.ArrayList;

public class Parser {
    private List<Token> tokens;
    private int currentPosition;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentPosition = 0;
    }

    public ProgramNode parse() {
        ProgramNode program = new ProgramNode();

        while (currentPosition < tokens.size()) {
            Token token = tokens.get(currentPosition);

            if (token.getType() == TokenType.TYPE_INT || token.getType() == TokenType.TYPE_STRING) {
                VariableDeclarationNode variableDeclaration = parseVariableDeclaration();
                program.addDeclaration(variableDeclaration);
            } else if (token.getType() == TokenType.IDENTIFIER) {
                StatementNode statement = parseAssignmentOrIfStatement();
                program.addStatement(statement);
            } else {
                // Handle errors or other statement types
            }
        }

        return program;
    }

    private VariableDeclarationNode parseVariableDeclaration() {
        Token dataTypeToken = tokens.get(currentPosition);
        match(dataTypeToken.getType());

        Token identifierToken = tokens.get(currentPosition);
        match(TokenType.IDENTIFIER);

        if (match(TokenType.ASSIGN)) {
            ExpressionNode initialValue = parseExpression();
            match(TokenType.SEMICOLON);
            return new VariableDeclarationNode(dataTypeToken.getLexeme(), identifierToken.getLexeme(), initialValue);
        } else {
            match(TokenType.SEMICOLON);
            return new VariableDeclarationNode(dataTypeToken.getLexeme(), identifierToken.getLexeme(), null);
        }
    }

    private StatementNode parseAssignmentOrIfStatement() {
        Token identifierToken = tokens.get(currentPosition);
        match(TokenType.IDENTIFIER);

        if (match(TokenType.ASSIGN)) {
            ExpressionNode assignmentValue = parseExpression();
            match(TokenType.SEMICOLON);
            return new AssignmentStatementNode(identifierToken.getLexeme(), assignmentValue);
        } else if (match(TokenType.KEYWORD_IF)) {
            ExpressionNode condition = parseExpression();
            BlockStatementNode thenBranch = parseBlockStatement();
            BlockStatementNode elseBranch = null;

            if (match(TokenType.KEYWORD_ELSE)) {
                elseBranch = parseBlockStatement();
            }

            return new IfStatementNode(condition, thenBranch, elseBranch);
        } else {
            // Handle other statement types
            return null;
        }
    }

    private BlockStatementNode parseBlockStatement() {
        List<StatementNode> statements = new ArrayList<>();
        match(TokenType.LEFT_BRACE);

        while (currentPosition < tokens.size() && tokens.get(currentPosition).getType() != TokenType.RIGHT_BRACE) {
            StatementNode statement = parseAssignmentOrIfStatement();
            if (statement != null) {
                statements.add(statement);
            }
        }

        match(TokenType.RIGHT_BRACE);
        return new BlockStatementNode(statements);
    }

    private ExpressionNode parseExpression() {
        Token currentToken = tokens.get(currentPosition);

        if (currentToken.getType() == TokenType.INTEGER_LITERAL) {
            int value = Integer.parseInt(currentToken.getLexeme());
            currentPosition++;
            return new IntegerLiteralNode(value);
        } else if (currentToken.getType() == TokenType.STRING_LITERAL) {
            String value = currentToken.getLexeme();
            currentPosition++;
            return new StringLiteralNode(value);
        } else if (currentToken.getType() == TokenType.IDENTIFIER) {
            // Handle variable references or function calls
            String variableName = currentToken.getLexeme();
            currentPosition++;
            return new VariableNode(variableName);
        }

        return null;  // Handle other expression types
    }

    private boolean match(TokenType expectedType) {
        if (currentPosition < tokens.size() && tokens.get(currentPosition).getType() == expectedType) {
            currentPosition++;
            return true;
        }
        return false;
    }
}
