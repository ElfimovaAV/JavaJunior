package ru.geekbrains.junior.home3.task2;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int age;
    @JsonIgnore
    transient double GPA;

    public Student() {
    }

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    @JsonIgnore
    public double getGPA() {
        return GPA;
    }
}
