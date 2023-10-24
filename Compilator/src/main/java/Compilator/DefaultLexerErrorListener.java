package Compilator;

public class DefaultLexerErrorListener implements LexerErrorListener {

    @Override
    public void lexicalError(String errorMessage, int line) {

        System.err.print(errorMessage + " at line: " + line +'\n');
    }
}
