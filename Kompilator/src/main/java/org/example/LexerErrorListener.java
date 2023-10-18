package org.example;

public interface LexerErrorListener {
    void syntaxError(String message, int line);
    void lexicalError(String message, int line);
    // Add more error types and methods as needed
}
