package main.fhms;

import java.util.Scanner;

/**
 * This is main hospital management class.
 *
 * @author Furkan Tokac
 */
public class Fhms {
    private Scanner scan = new Scanner(System.in);

    public Doctors doctors = new Doctors();
    public Nurses nurses = new Nurses();
    public Patients patients = new Patients();


    /**
     * Constructor prints pre information about the program.
     */
    public Fhms() {
        System.out.println("_Welcome Faunus Hospital Management System V2.0 BETA_\n");
    }


    /**
     * The main program start running by this function.
     *
     * @return int Returns the exit status of the function.
     */

    public int run() {
        int choice = 0;

        while (choice != 99) {
            choice = menu();

            System.out.println();

            if (choice == 10) {
                doctors.createAndAddSenior();
            } else if (choice == 11) {
                doctors.createAndAddJunior();
            } else if (choice == 12) {
                doctors.printDoctorDetailsBySsn();
            } else if (choice == 13) {
                doctors.getPromotedBySsn();
            } else if (choice == 18) {
                doctors.printAllDoctorDetails();
            } else if (choice == 19) {
                doctors.removeDoctorBySsn();
            } else if (choice == 20) {
                patients.createAndAddPatient();
            } else if (choice == 21) {
                patients.printPatientDetailsBySsn();
            } else if (choice == 22) {
                patients.printPatientDepartmentBySsn();
            } else if (choice == 28) {
                patients.printAllPatientDetails();
            } else if (choice == 29) {
                patients.removePatientBySsn();
            } else if (choice == 30) {
                nurses.createAndAddNurse();
            } else if (choice == 31) {
                nurses.printNurseDetailsBySsn();
            } else if (choice == 38) {
                nurses.printAllNurseDetails();
            } else if (choice == 39) {
                nurses.removeNurseBySsn();
            } else if (choice == 90) {
                patients.printAllTreatments();
            } else if (choice == 91) {
                spendAnnualLeaveBySsn();
            } else if (choice == 92) {
                System.out.println("All treatments income : " + patients.treatmentIncome());
            } else if (choice == 93) {
                budgetStatus();
            } else if (choice == 99) {
                break;
            } else {
                System.out.println("ERROR! Unknown choice. Try again.");
            }

            System.out.println();
        }


        return 0; // means finished without any problem
    }

    /**
     * Calculates total salary of all employes.
     *
     * @return float Total salary of all employees.
     */
    public float getTotalSalaries() {
        return doctors.getTotalDoctorSalaries() + nurses.getTotalNurseSalaries();
    }

    /**
     * Print outs current budget status of the hostpital.
     */
    public void budgetStatus() {
        System.out.println("---> Budget status");

        float totalIncome = patients.treatmentIncome();
        float totalSalaries = getTotalSalaries();

        System.out.println("Treatment incomes : " + totalIncome);
        System.out.println("Total salaries    : " + totalSalaries);
        System.out.println("Budget status     : " + (totalIncome - totalSalaries));
    }

    /**
     * Get input from user and according to given information, spend some annual leave of an employee.
     */
    public void spendAnnualLeaveBySsn() {
        System.out.println("---> Spend annual leave");
        System.out.print("Enter SSN : ");
        int ssn = scan.nextInt();
        scan.nextLine();

        System.out.print("Enter number of leave days : ");
        int noDays = scan.nextInt();
        scan.nextLine();

        if (doctors.spendAnnualLeave(ssn, noDays))
            return;

        if (nurses.spendAnnualLeave(ssn, noDays))
            return;

        System.out.println("ERROR! There is no employee with given SSN.");
    }

    /**
     * Prints hard-coded text menu.
     *
     * @return int Get choice from user and returns it.
     */
    public int menu() {
        System.out.print("" +
                "____________| MENU |____________\n" +
                "______| Doctor Options\n" +
                "10) Create and save a new senior doctor\n" +
                "11) Create and save a new junior doctor\n" +
                "12) Get details of a doctor by SSN\n" +
                "13) Promote a junior doctor by SSN\n" +
                "18) Print all doctors\n" +
                "19) Delete a doctor\n" +
                "______| Patient Options\n" +
                "20) Create and save a new patient\n" +
                "21) Get details of a patient by SSN\n" +
                "22) Get patient department\n" +
                "28) Print all patients\n" +
                "29) Delete a patient\n" +
                "______| Nurse Options\n" +
                "30) Create and save a new nurse\n" +
                "31) Get details of a nurse by SSN\n" +
                "38) Print all nurses\n" +
                "39) Delete a nurse\n" +
                "______| Program Options\n" +
                "90) Print all treatments\n" +
                "91) Spend annual leave\n" +
                "92) Get treatment income\n" +
                "93) Budget status\n" +
                "99) Exit\n" +
                "---------> Choice : ");

        int choice = scan.nextInt();
        scan.nextLine();

        return choice;
    }
}
