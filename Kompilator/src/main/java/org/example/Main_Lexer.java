package org.example;

import java.util.LinkedList;

public class Main_Lexer {
    public static void main(String[] args) {
        JavaLexer lexer = new JavaLexer();
        String input = "int i = 555; int j = 231";
        LinkedList<Token> tokens = lexer.lex(input);

        // Print the tokens
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}