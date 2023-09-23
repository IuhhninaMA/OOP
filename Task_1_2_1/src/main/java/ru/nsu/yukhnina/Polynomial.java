package ru.nsu.yukhnina;

import static java.lang.String.valueOf;


/**
* Класс, в котором определены все штучки для многочлена.
* Сложениеб вычитание, умножение, нахождение производных любого порядка.
*/
public class Polynomial {
    float[] polynomIndexes;
    int maxIndex;

    Polynomial(float[] array) {
        maxIndex = array.length;
        polynomIndexes = new float[maxIndex];
        System.arraycopy(array, 0, polynomIndexes, 0, maxIndex);
    }

    /**
    * Функция для сложения двух многочленов.
    * @param p - второе слагаемое.
    * @return результат сложения двух многочленов.
    */
    Polynomial addition(Polynomial p) {
        int minLenght = Math.min(maxIndex, p.maxIndex);
        int maxLenght = Math.max(maxIndex, p.maxIndex);
        Polynomial result = new Polynomial(new float[maxLenght]);
        //перебираем всё до конца наименьшего массива.
        for (int i = 0; i < minLenght; i++) {
            result.polynomIndexes[i] = polynomIndexes[i] + p.polynomIndexes[i];
        }
        //если массивы не равны по длинне, то должно ещё что-то остаться, добавляем.
        if (maxLenght - p.maxIndex >= 0) {
            System.arraycopy(polynomIndexes, minLenght, result.polynomIndexes,
                    minLenght, maxLenght - p.maxIndex);
        }
        if (maxLenght - maxIndex >= 0) {
            System.arraycopy(p.polynomIndexes, minLenght, result.polynomIndexes,
                    minLenght, maxLenght - maxIndex);
        }
        return result;
    }

    /**
    *
    * @param p - вычитаемое.
    * @return разность многочленов.
    */
    Polynomial difference(Polynomial p) {
        int minLenght = Math.min(maxIndex, p.maxIndex);
        int maxLenght = Math.max(maxIndex, p.maxIndex);
        Polynomial result = new Polynomial(new float[maxLenght]);
        //перебираем всё до конца наименьшего массива
        for (int i = 0; i < minLenght; i++) {
            result.polynomIndexes[i] = polynomIndexes[i] - p.polynomIndexes[i];
        }
        //если массивы не равны по длинне, то должно ещё что-то остаться, добавляем
        if (maxLenght - p.maxIndex >= 0)
            System.arraycopy(polynomIndexes, minLenght, result.polynomIndexes,
                    minLenght, maxLenght - p.maxIndex);
        for (int i = 0; i < maxLenght - maxIndex; i++) {
            result.polynomIndexes[minLenght + i] = -p.polynomIndexes[minLenght + i];
        }
        return result;
    }

    /**
    * @param p - второй множитель.
    * @return произведение многочленов.
    * получаем полином равный произведению двух полиномов.
    */
    Polynomial mult(Polynomial p) {
        int maxLenght = Math.max(maxIndex, p.maxIndex);
        float[] resultArray = new float[maxIndex + p.maxIndex + 1];
        Polynomial result = new Polynomial(resultArray);
        for (int i = 0; i < maxIndex; i++) {
            for (int j = 0; j < p.maxIndex; j++) {
                result.polynomIndexes[i + j] += polynomIndexes[i] * p.polynomIndexes[j];
            }
        }
        return result;
    }

    /**
    * @param x - точка,в которой нужно вычислить значение многочлена.
    * @return чему равен многочлен в точке x.
    * вычисляет значение полинома в точке x.
    */
    float calculationAtPoint(float x) {
        float helperX = 1;
        float result = 0;
        for (int i = 0; i < maxIndex; i++){
            result += helperX * polynomIndexes[i];
            helperX *= x;
        }
        return result;
    }

    /**
    * @param n - порядок производной.
    * @return производная n-ной степени многочлена.
    */
    Polynomial differential(int n) {
        Polynomial result = new Polynomial(polynomIndexes);
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < result.maxIndex; j++) {
                result.polynomIndexes[j - 1] = result.polynomIndexes[j] * j;
            }
            result.maxIndex--;
            result.polynomIndexes[result.maxIndex] = 0;

        }
        return result;
    }

    /**
    * @param p - многочлен, с которым нужно сравнить.
    * @return true or fale  или равны многочлены или нет.
    * Я не стала писать Override потому что он на него ругался и так работает.
    */
    public boolean equals(Polynomial p) {
        if (maxIndex != p.maxIndex) {
            return false;
        }
        for (int i = 0; i < maxIndex; i++) {
            if (polynomIndexes[i] != p.polynomIndexes[i]) {
                return false;
            }
        }
        return true;
    }

    /** переводит полином из вида, удобного программе в человеческий вид.
    * @return многочлен в человеческом виде.
    */

    public String toString() {
        boolean sign = false;
        String result = "";
        for (int i = maxIndex - 1; i > 1; i--) {
            if (polynomIndexes[i] > 0 && sign) {
                result = result.concat(" + " + valueOf(polynomIndexes[i]) + "x^" + i);
            }
            if (polynomIndexes[i] < 0) {
                result = result.concat(" - " + valueOf(Math.abs(polynomIndexes[i])) + "x^" + i);
                sign = true;
            }
            if (polynomIndexes[i] > 0 && !sign) {
                result = result.concat(valueOf(polynomIndexes[i]) + "x^" + i);
                sign = true;
            }
        }
        //для x в первой степени
        if (polynomIndexes[1] > 0 && sign) {
            result = result.concat(" + " + valueOf(polynomIndexes[1]) + "x");
        }
        if (polynomIndexes[1] < 0) {
            result =result.concat(" - " + valueOf(Math.abs(polynomIndexes[1])) + "x");
            sign = true;
        }
        if (polynomIndexes[1] > 0 && !sign) {
            result = result.concat(valueOf(polynomIndexes[1]) + "x");
            sign = true;
        }
        //для x в нулевой степени
        if (polynomIndexes[0] > 0 && sign) {
            result = result.concat(" + " + valueOf(polynomIndexes[0]));
        }
        if (polynomIndexes[0] < 0) {
            result = result.concat(" - " + valueOf(Math.abs(polynomIndexes[0])));
            sign = true;
        }
        if (polynomIndexes[0] >= 0 && !sign) {
            result = result.concat(valueOf(polynomIndexes[0]));
        }
        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial(new float[] {4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new float[] {3, 2, 8});
        System.out.println(p1.difference(p2).toString());
        Polynomial p3 = new Polynomial(new float[] {1, 0, 4, 5, 0, 0, 0, 1});
        Polynomial p4 = new Polynomial(new float[] {0, 3, 1});
        System.out.println(p3.mult(p4).toString());
        float res = p3.calculationAtPoint(2);
        float res2 = p1.calculationAtPoint(2);
        System.out.println(p3.differential(2).toString());
    }
}