package ru.nsu.yukhnina;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PolynomialTest {

    @Test
    void addWithDifLen() {
        Polynomial p1 = new Polynomial(new float[] {4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new float[] {3, 2, 8});
        assertEquals(p1.addition(p2).printToString(), "7.0 + 5.0x + 14.0x^2 + 7.0x^3");
    }

    @Test
    void addFloatWithEqLen() {
        Polynomial p3 = new Polynomial(new float[] {4, 3.2F, 6, 7});
        Polynomial p4 = new Polynomial(new float[] {3, 2, 8, 10.5F});
        assertEquals(p3.addition(p4).printToString(), "7.0 + 5.2x + 14.0x^2 + 17.5x^3");
    }

    @Test
    void differenceWithEqLen() {
        Polynomial p3 = new Polynomial(new float[] {4, 3.2F, 6, 7});
        Polynomial p4 = new Polynomial(new float[] {3, 2, 8, 10.5F});
        assertEquals(p3.difference(p4).printToString(), "1.0 + 1.2x - 2.0x^2 - 3.5x^3");
    }

    @Test
    void differenceWithDifLen() {
        Polynomial p5 = new Polynomial(new float[] {4, 3.2F, 6, 7});
        Polynomial p6 = new Polynomial(new float[] {3, 2, 8});
        assertEquals(p5.difference(p6).printToString(), "1.0 + 1.2x - 2.0x^2 + 7.0x^3");
    }

    @Test
    void differenceWithDifLen2() {
        Polynomial p5 = new Polynomial(new float[] {4, 3.2F, 6});
        Polynomial p6 = new Polynomial(new float[] {3, 2, 8, 10});
        assertEquals(p5.difference(p6).printToString(), "1.0 + 1.2x - 2.0x^2 - 10.0x^3");
    }

    @Test
    void mult() {
        Polynomial p7 = new Polynomial(new float[] {1, 0, 4, 5, 0, 0, 0, 1});
        Polynomial p8 = new Polynomial(new float[] {0, 3, 1});
        assertEquals(p7.mult(p8).printToString(), "3.0x + 1.0x^2 + 12.0x^3 + 19.0x^4 + 5.0x^5 + 3.0x^8 + 1.0x^9");
    }

    @Test
    void calculationAtPoint1() {
        Polynomial p9 = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        assertEquals(p9.calculationAtPoint(1), 156);
    }

    @Test
    void calculationAtPoint2() {
        Polynomial p10 = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        assertEquals(p10.calculationAtPoint(2), 1424.0);
    }

    @Test
    void differential() {
        Polynomial p11 = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        assertArrayEquals(p11.differential(2).polynomIndexes, new float[] {6, 48, 108, 200, 360, 0, 0});
    }

    @Test
    void compare() {
        Polynomial p12 = new Polynomial(new float[] {1, 0, 4, 5, 0, 0, 0, 1});
        Polynomial p13 = new Polynomial(new float[] {0, 3, 1});
        assertEquals(p12.compare(p13), false);
    }

    @Test
    void compare2() {
        Polynomial p14 = new Polynomial(new float[] {});
        assertEquals(p14.compare(p14), true);
    }
}