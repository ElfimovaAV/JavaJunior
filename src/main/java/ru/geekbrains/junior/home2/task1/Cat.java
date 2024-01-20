package ru.geekbrains.junior.home2.task1;

public class Cat extends Animal{
    private String name;
    private int age;
    private String color;

    public Cat(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }
    
    public void makeSound(){
        System.out.println(name + " мурчит на ухо хозяину");
    }

    public void catchMice(){
        System.out.println(name + " поймал мышку");
    }

    public int getAge() {
        return age;
    }

    public void printInfo() {
        System.out.printf("Кличка: %s; Возраст: %d; Окрас: %s\n", name, age, color);
    }
}
