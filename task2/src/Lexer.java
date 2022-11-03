import java.io.BufferedReader;
import java.io.Reader;

public class Lexer {

    private int current;
    private int value;
    private Reader input;

    public Lexer(BufferedReader input){
        this.input = input;
    }

    public Token nextToken(){
        if(current == 0) {
            consume();
            if(current == -1)
                return Token.EOF;
        }
        while (current == 32)
            consume();

        if(current >= 48 && current <= 58){
            value = current - 48;
            consume();

            while (current >= 48 && current <= 58) {
                value *= 10;
                value += current - 48;
                consume();
            }
            return Token.NUMBER;
        }

        switch (current) {
            case '+':
                consume();
                return Token.PLUS;
            case '-':
                consume();
                return Token.MINUS;
            case '*':
                consume();
                return Token.MUL;
            case '/':
                consume();
                return Token.DIV;
            case '(':
                consume();
                return Token.LPAR;
            case ')':
                consume();
                return Token.RPAR;
            case -1:
                return Token.EOF;
            default:
        }
        return Token.EOF;
    }

    private void consume(){
        try{
            current = input.read();
        } catch (Exception e){
            System.out.println("EOEE");
        }
    }

    public int getValue(){
        return value;
    }
}
