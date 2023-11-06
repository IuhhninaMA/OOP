package ru.nsu.yukhnina;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;

public class RabinKarpAlgorithm {

    public static ArrayList<Integer> find(String filename, String toFind) throws Exception {
        InputStream inputStream;
        final int primeNum = 17;
        int strLen = toFind.length();
        Properties prop = new Properties();
        ArrayList<Integer> result = new ArrayList<Integer>();
        int pow = 1;
        int hashToFind = 0;
        int hashInput = 0;
        try {
            //если в строке не нашлось / значит файл ресурс
            if (filename.indexOf('/') == -1) {
                inputStream =
                        RabinKarpAlgorithm.class.getClassLoader().getResourceAsStream(filename);
            } else {
                inputStream = new FileInputStream(filename);
            }
            InputStreamReader fileReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            CharBuffer charBuffer = CharBuffer.allocate(1);
            StringBuilder substring = new StringBuilder();
            int charsRead;
            //считываю первые n символов по размеру строки которую ищу
            // и сразу нахожу первичные хэши
            for (int i = 0; i < strLen; i++) {
                charsRead = fileReader.read(charBuffer);
                charBuffer.flip(); // Переворачиваем буфер для чтения
                charBuffer.rewind();
                substring.append(charBuffer);
                charBuffer.clear(); // Очищаем буфер для следующего чтения
                hashToFind += toFind.charAt(i) * pow;
                hashInput += substring.charAt(i) * pow;
                pow *= primeNum;
            }
            pow /= primeNum;
            int j;
            int k = 0;//счётчик считанных символов

            //начинаю считывать весь оставшийся файл до конца
            while ((charsRead = fileReader.read(charBuffer)) != -1) {
                k++;
                //прверяю на равенство хэшей, если равны проверяю посимвольно
                if (hashToFind == hashInput) {
                    for (j = 0; j < strLen; j++) {
                        if (substring.charAt(j) != toFind.charAt(j)) {
                            break;
                        }
                    }

                    //если дошли до конца в проверке, значит строки равны,
                    // тогда выводим индекс, завершаем программу
                    if (j == strLen) {
                        result.add(k);
                    }
                }
                charBuffer.flip(); // Переворачиваем буфер для чтения
                substring.append(charBuffer.get()); //Добавляю в текущую подстроку считанный символ
                charBuffer.clear(); // Очищаем буфер для следующего чтения

                //Пересчитываю хэш
                hashInput = (hashInput - substring.charAt(0))
                        / primeNum + substring.charAt(strLen) * pow;
                substring.deleteCharAt(0);
            }
            //сравнение последнего пересчитанного хэша
            if (hashToFind == hashInput) {
                for (j = 0; j < strLen; j++) {
                    if (substring.charAt(j) != toFind.charAt(j)) {
                        break;
                    }
                }

                //если дошли до конца в проверке, значит строки равны,
                // тогда выводим индекс, завершаем программу
                if (j == strLen)
                    result.add(k + 1);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}