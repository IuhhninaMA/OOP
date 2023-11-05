package ru.nsu.yukhnina;

//Итог: криво, но почти работает, не обрабатывает случай, если подстрока находится в конце файла

import java.io.*;
import java.nio.CharBuffer;

public class RabinKarpAlgorithm {
    public static int find(String filename, String toFind) throws Exception {
        int strLen = toFind.length();
        rabinKarp(filename, toFind, strLen);
        return 0;
    }

    private static void rabinKarp(String filename, String toFind, int strLen) throws Exception {
        int pow = 1;
        int hashToFind = 0;
        int hashInput = 0;
        try {
            FileReader fileReader = new FileReader(filename);
            CharBuffer charBuffer = CharBuffer.allocate(1);
            StringBuilder substring = new StringBuilder();
            int charsRead;

            //считываю первые n символов по размеру строки которую ищу
            // и сразу нахожу первичные хэши
            for (int i = 0; i < strLen; i++) {
                charsRead = fileReader.read(charBuffer);
                charBuffer.flip(); // Переворачиваем буфер для чтения
                charBuffer.rewind();
                substring.append(charBuffer.get());
                charBuffer.clear(); // Очищаем буфер для следующего чтения
                hashToFind += toFind.charAt(i)*pow;
                hashInput += substring.charAt(i)*pow;
                pow *= 17;
            }
            pow /= 17;
            int j;
            int k = 0;//счётчик считанных символов

            //начинаю считывать весь оставшийся файл до конца
            while ((charsRead = fileReader.read(charBuffer)) != -1) {
                k++;
                //прверяю на равенство хэшей, если равны проверяю посимвольно
                if (hashToFind == hashInput) {
                    for (j = 0; j < strLen; j++) {
                        if (substring.charAt(j) != toFind.charAt(j))
                            break;
                    }

                    //если дошли до конца в проверке, значит строки равны, тогда выводим индекс, завершаем программу
                    if (j == strLen)
                        System.out.println(k);
                }
                charBuffer.flip(); // Переворачиваем буфер для чтения
                substring.append(charBuffer.get()); //Добавляю в текущую подстроку считанный символ
                charBuffer.clear(); // Очищаем буфер для следующего чтения

                //Пересчитываю хэш
                hashInput = (hashInput - substring.charAt(0)) / 17 + substring.charAt(strLen)*pow;
                substring.deleteCharAt(0);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        find("src/main/resources/input.txt", "ed");
    }
}