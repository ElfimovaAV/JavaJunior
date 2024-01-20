package ru.geekbrains.junior.home3.task2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.geekbrains.junior.seminar3.task2.ToDoV2;

import java.io.*;
import java.util.List;

public class StudentApp {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public void saveStudentToFile(String fileName, Student student) {
        try {
            if (fileName.endsWith(".json")) {
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), student);
            } else if (fileName.endsWith(".xml")) {
                xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                xmlMapper.writeValue(new File(fileName), student);
            }
            System.out.println("Объект Student сериализован в файл " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadTasksFromFile(String fileName) {
        Student student = new Student();
        File file = new File(fileName);
        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    student = objectMapper.readValue(file, Student.class);
                } else if (fileName.endsWith(".xml")) {
                    student = xmlMapper.readValue(file, Student.class);
                }
                System.out.println("Объект Student десериализован из файла " + fileName);
                System.out.println("Имя: " + student.getName());
                System.out.println("Возраст: " + student.getAge());
                System.out.println("Пароль (должен быть 0.0, так как transient): " + student.getGPA());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
