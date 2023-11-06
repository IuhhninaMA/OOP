package ru.nsu.yukhnina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RabinKarpAlgorithmTest {

    @Test
    void shortFile() throws Exception {
        ArrayList<Integer> result_actual = RabinKarpAlgorithm.find("input.txt", "ed");
        int[] result_exception = {6, 15, 20, 31};
        for (int i = 0; i < 4; i++) {
            assertEquals(result_exception[i], result_actual.get(i));
        }
    }



/*    Тест задан такими данными:
    f = open('text.txt', 'w')
    f.write("find")
    for i in range(10000):
       f.write('aaaaaa')
    f.write("find")
    for i in range(10000):
       f.write('aaaaaa')
    f.write("find")*/
    @Test
    void mediumFile() throws Exception {
        ArrayList<Integer> result_actual = RabinKarpAlgorithm.find("text.txt", "find");
        int[] result_exception = {1, 60005, 120009};
        for (int i = 0; i < 3; i++) {
            assertEquals(result_exception[i], result_actual.get(i));
        }
    }



//f = open('text2.txt', 'w')
//f.write("text")
//for i in range(10000):
//    f.write('ffffielrkgnlekrngrtgn4gorlknglekdmfer;gprotgnpotrgnirtd')
//f.write("text")
//for i in range(10000):
//    f.write('ddddohdeoihdoehfioheerkgnlern')
//f.write("text")
//f.close()
// Он работает, но входняе данные больше 800 кб и ппокажу на паре если нужно
//    @Test
//    void bigFile() throws Exception {
//        ArrayList<Integer> result_actual = RabinKarpAlgorithm.find("text2.txt", "text");
//        int[] result_exception = {1, 550005, 840009};
//        for (int i = 0; i < 3; i++) {
//            assertEquals(result_exception[i], result_actual.get(i));
//        }
//
//    }

//f = open('text3.txt', 'w')
//for i in range(100): f.write("repeat")
//f.close()


    @Test
    void allWordsToFind() throws Exception {
        ArrayList<Integer> result_actual = RabinKarpAlgorithm.find("text3.txt", "repeat");
        assertEquals(100, result_actual.size());

    }

    @Test
    void notInFile() throws Exception {
        ArrayList<Integer> result_actual = RabinKarpAlgorithm.find("text4.txt", "fruits");
        assertEquals(0, result_actual.size());

    }

}