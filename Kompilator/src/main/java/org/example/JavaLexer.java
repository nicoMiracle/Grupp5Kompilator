package org.example;

import java.util.LinkedList;

public class JavaLexer {
    private DefaultLexerErrorListener listener;

    public void setListener(DefaultLexerErrorListener listener) {
        this.listener = listener;
    }

    protected String getAtom(String text, int i) {
        int j = i;
        while (j < text.length() && (Character.isLetter(text.charAt(j)) || Character.isDigit(text.charAt(j)))) {
            j++;
        }
        return text.substring(i, j);
    }
    protected String getStringValue(String text, int i) {
        int j = i;
        do{
            j++;
        }while(j < text.length() && text.charAt(j)!='"');
        return text.substring(i, j+1);
    }

    public LinkedList<Token> lex(String input) {
        LinkedList<Token> tokenList = new LinkedList<>();
        int i = 0;
        int lineNumber=1;
        while (i < input.length()) {
            char currentChar = input.charAt(i);
            switch (currentChar) {
                case '{':
                    tokenList.add(new Token(TokenType.LBRACE, "{", lineNumber));
                    i++;
                    break;
                case '}':
                    tokenList.add(new Token(TokenType.RBRACE, "}", lineNumber));
                    i++;
                    break;
                case '(':
                    tokenList.add(new Token(TokenType.LPAREN, "(", lineNumber));
                    i++;
                    break;
                case ')':
                    tokenList.add(new Token(TokenType.RPAREN, ")",lineNumber));
                    i++;
                    break;
                case '=':
                    int nextCharPosition = i+1;
                    if(input.charAt(nextCharPosition)=='='){
                        tokenList.add(new Token(TokenType.EQUAL, "==",lineNumber));
                        i+=2;
                        break;
                    }
                    tokenList.add(new Token(TokenType.ASSIGN, "=",lineNumber));
                    i++;
                    break;
                case '+':
                    tokenList.add(new Token(TokenType.PLUS, "+",lineNumber));
                    i++;
                    break;
                case '-':
                    tokenList.add(new Token(TokenType.MINUS, "-",lineNumber));
                    i++;
                    break;
                case ';':
                    tokenList.add(new Token(TokenType.EOF, ";",lineNumber));
                    lineNumber++;
                    i++;
                    break;
                case ',':
                    tokenList.add(new Token(TokenType.COMMA, ",",lineNumber));
                    i++;
                    break;
                case ' ':
                    i++;
                    break;
                case '"':
                    String stringValue = getStringValue(input,i);
                    tokenList.add(new Token(TokenType.STRING_LITERAL, stringValue,lineNumber));
                    i += stringValue.length();
                    break;
                case '\t':
                case '\n':
                    // Skip whitespace characters
                    lineNumber++;
                    i++;
                    break;
                default:
                    if (Character.isDigit(currentChar)) {
                        String number = getAtom(input, i);
                        i += number.length();
                        tokenList.add(new Token(TokenType.INTEGER_LITERAL, number,lineNumber));
                    } else if (Character.isLetter(currentChar)) {
                        String atom = getAtom(input, i);
                        i += atom.length();
                        if (atom.equals("int")) {
                            tokenList.add(new Token(TokenType.TYPE_INT, atom,lineNumber));
                        } else if(atom.equals("String")){
                            tokenList.add(new Token(TokenType.TYPE_STRING, atom, lineNumber));
                        }else if(atom.equals("if")){
                            tokenList.add(new Token(TokenType.IF, atom, lineNumber));
                        }else if(atom.equals("while")){
                            tokenList.add(new Token(TokenType.WHILE, atom, lineNumber));
                        }else if(atom.equals("return")){
                            tokenList.add(new Token(TokenType.FUNCTION_RETURN, atom, lineNumber));
                        } else if(input.charAt(i+1)=='('){
                            tokenList.add(new Token(TokenType.FUNCTION, atom,lineNumber));
                        } else {
                            tokenList.add(new Token(TokenType.IDENTIFIER, atom,lineNumber));
                        }
                    }else{

                        char invalidChar = input.charAt(i);
                        String errorMessage = "Unrecognized character: '" + invalidChar + "'";

                        // Report the error using the error listener
                        listener.lexicalError(errorMessage,lineNumber);

                        // Move to the next character to continue processing
                        i++;

                    }
            }
        }
        return tokenList;
    }
}