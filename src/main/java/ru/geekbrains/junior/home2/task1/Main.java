package ru.geekbrains.junior.home2.task1;
/*
Задача 1:
Создайте абстрактный класс "Animal" с полями "name" и "age".
Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
Выведите на экран информацию о каждом объекте.
Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
 */

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        Animal[] animals = {new Dog("Барон", 4, "Сторожевая"),
                new Dog("Молния", 8, "Охотничья"),
                new Cat("Пушок", 7, "Рыжий"),
                new Cat("Багира", 1, "Черный")};

        for (Animal animal : animals) {
            Field ageField = animal.getClass().getDeclaredField("age");
            ageField.setAccessible(true);
            int tmp = ageField.getInt(animal);
            ageField.setInt(animal, tmp + 1);
        }


        System.out.println("Вывод на экран информации о каждом объекте:");
        for (Animal animal : animals) {
            try {
                Method printInfoMethod = animal.getClass().getMethod(("printInfo"));
                printInfoMethod.invoke(animal);
            } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException ignored) {
            }
        }
        System.out.println();
        System.out.println("Вызов метода \"makeSound()\":");
        for (Animal animal : animals) {
            try {
                Method makeSoundMethod = animal.getClass().getMethod("makeSound");
                makeSoundMethod.invoke(animal);
            } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException ignored) {
            }
        }

    }
}

