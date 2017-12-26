package main.fhms;

import java.util.Date;

/**
 * Patient class helds patient information
 *
 * @author Furkan Tokac
 * {@inheritDoc}
 */
public class Patient extends Person {
    private boolean firstVisitToHospital;
    MedicalRecords medicalRecords = new MedicalRecords();

    /**
     * @param t_ssn
     * @param t_name
     * @param t_gender
     * @param t_dateOfBirth
     * @param t_insuranceType
     * @param t_firstVisitToHospital
     */
    public Patient(int t_ssn, String t_name, char t_gender, Date t_dateOfBirth, int t_insuranceType,
                   boolean t_firstVisitToHospital) {
        super(t_ssn, t_name, t_gender, t_dateOfBirth, t_insuranceType);
        firstVisitToHospital = t_firstVisitToHospital;
    }

    /**
     * @param newPerson
     * @param t_firstVisitToHospital
     */
    public Patient(Person newPerson, boolean t_firstVisitToHospital) {
        super(newPerson);

        firstVisitToHospital = t_firstVisitToHospital;
    }

    /**
     * Prints details of patient.
     */
    @Override
    public void printDetails() {
        System.out.print("" +
                "SSN            : " + getSsn() + "\n" +
                "Name           : " + getName() + "\n" +
                "Gender         : " + getGender() + "\n" +
                "Date of birth  : " + getDateOfBirth() + "\n" +
                "Insurance      : " + getInsurance().getDescription() + "\n" +
                "First Visit    : " + (firstVisitToHospital ? "Yes" : "No") + "\n");
    }

    public String retrieveDetails() {
        String msg = "" +
                "SSN\t: " + getSsn() + "\n" +
                "Name\t: " + getName() + "\n" +
                "Gender\t: " + getGender() + "\n" +
                "Date of birth\t: " + getDateOfBirth() + "\n" +
                "Insurance\t: " + getInsurance().getDescription() + "\n" +
                "First Visit\t: " + (firstVisitToHospital ? "Yes" : "No") + "\n";

        return msg;
    }

    // ----> GETTERS SETTERS
    public boolean isFirstVisitToHospital() {
        return firstVisitToHospital;
    }

    public void setFirstVisitToHospital(boolean firstVisitToHospital) {
        this.firstVisitToHospital = firstVisitToHospital;
    }

    public MedicalRecords getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(MedicalRecords medicalRecords) {
        this.medicalRecords = medicalRecords;
    }
}
