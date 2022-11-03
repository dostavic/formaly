package sk.tuke.fj.stmlang;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class LexerTest {

    @Test
    void nextToken() {
        Reader input = new BufferedReader(new StringReader("-> { } events 'a' commands name -+"));
        Lexer lexer = new Lexer(input);

        assertEquals(new Token(TokenType.ARROW), lexer.nextToken(), "ARROW");
        assertEquals(new Token(TokenType.SPACE), lexer.nextToken(), "SPACE");
        assertEquals(new Token(TokenType.LBRACE), lexer.nextToken(), "LBRACE");
        assertEquals(new Token(TokenType.SPACE), lexer.nextToken(), "SPACE");
        assertEquals(new Token(TokenType.RBRACE), lexer.nextToken(), "RBRACE");
        assertEquals(new Token(TokenType.SPACE), lexer.nextToken(), "SPACE");
        assertEquals(new Token(TokenType.EVENTS, "events"), lexer.nextToken(),  "EVENTS");
        assertEquals(new Token(TokenType.SPACE), lexer.nextToken(), "SPACE");
        assertEquals(new Token(TokenType.CHAR, "a"), lexer.nextToken(), "CHAR");
        assertEquals(new Token(TokenType.SPACE), lexer.nextToken(), "SPACE");
        assertEquals(new Token(TokenType.COMMANDS, "commands"), lexer.nextToken(), "COMMANDS");
        assertEquals(new Token(TokenType.SPACE), lexer.nextToken(), "SPACE");
        assertEquals(new Token(TokenType.NAME, "name"), lexer.nextToken(), "NAME");
        assertEquals(new Token(TokenType.SPACE), lexer.nextToken(), "SPACE");

        Exception exc = assertThrows(StateMachineException.class, () ->
        {
            lexer.nextToken();
        });

        var test = "Error lexer match";
        assertEquals(test, exc.getMessage());
    }

    @Test
    void nextToken1() {
        Reader input = new BufferedReader(new StringReader("-> +"));
        Lexer lexer = new Lexer(input);

        assertEquals(new Token(TokenType.ARROW), lexer.nextToken(), "ARROW");
        assertEquals(new Token(TokenType.SPACE), lexer.nextToken(), "SPACE");

        Exception exc = assertThrows(StateMachineException.class, () ->
        {
            lexer.nextToken();
        });

        var test = "Invalid syntax";
        assertEquals(test, exc.getMessage());
    }

    @Test
    void nextToken2() {
        Reader input = new BufferedReader(new StringReader(" ''a'"));
        Lexer lexer = new Lexer(input);

        assertEquals(new Token(TokenType.SPACE), lexer.nextToken(), "SPACE");

        Exception exc = assertThrows(StateMachineException.class, () ->
        {
            lexer.nextToken();
        });

        var test = "Error 'ABC'";
        assertEquals(test, exc.getMessage());
    }
}