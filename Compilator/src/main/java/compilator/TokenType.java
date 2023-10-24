
package compilator;

public enum TokenType {
    // Keywords
    IF,
    WHILE,
    OUT,
    PRINTLN,
    INPUT,
    NEXT_LINE,
    SYSTEM,

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

    // Delimiters and Punctuation
    LPAREN,   // Opening Left parenthesis
    RPAREN,  // Closing Right parenthesis
    LBRACE,   // Opening Left brace
    RBRACE,  // Closing Right brace


    // Function Control
    FUNCTION_RETURN,

    // End of File
    SEMICOLON,
    COMMA,
    DOT
    }

