package org.example;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        JavaLexer lexer = new JavaLexer();
        String input = "int sum(int a, int b) {return a+b;#}";
        LinkedList<Token> tokens = lexer.lex(input);

        // Print the tokens
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}