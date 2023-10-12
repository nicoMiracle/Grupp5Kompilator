package org.example;
import java.util.LinkedList;
public class TokenStream {
    private LinkedList<Token> tokens;
    public TokenStream() {
        tokens = new LinkedList<>();
    }
    public void addToken(Token token){
        tokens.add(token);
    }
    public Token getNextToken(){
        return tokens.poll();
    }
    public Token hasNextToken(){
        return tokens.peek();
    }
    public boolean hasMoreTokens(){
        return !tokens.isEmpty();
    }
}
