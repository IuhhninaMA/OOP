package ru.nsu.yukhnina;

import java.util.ArrayList;

public class Counter {
    private ArrayList<Integer> numbers;
    public Counter(ArrayList<Integer> numbers) {
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
