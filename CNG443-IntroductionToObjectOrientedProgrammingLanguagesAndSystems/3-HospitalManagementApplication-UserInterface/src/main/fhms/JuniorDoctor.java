package main.fhms;

import java.util.Date;

/**
 * Junior doctor class
 *
 * @author Furkan Tokac
 * {@inheritDoc}
 */
public class JuniorDoctor extends Doctor {
    private Date startDate;
    private Date expectedEndDate;
    private SeniorDoctor responsibleDoctor;

    /**
     * @param t_ssn
     * @param t_name
     * @param t_gender
     * @param t_dateOfBirth
     * @param t_insuranceType
     * @param t_specialization
     * @param t_departmentWorksIn
     * @param t_startDate
     * @param t_expectedEndDate
     * @param t_responsibleDoctor
     * @param t_salary
     */
    public JuniorDoctor(int t_ssn, String t_name, char t_gender, Date t_dateOfBirth, int t_insuranceType,
                        String t_specialization, String t_departmentWorksIn, Date t_startDate,
                        Date t_expectedEndDate, SeniorDoctor t_responsibleDoctor, float t_salary) {
        super(t_ssn, t_name, t_gender, t_dateOfBirth, t_insuranceType, t_specialization,
                t_departmentWorksIn, 25, t_salary);

        startDate = t_startDate;
        expectedEndDate = t_expectedEndDate;
        responsibleDoctor = t_responsibleDoctor;
    }

    /**
     * @param newDoctor
     * @param t_startDate
     * @param t_expectedEndDate
     * @param t_responsibleDoctor
     */
    public JuniorDoctor(Doctor newDoctor, Date t_startDate, Date t_expectedEndDate,
                        SeniorDoctor t_responsibleDoctor) {
        super(newDoctor);

        startDate = t_startDate;
        expectedEndDate = t_expectedEndDate;
        responsibleDoctor = t_responsibleDoctor;
    }

    /**
     * Prints details of junior doctor.
     */
    @Override
    public void printDetails() {
        System.out.print("" +
                "SSN            : " + getSsn() + "\n" +
                "Name           : " + getName() + "\n" +
                "Gender         : " + getGender() + "\n" +
                "Date of birth  : " + getDateOfBirth() + "\n" +
                "Insurance      : " + getInsurance().getDescription() + "\n" +
                "Specialization : " + getSpecialization() + "\n" +
                "Department     : " + getDepartmentWorksIn() + "\n" +
                "Start Date     : " + startDate + "\n" +
                "Expected End   : " + expectedEndDate + "\n" +
                "Responsible    : " + responsibleDoctor.getName() + "\n" +
                "Annual Leave   : " + getAnnualLeaveLeft() + "days\n" +
                "Salary         : " + getSalary() + "\n");
    }

    public String retrieveDetails() {
        String msg = "" +
                "SSN\t: " + getSsn() + "\n" +
                "Name\t: " + getName() + "\n" +
                "Gender\t: " + getGender() + "\n" +
                "Date of birth\t: " + getDateOfBirth() + "\n" +
                "Insurance\t: " + getInsurance().getDescription() + "\n" +
                "Specialization\t: " + getSpecialization() + "\n" +
                "Department\t: " + getDepartmentWorksIn() + "\n" +
                "Start Date\t: " + startDate + "\n" +
                "Expected End\t: " + expectedEndDate + "\n" +
                "Responsible\t: " + responsibleDoctor.getName() + "\n" +
                "Annual Leave\t: " + getAnnualLeaveLeft() + "days\n" +
                "Salary\t: " + getSalary() + "\n";

        return msg;
    }

    // ----> GETTERS SETTERS
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpectedEndDate() {
        return expectedEndDate;
    }

    public void setExpectedEndDate(Date expectedEndDate) {
        this.expectedEndDate = expectedEndDate;
    }

    public SeniorDoctor getResponsibleDoctor() {
        return responsibleDoctor;
    }

    public void setResponsibleDoctor(SeniorDoctor responsibleDoctor) {
        this.responsibleDoctor = responsibleDoctor;
    }
}
