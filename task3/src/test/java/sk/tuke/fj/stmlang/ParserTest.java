package sk.tuke.fj.stmlang;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void stateMachineError() {
        Reader input = new BufferedReader(new StringReader("events {\n" +
                "    doorClosed   'd'\n" +
                "    drawerOpened 'w'\n" +
                "    lightOn      'l'\n" +
                "    doorOpened   'o'\n" +
                "    panelClosed  'p'\n"));
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);



        Exception exc = assertThrows(StateMachineException.class, () ->
        {
            parser.stateMachine();
        });

        assertEquals("Error", exc.getMessage());
    }

    @Test
    void stateMachine1Error() {
        Reader input = new BufferedReader(new StringReader("events {\n" +
                "    doorClosed   'd'\n" +
                "    drawerOpened 'w'\n" +
                "    lightOn      'l'\n" +
                "    doorOpened   'o'\n" +
                "    panelClosed  'p'\n"));
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);



        Exception exc = assertThrows(StateMachineException.class, () ->
        {
            parser.stateMachine();
        });

        assertEquals("Error", exc.getMessage());
    }

    @Test
    void stateMachine2Error() {
        Reader input = new BufferedReader(new StringReader("events {\n" +
                "    doorClosed   'd'\n" +
                "    drawerOpened 'd'\n" +
                "    lightOn      'l'\n" +
                "    doorOpened   'o'\n" +
                "    panelClosed  'p'\n" +
                "}"));
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        Exception exc = assertThrows(StateMachineException.class, () ->
        {
            parser.stateMachine();
        });

        assertEquals("2 char", exc.getMessage());
    }

    @Test
    void stateMachine3Error() {
        Reader input = new BufferedReader(new StringReader("commands {\n" +
                "    unlockPanel 'U'\n" +
                "    lockPanel   'U'\n" +
                "    lockDoor    'C'\n" +
                "    unlockDoor  'D'\n" +
                "}"));
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        Exception exc = assertThrows(StateMachineException.class, () ->
        {
            parser.stateMachine();
        });

        assertEquals("2 char", exc.getMessage());
    }

    @Test
    void stateMachine4Error() {
        Reader input = new BufferedReader(new StringReader("events {\n" +
                "    doorClosed   'd'\n" +
                "    drawerOpened 'w'\n" +
                "    lightOn      'l'\n" +
                "    doorOpened   'o'\n" +
                "    panelClosed  'p'\n" +
                "}"));
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        Exception exc = assertThrows(StateMachineException.class, () ->
        {
            parser.stateMachine();
        });

        assertEquals("No reset", exc.getMessage());
    }

    @Test
    void stateMachine5Error() {
        Reader input = new BufferedReader(new StringReader("events {\n" +
                "    doorClosed   'd'\n" +
                "    drawerOpened 'w'\n" +
                "    lightOn      'l'\n" +
                "    doorOpened   'o'\n" +
                "    panelClosed  'p'\n" +
                "}\n" +
                "\n" +
                "resetEvents {\n" +
                "    doorOpened\n" +
                "}\n" +
                "\n" +
                "commands {\n" +
                "    unlockPanel 'U'\n" +
                "    lockPanel   'L'\n" +
                "    lockDoor    'C'\n" +
                "    unlockDoor  'D'\n" +
                "}\n" +
                "\n" +
                "state idle {\n" +
                "    actions {unlockDoor lockPanel}\n" +
                "    doorClosed -> doorClosed\n" +
                "}\n" +
                "\n" +
                "state active {\n" +
                "    drawerOpened -> waitingForLight\n" +
                "    lightOn -> waitingForDrawer\n" +
                "}"));
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        Exception exc = assertThrows(StateMachineException.class, () ->
        {
            parser.stateMachine();
        });

        assertEquals("transition to the same state", exc.getMessage());
    }
}