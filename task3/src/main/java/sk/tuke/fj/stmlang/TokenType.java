package sk.tuke.fj.stmlang;

public enum TokenType {
    // Keywords
    EVENTS, RESET_EVENTS, COMMANDS, STATE, ACTIONS,
    // Symbols
    LBRACE, RBRACE, ARROW,
    // With attributes
    NAME, CHAR,
    // Other
    EOF, SPACE, END
}
