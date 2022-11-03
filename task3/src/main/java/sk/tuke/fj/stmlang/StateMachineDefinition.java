package sk.tuke.fj.stmlang;

import java.util.*;

public class StateMachineDefinition {
    private final Map<String, Character> events = new HashMap<>();
    private final Map<String, Character> commands = new HashMap<>();
    private final List<String> resetEvents = new ArrayList<>();
    private final Map<String, StateDefinition> states = new LinkedHashMap<>();
    private String initialStateName = null;

    public void addEvent(String name, char value) {
        events.put(name, value);
    }

    public void addCommand(String name, char value) {
        commands.put(name, value);
    }

    public void addState(String name, StateDefinition state) {
        states.put(name, state);
        if (initialStateName == null) {
            initialStateName = name;
        }
    }

    public void addResetEvent(String name) {
        resetEvents.add(name);
    }

    public String getInitialStateName() {
        return initialStateName;
    }

    public Map<String, Character> getEvents() {
        return events;
    }

    public Map<String, Character> getCommands() {
        return commands;
    }

    public List<String> getResetEvents() {
        return resetEvents;
    }

    public Map<String, StateDefinition> getStates() {
        return states;
    }
}
