public class Parser {

    private Token symbol;

    private Lexer lexer;


    public Parser(Lexer lexer){
        this.lexer = lexer;
    }

    public int statement(){
        //consume();
        //if(symbol == null)
        //    throw new RuntimeException();
        //else
            return expr();

        /*if(symbol == Token.EOF){
            try {
                return
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }

    private int expr(){
        int value = mul();

        while (symbol == Token.NUMBER || symbol == Token.PLUS || symbol == Token.MINUS){
            if(symbol == Token.NUMBER)
                consume();

            //match(symbol);
            switch (symbol){
                case PLUS:
                    //consume();
                    value += mul();
                    break;
                case MINUS:
                    //consume();
                    value -= mul();
                    break;
                default:
                    return value;
            }
        }
        return value;
    }

    private int mul(){
        int value = term();

        while (symbol == Token.NUMBER || symbol == Token.MUL || symbol == Token.DIV){
            if(symbol == Token.NUMBER)
                consume();
            //match(symbol);

            switch (symbol){
                case MUL:
                    value *= term();
                    break;
                case DIV:
                    value /= term();
                    break;
                //case RPAR:
                //    throw new RuntimeException();
                default:
                    return value;
            }
        }
        return value;
    }

    private int term(){
        consume();

        switch (symbol){
            case NUMBER:
                return lexer.getValue();
            case LPAR:
                int value = expr();
                if(symbol != Token.RPAR)
                    throw new RuntimeException();
                consume();
                return value;
            case MINUS:
                consume();
                return lexer.getValue() * -1;
            default:
                throw new RuntimeException();
        }
        //return 0;
    }

    private void consume() {
        Token buff = lexer.nextToken();
        if(buff == symbol && buff != Token.NUMBER && buff != Token.LPAR && buff != Token.EOF)
            throw new RuntimeException();
        symbol = buff;

    }

    /*private void match(Token extendedSymbol){
        Token buff = lexer.nextToken();
        if(buff == extendedSymbol && buff != Token.NUMBER)
            System.out.println("Error");
    }*/
}
