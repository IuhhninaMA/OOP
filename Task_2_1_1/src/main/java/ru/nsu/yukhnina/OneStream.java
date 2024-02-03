package ru.nsu.yukhnina;

/**
 * Небольшой класс, который необходим для того,
 * чтобы задачу можно было распараллелить.
 */
public class OneStream implements Runnable {
    private int num;
    boolean isPrime;

    /**
     * Тут хранится информация о числе,
     * само число и булевое значение простое оно или составное,
     * базовое значение true, пи запуске run поменяется.
     */
    public OneStream(int num) {
        this.isPrime = true;
        this.num = num;
    }

    /**
     * Так как run ничего не возвращает,
     * я создала приватное поле isPrime,
     * куда сохраняется результат проверки числа,
     * эта функция работает как get.
     */
    public boolean isPrimeNumber() {
        return isPrime;
    }

    /**
     * Функция, которая проверяет число на простоту.
     * Вызывается во время вызова start в PrimeNuStream.
     * Возвращает false если число составное.
     * true - базовое значение, число простое,
     * если не нашлось делителя.
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
