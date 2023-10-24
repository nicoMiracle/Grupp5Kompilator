package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        JavaLexer lexer = new JavaLexer();
        DefaultLexerErrorListener listener = new DefaultLexerErrorListener();
        lexer.setListener(listener);
        String input = "int isdas = 555;##int j = 231;while(sds==5){}      int sum(int a, int b) {return a+b;}# if(i==5){}; String pavel = \"this is a test string\"}; ";
//        String input2 = "if(a==4){int variable = 45 ; int b = 4; }";
//        String input3 = "if(a==b){int c=a+bbhhf }";
//        String input5 = "int b(){a = 4)}";
//        String input6 = "String getName(){}";
//        String input7 = "x = y.getInt();";
//        String input8 = "System.out.println() ;";
//        String input9 = "int b = 45abs";
        String input10 = "This is an unclosed \"string.";
        String input11 = "x = input.nextLine(); while(i==true){System.out.println(x);} ";
        List<Token> tokens = lexer.lex(input11);

        // Print the tokens
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}