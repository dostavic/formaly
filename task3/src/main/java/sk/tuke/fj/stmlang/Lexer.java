package sk.tuke.fj.stmlang;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Objects;

import static sk.tuke.fj.stmlang.TokenType.*;

/**
 * Lexical analyzer of the state machine language.
 */
public class Lexer {
    private static final Map<String, TokenType> keywords = Map.of(
            "events", EVENTS,
            "resetEvents", RESET_EVENTS,
            "commands", COMMANDS,
            "state", STATE,
            "actions", ACTIONS
    );

    private final Reader input;
    private int current;

    public Lexer(Reader input) {
        this.input = input;
    }

    public Token nextToken() {
        if(current == 0)
            consume();

        switch(current) {
            case '{':
                consume();
                return new Token(LBRACE);
            case '}':
                consume();
                return new Token(RBRACE);
            case '-':
                match(62);
                consume();
                return new Token(ARROW);
            case ' ':
                consume();
                return new Token(SPACE);
            case '\n':
                //consume();
            case '\r':
                consume();
                return new Token(END);
            case '\'':
                consume();
                if((current >= 'a' && current <= 'z') || (current >= 'A' && current <= 'Z')) {
                    var buff = new Token(CHAR, String.valueOf((char) current));
                    match('\'');
                    consume();
                    return buff;
                }
                else {
                    throw new StateMachineException("Error 'ABC'");
                }
            case -1:
                return new Token(EOF);
            default:
                return readNameOrKeyword();
        }
    }

    private Token readNameOrKeyword() {
        StringBuilder name = new StringBuilder();

        while((current >= 'a' && current <= 'z') || (current >= 'A' && current <= 'Z')){
            name.append((char)current);
            consume();
        }

        if(name.length() <= 0)
            throw new StateMachineException("Invalid syntax");

        switch (name.toString()){
            case "events":
                return new Token(EVENTS, name.toString());
            case "resetEvents":
                return new Token(RESET_EVENTS, name.toString());
            case "commands":
                return new Token(COMMANDS, name.toString());
            case "state":
                return new Token(STATE, name.toString());
            case "actions":
                return new Token(ACTIONS, name.toString());
            default:
                return new Token(NAME, name.toString());
        }
    }

    private void consume() {
        try {
            current = input.read();
        } catch (StateMachineException | IOException e) {
            throw new StateMachineException("Error lexer consume");
        }
    }

    private void match(int expectedChar) {
        consume();
        if(current != expectedChar) {
            throw new StateMachineException("Error lexer match");
        }
    }
}