package ru.geekbrains.junior.home1.task1;
/*
Напишите программу, которая использует Stream API для обработки списка чисел.
Программа должна вывести на экран среднее значение всех четных чисел в списке.
 */

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        IntStream.range(1, 11)
                .filter(number -> number%2 == 0)
                .average()
                .ifPresent(System.out::println);

    }
}
