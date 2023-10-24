package org.example;
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

    public StatementNode parseStatementNode() {

        if(tokens.get(position).getType().equals(TokenType.CONTROL_IF)){
            return new StatementNode(parseIfStatement());
        }else if(tokens.get(position).getType().equals(TokenType.CONTROL_WHILE)){
            return new StatementNode(parseWhileStatement());
        }else if(tokens.get(position).getType().equals(TokenType.FUNCTION_RETURN)){
            position++;
            return new StatementNode(parseReturnStatement());
        }else if(tokens.get(position).getType().equals(TokenType.IDENTIFIER)){
            String identifier = tokens.get(position).getLexeme();
            if (position< tokens.size()){
                position++;
                if(match(TokenType.ASSIGN)){
                    return new StatementNode(parseAssignmentStatementNode(identifier));
                }else if ((tokens.get(position).getType().equals(TokenType.LPAREN))
                        || tokens.get(position + 1).getType().equals(TokenType.DOT)) {
                    position++;
                    return new StatementNode(parseMethodCall());
                }else{
                    throw new ParseException("Unexpected token: " + tokens.get(position).getLexeme() + " on line " + tokens.get(position).getLine());
                }
            }else{
                throw new ParseException("Invalid statement");
            }
        }else if(tokens.get(position).getType().equals(TokenType.TYPE_INT) || tokens.get(position).getType().equals(TokenType.TYPE_STRING)){
            if (position< tokens.size()){
                position++;
                if (tokens.get(position).getType().equals(TokenType.IDENTIFIER) && tokens.get(position+1).getType().equals(TokenType.LPAREN)) {
                    return new StatementNode(parseMethodDeclarationStatement());
                }
                return new StatementNode(parseDeclarationStatement());
            }else{
                throw new ParseException("Invalid statement");
            }
        }else if(tokens.get(position).getType().equals(TokenType.SYSTEM)){
            position++;
            return new StatementNode(parseOutputStatement());
        }else{
            throw new ParseException("Unexpected token: " + tokens.get(position).getLexeme() + " on line " + tokens.get(position).getLine());
        }
    }

    private ReturnStatement parseReturnStatement(){
        ExpressionNode expressionNode = parseExpression();
        match(TokenType.SEMICOLON);
        return new ReturnStatement(expressionNode);
    }

    private MethodDeclarationStatement parseMethodDeclarationStatement(){
        IdentifierNode identifierFirstNode = parseIdentifierNode(tokens.get(position).getLexeme());
        position++;
        match(TokenType.LPAREN);
        ParameterListNode parameter = parseParameterListNode();
        position++;
        match(TokenType.RPAREN);
        BlockStatement block = parseBlockStatement();
        return new MethodDeclarationStatement(identifierFirstNode, parameter, block);
    }

    private ParameterListNode parseParameterListNode(){
        ParameterListNode parameterListNode = new ParameterListNode();
        while(position<tokens.size() && tokens.get(position+1).getType()!=(TokenType.RPAREN) ){
            parameterListNode.addParameter(parseParameterNode());
        }
        return parameterListNode;
    }

    private ParameterNode parseParameterNode(){
        match(tokens.get(position).getType());
        IdentifierNode identifierNode = parseIdentifierNode(tokens.get(position).getLexeme());
        return new ParameterNode(identifierNode);
    }

    public AssignmentStatementNode parseAssignmentStatementNode(String identifier){
        IdentifierNode identifierNode = parseIdentifierNode(identifier);
        ExpressionNode expressionNode = parseExpression();
        match(TokenType.SEMICOLON);
        return new AssignmentStatementNode(identifierNode,expressionNode);
    }

    public DeclarationStatement parseDeclarationStatement(){
        String identifier = tokens.get(position).getLexeme();
        position++;
        match(TokenType.ASSIGN);
        return new DeclarationStatement(parseAssignmentStatementNode(identifier));
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

    private MethodCall parseMethodCall(){
        IdentifierNode identifierFirstNode = parseIdentifierNode(tokens.get(position-1).getLexeme());
        if (tokens.get(position).getType().equals(TokenType.LPAREN)){
            position++;
            ExpressionNode expressionNode = parseExpression();
            match(TokenType.RPAREN);
            return new MethodCall(identifierFirstNode, expressionNode);
        }else if(tokens.get(position).getType().equals(TokenType.DOT)){
            position++;
            IdentifierNode identifierSecondNode = parseIdentifierNode(tokens.get(position).getLexeme());
            position++;
            match(TokenType.LPAREN);
            ExpressionNode expressionNode = parseExpression();
            match(TokenType.RPAREN);
            match(TokenType.SEMICOLON);
            return new MethodCall(identifierFirstNode, identifierSecondNode, expressionNode);
        }else {
            throw new ParseException("Unexpected token: " + tokens.get(position).getLexeme() + " on line " + tokens.get(position).getLine());
        }

    }

    public ExpressionNode parseExpression(){
        if(findEqual()){
            return new ExpressionNode(parseEquals());
        }
        return new ExpressionNode(parseTermList());
    }

    private boolean findEqual(){
        for(int i = position; i < tokens.size();i++){
            TokenType token = tokens.get(i).getType();
            if(token.equals(TokenType.EQUAL)){
                return true;
            }
        }
        return false;
    }

    public TermList parseTermList(){
        TermList termList = new TermList();
        while(position<tokens.size() && !tokens.get(position).getType().equals(TokenType.SEMICOLON)
                && !tokens.get(position).getType().equals(TokenType.RPAREN)){
            termList.add(parseTermNode());
        }

        if (!termList.isTermsEmpty()){
            return termList;
        }else{
            throw new ParseException("Error: a value must be assigned");
        }
    }

    public TermNode parseTermNode(){
        if(tokens.get(position).getType().equals(TokenType.INTEGER_LITERAL)){
            IntegerLiteralNode integerLiteralNode = parseInteger(tokens.get(position).getLexeme());
            position++;
            return new TermNode(integerLiteralNode);
        }else if(tokens.get(position).getType().equals(TokenType.STRING_LITERAL)){
            StringLiteralNode stringLiteralNode = parseString(tokens.get(position).getLexeme());
            position++;
            return  new TermNode(stringLiteralNode);
        }else if (position < tokens.size() && tokens.get(position).getType().equals(TokenType.IDENTIFIER)) {
            IdentifierNode identifierNode = parseIdentifierNode(tokens.get(position).getLexeme());
            position++;

            if (position < tokens.size() && (tokens.get(position).getType().equals(TokenType.LPAREN) ||
                    (position + 1 < tokens.size() && tokens.get(position + 1).getType().equals(TokenType.DOT)))) {
                return new TermNode(parseMethodCall());
            }

            return new TermNode(identifierNode);
        }
        else if(tokens.get(position).getType().equals(TokenType.INPUT)){
            position++;
            return new TermNode(parseInputStatement());
        }else  if(tokens.get(position).getType().equals(TokenType.PLUS)) {
            position++;
            return new TermNode(parsePositiveTerm());
        }else  if(tokens.get(position).getType().equals(TokenType.MINUS)) {
            position++;
            return new TermNode(parseNegativeTerm());
        }else {
            throw new ParseException("Unexpected token: " + tokens.get(position).getLexeme() + " on line " + tokens.get(position).getLine());
        }
    }

    private PositiveTermNode parsePositiveTerm(){
        TermNode termNode = parseTermNode();
        if(termNode.getIdentifierNode()!=null){
            return new PositiveTermNode(termNode.getIdentifierNode());
        }else  if(termNode.getIntegerLiteralNode()!=null){
            return new PositiveTermNode(termNode.getIntegerLiteralNode());
        }else  if(termNode.getStringLiteralNode()!=null){
            return new PositiveTermNode(termNode.getStringLiteralNode());
        }else  if(termNode.getInput()!=null){
            return new PositiveTermNode(termNode.getInput());
        }else  if(termNode.getMethodCall()!=null){
            return new PositiveTermNode(termNode.getMethodCall());
        }else{
            throw new ParseException("Invalid token efter Plus: " + tokens.get(position).getLexeme() + " on line " + tokens.get(position).getLine());
        }
    }


    private NegativeTerm parseNegativeTerm(){
        TermNode termNode = parseTermNode();
        if(termNode.getIdentifierNode()!=null){
            return new NegativeTerm(termNode.getIdentifierNode());
        }else  if(termNode.getIntegerLiteralNode()!=null){
            return new NegativeTerm(termNode.getIntegerLiteralNode());
        }else  if(termNode.getStringLiteralNode()!=null){
            return new NegativeTerm(termNode.getStringLiteralNode());
        }else  if(termNode.getInput()!=null){
            return new NegativeTerm(termNode.getInput());
        }else  if(termNode.getMethodCall()!=null){
            return new NegativeTerm(termNode.getMethodCall());
        }else{
            throw new ParseException("Invalid token efter Minus : " + tokens.get(position).getLexeme() + " on line " + tokens.get(position).getLine());
        }

    }


    private WhileNode parseWhileStatement() {
        match(TokenType. CONTROL_WHILE); // Match the "if" keyword
        match(TokenType.LPAREN); // Match the opening parenthesis
        ExpressionNode condition = parseExpression(); // Parse the condition
        match(TokenType.RPAREN); // Match the closing parenthesis
        BlockStatement thenBranch = parseBlockStatement(); // Parse the "then" branch
        return new WhileNode(condition, thenBranch);
    }

    private IfStatement parseIfStatement() {
        match(TokenType.CONTROL_IF); // Match the "if" keyword
        match(TokenType.LPAREN); // Match the opening parenthesis
        ExpressionNode condition = parseExpression(); // Parse the condition
        match(TokenType.RPAREN); // Match the closing parenthesis
        BlockStatement thenBranch = parseBlockStatement(); // Parse the "then" branch
        return new IfStatement(condition, thenBranch);
    }

    private InputStatement parseInputStatement(){
        match(TokenType.DOT);
        match(TokenType.NEXTLINE);
        match(TokenType.LPAREN);
        match(TokenType.RPAREN);
        return new InputStatement();
    }

    private EqualsNode parseEquals() {
        TermNode leftOperand = parseTermNode(); // Parse a VariableNode as the left operand
        match(TokenType.EQUAL);
        TermNode rightOperand = parseTermNode();
        return new EqualsNode(leftOperand, rightOperand);
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




