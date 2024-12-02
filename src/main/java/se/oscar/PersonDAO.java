package se.oscar;

import java.sql.SQLException;
import java.util.List;

public interface PersonDAO {
    void insertPerson(Person person) throws SQLException;
    void updatePerson(Person person) throws SQLException;
    void deletePerson(int id) throws SQLException;
    Person getPerson(int id) throws SQLException;
    List<Person> getPersons() throws SQLException;
}
