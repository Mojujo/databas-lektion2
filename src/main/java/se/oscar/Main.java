package se.oscar;

import se.oscar.utility.JDBCUtil;
import se.oscar.utility.loggerUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        PersonDAO personDAO = new PersonDAOImpl();
        Connection conn = JDBCUtil.getConnection();
        Person person = new Person("Gandalf", "Törnqvist", "M", "1905-03-23", 12345);
        Person person2;

        try {
            personDAO.insertPerson(person, conn);


        } catch (SQLException e) {
            loggerUtil.logError("Error", e);
        }
    }
}