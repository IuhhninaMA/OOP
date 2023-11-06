package ru.nsu.yukhnina;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

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

    @Test
    void bigFile() throws Exception {
        String fffile = "src/test/java/ru/nsu/yukhnina/testBigFile.txt";
        RandomAccessFile f = new RandomAccessFile(fffile, "rw");
        f.setLength(15L * 1024 * 1024 * 1024);
        try(FileWriter writer = new FileWriter(fffile, false))
        {
            writer.write("text");
            for (int j = 0; j < 9; j++) {
                for (int i = 0; i < 9; i++) {
                    writer.write("Hello Gold! Hi honey! I'm so tried(((");
                }
            }
            writer.write("text");
            writer.write("text");
            writer.write("text");
            writer.flush();

        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        ArrayList<Integer> result_actual = RabinKarpAlgorithm.find(fffile, "text");
        for (int i = 0; i < 3; i++) {
            assertEquals(4, result_actual.size());
        }
        (new File(fffile)).delete();
    }

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