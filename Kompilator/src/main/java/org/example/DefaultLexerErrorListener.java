package org.example;

public class DefaultLexerErrorListener implements LexerErrorListener {

    @Override
    public void lexicalError(String errorMessage, int line) {

        System.err.println(errorMessage + " at line: " + line);
    }
}
