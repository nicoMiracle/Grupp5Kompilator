package org.example;

public class DefaultLexerErrorListener implements LexerErrorListener {
    @Override
    public void syntaxError(String message, int line) {
        System.err.println("Syntax error at line " + line);
    }

    @Override
    public void lexicalError(String message, int line) {
        System.err.println("Lexical error at line " + line);
    }
}
