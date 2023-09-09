package org.example;

public class Main{
    public int[] sort(int[] arr, int n){

        for (int i = n/2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n-1; i>= 0; i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
        return arr;
    }

    void heapify(int arr[], int n, int i){

        int large = i, left = 2*i + 1,
                right = 2*i+2;

        if (left < n && arr[left] > arr[large]) large = left;

        if (right < n && arr[right] > arr[large]) large = right;



        if (large != i){
            int temp = arr[i];
            arr[i] = arr[large];
            arr[large] = temp;
            heapify(arr, n, large);
        }
    }


    public static void main(String[] args) {
        //int arr[] = {1, 4, 3, 8, 12, 90, 34, 4};
        //int n = arr.length;
        //Main my_sort = new Main();
        //my_sort.sort(arr, n);
        //System.out.println("Sorted array is");
        //for (int i=0; i<n; ++i)
        //    System.out.print(arr[i]+" ");
        //System.out.println();
    }

}



