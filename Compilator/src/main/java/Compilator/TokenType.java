
package Compilator;

public enum TokenType {
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
    ASSIGN,       // Assignment (=)
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
    CONTROL_WHILE,

    // Function Control
    FUNCTION_DECLARATION,
    FUNCTION_RETURN,

    SYSTEM, // End of File
    DOT, OUT, PRINT, INPUT, PRINTLN, NEXTLINE
}

