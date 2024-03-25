package ru.nsu.yukhnina;

import java.util.ArrayList;

public class Counter {
    private ArrayList<Integer> numbers;
    private boolean isPrime;
    public Counter(ArrayList<Integer> numbers) {
        isPrime = true;
        this.numbers =  numbers;
    }

    public boolean countPrime() {
        for (Integer i : numbers) {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}