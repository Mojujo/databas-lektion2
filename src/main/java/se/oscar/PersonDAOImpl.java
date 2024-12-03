package se.oscar;

import se.oscar.utility.JDBCUtil;
import se.oscar.utility.loggerUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {

    @Override
    public void insertPerson(Person person, Connection conn) {
        String sql = "INSERT INTO PERSON (first_name, last_name, gender, dob, income) " +
                     "VALUES (?,?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            statementParameter(person, pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            loggerUtil.logError("Error inserting person", e);
        }
    }

    @Override
    public void updatePerson(Person person, Connection conn) {
        String sql = "UPDATE PERSON SET first_name = ?, last_name = ?, gender = ?, dob = ?, income = ? " +
                     "WHERE person_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            statementParameter(person, pstmt);
            pstmt.setInt(6, person.getPersonId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            loggerUtil.logError("Error updating person", e);
        }
    }

    public void statementParameter(Person person, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, person.getFirstName());
        pstmt.setString(2, person.getLastName());
        pstmt.setString(3, person.getGender());
        if (person.getDob() != null) {
            pstmt.setDate(4, person.getDob());
        } else {
            pstmt.setNull(4, Types.DATE);
        }
        pstmt.setDouble(5, person.getIncome());
    }

    @Override
    public void deletePerson(int id) {
        String sql = "DELETE FROM PERSON WHERE person_id = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            loggerUtil.logError("Error deleting person", e);
        }
    }

    @Override
    public Person getPerson(int id) {
        String sql = "SELECT * FROM PERSON WHERE person_id = ?";
        Person person = null;
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    person = new Person(
                            id,
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("gender"),
                            rs.getDate("dob"),
                            rs.getInt("income")
                    );
                }
            }
        } catch (SQLException e) {
            loggerUtil.logError("Error retrieving person", e);
        }
        return person;
    }

    @Override
    public List<Person> getPersons() throws SQLException {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM PERSON";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Person person = new Person(
                        rs.getInt("person_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("gender"),
                        rs.getDate("dob"),
                        rs.getInt("income")
                );
                persons.add(person);
            }
        }
        return persons;
    }
}
