package main.fhms;

import main.common.Tools;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * This is main hospital management class.
 *
 * @author Furkan Tokac
 */
public class Fhms {
    private Tools tools = new Tools();
    private Scanner scan = new Scanner(System.in);
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Patient> patients = new ArrayList<>();

    /**
     * Construction just prints the "Welcome" message to the console
     */
    public Fhms() {
        System.out.println("_Welcome Faunus Hospital Management System_\n");
    }

    /**
     * The program start running by this function.
     *
     * @return int Returns the exit status of the function.
     */
    public int run() {
        int choice = 0;

        while (choice != 99) {
            choice = menu();

            System.out.println();

            if (choice == 10) {
                Doctor newDoctor = createNewDoctor();
                if (newDoctor != null)
                    addDoctor(newDoctor);
            } else if (choice == 11) {
                printAllDoctors();
            } else if (choice == 12) {
                Doctor t_doctor = getDoctorWithSsn();
                if (t_doctor != null)
                    t_doctor.printDetails();
            } else if (choice == 19) {
                deleteDoctorWithSsn();
            } else if (choice == 20) {
                Patient newPatient = createNewPatient();
                if (newPatient != null)
                    addPatient(newPatient);
            } else if (choice == 21) {
                printAllPatients();
            } else if (choice == 22) {
                Patient t_patient = getPatientWithSsn();
                if (t_patient != null)
                    t_patient.printDetails();
            } else if (choice == 23) {
                Patient t_patient = getPatientWithSsn();
                MedicalRecord t_medicalRecord;
                if (t_patient != null) {
                    t_medicalRecord = t_patient.getLatestMedicalRecord();
                    if (t_medicalRecord != null)
                        t_medicalRecord.printDetails();
                }
            } else if (choice == 29) {
                deletePatientWithSsn();
            } else if (choice == 90) {
                printAllTreatmentsInTheHospital();
            } else if (choice == 99) {
                break;
            } else {
                System.out.println("ERROR! Unknown choice. Try again.");
            }

            System.out.println();
        }

        return 0;
    }

    /**
     * Print all treatment data in about the hospital.
     */
    public void printAllTreatmentsInTheHospital() {
        System.out.println("---> Print all treatment records");
        for (Patient patient : patients) {
            System.out.println("-> Records for " + patient.getName());
            for (MedicalRecord medicalRecord : patient.getMedicalRecords()) {
                medicalRecord.printAllTreatments();
            }
            System.out.println(" ");
        }
    }

    /**
     * @return int Exit status of the function. 0 means no error, 1 means error.
     */
    public int deletePatientWithSsn() {
        System.out.println("---> Delete a patient");
        System.out.print("Enter SSN : ");
        int ssn = scan.nextInt();
        scan.nextLine();

        Patient t_patient = getPatient(ssn);

        if (t_patient == null) {
            System.out.println("ERROR! There is no patient with given SSN.");
            return 1;
        }

        removePatient(t_patient);
        System.out.println("Patient successfully deleted.");
        return 0;
    }

    /**
     * Delete given Patient object from the patients list if it exists.
     *
     * @param deletedPatient
     */
    public void removePatient(Patient deletedPatient) {
        patients.remove(deletedPatient);
    }

    /**
     * Create patient by getting inputs from console and return the patient.
     *
     * @return Patient
     */
    public Patient createNewPatient() {
        Person newPerson = createNewPerson();

        if (newPerson == null)
            return null;

        System.out.print("Enter blood pressure : ");
        float bloodPressure = scan.nextFloat();

        return new Patient(newPerson, bloodPressure);
    }

    /**
     * Add patient to the patients list.
     *
     * @param newPatient
     */
    public void addPatient(Patient newPatient) {
        patients.add(newPatient);
    }

    /**
     * Print detailed informations of all patients.
     */
    public void printAllPatients() {
        System.out.println("---> Print all patients (" + patients.size() + " total)");
        for (Patient i : patients) {
            i.printDetails();
            System.out.println("________________");
        }
    }

    /**
     * @return
     */
    public Patient getPatientWithSsn() {
        System.out.println("---> Retrieve a patient");
        System.out.print("Enter SSN : ");
        int ssn = scan.nextInt();
        scan.nextLine();

        Patient t_patient = getPatient(ssn);

        if (t_patient == null) {
            System.out.println("ERROR! There is no patient with given SSN.");
            return null;
        }

        return t_patient;
    }

    /**
     * @param ssn
     * @return
     */
    public Patient getPatient(int ssn) {
        for (Patient i : patients) {
            if (i.getSsn() == ssn) {
                return i;
            }
        }
        return null;
    }

    /**
     * @return
     */
    public Doctor getDoctorWithSsn() {
        System.out.println("---> Retrieve a doctor");
        System.out.print("Enter SSN : ");
        int ssn = scan.nextInt();
        scan.nextLine();

        Doctor t_doctor = getDoctor(ssn);

        if (t_doctor == null) {
            System.out.println("ERROR! There is no doctor with given SSN.");
            return null;
        }

        return t_doctor;
    }

    /**
     * @param ssn
     * @return
     */
    public Doctor getDoctor(int ssn) {
        for (Doctor i : doctors) {
            if (i.getSsn() == ssn) {
                return i;
            }
        }
        return null;
    }

    /**
     * @return
     */
    public int deleteDoctorWithSsn() {
        System.out.println("---> Delete a doctor");
        System.out.print("Enter SSN : ");
        int ssn = scan.nextInt();
        scan.nextLine();

        Doctor t_doctor = getDoctor(ssn);

        if (t_doctor == null) {
            System.out.println("ERROR! There is no doctor with given SSN.");
            return 1;
        }

        deleteDoctor(t_doctor);
        System.out.println("Doctor successfully deleted.");
        return 0;
    }

    /**
     * @param deletedDoctor
     */
    public void deleteDoctor(Doctor deletedDoctor) {
        doctors.remove(deletedDoctor);
    }

    public Doctor createNewDoctor() {
        System.out.println("---> Create a new doctor");
        Person newPerson = createNewPerson();

        if (newPerson == null)
            return null;

        Doctor newDoctor = new Doctor(newPerson);
        return newDoctor;
    }

    /**
     * @param newDoctor
     */
    public void addDoctor(Doctor newDoctor) {
        doctors.add(newDoctor);
    }

    /**
     *
     */
    public void printAllDoctors() {
        System.out.println("---> Print all doctors (" + doctors.size() + " total)");
        for (Doctor i : doctors) {
            i.printDetails();
            System.out.println("________________");
        }
    }

    /**
     * @return
     */
    public Person createNewPerson() {
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

        return new Person(ssn, name, gender, dateOfBirth);
    }

    /**
     * @return
     */
    public int menu() {
        System.out.print("" +
                "____________| MENU |____________\n" +
                "______| Doctor Options\n" +
                "10) Create and save a new doctor\n" +
                "11) Print all doctors\n" +
                "12) Retrieve and print details of a doctor\n" +
                "19) Delete a doctor\n" +
                "______| Patient Options\n" +
                "20) Create and save a new patient\n" +
                "21) Print all patients\n" +
                "22) Retrieve and print details of a patient\n" +
                "23) Retrieve and print latest medical record of a patient\n" +
                "29) Delete a patient\n" +
                "______| Program Options\n" +
                "90) Retrieve and print all treatments\n" +
                "99) Exit\n" +
                "---------> Choice : ");

        int choice = scan.nextInt();
        scan.nextLine();

        return choice;
    }
}
