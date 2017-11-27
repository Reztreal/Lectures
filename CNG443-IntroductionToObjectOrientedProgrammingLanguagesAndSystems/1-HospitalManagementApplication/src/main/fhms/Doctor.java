package main.fhms;

import java.util.Date;

/**
 * Doctor class
 *
 * @author Furkan Tokac
 * {@inheritDoc}
 */
public class Doctor extends Person {
    /**
     * Constructor of Doctor class that takes each parameters seperated.
     *
     * @param t_ssn
     * @param t_name
     * @param t_gender
     * @param t_dateOfBirth
     */
    public Doctor(int t_ssn, String t_name, char t_gender, Date t_dateOfBirth) {
        super(t_ssn, t_name, t_gender, t_dateOfBirth);
    }

    /**
     * Constructor of Doctor class that constructed with Person.
     *
     * @param newPerson
     */
    public Doctor(Person newPerson) {
        super(newPerson);
    }
}
