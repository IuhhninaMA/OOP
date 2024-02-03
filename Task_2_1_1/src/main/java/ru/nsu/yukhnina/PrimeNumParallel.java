package ru.nsu.yukhnina;

import java.util.ArrayList;

/**
 * Класс, который реализует поиск простого числа параллельным программированием.
 */
public class PrimeNumParallel {
    private ArrayList<Integer> numbers;

    /**
     * Конструктор класса.
     * Это одна из самых простых обработок массива, мне нужен только массив.
     */
    public PrimeNumParallel(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }

    /**
     * Использую parallelStream.
     * овзвращаю true, если есть хотя бы одно не простое число, иначе false.
     */
    public boolean checkParallel() {
        return (numbers.parallelStream().filter(x -> !isPrime(x)).count() > 0);
    }

    /**
     * Функция, которая проверяет число на простоту.
     * Вызывается из стрима.
     * Возвращает false если число составное.
     * true - число простое.
     */
    private boolean isPrime(int num) {
        for (int j = 2; j < (((int) Math.sqrt(num)) + 1); j++) {
            if (num % j == 0) {
                return false;
            }
        }
        return true;
    }
}
