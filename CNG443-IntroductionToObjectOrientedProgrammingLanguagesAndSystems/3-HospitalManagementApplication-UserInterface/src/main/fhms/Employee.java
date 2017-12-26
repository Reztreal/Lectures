package main.fhms;

/**
 * Employee interface
 */
public interface Employee {
    /**
     * Salary of employee.
     *
     * @return float Salary of employee.
     */
    float salary();

    /**
     * Checks how many annual leave left.
     *
     * @return int Left annual leave.
     */
    int annualLeaveLeft();

    /**
     * Spends annual leave of employee.
     *
     * @param noDays How many days to spend.
     */
    void goAnnualLeave(int noDays);
}
