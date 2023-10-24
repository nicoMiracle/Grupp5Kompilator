package compilator;
import java.util.List;

public class Parser {
    public static void main(String[] args) {
        JavaLexer lexer = new JavaLexer();
        List<Token> tokens = lexer.lex("hey=5;name=\"Nikki\";x=y;add=3+4+5+6;sub=3-4-5-6;mix=3+4-5+6-7;all=3+ID+\"text\"-ID-\"text\";");
        List<Token> tokens2 = lexer.lex("if(this==true){x=y;int x =3;while(that==false){x=y;y=6;}}yes=\"YESSSS\";");
        List<Token> tokens3 = lexer.lex("System.out.println(x); x=y; while(this==false){System.out.println(\"hello\");}");
        Parser parser = new Parser(tokens3);
        Code_Generator code_generator = new Code_Generator();
        System.out.println(code_generator.generateCode(parser.parseProgram()));
    }
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

        if (!termList.isTermsEmpty()){
            return termList;
        }else{
            throw new ParseException("Error: a value must be assigned");
        }
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
            throw new ParseException("Invalid token after Plus: " + tokens.get(position).lexem() + " on line " + tokens.get(position).line());
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
        }
        else  if(termNode.getInput()!=null){
            return new NegativeTerm(termNode.getInput());
        }else  if(termNode.getMethodCall()!=null){
            return new NegativeTerm(termNode.getMethodCall());
        }else{
            throw new ParseException("Invalid token after Minus : " + tokens.get(position).lexem() + " on line " + tokens.get(position).line());
        }
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




