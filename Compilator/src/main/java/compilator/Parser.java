package compilator;
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

    public StatementNode parseStatementNode() {
        if(tokens.get(position).type().equals(TokenType.IF)){
            return new StatementNode(parseIfStatement());
        }else if(tokens.get(position).type().equals(TokenType.WHILE)){
            return new StatementNode(parseWhileStatement());
        }else if(tokens.get(position).type().equals(TokenType.FUNCTION_RETURN)){
            position++;
            return new StatementNode(parseReturnStatement());
        }else if(tokens.get(position).type().equals(TokenType.IDENTIFIER)){
            String identifier = tokens.get(position).lexem();
            position++;
            if(tokens.get(position).type().equals(TokenType.ASSIGN)){
                position++;
                return new StatementNode(parseAssignmentStatementNode(identifier));
            }else if ((tokens.get(position).type().equals(TokenType.LPAREN))
                    || tokens.get(position).type().equals(TokenType.DOT)) {
                return new StatementNode(parseMethodCall(identifier));
            }else{
                throw new ParseException("Unexpected token: " + tokens.get(position).lexem() + " on line " + tokens.get(position).line());
            }
        }else if(tokens.get(position).type().equals(TokenType.TYPE_INT) || tokens.get(position).type().equals(TokenType.TYPE_STRING)){
            position++;
            if (tokens.get(position).type().equals(TokenType.IDENTIFIER) && tokens.get(position+1).type().equals(TokenType.LPAREN)) {
                return new StatementNode(parseMethodDeclarationStatement());
            }
            return new StatementNode(parseDeclarationStatement());
        }else if(tokens.get(position).type().equals(TokenType.SYSTEM)){
            position++;
            return new StatementNode(parseOutputStatement());
        }else{
            throw new ParseException("Unexpected token: " + tokens.get(position).lexem() + " on line " + tokens.get(position).line());
        }
    }

    private ReturnStatement parseReturnStatement(){
        ExpressionNode expressionNode = parseExpression();
        match(TokenType.SEMICOLON);
        return new ReturnStatement(expressionNode);
    }

    private MethodDeclarationStatement parseMethodDeclarationStatement(){
        IdentifierNode identifierFirstNode = parseIdentifierNode(tokens.get(position).lexem());
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
        while(position<tokens.size() && tokens.get(position+1).type()!=(TokenType.RPAREN) ){
            parameterListNode.addParameter(parseParameterNode());
        }
        return parameterListNode;
    }

    private ParameterNode parseParameterNode(){
        match(tokens.get(position).type());
        IdentifierNode identifierNode = parseIdentifierNode(tokens.get(position).lexem());
        return new ParameterNode(identifierNode);
    }

    public AssignmentStatementNode parseAssignmentStatementNode(String identifier){
        IdentifierNode identifierNode = parseIdentifierNode(identifier);
        ExpressionNode expressionNode = parseExpression();
        match(TokenType.SEMICOLON);
        return new AssignmentStatementNode(identifierNode,expressionNode);
    }

    public DeclarationStatement parseDeclarationStatement(){
        String identifier = tokens.get(position).lexem();
        position++;
        match(TokenType.ASSIGN);
        return new DeclarationStatement(parseAssignmentStatementNode(identifier));
    }


    private OutputStatement parseOutputStatement(){
        match(TokenType.DOT);
        match(TokenType.OUT);
        match(TokenType.DOT);
        match(TokenType.PRINTLN);
        match(TokenType.LPAREN);
        ExpressionNode expressionNode = parseExpression();
        match(TokenType.RPAREN);
        position++;
        return new OutputStatement(expressionNode);
    }

    private MethodCall parseMethodCall(String identifier){
        IdentifierNode identifierFirstNode = parseIdentifierNode(identifier);
        if (tokens.get(position).type().equals(TokenType.LPAREN)){
            position++;
            ExpressionNode expressionNode = parseExpression();
            match(TokenType.RPAREN);
            return new MethodCall(identifierFirstNode, expressionNode);
        } else{
            position++;
            IdentifierNode identifierSecondNode = parseIdentifierNode(tokens.get(position).lexem());
            position++;
            match(TokenType.LPAREN);
            ExpressionNode expressionNode = parseExpression();
            match(TokenType.RPAREN);
            match(TokenType.SEMICOLON);
            return new MethodCall(identifierFirstNode, identifierSecondNode, expressionNode);
        }

    }

    public ExpressionNode parseExpression(){
        if(tokens.get(position).type().equals(TokenType.SEMICOLON)){
            throw new ParseException("ERROR:Expression expected at "+tokens.get(position).line());
        }
        else if(tokens.get(position+1).type().equals(TokenType.EQUAL)){
            return new ExpressionNode(parseEquals());
        }
        return new ExpressionNode(parseTermList());
    }

   public TermList parseTermList(){
        TermList termList = new TermList();
        while(position<tokens.size() && !tokens.get(position).type().equals(TokenType.SEMICOLON)
                && !tokens.get(position).type().equals(TokenType.RPAREN)){
            termList.add(parseTermNode());
        }

        return termList;
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
        }else if (tokens.get(position).type().equals(TokenType.IDENTIFIER)) {
            IdentifierNode identifierNode = parseIdentifierNode(tokens.get(position).lexem());
            String id = tokens.get(position).lexem();
            position++;
            if (position < tokens.size() && (tokens.get(position).type().equals(TokenType.LPAREN) ||
                     tokens.get(position).type().equals(TokenType.DOT))) {
                return new TermNode(parseMethodCall(id));
            }
            return new TermNode(identifierNode);
        }
        else if(tokens.get(position).type().equals(TokenType.INPUT)){
            position++;
            return new TermNode(parseInputStatement());
        }else  if(tokens.get(position).type().equals(TokenType.PLUS)) {
            position++;
            return new TermNode(parsePositiveTerm());
        }else  if(tokens.get(position).type().equals(TokenType.MINUS)) {
            position++;
            return new TermNode(parseNegativeTerm());
        }else {
            throw new ParseException("Unexpected token: " + tokens.get(position).lexem() + " on line " + tokens.get(position).line());
        }
    }

    private PositiveTermNode parsePositiveTerm() {
        TermNode termNode = parseTermNode();
        PositiveTermNode positiveTermNode = null;

        if (termNode.getIdentifierNode() != null) {
            positiveTermNode = new PositiveTermNode(termNode.getIdentifierNode());
        }
        if (termNode.getIntegerLiteralNode() != null) {
            positiveTermNode = new PositiveTermNode(termNode.getIntegerLiteralNode());
        }
        if (termNode.getStringLiteralNode() != null) {
            positiveTermNode = new PositiveTermNode(termNode.getStringLiteralNode());
        }
        if (termNode.getInput() != null) {
            positiveTermNode = new PositiveTermNode(termNode.getInput());
        }
        if (termNode.getMethodCall() != null) {
            positiveTermNode = new PositiveTermNode(termNode.getMethodCall());
        }

        return positiveTermNode;
    }

    private NegativeTerm parseNegativeTerm() {
        TermNode termNode = parseTermNode();
        NegativeTerm negativeTerm = null;

        if (termNode.getIdentifierNode() != null) {
            negativeTerm = new NegativeTerm(termNode.getIdentifierNode());
        }
        if (termNode.getIntegerLiteralNode() != null) {
            negativeTerm = new NegativeTerm(termNode.getIntegerLiteralNode());
        }
        if (termNode.getStringLiteralNode() != null) {
            negativeTerm = new NegativeTerm(termNode.getStringLiteralNode());
        }
        if (termNode.getInput() != null) {
            negativeTerm = new NegativeTerm(termNode.getInput());
        }
        if (termNode.getMethodCall() != null) {
            negativeTerm = new NegativeTerm(termNode.getMethodCall());
        }
        return negativeTerm;
    }

    private WhileNode parseWhileStatement() {
        match(TokenType. WHILE); // Match the "if" keyword
        match(TokenType.LPAREN); // Match the opening parenthesis
        ExpressionNode condition = parseExpression(); // Parse the condition
        match(TokenType.RPAREN); // Match the closing parenthesis
        BlockStatement thenBranch = parseBlockStatement(); // Parse the "then" branch
        return new WhileNode(condition, thenBranch);
    }

    public IfStatement parseIfStatement() {
        match(TokenType.IF); // Match the "if" keyword
        match(TokenType.LPAREN); // Match the opening parenthesis
        ExpressionNode condition = parseExpression(); // Parse the condition
        match(TokenType.RPAREN); // Match the closing parenthesis
        BlockStatement thenBranch = parseBlockStatement(); // Parse the "then" branch
        return new IfStatement(condition, thenBranch);
    }
    private InputStatement parseInputStatement(){
        match(TokenType.DOT);
        match(TokenType.NEXT_LINE);
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
        match(TokenType.LBRACE);
        while (position < tokens.size() && tokens.get(position).type() != TokenType.RBRACE) {
            statementListNode.addStatement(parseStatementNode());
        }
        match(TokenType.RBRACE);
        return new BlockStatement(statementListNode);
    }


    private IntegerLiteralNode parseInteger(String integer){
        return new IntegerLiteralNode(Integer.parseInt(integer));
    }


    private StringLiteralNode parseString(String string) {
        return new StringLiteralNode(string);
    }


    private IdentifierNode parseIdentifierNode(String identifier){
        return new IdentifierNode(identifier);
    }

    private void match(TokenType expectedType) {
        Token token = tokens.get(position);
        if (token.type() == expectedType) {
            position++;
        } else {
            throw new ParseException("Expected " + expectedType + " but found " + token.type() + " on line " + token.line());
        }
    }
}




