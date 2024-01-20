package ru.geekbrains.junior.home2.task1;

public class Dog extends Animal{
    private String name;
    private int age;
    private String purpose;

    public Dog(String name, int age, String purpose) {
        this.name = name;
        this.age = age;
        this.purpose = purpose;
    }

    public int getAge() {
        return age;
    }

    public void makeSound(){
        if(this.purpose == "Сторожевая") {
            System.out.println(name + " предупреждает лаем о приходе посетителя");
        } else if (this.purpose == "Охотничья") {
            System.out.println(name + " подает сигнал о пойманном следе");
        } else if (this.purpose == "Компаньон") {
            System.out.println(name + " радостным лаем встречает хозяина");
        } else {
            System.out.println(name + " угрожающе рычит");
        }
    }

    public void gnawingOnABone(){
        System.out.println(name + " грызет сладкую косточку");
    }

    public void printInfo() {
        System.out.printf("Кличка: %s; Возраст: %d; Назначение породы: %s\n", name, age, purpose);
    }
}
