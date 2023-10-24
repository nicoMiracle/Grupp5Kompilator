package compilator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaLexer {
    private DefaultLexerErrorListener listener;
    private final Map<Character, TokenType> charTokenMap = new HashMap<>();
    public JavaLexer() {
        charTokenMap.put('{', TokenType.LBRACE);
        charTokenMap.put('}', TokenType.RBRACE);
        charTokenMap.put('(', TokenType.LPAREN);
        charTokenMap.put(')', TokenType.RPAREN);
        charTokenMap.put('=', TokenType.ASSIGN);
        charTokenMap.put('+', TokenType.PLUS);
        charTokenMap.put('-', TokenType.MINUS);
        charTokenMap.put(';', TokenType.SEMICOLON);
        charTokenMap.put(',', TokenType.COMMA);
        charTokenMap.put('.', TokenType.DOT);
    }
    public void setListener(DefaultLexerErrorListener listener) {
        this.listener = listener;
    }

    public List<Token> lex(String input) {
        List<Token> tokenList = new ArrayList<>();
        int currentPosition = 0;
        int lineNumber = 1;

        while (currentPosition < input.length()) {
            char currentChar = input.charAt(currentPosition);
            if (Character.isWhitespace(currentChar)) {
                currentPosition++;
                continue;
            }
            if (charTokenMap.containsKey(currentChar)) {
                TokenType tokenType = charTokenMap.get(currentChar);
                String tokenValue = String.valueOf(currentChar);
                if (tokenType == TokenType.ASSIGN) {
                    int nextCharPosition = currentPosition + 1;
                    if (nextCharPosition < input.length() && input.charAt(nextCharPosition) == '=') {
                        tokenType = TokenType.EQUAL;
                        tokenValue = "==";
                        currentPosition++;
                    }
                }
                tokenList.add(new Token(tokenType, tokenValue, lineNumber));
                currentPosition++;
                if (tokenType==TokenType.SEMICOLON) {
                    lineNumber++;
                }
            } else if (currentChar == '"') {
                String stringValue = getTextBetweenQuotationMarks(input, currentPosition);
                tokenList.add(new Token(TokenType.STRING_LITERAL, stringValue, lineNumber));
                currentPosition += stringValue.length() + 2; // Skip past the string and both double quotes
            } else if (Character.isDigit(currentChar)) {
                String number = getSequenceOfNumbers(input, currentPosition);
                currentPosition += number.length();
                tokenList.add(new Token(TokenType.INTEGER_LITERAL, number, lineNumber));
            } else if (Character.isLetter(currentChar)) {
                String letter = getSequenceOfLetters(input, currentPosition);
                currentPosition += letter.length();
                handleIdentifierToken(tokenList, letter, lineNumber);
            } else {
                listener.lexicalError("Unrecognized character: '" + currentChar + "'", lineNumber);
                currentPosition++;
            }
        }
        return tokenList;
    }
    private String getTextBetweenQuotationMarks(String text, int i) {
        int j = i + 1;
        while (j < text.length() && text.charAt(j) != '"') {
            j++;
        }
        return text.substring(i + 1, j).trim(); // Trim removes leading and trailing spaces.
    }
    private String getSequenceOfLetters(String text, int i) {
        int j = i;
        while (j < text.length() && (Character.isLetter(text.charAt(j)))) {
            j++;
        }
        return text.substring(i, j);
    }
    private String getSequenceOfNumbers(String text, int i) {
        int j = i;
        while (j < text.length() && (Character.isDigit(text.charAt(j)))) {
            j++;
        }
        return text.substring(i, j);
    }
    private void handleIdentifierToken(List<Token> tokenList, String letter, int lineNumber) {
        switch (letter) {
            case "System" -> tokenList.add(new Token(TokenType.SYSTEM, letter, lineNumber));
            case "out" -> tokenList.add(new Token(TokenType.OUT, letter, lineNumber));
            case "println" -> tokenList.add(new Token(TokenType.PRINTLN, letter, lineNumber));
            case "input" -> tokenList.add(new Token(TokenType.INPUT, letter, lineNumber));
            case "nextLine" -> tokenList.add(new Token(TokenType.NEXT_LINE, letter, lineNumber));
            case "int" -> tokenList.add(new Token(TokenType.TYPE_INT, letter, lineNumber));
            case "String" -> tokenList.add(new Token(TokenType.TYPE_STRING, letter, lineNumber));
            case "if" -> tokenList.add(new Token(TokenType.IF, letter, lineNumber));
            case "while" -> tokenList.add(new Token(TokenType.WHILE, letter, lineNumber));
            case "return" -> tokenList.add(new Token(TokenType.FUNCTION_RETURN, letter, lineNumber));
            default -> tokenList.add(new Token(TokenType.IDENTIFIER, letter, lineNumber));
        }
    }
}