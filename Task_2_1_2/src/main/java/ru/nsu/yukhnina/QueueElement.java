package ru.nsu.yukhnina;

import java.util.ArrayList;

public class QueueElement {
    private ArrayList<Integer> numbers;
    public QueueElement(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }

    public ArrayList<Integer> getArray() {
        return numbers;
    }
}
