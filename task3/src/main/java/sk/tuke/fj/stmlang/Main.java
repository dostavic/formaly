package sk.tuke.fj.stmlang;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // produce stm.c file that is able to simulate simple state machine

        try {
            BufferedReader input = new BufferedReader(new FileReader("finalData/test.txt"));
            Lexer lexer = new Lexer(input);
            Parser parser = new Parser(lexer);
            PrintWriter output = new PrintWriter("finalData/stm.c");
            Generator generator = new Generator(parser.stateMachine(), output);
            generator.generate();
            output.close();
            input.close();
            System.out.println("ok");
        } catch(StateMachineException e) {
            System.out.println("Error Main");
        }
    }
}