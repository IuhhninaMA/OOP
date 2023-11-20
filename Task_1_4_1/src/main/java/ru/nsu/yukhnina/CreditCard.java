package ru.nsu.yukhnina;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Record book class. Find avg mark,
 * check opportunity red diploma,
 * check opportunity scholarship.
 */
public class CreditCard {
    //один и тот же предмет может повторяться несколько семестров,
    // но на оценку диплома влияет только посленяя, которая и будет храниться тут
    private ArrayList<HashMap<String, Mark>> marks;
    private int semestr;
    private Mark cvalification;

    /**
     * finalMark contains subjects final marks without all current,
     * marks it's all marks,
     * now we think that semestr is 0 and we havent marks.
     * It's for calculate first stipa.
     * And enums its marks we can have.
     */
    public CreditCard() {
        marks = new ArrayList<HashMap<String, Mark>>();
        marks.add(new HashMap<String, Mark>()); //добавляю значения для расчёта стипы за 1 семестр
        semestr = 0;
        cvalification = Mark.EXCELLENT;
    }

    /**
     * Marks we can have.
     */
    public enum Mark {
        EXCELLENT(5),
        GOOD(4),
        SATISFACTORY(3),
        UNSATISFACTORY(2);
        private final int value;

        Mark(int mark) {
            this.value = mark;
        }
    }

    /**
     * Sset cvalification mark.
     * Default it's EXCELLENT to find opportunity red diplom.
     */
    public void setCvalificationMark(Mark mark) {
        cvalification = mark;
    }

    /**
     * Add new mark if I know semesrt numberб subjects name and mark.
     */
    public void setMark(int newSemestr, String subject, Mark mark) {
        // прверяем, что если данных по этому семестру ещё не было,
        // то array list там null, нужно инициализироавть.
        // предположим что пользователь не сильно умный и может начать заполнять с конца,
        // поэтому добавим for для инициализации всех штук
        if (newSemestr > this.semestr) {
            for (int i = this.semestr; i <= newSemestr; i++) {
                marks.add(new HashMap<String, Mark>());
            }
            this.semestr = newSemestr;
        }
        marks.get(newSemestr).put(subject, mark);
    }

    /**
     * Calculate average mark.
     */
    public double getAvgMark() {
        int avgMark = 0;
        int marksCount = 0;
        for (int i = 0; i <= this.semestr; i++) {
            for (String subject : marks.get(i).keySet()) {
                avgMark += marks.get(i).get(subject).value;
                marksCount++;
            }
        }
        return (double) avgMark / (double) marksCount;
    }

    /**
     * Calculate opportunity have red diploma.
     */
    public boolean redDiplom() {
        HashMap<String, Mark> finalMark = new HashMap<>();
        //если квалификационная оценка не 5, т смысла проверять дальше нет
        if (cvalification != Mark.EXCELLENT) {
            return false;
        }
        //прохожусь по всем предметам - оценкам,
        //записываю их в хэшмапку,
        //последняя оценка, котрая была записана по имени предмета - итоговая в диплом
        for (int i = 0; i < this.semestr; i++) {
            for (String subject : marks.get(i).keySet()) {
                finalMark.put(subject, marks.get(i).get(subject));
                if (marks.get(i).get(subject) == Mark.UNSATISFACTORY
                        || marks.get(i).get(subject) == Mark.SATISFACTORY) {
                    return false;
                }
            }
        }
        int countExc = 0;
        int marksCount = 0;
        //считаю сумму итгоовых оценок
        for (String subject : finalMark.keySet()) {
            if (finalMark.get(subject) == Mark.EXCELLENT) {
                countExc++;
            }
            if (finalMark.get(subject) == Mark.UNSATISFACTORY) {
                return false;
            }
            if (finalMark.get(subject) == Mark.SATISFACTORY) {
                return false;
            }
            marksCount++;
        }
        if ((double) countExc / (double) marksCount >= 0.75) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Find can student live in this semestr.
     */
    public boolean getScholarship(int semestrnum) {
        //раситываем semestrnum -1 так как считаем балл за прошлый семестр,
        // чтобы получить стипу за этот
        for (String subject : marks.get(semestrnum - 1).keySet()) {
            if (marks.get(semestrnum - 1).get(subject) == Mark.SATISFACTORY) {
                return false;
            }
            if (marks.get(semestrnum - 1).get(subject) == Mark.UNSATISFACTORY) {
                return false;
            }
            if (marks.get(semestrnum - 1).get(subject) == Mark.GOOD) {
                return false;
            }
        }
        return true;
    }
}