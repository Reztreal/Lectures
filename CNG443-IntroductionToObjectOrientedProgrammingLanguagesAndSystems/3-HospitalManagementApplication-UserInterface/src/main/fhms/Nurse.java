package main.fhms;

import java.util.ArrayList;
import java.util.Date;

/**
 * Nurse class implements Employee interface
 *
 * @author Furkan Tokac
 * {@inheritDoc}
 */
public class Nurse extends Person implements Employee {
    private String departmentWorksIn;
    private ArrayList<SeniorDoctor> worksForSeniors = new ArrayList<>();
    private float salary;
    private int annualLeaveLeft = 20;

    /**
     * @param t_ssn
     * @param t_name
     * @param t_gender
     * @param t_dateOfBirth
     * @param t_insuranceType
     * @param t_departmentWorksIn
     * @param t_salary
     */
    public Nurse(int t_ssn, String t_name, char t_gender, Date t_dateOfBirth, int t_insuranceType,
                 String t_departmentWorksIn, float t_salary) {
        super(t_ssn, t_name, t_gender, t_dateOfBirth, t_insuranceType);
        departmentWorksIn = t_departmentWorksIn;
        salary = t_salary;
        annualLeaveLeft = 20;
    }

    /**
     * Specify nurse is working for which senior doctor.
     *
     * @param newSenior Senior that nurse is working for.
     */
    public void addWorksForSeniors(SeniorDoctor newSenior) {
        worksForSeniors.add(newSenior);
        newSenior.getWorksWithNurses().add(this);
    }

    /**
     * Prints details of nurse.
     */
    @Override
    public void printDetails() {
        System.out.print("" +
                "SSN            : " + getSsn() + "\n" +
                "Name           : " + getName() + "\n" +
                "Gender         : " + getGender() + "\n" +
                "Date of birth  : " + getDateOfBirth() + "\n" +
                "Insurance      : " + getInsurance().getDescription() + "\n" +
                "Department     : " + departmentWorksIn + "\n" +
                "Annual Leave   : " + annualLeaveLeft + "days\n" +
                "Salary         : " + salary + "\n");
    }

    public String retrieveDetails() {
        String msg = "" +
                "SSN\t: " + String.valueOf(getSsn()) + "\n" +
                "Name\t: : " + getName() + "\n" +
                "Gender\t: : " + getGender() + "\n" +
                "Date of birth\t: " + getDateOfBirth() + "\n" +
                "Insurance\t: " + getInsurance().getDescription() + "\n" +
                "Department\t: " + departmentWorksIn + "\n" +
                "Annual Leave\t: " + String.valueOf(annualLeaveLeft) + "days\n" +
                "Salary\t: " + String.valueOf(salary) + "\n";
        return msg;
    }

    /**
     * @return float Salary of nurse.
     */
    @Override
    public float salary() {
        return salary;
    }

    /**
     * @return int How many leave days left.
     */
    @Override
    public int annualLeaveLeft() {
        return annualLeaveLeft;
    }

    /**
     * @param noDays How many days want to spend.
     */
    @Override
    public void goAnnualLeave(int noDays) {
        if (annualLeaveLeft >= noDays)
            annualLeaveLeft -= noDays;
        else
            System.out.println("ERROR! No enough annual leave left.");
    }


    // ----> GETTERS SETTERS
    public String getDepartmentWorksIn() {
        return departmentWorksIn;
    }

    public void setDepartmentWorksIn(String departmentWorksIn) {
        this.departmentWorksIn = departmentWorksIn;
    }

    public ArrayList<SeniorDoctor> getWorksForSeniors() {
        return worksForSeniors;
    }

    public void setWorksForSeniors(ArrayList<SeniorDoctor> worksForSeniors) {
        this.worksForSeniors = worksForSeniors;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getAnnualLeaveLeft() {
        return annualLeaveLeft;
    }

    public void setAnnualLeaveLeft(int annualLeaveLeft) {
        this.annualLeaveLeft = annualLeaveLeft;
    }
}
