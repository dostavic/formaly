package sk.tuke.fj.stmlang;

public record Token(TokenType type, String attribute) {
    public Token(TokenType type) {
        this(type, null);
    }
}
