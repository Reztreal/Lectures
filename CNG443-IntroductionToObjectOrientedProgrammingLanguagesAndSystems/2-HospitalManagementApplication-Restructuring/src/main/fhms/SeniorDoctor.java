package main.fhms;

import java.util.ArrayList;
import java.util.Date;

public class SeniorDoctor extends Doctor {
    private String employmentHistory;
    private ArrayList<Nurse> worksWithNurses = new ArrayList<>();

    public SeniorDoctor(int t_ssn, String t_name, char t_gender, Date t_dateOfBirth, int t_insuranceType,
                  String t_specialization, String t_departmentWorksIn, String t_employmentHistory, float salary) {
        super(t_ssn, t_name, t_gender, t_dateOfBirth, t_insuranceType, t_specialization, t_departmentWorksIn, 25, salary);

        employmentHistory = t_employmentHistory;
    }

    public SeniorDoctor(Doctor newDoctor, String t_employmentHistory) {
        super(newDoctor);

        employmentHistory = t_employmentHistory;
    }

    public SeniorDoctor(JuniorDoctor newJunior) {
        super(newJunior.getSsn(), newJunior.getName(), newJunior.getGender(), newJunior.getDateOfBirth(),
                newJunior.getInsurance().getType(), newJunior.getSpecialization(),
                newJunior.getDepartmentWorksIn(), newJunior.getAnnualLeaveLeft(), newJunior.getSalary());

        employmentHistory = "S/he was junior since " + newJunior.getStartDate() + "\n";
        employmentHistory += "                 Currently working as Senior.";
    }

    public void addWorksWithNurses(Nurse newNurse) {
        worksWithNurses.add(newNurse);
        newNurse.getWorksForSeniors().add(this);
    }

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
                "History        : " + employmentHistory + "\n" +
                "Annual Leave   : " + getAnnualLeaveLeft() + "days\n" +
                "Salary         : " + getSalary() + "\n");
    }

    // ----> GETTERS SETTERS
    public String getEmploymentHistory() {
        return employmentHistory;
    }

    public void setEmploymentHistory(String employmentHistory) {
        this.employmentHistory = employmentHistory;
    }

    public ArrayList<Nurse> getWorksWithNurses() {
        return worksWithNurses;
    }

    public void setWorksWithNurses(ArrayList<Nurse> worksWithNurses) {
        this.worksWithNurses = worksWithNurses;
    }
}
