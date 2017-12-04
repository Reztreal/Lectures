package main.fhms;

import main.common.Tools;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Nurses class manage nurse arraylists
 */
public class Nurses {
    private Scanner scan = new Scanner(System.in);
    private Tools tools = new Tools();

    private ArrayList<Nurse> nurses = new ArrayList<>();

    /**
     * Creates a new nurse by getting input from user and put new nurse
     * to the nurse arraylist.
     *
     * @return boolean True if created and added without any problem.
     */
    public boolean createAndAddNurse() {
        Nurse newNurse = createNurse();

        if (newNurse != null) {
            addNurse(newNurse);
            return true;
        }

        return false;
    }

    /**
     * Adds new nurse instance to the nurse arraylist.
     *
     * @param newNurse Nurse instance to be added.
     */
    public void addNurse(Nurse newNurse) {
        nurses.add(newNurse);
    }

    /**
     * Removes nurse according to its SSN.
     *
     * @param removedNurseSsn Removed nurse's SSN
     * @return boolean True if removing is successfull.
     */
    public boolean removeNurse(int removedNurseSsn) {
        Nurse removedNurse = getNurse(removedNurseSsn);

        if (removedNurse != null)
            return removeNurse(removedNurse);

        return false;
    }

    /**
     * Removes nurse from nurse arraylist.
     *
     * @param removedNurse Removed nurse instance.
     * @return boolean True if removing is successfull.
     */
    public boolean removeNurse(Nurse removedNurse) {
        return nurses.remove(removedNurse);
    }

    /**
     * Returns nurse instance according to given SSN.
     *
     * @param desiredNurseSsn Nurse's SSN.
     * @return Nurse Corresponding nurse instance. If not exists, return null.
     */
    public Nurse getNurse(int desiredNurseSsn) {
        for (Nurse nurse : nurses) {
            if (nurse.getSsn() == desiredNurseSsn)
                return nurse;
        }

        return null;
    }

    /**
     * Creates new nurse by getting input from user.
     *
     * @return Nurse New nurse.
     */
    public Nurse createNurse() {
        System.out.println("---> Create a new nurse");

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

        System.out.print("Enter nurse's department : ");
        String department = scan.nextLine();

        System.out.print("Enter salary of Nurse : ");
        float salary = scan.nextFloat();
        scan.nextLine();

        return new Nurse(ssn, name, gender, dateOfBirth, insuranceType, department, salary);
    }

    /**
     * Get SSN from user and prints corresponding nurse details.
     */
    public void printNurseDetailsBySsn() {
        System.out.println("---> Retrieve a nurse");
        System.out.print("Enter SSN : ");
        int ssn = scan.nextInt();
        scan.nextLine();

        Nurse nurse = getNurse(ssn);

        if (nurse != null) {
            nurse.printDetails();
            return;
        }

        System.out.println("ERROR! There is no nurse with given SSN.");
        return;
    }

    /**
     * Get SSN from user and removes corresponding nurse.
     */
    public void removeNurseBySsn() {
        System.out.println("---> Remove a nurse");
        System.out.print("Enter SSN : ");
        int ssn = scan.nextInt();
        scan.nextLine();

        Nurse nurse = getNurse(ssn);

        if (nurse != null) {
            removeNurse(nurse);
            return;
        }

        System.out.println("ERROR! There is no nurse with given SSN.");
        return;
    }

    /**
     * Spends annual leaves.
     *
     * @param ssn    Nurse's SSN that want to spend leave.
     * @param noDays Number of days to spend.
     * @return Boolean Return true if there is no problem.
     */
    public Boolean spendAnnualLeave(int ssn, int noDays) {
        Nurse nurse = getNurse(ssn);

        if (nurse != null) {
            nurse.goAnnualLeave(noDays);
            return true;
        }

        return false;
    }

    /**
     * Prints all nurse details.
     */
    public void printAllNurseDetails() {
        System.out.println("---> Print all nurses (" + nurses.size() + " total)");
        for (Nurse nurse : nurses) {
            nurse.printDetails();
            System.out.println("________________");
        }
    }

    /**
     * Calculates total nurse salaries.
     *
     * @return float Total nurse salaries.
     */
    public float getTotalNurseSalaries() {
        float total = 0;
        for (Nurse nurse : nurses) {
            total += nurse.getSalary();
        }

        return total;
    }


    // ----> GETTERS SETTERS
    public ArrayList<Nurse> getNurses() {
        return nurses;
    }

    public void setNurses(ArrayList<Nurse> nurses) {
        this.nurses = nurses;
    }

}
