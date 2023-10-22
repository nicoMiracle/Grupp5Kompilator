
package org.example;

public enum TokenType {
    // Keywords
    KEYWORD_IF,
    KEYWORD_ELSE,
    KEYWORD_WHILE,
    KEYWORD_FUNCTION,
    KEYWORD_RETURN,

    // Types
    TYPE_INT,
    TYPE_STRING,
    
    // Identifiers and Literals
    IDENTIFIER,
    INTEGER_LITERAL,
    STRING_LITERAL,

    // Operators
    PLUS,         // Addition
    MINUS,        // Subtraction
    MULTIPLY,     // Multiplication
    DIVIDE,       // Division
    ASSIGN,       // Assignment (=)
    CONCATENATE,  // String Concatenation
    EQUAL,        // Equality (==)
    NOT_EQUAL,    // Inequality (!=)
    LESS_THAN,    // Less Than (<)
    LESS_EQUAL,   // Less Than or Equal To (<=)
    GREATER_THAN, // Greater Than (>)
    GREATER_EQUAL,// Greater Than or Equal To (>=)

    // Delimiters and Punctuation
    SEMICOLON,    // Statement terminator
    COMMA,        // Parameter separator
    LPAREN,   // Opening parenthesis
    RPAREN,  // Closing parenthesis
    LEFT_BRACE,   // Opening brace
    RIGHT_BRACE,  // Closing brace

    // Control Flow
    CONTROL_IF,
    CONTROL_ELSE,
    CONTROL_WHILE,

    // Function Control
    FUNCTION_DECLARATION,
    FUNCTION_RETURN,

    // End of File
    EOF
}

