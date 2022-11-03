package sk.tuke.fj.stmlang;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {

    @Test
    void generate() throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("src/test/all/test1.txt"));
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);
        PrintWriter output = new PrintWriter("src/test/all/stm1.c");
        Generator generator = new Generator(parser.stateMachine(), output);
        generator.generate();
        output.close();
        input.close();
        //assertEquals(output, "src/test/all/stm1R.c");

        byte[] file1Bytes = Files.readAllBytes(Paths.get("src/test/all/stm1.c"));
        byte[] file2Bytes = Files.readAllBytes(Paths.get("src/test/all/stm1R.c"));

        String file1 = new String(file1Bytes, StandardCharsets.UTF_8);
        String file2 = new String(file2Bytes, StandardCharsets.UTF_8);

        assertEquals(file1, file2);
    }
}