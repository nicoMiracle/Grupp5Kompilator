package org.example;

import java.util.LinkedList;

public class JavaLexer {

    public String getAtom(String text, int i) {
        int j = i;
        while (j < text.length() && (Character.isLetter(text.charAt(j)) || Character.isDigit(text.charAt(j)))) {
            j++;
        }
        return text.substring(i, j);
    }

    public LinkedList<Token> lex(String input) {
        LinkedList<Token> tokenList = new LinkedList<>();
        int i = 0;
        int lineNumber=1;
        while (i < input.length()) {
            char currentChar = input.charAt(i);
            switch (currentChar) {
                case '(':
                    tokenList.add(new Token(TokenType.LPAREN, "(", lineNumber));
                    i++;
                    break;
                case ')':
                    tokenList.add(new Token(TokenType.RPAREN, ")",lineNumber));
                    i++;
                    break;
                case '=':
                    tokenList.add(new Token(TokenType.ASSIGN, "=",lineNumber));
                    i++;
                    break;
                case ';':
                    tokenList.add(new Token(TokenType.EOF, ";",lineNumber));
                    lineNumber++;
                    i++;
                    break;
                case ' ':
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
                        tokenList.add(new Token(TokenType.TYPE_INT, number,lineNumber));
                    } else if (Character.isLetter(currentChar)) {
                        String atom = getAtom(input, i);
                        i += atom.length();
                        if (atom.equals("int")) {
                            tokenList.add(new Token(TokenType.TYPE_INT, atom,lineNumber));
                        } else {
                            tokenList.add(new Token(TokenType.IDENTIFIER, atom,lineNumber));
                        }
                    }
                    break;
            }
        }
        return tokenList;
    }
}