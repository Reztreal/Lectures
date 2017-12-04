package main.fhms;

import java.util.Date;

/**
 * Doctor class implements Employee interface
 *
 * @author Furkan Tokac
 * {@inheritDoc}
 */
public class Doctor extends Person implements Employee {
    private String specialization;
    private String departmentWorksIn;
    private int annualLeaveLeft = 25;
    private float salary;

    /**
     * @param t_ssn
     * @param t_name
     * @param t_gender
     * @param t_dateOfBirth
     * @param t_insuranceType
     * @param t_specialization
     * @param t_departmentWorksIn
     * @param t_annualLeaveLeft
     * @param t_salary
     */
    public Doctor(int t_ssn, String t_name, char t_gender, Date t_dateOfBirth, int t_insuranceType,
                  String t_specialization, String t_departmentWorksIn, int t_annualLeaveLeft,
                  float t_salary) {
        super(t_ssn, t_name, t_gender, t_dateOfBirth, t_insuranceType);
        specialization = t_specialization;
        departmentWorksIn = t_departmentWorksIn;
        annualLeaveLeft = t_annualLeaveLeft;
        salary = t_salary;
    }

    /**
     * @param newDoctor
     */
    public Doctor(Doctor newDoctor) {
        super(newDoctor.getSsn(), newDoctor.getName(), newDoctor.getGender(), newDoctor.getDateOfBirth(), newDoctor.getInsurance().getType());
        specialization = newDoctor.getSpecialization();
        departmentWorksIn = newDoctor.getDepartmentWorksIn();
        annualLeaveLeft = newDoctor.getAnnualLeaveLeft();
        salary = newDoctor.getSalary();
    }

    /**
     * Prints details of doctor.
     */
    @Override
    public void printDetails() {
        System.out.print("" +
                "SSN            : " + getSsn() + "\n" +
                "Name           : " + getName() + "\n" +
                "Gender         : " + getGender() + "\n" +
                "Date of birth  : " + getDateOfBirth() + "\n" +
                "Insurance      : " + getInsurance().getDescription() + "\n" +
                "Specialization : " + specialization + "\n" +
                "Department     : " + departmentWorksIn + "\n" +
                "Annual Leave   : " + annualLeaveLeft + "days\n" +
                "Salary         : " + salary + "\n");
    }

    /**
     * @return float Salary of doctor.
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
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDepartmentWorksIn() {
        return departmentWorksIn;
    }

    public void setDepartmentWorksIn(String departmentWorksIn) {
        this.departmentWorksIn = departmentWorksIn;
    }

    public int getAnnualLeaveLeft() {
        return annualLeaveLeft;
    }

    public void setAnnualLeaveLeft(int annualLeaveLeft) {
        this.annualLeaveLeft = annualLeaveLeft;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
