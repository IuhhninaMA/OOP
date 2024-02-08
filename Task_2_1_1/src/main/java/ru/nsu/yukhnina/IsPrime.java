package ru.nsu.yukhnina;

/**
 * Check is num prime or not.
 */
public class IsPrime {

    /**
     * Check is num prime or not.
     */
    public static boolean isPrimeMethod(int n) {
        int maxPossibleDivider = (((int) Math.sqrt(n) + 1));
        for (int j = 2; j <= maxPossibleDivider; j++) {
            if (n % j == 0 && j != n) {
                return true;
            }
        }
        return false;
    }
}
