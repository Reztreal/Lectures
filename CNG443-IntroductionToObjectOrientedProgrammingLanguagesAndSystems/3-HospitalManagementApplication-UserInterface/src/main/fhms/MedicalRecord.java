package main.fhms;

import java.util.Date;

/**
 * Medical record class manage medical records
 */
public class MedicalRecord {
    private Date issueDate;
    private Treatments treatments = new Treatments();

    /**
     * @param t_issueDate
     */
    public MedicalRecord(Date t_issueDate) {
        issueDate = t_issueDate;
    }

    /**
     * @param newMedicalRecord
     */
    public MedicalRecord(MedicalRecord newMedicalRecord) {
        issueDate = newMedicalRecord.issueDate;
        treatments = newMedicalRecord.treatments;
    }

    /**
     * Prints details of medical record.
     */
    public void printDetails() {
        System.out.println("Issue date           : " + issueDate);
        System.out.println("Number of treatments : " + treatments.getNumberOfTreatments());
    }


    // ----> GETTERS SETTERS
    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Treatments getTreatments() {
        return treatments;
    }

    public void setTreatments(Treatments treatments) {
        this.treatments = treatments;
    }
}
