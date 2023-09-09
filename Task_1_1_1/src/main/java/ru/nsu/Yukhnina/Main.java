package ru.nsu.Yukhnina;

public class Main {

    /**
     * @param arr это массив который нужно отсортировать
     * @param n это длинна сртируемого массива
     * @brief
     * Постройте max-heap из входных данных.
     * На данном этапе самый большой элемент хранится в корне кучи. Замените его на последний элемент кучи, а затем уменьшите ее размер на 1. Наконец, преобразуйте полученное дерево в max-heap с новым корнем.
     * Повторяйте вышеуказанные шаги, пока размер кучи больше 1.
     */
    public int[] sort(int arr[], int n) {
        for (int i = n / 2 - 1; i >= 0; i--)
        {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i >= 0; i--)
        {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
        return arr;
    }


    /**
     * @param arr массив на основе которого строится куча
     * Функция для преобразования в кучу
     */
    void heapify(int arr[], int n, int i)
    {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest])
        {
            largest = l;
        }

        if (r < n && arr[r] > arr[largest])
        {
            largest = r;
        }

        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }
}