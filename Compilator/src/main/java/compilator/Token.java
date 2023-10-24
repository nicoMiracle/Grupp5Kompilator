package compilator;

public record Token(TokenType type, String lexem, int line){}
