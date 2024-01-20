package ru.geekbrains.junior.home3.task1;

import java.io.*;

public class StudentApp {

    public void saveStudentToFile(Student student) {
        try (FileOutputStream fileOut = new FileOutputStream("student_data.bin");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(student);
            System.out.println("Объект Student сериализован.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadTasksFromFile(String fileName) {
        Student student;
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            student = (Student) in.readObject();
            System.out.println("Объект Student десериализован.");
            System.out.println("Имя: " + student.getName());
            System.out.println("Возраст: " + student.getAge());
            System.out.println("Пароль (должен быть 0.0, так как transient): " + student.getGPA());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
