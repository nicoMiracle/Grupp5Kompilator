package org.example;

public interface LexerErrorListener {
    void lexicalError(String message, int line);
    // Add more error types and methods as needed
}
