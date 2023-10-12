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

        while (i < input.length()) {
            char currentChar = input.charAt(i);

            switch (currentChar) {
                case '(':
                    tokenList.add(new Token(TokenType.LPAREN, "("));
                    i++;
                    break;
                case ')':
                    tokenList.add(new Token(TokenType.RPAREN, ")"));
                    i++;
                    break;
                case '=':
                    tokenList.add(new Token(TokenType.ASSIGN, "="));
                    i++;
                    break;
                case ';':
                    tokenList.add(new Token(TokenType.EOF, ";"));
                    i++;
                    break;
                case ' ':
                case '\t':
                case '\n':
                    // Skip whitespace characters
                    i++;
                    break;
                default:
                    if (Character.isDigit(currentChar)) {
                        String number = getAtom(input, i);
                        i += number.length();
                        tokenList.add(new Token(TokenType.TYPE_INT, number));
                    } else if (Character.isLetter(currentChar)) {
                        String atom = getAtom(input, i);
                        i += atom.length();
                        if (atom.equals("int")) {
                            tokenList.add(new Token(TokenType.TYPE_INT, atom));
                        } else {
                            tokenList.add(new Token(TokenType.IDENTIFIER, atom));
                        }
                    }
                    break;
            }
        }

        return tokenList;
    }
}