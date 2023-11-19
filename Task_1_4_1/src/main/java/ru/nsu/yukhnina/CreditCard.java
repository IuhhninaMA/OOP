package ru.nsu.yukhnina;

import java.util.ArrayList;
import java.util.HashMap;

public class CreditCard {
    //один и тот же предмет может повторяться несколько семестров,
    // но на оценку диплома влияет только посленяя, которая и будет храниться тут
    private HashMap<String, Mark> final_mark;
    private ArrayList<HashMap<String, Mark>> marks;
    private int semestr;
    private Mark cvalification;
    public CreditCard() {
        final_mark = new HashMap<>();
        marks = new ArrayList<HashMap<String, Mark>>();
        marks.add(new HashMap<String, Mark>()); //добавляю значения для расчёта стипы за 1 семестр
        semestr = 0;
        cvalification = Mark.EXCELLENT;
    }

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

    public void setCvalificationMark(Mark mark) {
        cvalification = mark;
    }

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

    public double getAvgMark() {
        int avgMark = 0;
        int marksCount = 0;
        for (int i = 0; i <= this.semestr; i++) {
            for (String subject : marks.get(i).keySet()) {
                avgMark += marks.get(i).get(subject).value;
                marksCount++;
            }
        }
        return (double)avgMark/(double) marksCount;
    }

    public boolean redDiplom() {
        //если квалификационная оценка не 5, т смысла проверять дальше нет
        if (cvalification != Mark.EXCELLENT) {
            System.out.println("Хороший подстаканник");
            return false;
        }
        //прохожусь по всем предметам - оценкам,
        //записываю их в хэшмапку,
        //последняя оценка, котрая была записана по имени предмета - итоговая в диплом
        for (int i = 0; i < this.semestr; i++) {
            for (String subject : marks.get(i).keySet()) {
                final_mark.put(subject, marks.get(i).get(subject));
                if (marks.get(i).get(subject) == Mark.UNSATISFACTORY
                        || marks.get(i).get(subject) == Mark.SATISFACTORY) {
                    return false;
                }
            }
        }
        int countExc = 0;
        int marksCount = 0;
        //считаю сумму итгоовых оценок
        for (String subject : final_mark.keySet()) {
            if (final_mark.get(subject) == Mark.EXCELLENT) {
                countExc++;
            }
            if (final_mark.get(subject) == Mark.UNSATISFACTORY) {
                System.out.println("Ну отчислен...");
                return false;
            }
            if (final_mark.get(subject) == Mark.SATISFACTORY) {
                System.out.println("Хороший подстаканник");
                return false;
            }
            marksCount ++;
        }
        if ((double)countExc / (double)marksCount >= 0.75) {
            System.out.println("Ну может и красный");
            return true;
        }
        else {
            System.out.println("Хороший подстаканник");
            return false;
        }
    }

    public boolean getScholarship (int semestrnum) {
        //раситываем semestrnum -1 так как считаем балл за прошлый семестр,
        // чтобы получить стипу за этот
        for (String subject : marks.get(semestrnum - 1).keySet()) {
            if (marks.get(semestrnum - 1).get(subject) == Mark.SATISFACTORY) {
                System.out.println("Ну пролетел ты со стипой совсем, даже 3500 не увидишь");
                return false;
            }
            if (marks.get(semestrnum - 1).get(subject) == Mark.UNSATISFACTORY) {
                System.out.println("Ну пролетел ты со стипой, так ещё и на пересдачу отправился");
                return false;
            }
            if (marks.get(semestrnum - 1).get(subject) == Mark.GOOD) {
                System.out.println("Ну молодец 3500");
                return false;
            }
        }
        System.out.println("О, ну даже 5000, а нервы остались?");
        return true;
    }
}