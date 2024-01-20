package ru.geekbrains.junior.home3.task1;
/*
Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
Обеспечьте поддержку сериализации для этого класса.
Создайте объект класса Student и инициализируйте его данными.
Сериализуйте этот объект в файл.
Десериализуйте объект обратно в программу из файла.
Выведите все поля объекта, включая GPA, и ответьте на вопрос,
почему значение GPA не было сохранено/восстановлено.


 */
public class Main {
    public static void main(String[] args) {
        Student student = new Student("Anastasiya", 36, 4.9);
        StudentApp studentApp = new StudentApp();
        studentApp.saveStudentToFile(student);
        studentApp.loadTasksFromFile("student_data.bin");
    }
}
