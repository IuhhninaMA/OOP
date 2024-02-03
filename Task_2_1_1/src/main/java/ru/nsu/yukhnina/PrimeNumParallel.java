package ru.nsu.yukhnina;

import java.util.ArrayList;

public class PrimeNumParallel {
    private ArrayList<Integer> numbers;

    public PrimeNumParallel(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }

    public boolean checkParallel() {
        return (numbers.parallelStream().filter(x -> isPrime(x) == false).count() > 0);
    }

    private boolean isPrime(int num) {
        for (int j = 2; j < (((int) Math.sqrt(num)) + 1); j++) {
            if (num % j == 0) {
                return false;
            }
        }
        return true;
    }
}
