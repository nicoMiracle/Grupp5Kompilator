package org.example;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaLexerTest {
    private JavaLexer lexer;
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() {
        lexer = new JavaLexer();
        System.setErr(new PrintStream(errContent));
    }
    @AfterEach
    public void restoreStreams() {
        System.setErr(originalErr);
    }

    @Test
    public void testGetTextBetweenQuotation() throws Exception {
        Method method = JavaLexer.class.getDeclaredMethod("getTextBetweenQuotation", String.class, int.class);
        method.setAccessible(true);

        String input1 = "This is a \"test\".";
        String expected1 = "test";
        assertEquals(expected1, method.invoke(lexer, input1, 10));

        String input2 = "This is an empty \"\" string.";
        String expected2 = "";
        assertEquals(expected2, method.invoke(lexer, input2, 15));

        String input3 = "This is an unclosed \"string.";
        String expected3 = "string.";
        assertEquals(expected3, method.invoke(lexer, input3, 20));

        String input4 = "This is a \"test with 'nested' quotes\".";
        String expected4 = "test with 'nested' quotes";
        assertEquals(expected4, method.invoke(lexer, input4, 10));

        String input5 = "quote at the end \"";
        String expected5 = "";
        assertEquals(expected5, method.invoke(lexer, input5, 16));
    }
    @Test
    public void testUnrecognizedCharacter() {
        DefaultLexerErrorListener listener = new DefaultLexerErrorListener();
        lexer.setListener(listener);
        String input = "x = y#z";
        int lineNumber = 1;
        String expectedErrorMessage = "Unrecognized character: '#' at line: " + lineNumber +'\n';
        lexer.lex(input);
        assertEquals(expectedErrorMessage, errContent.toString());
    }


    @Test
    public void testEmptyStringAssignment() {
        JavaLexer lexer = new JavaLexer();
        String input = "String str = \"\";";

        Token[] expectedTokens = {
                new Token(TokenType.TYPE_STRING, "String", 1),
                new Token(TokenType.IDENTIFIER, "str", 1),
                new Token(TokenType.ASSIGN, "=", 1),
                new Token(TokenType.STRING_LITERAL, "", 1),
                new Token(TokenType.EOF, ";", 1)
        };
        List<Token> actualTokens = lexer.lex(input);

        for (int i = 0; i < expectedTokens.length; i++) {
            assertEquals(expectedTokens[i], actualTokens.get(i));
        }
    }

    @Test
    public void testSystemOutPrintlnEmpty() {
        JavaLexer lexer = new JavaLexer();
        String input = "System.out.println();";

        Token[] expectedTokens = {
                new Token(TokenType.IDENTIFIER, "System", 1),
                new Token(TokenType.DOT, ".", 1),
                new Token(TokenType.IDENTIFIER, "out", 1),
                new Token(TokenType.DOT, ".", 1),
                new Token(TokenType.IDENTIFIER, "println", 1),
                new Token(TokenType.LPAREN, "(", 1),
                new Token(TokenType.RPAREN, ")", 1),
                new Token(TokenType.EOF, ";", 1)
        };

        List<Token> actualTokens = lexer.lex(input);

        for (int i = 0; i < expectedTokens.length; i++) {
            assertEquals(expectedTokens[i], actualTokens.get(i));
        }
    }

    @Test
    public void testSystemOutPrintlnWithExpression() {
        JavaLexer lexer = new JavaLexer();
        String input = "System.out.println(\"Hello, World!\");";

        List<Token> actualTokens = lexer.lex(input);

        List<Token> expectedTokens = Arrays.asList(
                new Token(TokenType.IDENTIFIER, "System", 1),
                new Token(TokenType.DOT, ".", 1),
                new Token(TokenType.IDENTIFIER, "out", 1),
                new Token(TokenType.DOT, ".", 1),
                new Token(TokenType.IDENTIFIER, "println", 1),
                new Token(TokenType.LPAREN, "(", 1),
                new Token(TokenType.STRING_LITERAL, "Hello, World!", 1),
                new Token(TokenType.RPAREN, ")", 1),
                new Token(TokenType.EOF, ";", 1)
        );

        assertThat(actualTokens, IsIterableContainingInOrder.contains(expectedTokens.toArray()));
    }

    @Test
    public void testVariableDeclarationWithInitialization() {
        String input = "int x = 42;";
        List<Token> tokens = lexer.lex(input);
        List<Token> expectedTokens = Arrays.asList(
                new Token(TokenType.TYPE_INT, "int",1),
                new Token(TokenType.IDENTIFIER, "x",1),
                new Token(TokenType.ASSIGN, "=",1),
                new Token(TokenType.INTEGER_LITERAL, "42",1),
                new Token(TokenType.EOF, ";",1)
        );
        assertThat(tokens, IsIterableContainingInOrder.contains(expectedTokens.toArray()));
    }
    @Test
    public void testSpaces() {
        JavaLexer lexer = new JavaLexer();
        String input = "int      x  =   42   ; ";

        List<Token> expectedTokens = Arrays.asList(
                new Token(TokenType.TYPE_INT, "int",1),
                new Token(TokenType.IDENTIFIER, "x",1),
                new Token(TokenType.ASSIGN, "=",1),
                new Token(TokenType.INTEGER_LITERAL, "42",1),
                new Token(TokenType.EOF, ";",1)
        );

        List<Token> actualTokens = lexer.lex(input);

        assertThat(actualTokens, IsIterableContainingInOrder.contains(expectedTokens.toArray()));
    }
    @Test
    public void testIfStatementWithVariableDeclarationAndAddition() {
        String input = "if(a==b){int c=a+b;}";
        List<Token> tokens = lexer.lex(input);
        List<Token> expectedTokens = Arrays.asList(
                new Token(TokenType.IF, "if",1),
                new Token(TokenType.LPAREN, "(",1),
                new Token(TokenType.IDENTIFIER, "a",1),
                new Token(TokenType.EQUAL, "==",1),
                new Token(TokenType.IDENTIFIER, "b",1),
                new Token(TokenType.RPAREN, ")",1),
                new Token(TokenType.LBRACE, "{",1),
                new Token(TokenType.TYPE_INT, "int",1),
                new Token(TokenType.IDENTIFIER, "c",1),
                new Token(TokenType.ASSIGN, "=",1),
                new Token(TokenType.IDENTIFIER, "a",1),
                new Token(TokenType.PLUS, "+",1),
                new Token(TokenType.IDENTIFIER, "b",1),
                new Token(TokenType.EOF, ";",1),
                new Token(TokenType.RBRACE, "}",2)
        );
        assertThat(tokens, IsIterableContainingInOrder.contains(expectedTokens.toArray()));
    }
    @Test
    public void testFunctionToken() {
        JavaLexer lexer = new JavaLexer();
        String input = "int add(int a, int b) { return a - b; }";

        List<Token> expectedTokens = Arrays.asList(
                new Token(TokenType.TYPE_INT, "int", 1),
                new Token(TokenType.IDENTIFIER, "add", 1),
                new Token(TokenType.LPAREN, "(", 1),
                new Token(TokenType.TYPE_INT, "int", 1),
                new Token(TokenType.IDENTIFIER, "a", 1),
                new Token(TokenType.COMMA, ",", 1),
                new Token(TokenType.TYPE_INT, "int", 1),
                new Token(TokenType.IDENTIFIER, "b", 1),
                new Token(TokenType.RPAREN, ")", 1),
                new Token(TokenType.LBRACE, "{", 1),
                new Token(TokenType.FUNCTION_RETURN, "return", 1),
                new Token(TokenType.IDENTIFIER, "a", 1),
                new Token(TokenType.MINUS, "-", 1),
                new Token(TokenType.IDENTIFIER, "b", 1),
                new Token(TokenType.EOF, ";", 1),
                new Token(TokenType.RBRACE, "}", 2)
        );

        List<Token> actualTokens = lexer.lex(input);

        assertThat(actualTokens, IsIterableContainingInOrder.contains(expectedTokens.toArray()));
    }
    @Test
    public void testLexValidInput() {
        JavaLexer lexer = new JavaLexer();
        String input = "x = y.getInt();";

        List<Token> expectedTokens = Arrays.asList(
                new Token(TokenType.IDENTIFIER, "x", 1),
                new Token(TokenType.ASSIGN, "=", 1),
                new Token(TokenType.IDENTIFIER, "y", 1),
                new Token(TokenType.DOT, ".", 1),
                new Token(TokenType.IDENTIFIER, "getInt", 1),
                new Token(TokenType.LPAREN, "(", 1),
                new Token(TokenType.RPAREN, ")", 1),
                new Token(TokenType.EOF, ";", 1)
        );

        List<Token> actualTokens = lexer.lex(input);

        assertEquals(expectedTokens, actualTokens);
    }
    @Test
    public void testWhileLoopWithAssignment() {
        JavaLexer lexer = new JavaLexer();
        String input = "int i = 0; while(i==0){a=a+5; i=i+1;}";

        Token[] expectedTokens = {
                new Token(TokenType.TYPE_INT, "int", 1),
                new Token(TokenType.IDENTIFIER, "i", 1),
                new Token(TokenType.ASSIGN, "=", 1),
                new Token(TokenType.INTEGER_LITERAL, "0", 1),
                new Token(TokenType.EOF, ";", 1),
                new Token(TokenType.WHILE, "while", 2),
                new Token(TokenType.LPAREN, "(", 2),
                new Token(TokenType.IDENTIFIER, "i", 2),
                new Token(TokenType.EQUAL, "==", 2),
                new Token(TokenType.INTEGER_LITERAL, "0", 2),
                new Token(TokenType.RPAREN, ")", 2),
                new Token(TokenType.LBRACE, "{", 2),
                new Token(TokenType.IDENTIFIER, "a", 2),
                new Token(TokenType.ASSIGN, "=", 2),
                new Token(TokenType.IDENTIFIER, "a", 2),
                new Token(TokenType.PLUS, "+", 2),
                new Token(TokenType.INTEGER_LITERAL, "5", 2),
                new Token(TokenType.EOF, ";", 2),
                new Token(TokenType.IDENTIFIER, "i", 3),
                new Token(TokenType.ASSIGN, "=", 3),
                new Token(TokenType.IDENTIFIER, "i", 3),
                new Token(TokenType.PLUS, "+", 3),
                new Token(TokenType.INTEGER_LITERAL, "1", 3),
                new Token(TokenType.EOF, ";", 3),
                new Token(TokenType.RBRACE, "}", 4)
        };

        List<Token> actualTokens = lexer.lex(input);

        for (int i = 0; i < expectedTokens.length; i++) {
            assertEquals(expectedTokens[i], actualTokens.get(i));
        }
    }
}
