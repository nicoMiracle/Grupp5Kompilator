package org.example;

import java.util.ArrayList;
import java.util.List;

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
    public String getTextBetweenQuotation(String text, int i) {
        int j = i;
        do{
            j++;
        }while(j < text.length() && text.charAt(j)!='"');
        int positionsSkipped = 1;
        return text.substring(i+positionsSkipped, j);
    }

    public List<Token> lex(String input) {
        List<Token> tokenList = new ArrayList<>();
        int currentPosition = 0;
        int lineNumber=1;
        while (currentPosition < input.length()) {
            char currentChar = input.charAt(currentPosition);
            switch (currentChar) {
                case '{':
                    tokenList.add(new Token(TokenType.LBRACE, "{", lineNumber));
                    currentPosition++;
                    break;
                case '}':
                    tokenList.add(new Token(TokenType.RBRACE, "}", lineNumber));
                    currentPosition++;
                    break;
                case '(':
                    tokenList.add(new Token(TokenType.LPAREN, "(", lineNumber));
                    currentPosition++;
                    break;
                case ')':
                    tokenList.add(new Token(TokenType.RPAREN, ")",lineNumber));
                    currentPosition++;
                    break;
                case '=':
                    int nextCharPosition = currentPosition+1;
                    if(input.charAt(nextCharPosition)=='='){
                        tokenList.add(new Token(TokenType.EQUAL, "==",lineNumber));
                        int positionsSkipped = 2;
                        currentPosition+=positionsSkipped;
                        break;
                    }
                    tokenList.add(new Token(TokenType.ASSIGN, "=",lineNumber));
                    currentPosition++;
                    break;
                case '+':
                    tokenList.add(new Token(TokenType.PLUS, "+",lineNumber));
                    currentPosition++;
                    break;
                case '-':
                    tokenList.add(new Token(TokenType.MINUS, "-",lineNumber));
                    currentPosition++;
                    break;
                case ';':
                    tokenList.add(new Token(TokenType.EOF, ";",lineNumber));
                    lineNumber++;
                    currentPosition++;
                    break;
                case ',':
                    tokenList.add(new Token(TokenType.COMMA, ",",lineNumber));
                    currentPosition++;
                    break;
                case '.':
                    tokenList.add(new Token(TokenType.DOT, ".",lineNumber));
                    currentPosition++;
                    break;
                case ' ':
                    currentPosition++;
                    break;
                case '"':
                    String stringValue = getTextBetweenQuotation(input,currentPosition);
                    tokenList.add(new Token(TokenType.STRING_LITERAL, stringValue,lineNumber));
                    int positionsSkipped = 2;
                    currentPosition += stringValue.length()+positionsSkipped;
                    break;
                default:
                    if (Character.isDigit(currentChar)) {
                        String number = getAtom(input, currentPosition);
                        currentPosition += number.length();
                        tokenList.add(new Token(TokenType.INTEGER_LITERAL, number,lineNumber));
                    } else if (Character.isLetter(currentChar)) {
                        String atom = getAtom(input, currentPosition);
                        currentPosition += atom.length();
                        switch (atom) {
                            case "int" -> tokenList.add(new Token(TokenType.TYPE_INT, atom, lineNumber));
                            case "String" -> tokenList.add(new Token(TokenType.TYPE_STRING, atom, lineNumber));
                            case "if" -> tokenList.add(new Token(TokenType.IF, atom, lineNumber));
                            case "while" -> tokenList.add(new Token(TokenType.WHILE, atom, lineNumber));
                            case "return" -> tokenList.add(new Token(TokenType.FUNCTION_RETURN, atom, lineNumber));
                            default -> tokenList.add(new Token(TokenType.IDENTIFIER, atom, lineNumber));
                        }
                    }else{
                        char invalidChar = input.charAt(currentPosition);
                        String errorMessage = "Unrecognized character: '" + invalidChar + "'";
                        listener.lexicalError(errorMessage,lineNumber);
                        currentPosition++;
                    }
            }
        }
        return tokenList;
    }
}