package se.oscar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import se.oscar.utility.JDBCUtil;
import se.oscar.utility.loggerUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonDAOImplTest {
    Person person;
    Person person2;
    PersonDAO personDAO = new PersonDAOImpl();

    @AfterEach
    public void cleanUp() {
        try (Connection conn = JDBCUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("DROP TABLE IF EXISTS person");
        } catch (SQLException e) {
            loggerUtil.logError("Error cleaning up test", e);
        }
        person = null;
        person2 = null;
    }

    @Test
    public void addPersonTest() {
        person = new Person("Test", "Törnqvist", "M", "2001-07-01", 100);
        try (Connection conn = JDBCUtil.getConnection()) {
            personDAO.insertPerson(person, conn);
            JDBCUtil.commit(conn);

            person2 = personDAO.getPerson(1);
            assertEquals("Test", person2.getFirstName());

        } catch (SQLException e) {
            loggerUtil.logError("Error inserting person", e);
        }
    }

    @Test
    public void getPersonsUpdateTest() {
        List<Person> persons;
        person = new Person("Test", "Törnqvist", "M", "2001-07-01", 100);
        try (Connection conn = JDBCUtil.getConnection()) {
            personDAO.insertPerson(person, conn);
            JDBCUtil.commit(conn);

            persons = personDAO.getPersons();
            person = personDAO.getPerson(1);
            System.out.println(persons.getFirst());

            assertEquals("Test", person.getFirstName());

            person.setFirstName("Update");
            person.setIncome(5000);
            personDAO.updatePerson(person, conn);
            JDBCUtil.commit(conn);

            persons = personDAO.getPersons();
            System.out.println(persons.getFirst());

            assertEquals("Update", personDAO.getPerson(1).getFirstName());

        } catch (SQLException e) {
            loggerUtil.logError("Error getting persons", e);
        }
    }
}
