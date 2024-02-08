package ru.nsu.yukhnina;

public class isPrime {
    public static boolean isPrime(int n) {
        int maxPossibleDivider = (((int) Math.sqrt(n) + 1));
        for (int j = 2; j <= maxPossibleDivider; j++) {
            if (n % j == 0 && j != n) {
                return true;
            }
        }
        return false;
    }
}
