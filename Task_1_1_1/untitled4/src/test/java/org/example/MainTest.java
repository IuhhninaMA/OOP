package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void sort() {
        int arr[] = {1, 4, 3, 8, 12, 90, 34, 4};
        int result[] = {1, 3, 4, 4, 8, 12, 34, 90};
        int n = arr.length;
        Main my_sort = new Main();
        my_sort.sort(arr, n);
        for (int i = 0; i < n; i++)
            assertEquals(arr[i], result[i]);


        int arr2[] = {1, 4, 3, 8, 12, 90, 34, 4, -1};
        int result2[] = {-1, 1, 3, 4, 4, 8, 12, 34, 90};
        int n2 = arr2.length;
        my_sort.sort(arr2, n2);
        for (int i = 0; i < n2; i++)
            assertEquals(arr2[i], result2[i]);


        int test3[] = {-1, -1, -1, -1, -1, -1, -1};
        int result3[] = {-1, -1, -1, -1, -1, -1, -1};
        int n3 = test3.length;
        my_sort.sort(test3, n3);
        for (int i = 0; i < n3; i++)
            assertEquals(test3[i], result3[i]);


        int test4[] = {-1, 1, 1, 1, 1, 3,4, 6 ,6, 687058, 123, -100};
        int result4[] = {-100, -1, 1, 1, 1, 1, 3, 4, 6, 6, 123, 687058};
        int n4 = test4.length;
        my_sort.sort(test4, n4);
        for (int i = 0; i < n4; i++)
            assertEquals(test4[i], result4[i]);
    }
}