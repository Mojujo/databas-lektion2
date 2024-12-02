package se.oscar;

import java.sql.Date;
import java.util.Objects;

public class Person {
    private int personId;
    private String firstName;
    private String lastName;
    private String gender;
    private java.sql.Date dob;
    private double income;

    public Person(int personId, String firstName, String lastName, String gender, Date dob, double income) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.income = income;
    }

    public Person(String firstName, String lastName, String gender, String dob, double income) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = java.sql.Date.valueOf(dob);
        this.income = income;
    }

    public int getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public double getIncome() {
        return income;
    }

    public Date getDob() {
        return dob;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDob(String dob) {
        this.dob = java.sql.Date.valueOf(dob);
    }

    public void setIncome(int income) {
        this.income = income;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId && Double.compare(income, person.income) == 0 && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(gender, person.gender) && Objects.equals(dob, person.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, firstName, lastName, gender, income, dob);
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", income=" + income +
                '}';
    }
}
