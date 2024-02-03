package ru.nsu.yukhnina;

public class OneStream implements Runnable{
    private int num;
    boolean isPrime;

    public OneStream(int num) {
        this.isPrime = true;
        this.num = num;
    }

    public boolean isPrimeNumber() {
        return isPrime;
    }
    /**
     *
     */
    @Override
    public void run() {
        for (int j = 2; j < (((int) Math.sqrt(num)) + 1); j++) {
            if (num % j == 0) {
                isPrime = false;
            }
        }
    }
}
