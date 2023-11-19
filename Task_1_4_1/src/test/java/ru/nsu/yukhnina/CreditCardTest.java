package ru.nsu.yukhnina;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CreditCardTest {
    @Test
     void testSetMarksAndAvg() {
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(1, "A", CreditCard.Mark.GOOD);
        testAdd.setMark(2, "A", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(3, "B", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(4, "B", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(5, "C", CreditCard.Mark.EXCELLENT);
        assertEquals(4.8, testAdd.getAvgMark());
    }

    @Test
    void testRedDiplomWithoutCvalificationMark() {
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(1, "A", CreditCard.Mark.GOOD);
        testAdd.setMark(2, "A", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(3, "B", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(4, "B", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(5, "C", CreditCard.Mark.EXCELLENT);
        assertEquals(true, testAdd.redDiplom());
    }

    @Test
    void testRedDiplomWithExcCvalificationMark() {
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(1, "A", CreditCard.Mark.GOOD);
        testAdd.setMark(2, "A", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(3, "B", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(4, "B", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(5, "C", CreditCard.Mark.EXCELLENT);
        testAdd.setCvalificationMark(CreditCard.Mark.EXCELLENT);
        assertEquals(true, testAdd.redDiplom());
    }

    @Test
    void testRedDiplomWithGoodCvalificationMark() {
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(1, "A", CreditCard.Mark.GOOD);
        testAdd.setMark(2, "A", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(3, "B", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(4, "B", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(5, "C", CreditCard.Mark.EXCELLENT);
        testAdd.setCvalificationMark(CreditCard.Mark.GOOD);
        assertEquals(false, testAdd.redDiplom());
    }

    @Test
    void testRedDiplomWithBadMarks() {
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(1, "A", CreditCard.Mark.SATISFACTORY);
        testAdd.setMark(2, "A", CreditCard.Mark.UNSATISFACTORY);
        testAdd.setMark(3, "B", CreditCard.Mark.SATISFACTORY);
        testAdd.setMark(4, "GYM", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(5, "Art", CreditCard.Mark.EXCELLENT);
        testAdd.setCvalificationMark(CreditCard.Mark.GOOD);
        assertEquals(false, testAdd.redDiplom());
    }

    @Test
    void testRedDiplomWithBadMarks2() {
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(2, "A", CreditCard.Mark.UNSATISFACTORY);
        testAdd.setMark(3, "B", CreditCard.Mark.SATISFACTORY);
        testAdd.setMark(4, "GYM", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(5, "Art", CreditCard.Mark.EXCELLENT);
        testAdd.setCvalificationMark(CreditCard.Mark.GOOD);
        assertEquals(false, testAdd.redDiplom());
    }

    @Test
    void testRedDiplomWithBadMarks3() {
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(2, "A", CreditCard.Mark.UNSATISFACTORY);
        testAdd.setMark(4, "A", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(3, "B", CreditCard.Mark.SATISFACTORY);
        testAdd.setMark(3, "B", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(4, "GYM", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(5, "Art", CreditCard.Mark.EXCELLENT);
        testAdd.setCvalificationMark(CreditCard.Mark.GOOD);
        assertEquals(false, testAdd.redDiplom());
    }

    @Test
    void testAddDataInBadSequince() {
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(6, "A", CreditCard.Mark.UNSATISFACTORY);
        testAdd.setMark(4, "GYM", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(2, "A", CreditCard.Mark.UNSATISFACTORY);
        testAdd.setMark(5, "Art", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(3, "B", CreditCard.Mark.SATISFACTORY);
        testAdd.setCvalificationMark(CreditCard.Mark.GOOD);
        assertEquals(false, testAdd.redDiplom());
    }

    @Test
    void testGetStipa() {
        //стипа на текущий семестр расчитывается на основе данных о прошлом
        CreditCard testAdd = new CreditCard();
        testAdd.setMark(6, "A", CreditCard.Mark.UNSATISFACTORY);
        testAdd.setMark(4, "GYM", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(2, "A", CreditCard.Mark.UNSATISFACTORY);
        testAdd.setMark(5, "Art", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(4, "Gym", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(3, "B", CreditCard.Mark.SATISFACTORY);
        testAdd.setMark(3, "C", CreditCard.Mark.EXCELLENT);
        testAdd.setMark(3, "D", CreditCard.Mark.GOOD);
        testAdd.setCvalificationMark(CreditCard.Mark.GOOD);
        assertEquals(true, testAdd.getScholarship(1));
        assertEquals(true, testAdd.getScholarship(2));
        assertEquals(false, testAdd.getScholarship(3));
        assertEquals(false, testAdd.getScholarship(4));
        assertEquals(true, testAdd.getScholarship(5));
        assertEquals(true, testAdd.getScholarship(6));
    }
}