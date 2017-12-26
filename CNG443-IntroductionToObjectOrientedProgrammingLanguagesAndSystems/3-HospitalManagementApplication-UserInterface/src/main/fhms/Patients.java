package main.fhms;

import main.common.Tools;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Patients class manages patient arraylist
 */
public class Patients {
    private Scanner scan = new Scanner(System.in);
    private Tools tools = new Tools();

    private ArrayList<Patient> patients = new ArrayList<>();

    /**
     * Creates a new patient by getting input from user and put new patient
     * to the patient arraylist.
     *
     * @return boolean True if created and added without any problem.
     */
    public boolean createAndAddPatient() {
        Patient newPatient = createPatient();

        if (newPatient != null) {
            addPatient(newPatient);
            return true;
        }

        return false;
    }

    /**
     * Adds new patient instance to the patient arraylist.
     *
     * @param newPatient Patient instance to be added.
     */
    public void addPatient(Patient newPatient) {
        patients.add(newPatient);
    }

    /**
     * Removes patient according to its SSN.
     *
     * @param removedPatientSsn Removed patient's SSN
     * @return boolean True if removing is successfull.
     */
    public boolean removePatient(int removedPatientSsn) {
        Patient removedPatient = getPatient(removedPatientSsn);

        if (removedPatient != null)
            return removePatient(removedPatient);

        return false;
    }

    /**
     * Removes patient from patient arraylist.
     *
     * @param removedPatient Removed patient instance.
     * @return boolean True if removing is successfull.
     */
    public boolean removePatient(Patient removedPatient) {
        return patients.remove(removedPatient);
    }

    /**
     * Returns patient instance according to given SSN.
     *
     * @param desiredPatientSsn Patient's SSN.
     * @return Patient Corresponding patient instance. If not exists, return null.
     */
    public Patient getPatient(int desiredPatientSsn) {
        for (Patient patient : patients) {
            if (patient.getSsn() == desiredPatientSsn)
                return patient;
        }

        return null;
    }

    /**
     * Creates new patient by getting input from user.
     *
     * @return Patient New patient.
     */
    public Patient createPatient() {
        System.out.println("---> Create a new patient");

        System.out.print("Enter SSN : ");
        int ssn = scan.nextInt();
        scan.nextLine();

        System.out.print("Enter name : ");
        String name = scan.nextLine();

        System.out.print("Enter gender (m or f) : ");
        char gender;
        try {
            gender = scan.nextLine().charAt(0);
        } catch (Exception e) {
            System.out.println("ERROR! Please don't leave the gender empty. Try again.");
            return null;
        }

        System.out.print("Enter date of birth (dd.mm.yyyy) : ");
        Date dateOfBirth = tools.stringToDate(scan.nextLine());
        if (dateOfBirth == null) {
            System.out.println("ERROR! Format of the date is wrong. Try again.");
            return null;
        }

        System.out.print("Enter insurance type (1,2 or 3) : ");
        int insuranceType = scan.nextInt();
        scan.nextLine();

        return new Patient(ssn, name, gender, dateOfBirth, insuranceType, true);
    }

    /**
     * Get SSN from user and prints corresponding patient details.
     *
     * @return Patient Correcponding patient.
     */
    public Patient printPatientDetailsBySsn() {
        System.out.println("---> Retrieve a patient");
        System.out.print("Enter SSN : ");
        int ssn = scan.nextInt();
        scan.nextLine();

        Patient patient = getPatient(ssn);

        if (patient != null) {
            patient.printDetails();
            return patient;
        }

        System.out.println("ERROR! There is no patient with given SSN.");
        return null;
    }

    public String retrievePatientDetailsBySsn(int ssn) {
        Patient patient = getPatient(ssn);

        if (patient != null) {
            return patient.retrieveDetails();
        }

        return null;
    }

    /**
     * Prints all patient details.
     */
    public void printAllPatientDetails() {
        System.out.println("---> Print all patients (" + patients.size() + " total)");
        for (Patient patient : patients) {
            patient.printDetails();
            System.out.println("________________");
        }
    }

    public String retrieveAllPatientDetails() {
        String msg = "";
        for (Patient patient : patients) {
            msg += patient.retrieveDetails();
            msg += "________________\n";
        }

        return msg;
    }

    /**
     * Get SSN from user and removes corresponding patient.
     */
    public void removePatientBySsn() {
        System.out.println("---> Remove a patient");
        System.out.print("Enter SSN : ");
        int ssn = scan.nextInt();
        scan.nextLine();

        Patient patient = getPatient(ssn);

        if (patient != null) {
            removePatient(patient);
            return;
        }

        System.out.println("ERROR! There is no patient with given SSN.");
        return;
    }

    /**
     * Get SSN from user and print corresponding patient's department.
     *
     * @return Treatment Last treatment.
     */
    public Treatment printPatientDepartmentBySsn() {
        System.out.println("---> Retrieve a patient department");
        System.out.print("Enter SSN : ");
        int ssn = scan.nextInt();
        scan.nextLine();

        Patient patient = getPatient(ssn);

        if (patient != null) {
            MedicalRecord medicalRecord = patient.getMedicalRecords().getLatestMedicalRecord();

            if (medicalRecord != null) {
                Treatment treatment = medicalRecord.getTreatments().getLatestTreatment();

                if (treatment != null) {
                    treatment.printDetails();
                    return treatment;
                } else {
                    System.out.println("ERROR! There is no treatment for given SSN.");
                    return null;
                }
            } else {
                System.out.println("ERROR! There is no medical record for given SSN.");
                return null;
            }
        }

        System.out.println("ERROR! There is no patient with given SSN.");
        return null;
    }

    public String retrievePatientDepartmentBySsn(int ssn) {
        Patient patient = getPatient(ssn);

        if (patient != null) {
            MedicalRecord medicalRecord = patient.getMedicalRecords().getLatestMedicalRecord();

            if (medicalRecord != null) {
                Treatment treatment = medicalRecord.getTreatments().getLatestTreatment();

                if (treatment != null) {
                    return treatment.retrieveDetails();
                } else {
                    System.out.println("ERROR! There is no treatment for given SSN.");
                    return null;
                }
            } else {
                System.out.println("ERROR! There is no medical record for given SSN.");
                return null;
            }
        }

        System.out.println("ERROR! There is no patient with given SSN.");
        return null;
    }

    /**
     * Prints all treatments of all users.
     */
    public void printAllTreatments() {
        for (Patient patient : patients) {
            patient.getMedicalRecords().printAllMedicalRecords();
        }
    }

    /**
     * Calculates all incomes done by treatments.
     *
     * @return float Total income.
     */
    public float treatmentIncome() {
        float total = 0;
        for (Patient patient : patients) {
            total += patient.getMedicalRecords().medicalRecordsIncome(patient.getInsurance());
        }
        return total;
    }

    // ----> GETTERS SETTERS
    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }

}
