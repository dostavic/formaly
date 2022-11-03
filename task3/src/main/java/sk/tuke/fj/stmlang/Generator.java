package sk.tuke.fj.stmlang;

import java.io.IOException;
import java.io.Writer;

public class Generator {
    private final StateMachineDefinition stateMachine;
    private final Writer writer;

    public Generator(StateMachineDefinition stateMachine, Writer writer) {
        this.stateMachine = stateMachine;
        this.writer = writer;
    }

    public void generate() throws IOException {
        StringBuilder stateVoid = new StringBuilder("#include \"common.h\"\n\n");
        Object[] stateState = stateMachine.getStates().keySet().toArray();
        int index = 0;

        for (Object state : stateMachine.getStates().keySet())
            stateVoid.append("void state_" + state.toString() + "();\n");
        stateVoid.append("\n");

        writer.write(stateVoid.toString());

        for (StateDefinition state : stateMachine.getStates().values()) {
            writeState(String.valueOf(stateState[index]), state);
            index++;
        }

        writer.write("void main() {\n\tstate_" + stateState[0].toString() + "();\n}");
    }

    private void writeState(String name, StateDefinition state) throws IOException {
        Object[] stateState = stateMachine.getStates().keySet().toArray();
        int index = 0;

        StringBuilder stateVoid = new StringBuilder("void state_" + name + "() {\n");

        for (String action : state.getActions())
            stateVoid.append("\tsend_command('" + stateMachine.getCommands().get(action) + "');\n");

        stateVoid.append("\tchar ev;\n\twhile(ev = read_event()) {\n\t\tswitch (ev) {\n");

        for (TransitionDefinition transition : state.getTransitions())
            stateVoid.append("\t\tcase '" + stateMachine.getEvents().get(transition.eventName()) + "':\n\t\t\treturn state_" + transition.targetName() + "();\n");

        for (String reset : stateMachine.getResetEvents()) {
            stateVoid.append("\t\tcase '" + stateMachine.getEvents().get(reset) + "':\n\t\t\treturn state_" + stateState[index] +"();\n");
            index++;
        }

        stateVoid.append("\t\t}\n\t}\n}\n\n");

        writer.write(stateVoid.toString());
    }
}
