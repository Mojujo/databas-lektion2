package se.oscar;

import se.oscar.utility.loggerUtil;

import java.sql.SQLException;
import java.util.Scanner;

public class Directory {
    PersonDAO personDAO = new PersonDAOImpl();
    Scanner scanner = new Scanner(System.in);
    boolean run = true;
    Person person;

    public void start() {
        while (run) {
            options();
            run = processInput();
        }
    }

    public void startMessage() {

    }

    private boolean processInput() {
        String choice = scanner.nextLine().toLowerCase();
        switch (choice) {
            case "add" -> addPerson(createPerson());
            case "delete" -> {
                System.out.println("Enter ID to delete: ");
                deletePerson(scanner.nextInt());
            }
            case "view" -> {
                System.out.println("Enter ID to view: ");
                viewPerson(scanner.nextInt());
            }
            case "list" -> listPersons();
            case "exit" -> {
                return false;
            }
            default -> {
                System.out.println("Bad Input");
            }
        }
        return true;
    }

    private Person createPerson() {
        System.out.println("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter gender(M/F): ");
        String gender = scanner.next().toUpperCase();
        System.out.println("Enter date of birth(yyyy-mm-dd): ");
        String dob = scanner.nextLine();
        System.out.println("Enter income: ");
        double income = scanner.nextDouble();
        return person = new Person(firstName, lastName, gender, dob, income);
    }

    private void addPerson(Person person) {
        try {
            personDAO.insertPerson(person);
        } catch (SQLException e) {
            loggerUtil.logError("Directory add error", e);
        }
    }

    private void deletePerson(int id) {
        try {
            personDAO.deletePerson(id);
        } catch (SQLException e) {
            loggerUtil.logError("Directory delete error", e);
        }
    }

    private void updatePerson(Person person) {

    }

    private void viewPerson(int id) {

    }

    private void listPersons() {

    }

    private void options() {

    }
}
