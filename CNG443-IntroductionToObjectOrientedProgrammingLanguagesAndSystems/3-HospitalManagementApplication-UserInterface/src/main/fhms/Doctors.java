package main.fhms;

import main.common.Tools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Doctors class manage senior doctor and junior doctor arraylists
 */
public class Doctors {
    private Scanner scan = new Scanner(System.in);
    private Tools tools = new Tools();

    private ArrayList<SeniorDoctor> seniors = new ArrayList<>();
    private ArrayList<JuniorDoctor> juniors = new ArrayList<>();

    /**
     * Creates a new junior by getting input from user and put new junior
     * to the junior doctor arraylist.
     *
     * @return boolean True if created and added without any problem.
     */
    public boolean createAndAddJunior() {
        JuniorDoctor newJunior = createJunior();

        if (newJunior != null) {
            addJunior(newJunior);
            return true;
        }

        return false;
    }

    /**
     * Creates new junior by getting input from user.
     *
     * @return JuniorDoctor New junior doctor.
     */
    public JuniorDoctor createJunior() {
        System.out.println("---> Create a new junior doctor");

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

        System.out.print("Enter specialization : ");
        String specialization = scan.nextLine();

        System.out.print("Enter doctor's department : ");
        String department = scan.nextLine();

        System.out.print("Enter job start date (dd.mm.yyyy) : ");
        Date startDate = tools.stringToDate(scan.nextLine());
        if (startDate == null) {
            System.out.println("ERROR! Format of the date is wrong. Try again.");
            return null;
        }

        System.out.print("Enter expected end date of the job (dd.mm.yyyy) : ");
        Date endDate = tools.stringToDate(scan.nextLine());
        if (endDate == null) {
            System.out.println("ERROR! Format of the date is wrong. Try again.");
            return null;
        }

        System.out.print("Enter SSN of responsible Senior : ");
        int responsibleDoctorSsn = scan.nextInt();
        scan.nextLine();
        SeniorDoctor responsibleDoctor = getSenior(responsibleDoctorSsn);
        if (responsibleDoctor == null) {
            System.out.println("ERROR! SSN couldn't found. Try again.");
            return null;
        }

        System.out.print("Enter salary of Junior : ");
        float salary = scan.nextFloat();
        scan.nextLine();

        return new JuniorDoctor(ssn, name, gender, dateOfBirth, insuranceType, specialization,
                department, startDate, endDate, responsibleDoctor, salary);
    }

    /**
     * Removes junior according to its SSN.
     *
     * @param removedJuniorSsn Removed junior's SSN
     * @return boolean True if removing is successfull.
     */
    public boolean removeJunior(int removedJuniorSsn) {
        JuniorDoctor removedJunior = getJunior(removedJuniorSsn);

        if (removedJunior != null)
            return removeJunior(removedJunior);

        return false;
    }

    /**
     * Removes junior from junior arraylist.
     *
     * @param removedJunior Removed junior instance.
     * @return boolean True if removing is successfull.
     */
    public boolean removeJunior(JuniorDoctor removedJunior) {
        return juniors.remove(removedJunior);
    }

    /**
     * Adds new junior instance to the junior arraylist.
     *
     * @param newJunior Junior instance to be added.
     */
    public void addJunior(JuniorDoctor newJunior) {
        juniors.add(newJunior);
    }

    /**
     * Returns junior instance according to given SSN.
     *
     * @param desiredJuniorSsn Junior's SSN.
     * @return JuniorDoctor Corresponding junior instance. If not exists, return null.
     */
    public JuniorDoctor getJunior(int desiredJuniorSsn) {
        for (JuniorDoctor junior : juniors) {
            if (junior.getSsn() == desiredJuniorSsn)
                return junior;
        }

        return null;
    }

    /**
     * Promotes junior to senior.
     *
     * @param desiredJuniorSsn Promoted junior's SSN
     */
    public void getPromoted(int desiredJuniorSsn) {
        JuniorDoctor desiredJunior = getJunior(desiredJuniorSsn);

        if (desiredJunior == null)
            return;

        getPromoted(desiredJunior);
    }

