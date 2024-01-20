package ru.geekbrains.junior.home3.task2;

/*

2. * Выполнить задачу 1 используя другие типы сериализаторов (в xml и json документы).

 */
public class Main {
    public static void main(String[] args) {
        Student student = new Student("Anastasiya", 36, 4.9);
        StudentApp studentApp = new StudentApp();
        studentApp.saveStudentToFile("student_data.json", student);
        studentApp.saveStudentToFile("student_data.xml", student);
        studentApp.loadTasksFromFile("student_data.json");
        studentApp.loadTasksFromFile("student_data.xml");
    }
}
