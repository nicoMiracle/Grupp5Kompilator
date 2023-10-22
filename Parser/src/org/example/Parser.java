package org.example;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private List<Token> tokens;
    private int position;

    public Parser(List<Token> tokens){
        this.tokens = tokens;
        this.position = 0;
    }

    public int getPosition() {
        return position;
    }

    public List<Token> getTokens() {
        return tokens;
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
        }else if(tokens.get(position).getType().equals(TokenType.TYPE_INT) || tokens.get(position).getType().equals(TokenType.TYPE_STRING)){
            String dataType = tokens.get(position).getLexeme(); //int
            position++;
            return new StatementNode(parseDeclarationStatement(dataType));
        }else if(tokens.get(position).getType().equals(TokenType.KEYWORD_IF)){
            return new StatementNode(parseIfStatement());
        }else if(tokens.get(position).getType().equals(TokenType.KEYWORD_WHILE)){
            return new StatementNode(parseWhileStatement());
        }else{
            throw new ParseException("Unexpected token: " + tokens.get(position).getLexeme() + " on line " + tokens.get(position).getLine());
        }

        return null;
    }


    private DeclarationStatement parseDeclarationStatement(String dataType) {
        TypeNode typeNode = parseTypeNode(dataType);
        String identifier = tokens.get(position).getLexeme();
        IdentifierNode variable = parseIdentifierNode(identifier);
        position++;
        if(tokens.get(position).getType().equals(TokenType.SEMICOLON)){
            position++;
            return new DeclarationStatement(typeNode, variable);
        }else{
            position++;
            AssignmentStatementNode assign = parseAssignmentStatementNode(identifier);
            return new DeclarationStatement(typeNode, assign);
        }
    }

    public AssignmentStatementNode parseAssignmentStatementNode(String identifier){
        IdentifierNode identifierNode = parseIdentifierNode(identifier);

        if(tokens.get(position).getType().equals(TokenType.INTEGER_LITERAL)) {
            position++;
            if (tokens.get(position).getType().equals(TokenType.PLUS) || tokens.get(position).getType().equals(TokenType.MINUS)) {
                ExpressionNode expressionNode = parseAdditionSubtractionExpression();
                return new AssignmentStatementNode(identifierNode,expressionNode);
            }
        }
        ExpressionNode expressionNode = parseExpression();
        match(TokenType.SEMICOLON);
        return new AssignmentStatementNode(identifierNode,expressionNode);
    }


    public ExpressionNode parseExpression(){
        return new ExpressionNode(parseTermNode());
    }

    /*
    else if(tokens.get(position).getType().equals(TokenType.INTEGER_LITERAL)){
            position++;
            if (tokens.get(position).getType().equals(TokenType.PLUS) || tokens.get(position).getType().equals(TokenType.MINUS)){
                ExpressionNode expressionNode = parseAdditionSubtractionExpression();
                ExpressionStatement expressionStatement = new ExpressionStatement(expressionNode);
                return new StatementNode(expressionStatement);
            }
        }
     */

    public TermNode parseTermNode(){
        if(tokens.get(position).getType().equals(TokenType.INTEGER_LITERAL)){
            IntegerLiteralNode integerLiteralNode = parseInteger(tokens.get(position).getLexeme());
            position++;
            return new TermNode(integerLiteralNode);
        }else if(tokens.get(position).getType().equals(TokenType.STRING_LITERAL)){
            StringLiteralNode stringLiteralNode = parseString(tokens.get(position).getLexeme());
            position++;
            return  new TermNode(stringLiteralNode);
        }else if(tokens.get(position).getType().equals(TokenType.IDENTIFIER)){
            IdentifierNode identifierNode = parseIdentifierNode(tokens.get(position).getLexeme());
            position++;
            return new TermNode(identifierNode);
        }else {
           return null;
        }


    }

    private ExpressionNode parseAdditionSubtractionExpression() {
        position--;
        TermNode leftTerm = parseTermNode();

        while (tokens.get(position).getType().equals(TokenType.PLUS) || tokens.get(position).getType().equals(TokenType.MINUS)) {
            Token operator = tokens.get(position);
            position++;

            TermNode rightTerm = parseTermNode();
            ExpressionNode binaryExpression;
            if (operator.getType() == TokenType.PLUS) {
                binaryExpression = new ExpressionNode(new AdditionNode(leftTerm, rightTerm));
            } else {
                binaryExpression = new ExpressionNode(new SubtractionNode(leftTerm, rightTerm));
            }
            leftTerm = binaryExpression.getTermNode();
        }

        match(TokenType.SEMICOLON);
        return new ExpressionNode(leftTerm);
    }

    private IfStatement parseIfStatement() {
        match(TokenType.KEYWORD_IF); // Match the "if" keyword
        match(TokenType.LPAREN); // Match the opening parenthesis
        ExpressionNode condition = new ExpressionNode(parseEquals()); // Parse the condition
        match(TokenType.RPAREN); // Match the closing parenthesis
        BlockStatement thenBranch = parseBlockStatement(); // Parse the "then" branch
        return new IfStatement(condition, thenBranch);
    }

    private WhileNode parseWhileStatement() {
        match(TokenType.KEYWORD_WHILE); // Match the "if" keyword
        match(TokenType.LPAREN); // Match the opening parenthesis
        ExpressionNode condition = new ExpressionNode(parseEquals()); // Parse the condition
        match(TokenType.RPAREN); // Match the closing parenthesis
        BlockStatement thenBranch = parseBlockStatement(); // Parse the "then" branch
        return new WhileNode(condition, thenBranch);
    }

    private BlockStatement parseBlockStatement(){
        StatementListNode statementListNode = new StatementListNode();
        match(TokenType.LEFT_BRACE);

        while (position < tokens.size() && tokens.get(position).getType() != TokenType.RIGHT_BRACE) {
            statementListNode.addStatement(parseStatementNode());
        }
        match(TokenType.RIGHT_BRACE);
        return new BlockStatement(statementListNode);
    }

    private EqualsNode parseEquals() {
        TermNode leftOperand = parseTermNode(); // Parse a VariableNode as the left operand
        match(TokenType.EQUAL);
        TermNode rightOperand = parseTermNode();
        return new EqualsNode(leftOperand, rightOperand);
    }



    private TypeNode parseTypeNode(String type){
        return new TypeNode(type);
    }

    private IntegerLiteralNode parseInteger(String integer){
        return new IntegerLiteralNode(Integer.parseInt(integer));
    }

    private StringLiteralNode parseString(String string){
        return new StringLiteralNode(string);
    }

    private IdentifierNode parseIdentifierNode(String identifier){
        return new IdentifierNode(identifier);
    }


    private boolean match(TokenType expectedType) {
        Token token = tokens.get(position);
        if (token.getType() == expectedType) {
            position++;
            return true; // Match successful
        } else {
            throw new ParseException("Expected " + expectedType + " but found " + token.getType() + " on line " + token.getLine());
        }
    }
}

