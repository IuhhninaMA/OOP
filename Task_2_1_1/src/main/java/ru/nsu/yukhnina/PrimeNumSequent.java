package ru.nsu.yukhnina;

import java.util.ArrayList;

public class PrimeNumSequent{
    private ArrayList<Integer> numbers;

    public PrimeNumSequent(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }

    public boolean check() {
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 2; j <= (((int) Math.sqrt(numbers.get(i))) + 1); j++) {
                if (numbers.get(i) % j == 0 && j != numbers.get(i)) {
                    return true;
                }
            }
        }
        return false;
    }
}