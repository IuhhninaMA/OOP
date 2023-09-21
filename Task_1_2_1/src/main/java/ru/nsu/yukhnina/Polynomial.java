package ru.nsu.yukhnina;

import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Polynomial {
    float[] indexes;
    int len_indexes_array;
    Polynomial(float[] array){
        indexes = array;
        len_indexes_array = array.length;
    }

    Polynomial addition(Polynomial p){

        int minLenght = Math.min(len_indexes_array, p.len_indexes_array);
        int maxLenght = Math.max(len_indexes_array, p.len_indexes_array);
        Polynomial result = new Polynomial(new float[maxLenght]);

        //перебираем всё до конца наименьшего массива
        for (int i = 0; i < minLenght; i++){
            result.indexes[i] = indexes[i] + p.indexes[i];
        }

        //если массивы не равны по длинне, то должно ещё что-то остаться, добавляем
        for (int i = 0; i < maxLenght - p.len_indexes_array; i++){
            result.indexes[minLenght+i] = indexes[minLenght+i];
        }
        for (int i = 0; i < maxLenght - len_indexes_array; i++){
            result.indexes[minLenght+i] = p.indexes[minLenght+i];
        }
        return result;
    }


    Polynomial difference(Polynomial p){
        int minLenght = Math.min(len_indexes_array, p.len_indexes_array);
        int maxLenght = Math.max(len_indexes_array, p.len_indexes_array);
        Polynomial result = new Polynomial(new float[maxLenght]);
        //перебираем всё до конца наименьшего массива
        for (int i = 0; i < minLenght; i++){
            result.indexes[i] = indexes[i] - p.indexes[i];
        }
        //если массивы не равны по длинне, то должно ещё что-то остаться, добавляем
        for (int i = 0; i < maxLenght - p.len_indexes_array; i++){
            result.indexes[minLenght+i] = indexes[minLenght+i];
        }

        for (int i = 0; i < maxLenght - len_indexes_array; i++){
            result.indexes[minLenght+i] = -p.indexes[minLenght+i];
        }

        return result;
    }

    public static void main(String[] args){
        Polynomial p1 = new Polynomial(new float[] {4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new float[] {3, 2, 8});
        System.out.println(Arrays.toString(p1.difference(p2).indexes));
    }
}