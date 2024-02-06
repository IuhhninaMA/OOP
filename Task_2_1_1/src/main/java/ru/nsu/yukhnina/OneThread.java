package ru.nsu.yukhnina;

import java.util.ArrayList;

/**
 * Небольшой класс, который необходим для того,
 * чтобы задачу можно было распараллелить.
 */
public class OneThread implements Runnable {

    //я не сильно поняла зачем volatite, но пусть будет.
    private volatile boolean isPrime;
    private final int start;
    private final int len;
    private final ArrayList<Integer> numbers;

    /**
     * Информация о каждой ниточке.
     * Начало массива, с которого она читает;
     * сдвиг;
     * сам массив,
     * результат вычислений.
     */
    public OneThread(ArrayList<Integer> numbers, int start, int len) {
        isPrime = true; //true - все простые.
        this.start = start;
        this.len = len;
        this.numbers = numbers;
    }

    /**
     * возвращает результат вычислений.
     */
    public boolean isAllNumbersPrime() {
        return isPrime;
    }

    @Override
    public void run() {
        for (int i = start; i < start+len; i++) {
            for (int j = 2; j <= (((int) Math.sqrt(numbers.get(i))) + 1); j++) {
                if (numbers.get(i) % j == 0 && j != numbers.get(i)) {
                    isPrime = false;
                    break;
                }
            }
        }
    }
}
