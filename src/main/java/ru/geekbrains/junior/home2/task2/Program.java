package ru.geekbrains.junior.home2.task2;

import java.util.UUID;

public class Program {

    /*
    Дополнительная задача:

Доработайте метод генерации запроса на удаление объекта из таблицы БД (DELETE FROM <Table> WHERE ID = '<id>')
В классе QueryBuilder который мы разработали на семинаре.
    */

    public static void main(String[] args) throws IllegalAccessException {

        Employee user = new Employee("Stanislav", "sample@gmail.com");
        UUID pk = UUID.randomUUID();

        QueryBuilder queryBuilder = new QueryBuilder();

        // Генерация SQL-запроса для вставки
        String insertQuery = queryBuilder.buildInsertQuery(user);
        System.out.println("Insert Query: " + insertQuery);


        // Генерация SQL-запроса для выборки
        String selectQuery = queryBuilder.buildSelectQuery(Employee.class, pk);
        System.out.println("Select Query: " + selectQuery);

        user.setEmail("test@gmail.com");

        // Генерация SQL-запроса для обновления
        String updateQuery = queryBuilder.buildUpdateQuery(user);
        System.out.println("Update Query: " + updateQuery);

        // Генерация SQL-запроса для удаления
        String deleteQuery = queryBuilder.buildDeleteQuery(Employee.class, user.getId());
        System.out.println("Delete Query: " + deleteQuery);

    }

}
