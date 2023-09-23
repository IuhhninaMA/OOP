package ru.nsu.yukhnina;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PolynomialTest {

    @Test
    void addWithDifLen() {
        Polynomial p1 = new Polynomial(new float[] {4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new float[] {3, 2, 8});
        assertEquals("7.0x^3 + 14.0x^2 + 5.0x + 7.0", p1.addition(p2).toString());
    }

    @Test
    void addFloatWithEqLen() {
        Polynomial p3 = new Polynomial(new float[] {4, 3.2F, 6, 7});
        Polynomial p4 = new Polynomial(new float[] {3, 2, 8, 10.5F});
        assertEquals("17.5x^3 + 14.0x^2 + 5.2x + 7.0", p3.addition(p4).toString());
    }

    @Test
    void differenceWithEqLen() {
        Polynomial p5 = new Polynomial(new float[] {4, 3.2F, 6, 7});
        Polynomial p6 = new Polynomial(new float[] {3, 2, 8, 10.5F});
        assertEquals(" - 3.5x^3 - 2.0x^2 + 1.2x + 1.0", p5.difference(p6).toString());
    }

    @Test
    void differenceWithDifLen() {
        Polynomial p7 = new Polynomial(new float[] {4, 3.2F, 6, 7});
        Polynomial p8 = new Polynomial(new float[] {3, 2, 8});
        assertEquals("7.0x^3 - 2.0x^2 + 1.2x + 1.0", p7.difference(p8).toString());
    }

    @Test
    void differenceWithYourself() {
        Polynomial p9 = new Polynomial(new float[] {4, 3.2F, 6, 7});
        assertEquals("0.0", p9.difference(p9).toString());
    }

    @Test
    void differenceWithDifLen2() {
        Polynomial p10 = new Polynomial(new float[] {4, 3.2F, 6});
        Polynomial p11 = new Polynomial(new float[] {3, 2, 8, 10});
        assertEquals(" - 10.0x^3 - 2.0x^2 + 1.2x + 1.0", p10.difference(p11).toString());
    }

    @Test
    void differenceWithEmptySecond() {
        Polynomial p12 = new Polynomial(new float[] {4, 3.2F, 6, 7});
        Polynomial p13 = new Polynomial(new float[] {});
        assertEquals("7.0x^3 + 6.0x^2 + 3.2x + 4.0", p12.difference(p13).toString());
    }

    @Test
    void differenceWithEmptyFirst() {
        Polynomial p14 = new Polynomial(new float[] {});
        Polynomial p15 = new Polynomial(new float[] {4, 3.2F, 6, 7});
        assertEquals(" - 7.0x^3 - 6.0x^2 - 3.2x - 4.0", p14.difference(p15).toString());
    }

    @Test
    void mult() {
        Polynomial p16 = new Polynomial(new float[] {1, 0, 4, 5, 0, 0, 0, 1});
        Polynomial p17 = new Polynomial(new float[] {0, 3, 1});
        assertEquals("1.0x^9 + 3.0x^8 + 5.0x^5 + 19.0x^4 + 12.0x^3 + 1.0x^2 + 3.0x", p16.mult(p17).toString());
    }

    @Test
    void calculationAtPoint1() {
        Polynomial p18 = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        assertEquals(p18.calculationAtPoint(1), 156);
    }

    @Test
    void calculationAtPoint2() {
        Polynomial p19 = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        assertEquals(p19.calculationAtPoint(2), 1424.0);
    }

    @Test
    void differential() {
        Polynomial p20 = new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12});
        assertArrayEquals(p20.differential(2).polynomIndexes, new float[] {6, 48, 108, 200, 360, 0, 0});
        //проверка что изначальное не меняется
        assertEquals((new Polynomial(new float[] {112, 2, 3, 8, 9, 10, 12})).toString(), p20.toString());
    }

    @Test
    void testEqualsFalse() {
        Polynomial p21 = new Polynomial(new float[] {1, 0, 4, 5, 0, 0, 0, 1});
        Polynomial p22 = new Polynomial(new float[] {0, 3, 1});
        assertEquals(p21.equals(p22), false);
    }
    @Test
    void testEqualsEmpty() {
        Polynomial p23 = new Polynomial(new float[] {});
        assertEquals(p23.equals(new Polynomial(new float[] {})), true);
    }

    @Test
    void testEqualsTrue() {
        Polynomial p24 = new Polynomial(new float[] {1, 2, 3, 4});
        Polynomial p25 = new Polynomial(new float[] {1, 2, 3, 4});
        assertEquals(p24.equals(p25), true);
    }

    @Test
    void  testsFromTask() {
        Polynomial p26 = new Polynomial(new float[] {4, 3, 6, 7});
        Polynomial p27 = new Polynomial(new float[] {3, 2, 8});
        assertEquals("7.0x^3 + 6.0x^2 + 19.0x + 6.0", p26.addition(p27.differential(1)).toString());
        assertEquals(3510, p26.mult(p27).calculationAtPoint(2));
    }
}