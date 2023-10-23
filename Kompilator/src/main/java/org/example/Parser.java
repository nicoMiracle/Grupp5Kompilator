package org.example;
import java.util.List;

public class Parser {
    private final List<Token> tokens;
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
        if(tokens.get(position).type().equals(TokenType.IDENTIFIER)){
            String identifier = tokens.get(position).lexem();
            position++;
            if(tokens.get(position).type().equals(TokenType.ASSIGN)){
                position++;
                return new StatementNode(parseAssignmentStatementNode(identifier));
            }
        }else if(tokens.get(position).type().equals(TokenType.TYPE_INT) || tokens.get(position).type().equals(TokenType.TYPE_STRING)){
            position++;
            if (tokens.get(position).type().equals(TokenType.IDENTIFIER) && tokens.get(position+1).type().equals(TokenType.LPAREN)) {
                return new StatementNode(parseMethodDeclarationStatement());
            }
            return new StatementNode(parseDeclarationStatement());
        }else if(tokens.get(position).type().equals(TokenType.CONTROL_IF)){
            return new StatementNode(parseIfStatement());
        }else if(tokens.get(position).type().equals(TokenType.CONTROL_WHILE)){
            return new StatementNode(parseWhileStatement());
        }else if(tokens.get(position).type().equals(TokenType.FUNCTION_RETURN)){
            position++;
            return new StatementNode(parseReturnStatement());
        }else if(tokens.get(position).type().equals(TokenType.SYSTEM)){
            position++;
            return new StatementNode(parseOutputStatement());
        }else{
            throw new ParseException("Unexpected token: " + tokens.get(position).lexem() + " on line " + tokens.get(position).type());
        }
        return null;
    }

    private MethodDeclarationStatement parseMethodDeclarationStatement(){
        IdentifierNode identifierFirstNode = parseIdentifierNode(tokens.get(position).lexem());
        position++;
        match(TokenType.LPAREN);
        ParameterListNode parameterListNode = parseParameterListNode();
        position++;
        match(TokenType.RPAREN);
        BlockStatement block = parseBlockStatement();
        return new MethodDeclarationStatement(identifierFirstNode, parameterListNode, block);
    }

    private ParameterListNode parseParameterListNode(){
        ParameterListNode parameterListNode = new ParameterListNode();
        while(position<tokens.size() && tokens.get(position+1).type()!=(TokenType.RPAREN) ){
            parameterListNode.addParameter(parseParameterNode());
        }
        return parameterListNode;

    }

    private OutputStatement parseOutputStatement(){
        match(TokenType.DOT);
        match(TokenType.OUT);
        match(TokenType.DOT);
        match(TokenType.PRINT);
        match(TokenType.LPAREN);
        ExpressionNode expressionNode = parseExpression();
        match(TokenType.RPAREN);
        return new OutputStatement(expressionNode);
    }

    private ParameterNode parseParameterNode(){
        position++;
        IdentifierNode identifierNode = parseIdentifierNode(tokens.get(position).lexem());
        return new ParameterNode(identifierNode);
    }

    private MethodCall parseMethodCall(){
        IdentifierNode identifierFirstNode = parseIdentifierNode(tokens.get(position-1).lexem());
        if (tokens.get(position).type().equals(TokenType.LPAREN)){
            ExpressionNode expressionNode = parseExpression();
            match(TokenType.RPAREN);
            return new MethodCall(identifierFirstNode, expressionNode);
        }else if(tokens.get(position).type().equals(TokenType.DOT)){
            position++;
            IdentifierNode identifierSecondNode = parseIdentifierNode(tokens.get(position).lexem());
            position++;
            match(TokenType.LPAREN);
            ExpressionNode expressionNode = parseExpression();
            match(TokenType.RPAREN);
            match(TokenType.SEMICOLON);
            return new MethodCall(identifierFirstNode, identifierSecondNode, expressionNode);
        }else {
            throw new ParseException("Unexpected token: " + tokens.get(position).lexem() + " on line " + tokens.get(position).line());
        }

    }

    private DeclarationStatement parseDeclarationStatement() {
        String identifier = tokens.get(position).lexem();
        position++;
        if(tokens.get(position).type().equals(TokenType.SEMICOLON)){
            position++;
        }else{
            position++;
            AssignmentStatementNode assign = parseAssignmentStatementNode(identifier);
            return new DeclarationStatement(assign);
        }
        return null;
    }

    public AssignmentStatementNode parseAssignmentStatementNode(String identifier){
        IdentifierNode identifierNode = parseIdentifierNode(identifier);

        if(tokens.get(position).type().equals(TokenType.INTEGER_LITERAL)
                || tokens.get(position).type().equals(TokenType.STRING_LITERAL)
                || tokens.get(position).type().equals(TokenType.IDENTIFIER)) {
            position++;
            if (tokens.get(position).type().equals(TokenType.PLUS) || tokens.get(position).type().equals(TokenType.MINUS)) {
                ExpressionNode expressionNode = parseAdditionSubtractionExpression();
                return new AssignmentStatementNode(identifierNode,expressionNode);
            }else if (tokens.get(position).type().equals(TokenType.EQUAL)) {
                ExpressionNode expressionNode = new ExpressionNode(parseEquals());
                return new AssignmentStatementNode(identifierNode, expressionNode);
            }else if (tokens.get(position).type().equals(TokenType.LPAREN) || tokens.get(position).type().equals(TokenType.DOT)){
                return new AssignmentStatementNode(identifierNode, parseExpression());
            }
        }
        ExpressionNode expressionNode = parseExpression();
        match(TokenType.SEMICOLON);
        return new AssignmentStatementNode(identifierNode,expressionNode);
    }


    public ExpressionNode parseExpression(){
        return new ExpressionNode(parseTermNode());
    }


    public TermNode parseTermNode(){

        if(tokens.get(position).type().equals(TokenType.INTEGER_LITERAL)){
            IntegerLiteralNode integerLiteralNode = parseInteger(tokens.get(position).lexem());
            position++;
            return new TermNode(integerLiteralNode);
        }else if(tokens.get(position).type().equals(TokenType.STRING_LITERAL)){
            StringLiteralNode stringLiteralNode = parseString(tokens.get(position).lexem());
            position++;
            return  new TermNode(stringLiteralNode);
        }else if(tokens.get(position).type().equals(TokenType.IDENTIFIER)){
            IdentifierNode identifierNode = parseIdentifierNode(tokens.get(position).lexem());
            position++;
            if (position< tokens.size()
                    && ((tokens.get(position).type().equals(TokenType.LPAREN)) || tokens.get(position + 1).type().equals(TokenType.DOT))) {
                return new TermNode(parseMethodCall());
            }
            return new TermNode(identifierNode);
        }else if(tokens.get(position).type().equals(TokenType.INPUT)){
            position++;
            return new TermNode(parseInputStatement());
        }else {
            return null;
        }
    }

    private InputStatement parseInputStatement(){
        match(TokenType.DOT);
        match(TokenType.NEXTLINE);
        match(TokenType.LPAREN);
        match(TokenType.RPAREN);
        return new InputStatement();
    }

    private ReturnStatement parseReturnStatement(){
        if(tokens.get(position).type().equals(TokenType.INTEGER_LITERAL)
                || tokens.get(position).type().equals(TokenType.STRING_LITERAL)
                || tokens.get(position).type().equals(TokenType.IDENTIFIER)) {
            position++;
            if (tokens.get(position).type().equals(TokenType.PLUS) || tokens.get(position).type().equals(TokenType.MINUS)) {
                ExpressionNode expressionNode = parseAdditionSubtractionExpression();
                return new ReturnStatement(expressionNode);
            }else if (tokens.get(position).type().equals(TokenType.EQUAL)) {
                ExpressionNode expressionNode = new ExpressionNode(parseEquals());
                return new ReturnStatement(expressionNode);
            }else if (tokens.get(position).type().equals(TokenType.LPAREN) || tokens.get(position).type().equals(TokenType.DOT)){
                return new ReturnStatement(parseExpression());
            }
        }

        ExpressionNode expressionNode = parseExpression();
        match(TokenType.SEMICOLON);
        return new ReturnStatement(expressionNode);
    }


    private ExpressionNode parseAdditionSubtractionExpression() {
        position--;
        TermNode leftTerm = parseTermNode();

        while (tokens.get(position).type().equals(TokenType.PLUS) || tokens.get(position).type().equals(TokenType.MINUS)) {
            Token operator = tokens.get(position);
            position++;
            TermNode rightTerm = parseTermNode();
            ExpressionNode binaryExpression;
            if (operator.type() == TokenType.PLUS) {
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
        match(TokenType.CONTROL_IF); // Match the "if" keyword
        match(TokenType.LPAREN); // Match the opening parenthesis
        ExpressionNode condition = new ExpressionNode(parseEquals()); // Parse the condition
        match(TokenType.RPAREN); // Match the closing parenthesis
        BlockStatement thenBranch = parseBlockStatement(); // Parse the "then" branch
        return new IfStatement(condition, thenBranch);
    }

    private WhileNode parseWhileStatement() {
        match(TokenType.CONTROL_WHILE); // Match the "if" keyword
        match(TokenType.LPAREN); // Match the opening parenthesis
        ExpressionNode condition = new ExpressionNode(parseEquals()); // Parse the condition
        match(TokenType.RPAREN); // Match the closing parenthesis
        BlockStatement thenBranch = parseBlockStatement(); // Parse the "then" branch
        return new WhileNode(condition, thenBranch);
    }

    private BlockStatement parseBlockStatement(){
        StatementListNode statementListNode = new StatementListNode();
        match(TokenType.LEFT_BRACE);

        while (position < tokens.size() && tokens.get(position).type() != TokenType.RIGHT_BRACE) {
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
        if (token.type() == expectedType) {
            position++;
            return true; // Match successful
        } else {
            throw new ParseException("Expected " + expectedType + " but found " + token.type()+ " on line " + token.line());
        }
    }
}





