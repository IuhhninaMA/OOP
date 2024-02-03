package ru.nsu.yukhnina;

import java.util.ArrayList;

public class PrimeNuSterams {


    private int streamsCount;
    private ArrayList<Integer> numbers;
    private ArrayList<OneStream> numbers2;

    public PrimeNuSterams(ArrayList<Integer> numbers, int streamsCount) {
        this.numbers = numbers;
        this.streamsCount = streamsCount;
        numbers2 = new ArrayList<>();
    }

    public boolean checkStreams() {
        int numCount = numbers.size() - 1;
        while (numCount >= streamsCount) {
            for (int i = 0; i < streamsCount; i++) {
                OneStream newNum = new OneStream(numbers.get(numCount));
                numCount--;
                Thread childTread = new Thread(newNum);
                childTread.start();
                if (!newNum.isPrimeNumber()) {
                    return true;
                }
                numbers2.add(newNum);
            }
            //поставить wait иил сделать массив трэдов
        }
        while (numCount >= 0) {
            OneStream newNum = new OneStream(numbers.get(numCount));
            Thread childTread = new Thread(newNum);
            childTread.start();
            numCount--;
            if (!newNum.isPrimeNumber()) {
                return true;
            }
            numbers2.add(newNum);
        }
        for (int i = 0; i < numbers2.size(); i++) {
            if (!numbers2.get(i).isPrime) {
                return true;
            }
        }
        return false;
    }
}
