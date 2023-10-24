package compilator;

public class MyErrorListener implements ErrorListener {
    @Override
    public void syntaxError(String message, int line, int column) {
        System.err.println("Syntax error at line " + line + ", column " + column + ": " + message);
    }

    @Override
    public void lexicalError(String message, int line, int column) {
        System.err.println("Lexical error at line " + line + ", column " + column + ": " + message);
    }
}

