package main.fhms;

import main.common.Tools;


import java.util.ArrayList;
import java.util.Date;

/**
 * Patient class
 *
 * @author Furkan Tokac
 * {@inheritDoc}
 */
public class Patient extends Person {
    private Tools tools = new Tools();
    private float bloodPressure;
    private ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();

    /**
     * Patient can be created by direclty entering necessary variables
     * @param t_ssn
     * @param t_name
     * @param t_gender
     * @param t_dateOfBirth
     * @param t_bloodPressure
     */
    public Patient(int t_ssn, String t_name, char t_gender, Date t_dateOfBirth, float t_bloodPressure) {
        super(t_ssn, t_name, t_gender, t_dateOfBirth);
        bloodPressure = t_bloodPressure;
    }

    /**
     * Patient can be created with a person and additionally with a blood pressure of patient
     * @param newPerson
     * @param t_bloodPressure
     */
    public Patient(Person newPerson, float t_bloodPressure) {
        super(newPerson);
        bloodPressure = t_bloodPressure;
    }

    /**
     * Print details of patient
     */
    @Override
    public void printDetails() {
        System.out.print("" +
                "SSN            : " + ssn + "\n" +
                "Name           : " + name + "\n" +
                "Gender         : " + gender + "\n" +
                "Date of birth  : " + dateOfBirth + "\n" +
                "Blood pressure : " + bloodPressure + "\n");
    }

    /**
     * Adding new medical record to medical records by a date
     * @param recordDate
     */
    public void addMedicalRecord(Date recordDate) {
        MedicalRecord newRecord = new MedicalRecord(recordDate);
        medicalRecords.add(newRecord);
    }

    /**
     * Adding new medical record to medical records
     * @param newRecord
     */
    public void addMedicalRecord(MedicalRecord newRecord) {
        medicalRecords.add(newRecord);
    }

    /**
     * Gets the latest medical record in the medical records
     * @return MedicalRecord Latest medical record
     */
    public MedicalRecord getLatestMedicalRecord() {
        if (medicalRecords.size() == 0)
            return null;
        return getMedicalRecord(medicalRecords.size() - 1);
    }

    /**
     * Gets the medical record in medical records according to index
     * @param index
     * @return MedicalRecord
     */
    public MedicalRecord getMedicalRecord(int index) {
        if (index < 0)
            return null;
        return medicalRecords.get(index);
    }

    /**
     * Gets all medical records
     * @return ArrayList<MedicalRecord>
     */
    public ArrayList<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

}
