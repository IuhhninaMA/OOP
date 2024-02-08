package ru.nsu.yukhnina;

import java.util.ArrayList;

/**
 * Тут даже пояснять не надо.
 */
public class PrimeNumSequent {
    private ArrayList<Integer> numbers;
    /**
     * numbers - обрабатываемы массив.
     */
    public PrimeNumSequent(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }
    /**
     * Проверяем все делители до квадрата числа,
     * если таких не найдено - число простое.
     */
    public boolean check() {
        for (int i = 0; i < numbers.size(); i++) {
            if (IsPrime.isPrimeMethod(numbers.get(i))) {
                return true;
            }
        }
        return false;
    }
}