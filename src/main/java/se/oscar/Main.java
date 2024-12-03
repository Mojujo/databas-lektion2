package se.oscar;

import se.oscar.utility.JDBCUtil;
import se.oscar.utility.loggerUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        PersonDAO personDAO = new PersonDAOImpl();
        PersonService service = new PersonService(personDAO);
        Person person = new Person("Anna", "Törnqvist", "F", "1905-03-23", 12345);

        service.addPerson(person);
    }
}