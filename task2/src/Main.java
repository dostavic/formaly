import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String args[]){

        try {
            BufferedReader reader = new BufferedReader(new FileReader("E:\\Windows 10\\Desktop\\code\\CLion\\fj\\git\\fj-5626\\zadanie2\\src\\test.txt"));
            Lexer lexer = new Lexer(reader);
            int result = new Parser(lexer).statement();

            System.out.printf("\nResult %d\n", result);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