    /**
     * Promotes junior to senior.
     *
     * @param desiredJunior Promoted junior's instance.
     */
    public void getPromoted(JuniorDoctor desiredJunior) {
        SeniorDoctor promotedJunior = new SeniorDoctor(desiredJunior);
        removeJunior(desiredJunior);
        addSenior(promotedJunior);
    }


    /**
     * Creates a new senior by getting input from user and put new senior
     * to the senior doctor arraylist.
     *
     * @return boolean True if created and added without any problem.
     */
    public boolean createAndAddSenior() {
        SeniorDoctor newSenior = createSenior();

        if (newSenior != null) {
            addSenior(newSenior);
            return true;
        }

        return false;
    }

    /**
     * Adds new senior instance to the senior arraylist.
     *
     * @param newSenior Senior instance to be added.
     */
    public void addSenior(SeniorDoctor newSenior) {
        seniors.add(newSenior);
    }

    /**
     * Removes senior according to its SSN.
     *
     * @param removedSeniorSsn Removed senior's SSN
     * @return boolean True if removing is successfull.
     */
    public boolean removeSenior(int removedSeniorSsn) {
        SeniorDoctor removedSenior = getSenior(removedSeniorSsn);

        if (removedSenior != null)
            return removeSenior(removedSenior);

        return false;
    }

    /**
     * Removes senior from senior arraylist.
     *
     * @param removedSenior Removed senior instance.
     * @return boolean True if removing is successfull.
     */
    public boolean removeSenior(SeniorDoctor removedSenior) {
        return seniors.remove(removedSenior);
    }

    /**
     * Returns senior instance according to given SSN.
     *
     * @param desiredSeniorSsn Senior's SSN.
     * @return SeniorDoctor Corresponding senior instance. If not exists, return null.
     */
    public SeniorDoctor getSenior(int desiredSeniorSsn) {
        for (SeniorDoctor senior : seniors) {
            if (senior.getSsn() == desiredSeniorSsn)
                return senior;
        }

        return null;
    }

    /**
     * Creates new senior by getting input from user.
     *
     * @return SeniorDoctor New senior doctor.
     */
    public SeniorDoctor createSenior() {
        System.out.println("---> Create a new senior doctor");

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

        System.out.print("Enter specialization : ");
        String specialization = scan.nextLine();

        System.out.print("Enter doctor's department : ");
        String department = scan.nextLine();

        System.out.print("Enter doctor's employment history : ");
        String employmentHistory = scan.nextLine();

        System.out.print("Enter salary of Senior : ");
        float salary = scan.nextFloat();
        scan.nextLine();

        return new SeniorDoctor(ssn, name, gender, dateOfBirth, insuranceType, specialization,
                department, employmentHistory, salary);
    }

    /**
     * Prints all senior doctor details.
     */
    public void printAllSeniorDetails() {
        System.out.println("---> Print all seniors (" + seniors.size() + " total)");
        for (SeniorDoctor sd : seniors) {
            sd.printDetails();
            System.out.println("________________");
        }
    }

    public String retrieveAllSeniorDetails() {
        String msg = "";
        for (SeniorDoctor sd : seniors) {
            msg += sd.retrieveDetails() + "________________\n";
        }

        return msg;
    }

    /**
     * Prints all junior doctor details.
     */
    public void printAllJuniorDetails() {
        System.out.println("---> Print all juniors (" + juniors.size() + " total)");
        for (JuniorDoctor jd : juniors) {
            jd.printDetails();
            System.out.println("________________");
        }
    }

    public String retrieveAllJuniorDetails() {
        String msg = "";
        for (JuniorDoctor jd : juniors) {
            msg += jd.retrieveDetails() + "________________\n";
        }

        return msg;
    }

    /**
     * Prints all doctor details.
     */
    public void printAllDoctorDetails() {
        System.out.println("---> Print all doctors (" + seniors.size() + juniors.size() + " total)");
        printAllSeniorDetails();
        printAllJuniorDetails();
    }

    public String retrieveAllDoctorDetails() {
        return retrieveAllSeniorDetails() + retrieveAllJuniorDetails();
    }

