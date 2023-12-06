package ru.nsu.yukhnina;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    OutputStream output = new ByteArrayOutputStream();
    @Test
    void testPlus() {
        System.setOut(new PrintStream(output));
        ByteArrayInputStream myInput = new ByteArrayInputStream("+ 123 12\nstop".getBytes());
        System.setIn(myInput);
        String[] args = null;
        Calculator.main(args);
        assertEquals("135.0\nCalculator is off", output.toString().trim());
    }

    @Test
    void testMinus() {
        System.setOut(new PrintStream(output));
        ByteArrayInputStream myInput = new ByteArrayInputStream("- 123 12\nstop".getBytes());
        System.setIn(myInput);
        String[] args = null;
        Calculator.main(args);
        assertEquals("111.0\nCalculator is off", output.toString().trim());
    }

    @Test
    void testMult() {
        System.setOut(new PrintStream(output));
        ByteArrayInputStream myInput = new ByteArrayInputStream("* 123 12\nstop".getBytes());
        System.setIn(myInput);
        String[] args = null;
        Calculator.main(args);
        assertEquals("1476.0\nCalculator is off", output.toString().trim());
    }

    @Test
    void testDiv() {
        System.setOut(new PrintStream(output));
        ByteArrayInputStream myInput = new ByteArrayInputStream("/ 245 5\nstop".getBytes());
        System.setIn(myInput);
        String[] args = null;
        Calculator.main(args);
        assertEquals("49.0\nCalculator is off", output.toString().trim());
    }

    @Test
    void testLog() {
        System.setOut(new PrintStream(output));
        ByteArrayInputStream myInput = new ByteArrayInputStream("log 1\nstop".getBytes());
        System.setIn(myInput);
        String[] args = null;
        Calculator.main(args);
        assertEquals("0.0\nCalculator is off", output.toString().trim());
    }

    @Test
    void testSin() {
        System.setOut(new PrintStream(output));
        ByteArrayInputStream myInput = new ByteArrayInputStream("sin 0\nstop".getBytes());
        System.setIn(myInput);
        String[] args = null;
        Calculator.main(args);
        assertEquals("0.0\nCalculator is off", output.toString().trim());
    }

    @Test
    void testCos() {
        System.setOut(new PrintStream(output));
        ByteArrayInputStream myInput = new ByteArrayInputStream("cos 0\nstop".getBytes());
        System.setIn(myInput);
        String[] args = null;
        Calculator.main(args);
        assertEquals("1.0\nCalculator is off", output.toString().trim());
    }

    @Test
    void testPow() {
        System.setOut(new PrintStream(output));
        ByteArrayInputStream myInput = new ByteArrayInputStream("pow 11 2\nstop".getBytes());
        System.setIn(myInput);
        String[] args = null;
        Calculator.main(args);
        assertEquals("121.0\nCalculator is off", output.toString().trim());
    }

    @Test
    void testSqrt() {
        System.setOut(new PrintStream(output));
        ByteArrayInputStream myInput = new ByteArrayInputStream("sqrt 121\nstop".getBytes());
        System.setIn(myInput);
        String[] args = null;
        Calculator.main(args);
        assertEquals("11.0\nCalculator is off", output.toString().trim());
    }
}