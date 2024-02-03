package ru.nsu.yukhnina;

import java.util.ArrayList;

/**
 * Пока я не сильно довольна реализацией, буду менять.
 * Класс, обрабатывающий массив целых чисел.
 */
public class PrimeNuSterams {


    private int streamsCount;
    private ArrayList<Integer> numbers;
    private ArrayList<OneStream> numbers2;

    /**
     * По условию может быть разное количество потоков,
     * за это отвечает переменная streamsCount,
     * numbers - обрабатываемый массив.
     * Необходимость numbers2 объясню ниже.
     */
    public PrimeNuSterams(ArrayList<Integer> numbers, int streamsCount) {
        this.numbers = numbers;
        this.streamsCount = streamsCount;
        numbers2 = new ArrayList<>();
    }

    /**
     * Тут происходит проверка массива.
     * Я создаю объект класса OneStream,
     * от него вызываю start, который запускает run.
     * Именно благодаря start получается параллельное программирование.
     */
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
            //поставить wait или сделать массив трэдов
            //тут я конечно запускаю необходимое колличество потоков,
            //НО! я не уверена, что к новому проходу цикла while
            // все запущенные на прошлой итерации цикла процессы завершатся,
            // для этого я думаю или собирать все в один массив и потом проверять,
            // либо поставить что-то типа wait

            // ещё одна поблема с ожиданием завершения процесса,
            // он не всегда завершается к if (!newNum.isPrimeNumber()),
            // поэтому сначала происходит такая первичкная обраьботка,
            //потом числа складываются в массив,
            // и после завершения всех поцессов происходит повторная проверка.
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
