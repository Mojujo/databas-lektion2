package se.oscar;

import se.oscar.utility.JDBCUtil;
import se.oscar.utility.loggerUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class PersonService {

    private final PersonDAO personDAO;

    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public void addPerson(Person person) {
        if (person.getIncome() < 0) {
            throw new IllegalArgumentException("Income cannot be negative");
        }
        try (Connection conn = JDBCUtil.getConnection()) {
            personDAO.insertPerson(person, conn);
            JDBCUtil.commit(conn);
        } catch (SQLException e) {
            loggerUtil.logError("Error adding person", e);
        }
    }
}
