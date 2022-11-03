package sk.tuke.fj.stmlang;

import javax.swing.*;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static sk.tuke.fj.stmlang.TokenType.*;

/**
 * Parser for the state machine language
 *
 * StateMachine -> { Statement }
 * Statement    -> Events | ResetEvents | Commands | State
 * Events       -> "events" "{" { NAME CHAR } "}"
 * Commands     -> "commands" "{" { NAME CHAR } "}"
 * ResetEvents  -> "resetEvents" "{" { NAME } "}"
 * State        -> "state" "{" [Actions] { Transition } "}"
 * Actions      -> "actions" "{" { NAME } "}"
 * Transition   -> NAME "->" NAME
 */
public class Parser {
    private final Lexer lexer;
    private Token symbol;
    private StateMachineDefinition definition;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public StateMachineDefinition stateMachine() {
        definition = new StateMachineDefinition();
        var first = Set.of(EVENTS, COMMANDS, RESET_EVENTS, STATE);
        consume();
        while (first.contains(symbol.type())) {
            switch (symbol.type()) {
                case EVENTS -> events();
                case COMMANDS -> commands();
                case RESET_EVENTS -> resetEvents();
                case STATE -> state();
            }
        }

        checkToChar();
        checkToRes();
        checkToState();

        return definition;
    }

    // here is an example how to implement events
    private void events() {
        match(EVENTS);
        match(LBRACE);
        while (symbol.type() == NAME) {
            var name = symbol.attribute();
            consume();
            var value = symbol.attribute();
            match(CHAR);
            definition.addEvent(name, value.charAt(0));
        }
        match(RBRACE);
    }

    private void commands() {
        match(COMMANDS);
        match(LBRACE);
        while(symbol.type() == NAME){
            var name = symbol.attribute();
            consume();
            var value = symbol.attribute();
            match(CHAR);
            definition.addCommand(name, value.charAt(0));
        }
        match(RBRACE);
    }

    private void resetEvents() {
        match(RESET_EVENTS);
        match(LBRACE);
        while (symbol.type() == NAME){
            var name = symbol.attribute();
            consume();
            definition.addResetEvent(name);
        }
        match(RBRACE);
    }

    private void state() {
        StateDefinition state = new StateDefinition();
        match(STATE);
        String name = symbol.attribute();
        match(NAME);
        match(LBRACE);

        if(symbol.type() == ACTIONS)
            actions(state);

        while (symbol.type() == NAME)
            state.addTransition(transition());

        definition.addState(name, state);
        match(RBRACE);
    }

    private void actions(StateDefinition state){
        match(ACTIONS);
        match(LBRACE);
        while(symbol.type() == NAME){
            var name = symbol.attribute();
            state.addAction(name);
            consume();
        }
        match(RBRACE);
    }

    private TransitionDefinition transition() {
        String name = symbol.attribute();
        match(NAME);
        match(ARROW);
        String eName = symbol.attribute();
        match(NAME);
        if(name.equals(eName))
            throw new StateMachineException("transition to the same state");
        return new TransitionDefinition(name, eName);
    }

    private void match(TokenType expectedSymbol) {
        if(symbol.type() != expectedSymbol)
            throw new StateMachineException("Error");
        consume();
    }

    private void consume() {
        symbol = lexer.nextToken();
        while (symbol.type() == SPACE || symbol.type() == END)
            consume();
    }

    private void checkToChar(){
        StringBuilder mapAsString = new StringBuilder();
        for(Object buf : definition.getEvents().values())
            mapAsString.append(buf.toString());

        for(Object buff : definition.getCommands().values())
            mapAsString.append(buff.toString());

        //System.out.println(mapAsString.toString());
        String event = new String(mapAsString.toString());

        if(event.chars().distinct().count() != event.length()){
            throw new StateMachineException("2 char");
        }
    }

    private void checkToRes(){
        if(definition.getResetEvents().isEmpty())
            throw new StateMachineException("No reset");
    }

    private void checkToState(){
        if(definition.getStates().size() <= 1)
            throw new StateMachineException("1 state");
    }
}