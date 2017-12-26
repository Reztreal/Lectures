package main.fhms;

import java.util.ArrayList;

/**
 * Treatments class manages treatment arraylist
 */
public class Treatments {
    private ArrayList<Treatment> treatments = new ArrayList<>();

    /**
     * @return int Number of treatment data.
     */
    public int getNumberOfTreatments() {
        return treatments.size();
    }

    /**
     * Gets latest treatment data.
     *
     * @return Treatment Latest treatment.
     */
    public Treatment getLatestTreatment() {
        if (treatments.size() > 0)
            return treatments.get(treatments.size() - 1);

        return null;
    }

    /**
     * Adds new treatment instance to the treatment arraylist.
     *
     * @param newTreatment Treatment instance to be added.
     */
    public void addTreatment(Treatment newTreatment) {
        treatments.add(newTreatment);
    }

    /**
     * Prints all treatment details.
     */
    public void printAllTreatmentDetails() {
        for (Treatment treatment : treatments) {
            treatment.printDetails();
            System.out.println("________________");
        }
    }

    /**
     * Calculates all incomes done by treatments.
     *
     * @return float Total income.
     */
    public float treatmentsIncome(Insurance insurance) {
        float total = 0;
        for (Treatment treatment : treatments) {
            total += insurance.applyInsurenceToCost(treatment.getCost(), treatment.isMust());
        }
        return total;
    }
}