    /**
     * Get SSN from user and prints corresponding doctor details.
     */
    public void printDoctorDetailsBySsn() {
        System.out.println("---> Retrieve a doctor");
        System.out.print("Enter SSN : ");
        int ssn = scan.nextInt();
        scan.nextLine();

        SeniorDoctor senior = getSenior(ssn);

        if (senior != null) {
            senior.printDetails();
            return;
        }

        JuniorDoctor junior = getJunior(ssn);

        if (junior != null) {
            junior.printDetails();
            return;
        }

        System.out.println("ERROR! There is no doctor with given SSN.");
        return;
    }

    public String retrieveDoctorDetailsBySsn(int ssn) {
        SeniorDoctor senior = getSenior(ssn);

        if (senior != null) {
            return senior.retrieveDetails();
        }

        JuniorDoctor junior = getJunior(ssn);

        if (junior != null) {
            return junior.retrieveDetails();
        }

        return null;
    }

    /**
     * Get SSN from user and removes corresponding doctor.
     */
    public void removeDoctorBySsn() {
        System.out.println("---> Remove a doctor");
        System.out.print("Enter SSN : ");
        int ssn = scan.nextInt();
        scan.nextLine();

        SeniorDoctor senior = getSenior(ssn);

        if (senior != null) {
            removeSenior(senior);
            return;
        }

        JuniorDoctor junior = getJunior(ssn);

        if (junior != null) {
            removeJunior(junior);
            return;
        }

        System.out.println("ERROR! There is no doctor with given SSN.");
        return;
    }

    public boolean removeDoctorBySsn(int ssn) {
        SeniorDoctor senior = getSenior(ssn);

        if (senior != null) {
            removeSenior(senior);
            return true;
        }

        JuniorDoctor junior = getJunior(ssn);

        if (junior != null) {
            removeJunior(junior);
            return true;
        }
        return false;
    }

    /**
     * Get SSN from user and promote corresponding junior to the senior.
     */
    public void getPromotedBySsn() {
        System.out.println("---> Promote a junior");
        System.out.print("Enter SSN : ");
        int ssn = scan.nextInt();
        scan.nextLine();

        JuniorDoctor desiredJunior = getJunior(ssn);

        if (desiredJunior != null) {
            getPromoted(desiredJunior);
            return;
        }

        System.out.println("ERROR! There is no junior with given SSN.");
    }

    public boolean getPromotedBySsn(int ssn) {
        JuniorDoctor desiredJunior = getJunior(ssn);

        if (desiredJunior != null) {
            getPromoted(desiredJunior);
            return true;
        }

        return false;
    }

    /**
     * Spends annual leaves.
     *
     * @param ssn    Employee's SSN that want to spend leave.
     * @param noDays Number of days to spend.
     * @return Boolean Return true if there is no problem.
     */
    public Boolean spendAnnualLeave(int ssn, int noDays) {
        JuniorDoctor junior = getJunior(ssn);

        if (junior != null) {
            junior.goAnnualLeave(noDays);
            return true;
        }

        SeniorDoctor senior = getSenior(ssn);

        if (senior != null) {
            senior.goAnnualLeave(noDays);
            return true;
        }

        return false;
    }

    /**
     * Calculates total junior salaries.
     *
     * @return float Total junior salaries.
     */
    public float getTotalJuniorSalaries() {
        float total = 0;
        for (JuniorDoctor junior : juniors) {
            total += junior.getSalary();
        }

        return total;
    }

    /**
     * Calculates total senior salaries.
     *
     * @return float Total senior salaries.
     */
    public float getTotalSeniorSalaries() {
        float total = 0;
        for (SeniorDoctor senior : seniors) {
            total += senior.getSalary();
        }

        return total;
    }

    /**
     * Calculates total doctor salaries.
     *
     * @return float Total doctor salaries.
     */
    public float getTotalDoctorSalaries() {
        return getTotalJuniorSalaries() + getTotalSeniorSalaries();
    }

    // ----> GETTERS SETTERS
    public ArrayList<SeniorDoctor> getSeniors() {
        return seniors;
    }

    public void setSeniors(ArrayList<SeniorDoctor> seniors) {
        this.seniors = seniors;
    }

    public ArrayList<JuniorDoctor> getJuniors() {
        return juniors;
    }

    public void setJuniors(ArrayList<JuniorDoctor> juniors) {
        this.juniors = juniors;
    }
}
