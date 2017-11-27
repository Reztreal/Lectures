package main.fhms;

import java.util.ArrayList;
import java.util.Date;

/**
 * Class that handles medical records.
 *
 * @author Furkan Tokac
 */
public class MedicalRecord {

    private Date issueDate;
    private ArrayList<Treatment> treatments = new ArrayList<>();

    public MedicalRecord(Date t_issueDate) {
        issueDate = t_issueDate;
    }

    /**
     * Can create medical record by existing medical record
     * @param newMedicalRecord
     */
    public MedicalRecord(MedicalRecord newMedicalRecord) {
        issueDate = newMedicalRecord.issueDate;
        treatments = newMedicalRecord.treatments;
    }

    /**
     * Print details of medical record
     */
    public void printDetails() {
        System.out.println("Issue date           : " + issueDate);
        System.out.println("Number of treatments : " + treatments.size());
    }

    /**
     * Adding new treatment to the treatments
     * @param details
     * @param description
     * @param medication
     * @param ssnOfDoctor
     */
    public void addTreatment(String details, String description, String medication, int ssnOfDoctor) {
        Treatment newTreatment = new Treatment(details,description,medication,ssnOfDoctor);
        treatments.add(newTreatment);
    }

    /**
     * Prints all treatments
     */
    public void printAllTreatments() {
        for (Treatment treatment : treatments) {
            treatment.printTreatment();
        }
    }

}
