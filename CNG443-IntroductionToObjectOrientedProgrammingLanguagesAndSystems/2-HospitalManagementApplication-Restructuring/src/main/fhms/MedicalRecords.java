package main.fhms;

import java.util.ArrayList;

/**
 * Medical records class manages medical records arraylists
 */
public class MedicalRecords {
    private ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();

    /**
     * Adds new medical record instance to medical records arraylist.
     *
     * @param newMedicalRecord Medical record instance to be added.
     */
    public void addMedicalRecord(MedicalRecord newMedicalRecord) {
        medicalRecords.add(newMedicalRecord);
    }

    /**
     * Finds latest medical record.
     *
     * @return MedicalRecord Latest medical record.
     */
    public MedicalRecord getLatestMedicalRecord() {
        if (medicalRecords.size() > 0)
            return medicalRecords.get(medicalRecords.size() - 1);

        return null;
    }

    /**
     * Prints all medical records.
     */
    public void printAllMedicalRecords() {
        for (MedicalRecord medicalRecord : medicalRecords) {
            medicalRecord.getTreatments().printAllTreatmentDetails();
        }
    }

    /**
     * Calculates total income according to medical records.
     *
     * @param insurance Income will be calculated according to insurance.
     * @return float Total income.
     */
    public float medicalRecordsIncome(Insurance insurance) {
        float total = 0;
        for (MedicalRecord medicalRecord : medicalRecords) {
            total += medicalRecord.getTreatments().treatmentsIncome(insurance);
        }
        return total;
    }


    // ----> GETTERS SETTERS
    public ArrayList<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(ArrayList<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }
}
