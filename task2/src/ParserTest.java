import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.testng.AssertJUnit.assertEquals;

class ParserTest {

    @org.junit.jupiter.api.Test
    void statement() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Windows 10\\Desktop\\code\\CLion\\fj\\git\\fj-5626\\zadanie2\\src\\Tests\\test.txt"));
        Lexer lexer = new Lexer(reader);
        int result = new Parser(lexer).statement();
        assertEquals(30, result);
    }

    @org.junit.jupiter.api.Test
    void statement1() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Windows 10\\Desktop\\code\\CLion\\fj\\git\\fj-5626\\zadanie2\\src\\Tests\\test1.txt"));
        Lexer lexer = new Lexer(reader);
        int result = new Parser(lexer).statement();
        assertEquals(6, result);
    }

    @org.junit.jupiter.api.Test
    void statement2() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Windows 10\\Desktop\\code\\CLion\\fj\\git\\fj-5626\\zadanie2\\src\\Tests\\test2.txt"));
        Lexer lexer = new Lexer(reader);
        int result = new Parser(lexer).statement();
        assertEquals(-55, result);
    }

    @org.junit.jupiter.api.Test
    void statement3() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Windows 10\\Desktop\\code\\CLion\\fj\\git\\fj-5626\\zadanie2\\src\\Tests\\test3.txt"));
        Lexer lexer = new Lexer(reader);
        int result = new Parser(lexer).statement();
        assertEquals(-126, result);
    }

    @org.junit.jupiter.api.Test
    void statement4() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Windows 10\\Desktop\\code\\CLion\\fj\\git\\fj-5626\\zadanie2\\src\\Tests\\test4.txt"));
        Lexer lexer = new Lexer(reader);
        int result = new Parser(lexer).statement();
        assertEquals(200, result);
    }

    @org.junit.jupiter.api.Test
    void statement5() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Windows 10\\Desktop\\code\\CLion\\fj\\git\\fj-5626\\zadanie2\\src\\Tests\\test5.txt"));
        Lexer lexer = new Lexer(reader);
        int result = new Parser(lexer).statement();
        assertEquals(167, result);
    }

    @org.junit.jupiter.api.Test
    void statement6() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Windows 10\\Desktop\\code\\CLion\\fj\\git\\fj-5626\\zadanie2\\src\\Tests\\test6.txt"));
        Lexer lexer = new Lexer(reader);
        int result = new Parser(lexer).statement();
        assertEquals(6, result);
    }

    /*@org.junit.jupiter.api.Test
    void statement7() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Windows 10\\Desktop\\code\\CLion\\fj\\git\\fj-5626\\zadanie2\\src\\Tests\\test7.txt"));
        Lexer lexer = new Lexer(reader);
        int result = new Parser(lexer).statement();
        assertEquals(, result);
    }*/

    @org.junit.jupiter.api.Test
    void statement8() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Windows 10\\Desktop\\code\\CLion\\fj\\git\\fj-5626\\zadanie2\\src\\Tests\\test8.txt"));
        Lexer lexer = new Lexer(reader);
        int result = new Parser(lexer).statement();
        assertEquals(30, result);
    }

    @org.junit.jupiter.api.Test
    void statement9() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Windows 10\\Desktop\\code\\CLion\\fj\\git\\fj-5626\\zadanie2\\src\\Tests\\test9.txt"));
        Lexer lexer = new Lexer(reader);
        int result = new Parser(lexer).statement();
        assertEquals(20, result);
    }

    @org.junit.jupiter.api.Test
    void statement10() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Windows 10\\Desktop\\code\\CLion\\fj\\git\\fj-5626\\zadanie2\\src\\Tests\\test10.txt"));
        Lexer lexer = new Lexer(reader);
        int result = new Parser(lexer).statement();
        assertEquals(-4, result);
    }

    @org.junit.jupiter.api.Test
    void statement11() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Windows 10\\Desktop\\code\\CLion\\fj\\git\\fj-5626\\zadanie2\\src\\Tests\\test11.txt"));
        Lexer lexer = new Lexer(reader);
        int result = new Parser(lexer).statement();
        assertEquals(-4, result);
    }

    @org.junit.jupiter.api.Test
    void statement12() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Windows 10\\Desktop\\code\\CLion\\fj\\git\\fj-5626\\zadanie2\\src\\Tests\\test12.txt"));
        Lexer lexer = new Lexer(reader);
        int result = new Parser(lexer).statement();
        assertEquals(6, result);
    }

    @org.junit.jupiter.api.Test
    void statement13() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Windows 10\\Desktop\\code\\CLion\\fj\\git\\fj-5626\\zadanie2\\src\\Tests\\test13.txt"));
        Lexer lexer = new Lexer(reader);
        int result = new Parser(lexer).statement();
        assertEquals(0, result);
    }

    @org.junit.jupiter.api.Test
    void statement14() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Windows 10\\Desktop\\code\\CLion\\fj\\git\\fj-5626\\zadanie2\\src\\Tests\\test14.txt"));
        Lexer lexer = new Lexer(reader);
        int result = new Parser(lexer).statement();
        assertEquals(-6, result);
    }

    @org.junit.jupiter.api.Test
    void statement15() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Windows 10\\Desktop\\code\\CLion\\fj\\git\\fj-5626\\zadanie2\\src\\Tests\\test15.txt"));
        Lexer lexer = new Lexer(reader);
        int result = new Parser(lexer).statement();
        assertEquals(5, result);
    }

    @org.junit.jupiter.api.Test
    void statement16() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Windows 10\\Desktop\\code\\CLion\\fj\\git\\fj-5626\\zadanie2\\src\\Tests\\test16.txt"));
        Lexer lexer = new Lexer(reader);
        int result = new Parser(lexer).statement();
        assertEquals(55555555, result);
    }

    @org.junit.jupiter.api.Test
    void statement17() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Windows 10\\Desktop\\code\\CLion\\fj\\git\\fj-5626\\zadanie2\\src\\Tests\\test17.txt"));
        Lexer lexer = new Lexer(reader);
        int result = new Parser(lexer).statement();
        assertEquals(-5, result);
    }
}