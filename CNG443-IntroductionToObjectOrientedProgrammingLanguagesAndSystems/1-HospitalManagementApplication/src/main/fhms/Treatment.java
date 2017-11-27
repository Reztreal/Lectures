package main.fhms;


/**
 * Treatment class
 *
 * @author Furkan Tokac
 */
public class Treatment {

    private String details;
    private String description;
    private String medication;
    private int ssnOfDoctor; // Doctor that create this medical report

    /**
     * Can be created by directly getting the variables
     * @param t_details
     * @param t_description
     * @param t_medication
     * @param t_ssnOfDoctor
     */
    public Treatment(String t_details, String t_description, String t_medication, int t_ssnOfDoctor) {
        details = t_details;
        description = t_description;
        medication = t_medication;
        ssnOfDoctor = t_ssnOfDoctor;
    }

    /**
     * Can be created from new treatment object
     * @param newTreatment
     */
    public Treatment(Treatment newTreatment) {
        details = newTreatment.details;
        description = newTreatment.description;
        medication = newTreatment.medication;
        ssnOfDoctor = newTreatment.ssnOfDoctor;
    }

    /**
     * Prints all treatment details
     */
    public void printTreatment() {
        System.out.println("" +
                "Details        : " + details + "\n" +
                "Description    : " + description + "\n" +
                "Medication     : " + medication + "\n" +
                "SSN of Doctor  : " + ssnOfDoctor);
    }
}
