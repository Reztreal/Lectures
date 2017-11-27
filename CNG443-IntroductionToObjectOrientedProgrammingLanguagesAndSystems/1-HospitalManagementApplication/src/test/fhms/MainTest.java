package test.fhms;

import main.common.Tools;
import main.fhms.Doctor;
import main.fhms.Fhms;
import main.fhms.MedicalRecord;
import main.fhms.Patient;

/**
 * This class is basically run the program, add some records and display them again.
 *
 * @author Furkan Tokac
 */
public class MainTest {
    /**
     * When the main function start working, it adds doctors, patients, medical records and their treatment.
     * @param args
     */
    public static void main(String[] args) {
        Fhms fhms = new Fhms();
        Tools tools = new Tools();
        // Example doctor records
        fhms.addDoctor(new Doctor(10, "Linus Torvals", 'm', tools.stringToDate("20.06.1982")));
        fhms.addDoctor(new Doctor(11, "Guido van Rossum", 'm', tools.stringToDate("20.06.1972")));
        fhms.addDoctor(new Doctor(12, "Bjarne Stroustrup", 'm', tools.stringToDate("30.12.1950")));
        fhms.addDoctor(new Doctor(13, "Yeliz Yesilada", 'f', tools.stringToDate("30.12.1950")));
        fhms.addDoctor(new Doctor(14, "Margaret Hamilton", 'f', tools.stringToDate("06.03.1925")));
        fhms.addDoctor(new Doctor(15, "Madame Curie", 'f', tools.stringToDate("07.11.1867")));

        // Example patient records
        fhms.addPatient(new Patient(16, "Furkan Tokac", 'm', tools.stringToDate("20.06.1990"), 94));
        fhms.addPatient(new Patient(17, "Ahmet Kara", 'm', tools.stringToDate("21.07.1991"), 100));
        fhms.addPatient(new Patient(18, "Tansel Trabzon", 'm', tools.stringToDate("22.08.1992"), 90));
        fhms.addPatient(new Patient(19, "Deniz Tatli", 'm', tools.stringToDate("23.09.1993"), 80));
        fhms.addPatient(new Patient(20, "Sinan Ulusoy", 'm', tools.stringToDate("24.10.1994"), 85));
        fhms.addPatient(new Patient(21, "Batuhan Cihan", 'm', tools.stringToDate("25.11.1995"), 120));

        // Example medical records
        MedicalRecord newMedicalRecord = new MedicalRecord(tools.stringToDate("10.01.2017"));
        newMedicalRecord.addTreatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment", 10);
        fhms.getPatient(16).addMedicalRecord(newMedicalRecord);

        newMedicalRecord = new MedicalRecord(tools.stringToDate("10.01.2017"));
        newMedicalRecord.addTreatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment", 11);
        fhms.getPatient(17).addMedicalRecord(newMedicalRecord);

        newMedicalRecord = new MedicalRecord(tools.stringToDate("11.02.2017"));
        newMedicalRecord.addTreatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment", 12);
        fhms.getPatient(18).addMedicalRecord(newMedicalRecord);

        newMedicalRecord = new MedicalRecord(tools.stringToDate("12.03.2017"));
        newMedicalRecord.addTreatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment", 13);
        fhms.getPatient(19).addMedicalRecord(newMedicalRecord);

        newMedicalRecord = new MedicalRecord(tools.stringToDate("13.04.2017"));
        newMedicalRecord.addTreatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment", 14);
        fhms.getPatient(20).addMedicalRecord(newMedicalRecord);

        newMedicalRecord = new MedicalRecord(tools.stringToDate("14.05.2017"));
        newMedicalRecord.addTreatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment", 15);
        fhms.getPatient(21).addMedicalRecord(newMedicalRecord);

        // Print datas
        fhms.printAllDoctors();
        fhms.printAllPatients();
        fhms.printAllTreatmentsInTheHospital();
    }
}
