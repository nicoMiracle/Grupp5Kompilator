package org.example;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        JavaLexer lexer = new JavaLexer();
        DefaultLexerErrorListener listener = new DefaultLexerErrorListener();
        lexer.setListener(listener);
        String input = "int isdas = 555;##int j = 231;while(sds==5){}int sum(int a, int b) {return a+b;}# if(i==5){}; String pavel = \"this is a test string\" ;  ";
        LinkedList<Token> tokens = lexer.lex(input);

        // Print the tokens
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}