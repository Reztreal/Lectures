package main.fhms;

import java.util.Date;

/**
 * Base class of Patient and Hospital.
 *
 * @author Furkan Tokac
 */
public class Person {

    protected int ssn;
    protected String name;
    protected char gender; // m : male, 0 : female
    protected Date dateOfBirth;

    /**
     * Can be created by entering necessary variables
     * @param t_ssn
     * @param t_name
     * @param t_gender
     * @param t_dateOfBirth
     */
    public Person(int t_ssn, String t_name, char t_gender, Date t_dateOfBirth) {
        ssn = t_ssn;
        name = t_name;
        gender = t_gender;
        dateOfBirth = t_dateOfBirth;
    }

    /**
     * Can be created by new person class
     * @param newPerson
     */
    public Person(Person newPerson) {
        ssn = newPerson.ssn;
        name = newPerson.name;
        gender = newPerson.gender;
        dateOfBirth = newPerson.dateOfBirth;
    }

    /**
     * Prints the details of the person
     */
    public void printDetails() {
        System.out.print("" +
                "SSN            : " + ssn + "\n" +
                "Name           : " + name + "\n" +
                "Gender         : " + gender + "\n" +
                "Date of birth  : " + dateOfBirth + "\n");
    }

    /**
     * Gets the ssn of the person
     * @return int
     */
    public int getSsn() {
        return ssn;
    }

    /**
     * Gets the name of the person
     * @return String
     */
    public String getName() {
        return name;
    }
}
