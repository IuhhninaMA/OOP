package ru.nsu.yukhnina;

import java.util.ArrayList;

/**
 * Класс, который реализует поиск простого числа параллельным программированием.
 */
public class PrimeNumParallelStream {
    private ArrayList<Integer> numbers;

    /**
     * Конструктор класса.
     * Это одна из самых простых обработок массива, мне нужен только массив.
     */
    public PrimeNumParallelStream(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }

    /**
     * Использую parallelStream.
     * овзвращаю true, если есть хотя бы одно не простое число, иначе false.
     */
    public boolean checkParallel() {
        return (numbers.parallelStream().anyMatch(IsPrime::isPrimeMethod));
    }

}
