package org.example;

import java.util.List;

public class Parser {
    private List<Token> tokens;
    private int position;
    public Parser(List<Token> tokens){
        this.tokens = tokens;
        this.position = 0;
    }
    public ProgramNode parseProgram(){
        return new ProgramNode(parseStatementList());
    }
    public StatementListNode parseStatementList(){
        StatementListNode statementListNode = new StatementListNode();
        while(position<tokens.size()){
            statementListNode.addStatement(parseStatementNode());
        }
        return statementListNode;
    }
    public StatementNode parseStatementNode(){
        if(tokens.get(position).getType().equals(TokenType.IDENTIFIER)){
            String identifier = tokens.get(position).getLexeme();
            position++;
            if(tokens.get(position).getType().equals(TokenType.ASSIGN)){
                position++;
                return new StatementNode(parseAssignmentStatementNode(identifier));
            }
        }
        return null;
    }
    public AssignmentStatementNode parseAssignmentStatementNode(String identifier){
        IdentifierNode identifierNode = parseIdentifierNode(identifier);
        ExpressionNode expressionNode = parseExpression();
        return new AssignmentStatementNode(identifierNode,expressionNode);
    }
    public ExpressionNode parseExpression(){
        return new ExpressionNode(parseTermNode());
    }
    public TermNode parseTermNode(){
        if(tokens.get(position).getType().equals(TokenType.INTEGER_LITERAL)){
            IntegerLiteralNode integerLiteralNode = parseInteger(tokens.get(position).getLexeme());
            position++;
            if (tokens.get(position).getType().equals(TokenType.SEMICOLON)){
                TermNode termNode = new TermNode(integerLiteralNode);
                position++;
                return termNode;
            }
        }
        return null;
    }
    public IntegerLiteralNode parseInteger(String integer){
        return new IntegerLiteralNode(Integer.parseInt(integer));
    }
    public IdentifierNode parseIdentifierNode(String identifier){
        return new IdentifierNode(identifier);
    }




    public boolean match(TokenType expectedToken){
        Token token = tokens.get(position);
        return token.getType() == expectedToken;
    }
}
