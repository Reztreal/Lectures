package main.fhms;

/**
 * Treatment class helds treatment data.
 */
public class Treatment {
    private String details;
    private String description;
    private String medication;
    private float cost;
    private boolean must;
    private SeniorDoctor responsibleDoctor;

    /**
     *
     * @param t_details
     * @param t_description
     * @param t_medication
     * @param t_cost
     * @param t_must
     * @param t_responsibleDoctor
     */
    public Treatment(String t_details, String t_description, String t_medication,
                     float t_cost, boolean t_must, SeniorDoctor t_responsibleDoctor) {
        details = t_details;
        description = t_description;
        medication = t_medication;
        cost = t_cost;
        must = t_must;
        responsibleDoctor = t_responsibleDoctor;
    }

    /**
     *
     * @param newTreatment
     */
    public Treatment(Treatment newTreatment) {
        details = newTreatment.getDetails();
        description = newTreatment.getDescription();
        medication = newTreatment.getMedication();
        cost = newTreatment.getCost();
        must = newTreatment.isMust();
        responsibleDoctor = newTreatment.getResponsibleDoctor();
    }

    /**
     * Print details of treatment.
     */
    public void printDetails() {
        System.out.println("" +
                "Details        : " + details + "\n" +
                "Description    : " + description + "\n" +
                "Medication     : " + medication + "\n" +
                "Cost           : " + cost + "\n" +
                "Must           : " + (must ? "Yes" : "No") + "\n" +
                "Name of Doctor : " + responsibleDoctor.getName());
    }

    // ----> GETTERS SETTERS
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public boolean isMust() {
        return must;
    }

    public void setMust(boolean must) {
        this.must = must;
    }

    public SeniorDoctor getResponsibleDoctor() {
        return responsibleDoctor;
    }

    public void setResponsibleDoctor(SeniorDoctor responsibleDoctor) {
        this.responsibleDoctor = responsibleDoctor;
    }
}
