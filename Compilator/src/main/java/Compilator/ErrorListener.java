package Compilator;

public interface ErrorListener {
    void syntaxError(String message, int line, int column);
    void lexicalError(String message, int line, int column);
    // Add more error types and methods as needed
}
