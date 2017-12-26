package main.fhms;

import java.util.Date;

/**
 * Abstract Person class.
 */
public abstract class Person {
    private int ssn;
    private String name;
    private char gender; // m : male, 0 : female
    private Date dateOfBirth;
    private Insurance insurance;

    /**
     * @param t_ssn
     * @param t_name
     * @param t_gender
     * @param t_dateOfBirth
     * @param t_insuranceType
     */
    public Person(int t_ssn, String t_name, char t_gender, Date t_dateOfBirth, int t_insuranceType) {
        ssn = t_ssn;
        name = t_name;
        gender = t_gender;
        dateOfBirth = t_dateOfBirth;
        insurance = new Insurance(t_insuranceType);
    }

    /**
     * @param newPerson
     */
    public Person(Person newPerson) {
        ssn = newPerson.getSsn();
        name = newPerson.getName();
        gender = newPerson.getGender();
        dateOfBirth = newPerson.getDateOfBirth();
        insurance = newPerson.getInsurance();
    }

    /**
     * Abstract print details.
     */
    public abstract void printDetails();


    // ----> GETTERS SETTERS
    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
}
