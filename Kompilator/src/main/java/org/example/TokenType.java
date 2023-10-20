
package org.example;

public enum TokenType {
    // Keywords
    IF,
    ELSE,
    WHILE,
    FUNCTION,

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
    LPAREN,   // Opening Left parenthesis
    RPAREN,  // Closing Right parenthesis
    LBRACE,   // Opening Left brace
    RBRACE,  // Closing Right brace


    // Function Control
    FUNCTION_DECLARATION,
    FUNCTION_RETURN,

    // End of File
    EOF,
    COMMA,
    DOT
    }

