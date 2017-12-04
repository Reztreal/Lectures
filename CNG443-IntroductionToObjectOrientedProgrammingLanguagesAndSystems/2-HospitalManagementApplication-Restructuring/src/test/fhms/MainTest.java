package test.fhms;

import main.common.Tools;
import main.fhms.*;

public class MainTest {
    public static void main(String[] args) {
        Fhms fhms = createDumpProgram();

        fhms.run();
    }

    public static Fhms createDumpProgram() {
        Fhms fhms = new Fhms();
        Tools tools = new Tools();

        //----> Example senior records
        fhms.doctors.addSenior(new SeniorDoctor(10, "Linus Torvals", 'm', tools.stringToDate("20.06.1982"), 1, "Oncology", "General surgery", "He was junior in 1990.", 19900));
        fhms.doctors.addSenior(new SeniorDoctor(11, "Guido van Rossum", 'm', tools.stringToDate("20.06.1972"), 1, "Cardiology", "Maternity", "He was junior in 1991.", 19000));
        fhms.doctors.addSenior(new SeniorDoctor(12, "Bjarne Stroustrup", 'm', tools.stringToDate("30.12.1950"), 1, "Obsterics", "Critical care", "He was junior in 1992.", 18000));
        fhms.doctors.addSenior(new SeniorDoctor(13, "Margaret Hamilton", 'f', tools.stringToDate("06.03.1925"), 1, "Gynaecology", "Discharge lounge", "She was junior in 1993.", 17000));
        fhms.doctors.addSenior(new SeniorDoctor(14, "Madame Curie", 'f', tools.stringToDate("07.11.1867"), 1, "Radiology", "Diagnostic imaging", "She was junior in 1993.", 16000));


        //----> Example junior records
        SeniorDoctor resSenior = fhms.doctors.getSenior(10);
        fhms.doctors.addJunior(new JuniorDoctor(20, "Furkan Tokac", 'm', tools.stringToDate("20.06.1990"), 2, "Oncology", "General surgery", tools.stringToDate("10.12.2000"), tools.stringToDate("10.12.2005"), resSenior, 9900));
        resSenior = fhms.doctors.getSenior(11);
        fhms.doctors.addJunior(new JuniorDoctor(21, "Ahmet Kara", 'm', tools.stringToDate("21.07.1991"), 2, "Cardiology", "Maternity", tools.stringToDate("10.12.2001"), tools.stringToDate("10.12.2006"), resSenior, 8000));
        resSenior = fhms.doctors.getSenior(12);
        fhms.doctors.addJunior(new JuniorDoctor(22, "Tansel Trabzon", 'm', tools.stringToDate("22.08.1992"), 2, "Obsterics", "Critical care", tools.stringToDate("10.12.2002"), tools.stringToDate("10.12.2007"), resSenior, 7000));
        resSenior = fhms.doctors.getSenior(13);
        fhms.doctors.addJunior(new JuniorDoctor(23, "Deniz Tatli", 'm', tools.stringToDate("23.09.1993"), 2, "Gynaecology", "Discharge lounge", tools.stringToDate("10.12.2003"), tools.stringToDate("10.12.2008"), resSenior, 6000));
        resSenior = fhms.doctors.getSenior(14);
        fhms.doctors.addJunior(new JuniorDoctor(24, "Sinan Ulusoy", 'm', tools.stringToDate("24.10.1994"), 2, "Radiology", "Diagnostic imaging", tools.stringToDate("10.12.2004"), tools.stringToDate("10.12.2009"), resSenior, 5500));


        //----> Example nurse records
        fhms.nurses.addNurse(new Nurse(30, "Hilmiye Nurcan", 'f', tools.stringToDate("10.07.1990"), 2, "General surgery", 4900));
        fhms.nurses.addNurse(new Nurse(31, "Hilmi Hakkican", 'm', tools.stringToDate("03.11.1991"), 2, "Maternity", 3500));
        fhms.nurses.addNurse(new Nurse(32, "Nuriye Gulcan", 'f', tools.stringToDate("11.04.1992"), 2, "Critical care", 4500));
        fhms.nurses.addNurse(new Nurse(33, "Fadime Hurcan", 'f', tools.stringToDate("19.10.1993"), 2, "Discharge lounge", 4000));
        fhms.nurses.addNurse(new Nurse(34, "Nurcan Gul", 'f', tools.stringToDate("23.02.1994"), 2, "Diagnostic imaging", 3900));


        //----> Example patient, medical, treatment records
        // Patient1
        Patient newPatient = new Patient(101, "Burak Akcan", 'm', tools.stringToDate("10.12.1996"), 3, true);

        MedicalRecord newMedicalRecord1 = new MedicalRecord(tools.stringToDate("21.03.2003"));
        Treatment newTreatment1 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 16441, false, fhms.doctors.getSenior(10));
        Treatment newTreatment2 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 8213, false, fhms.doctors.getSenior(11));
        newMedicalRecord1.getTreatments().addTreatment(newTreatment1);
        newMedicalRecord1.getTreatments().addTreatment(newTreatment2);

        MedicalRecord newMedicalRecord2 = new MedicalRecord(tools.stringToDate("21.04.2003"));
        newTreatment1 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 1213, true, fhms.doctors.getSenior(10));
        newTreatment2 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 8415, false, fhms.doctors.getSenior(11));
        newMedicalRecord2.getTreatments().addTreatment(newTreatment1);
        newMedicalRecord2.getTreatments().addTreatment(newTreatment2);

        newPatient.getMedicalRecords().addMedicalRecord(newMedicalRecord1);
        newPatient.getMedicalRecords().addMedicalRecord(newMedicalRecord2);

        fhms.patients.addPatient(newPatient);

        // Patient2
        newPatient = new Patient(102, "Osman Harman", 'm', tools.stringToDate("18.12.1997"), 1, false);

        newMedicalRecord1 = new MedicalRecord(tools.stringToDate("20.03.2003"));
        newTreatment1 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 8123, false, fhms.doctors.getSenior(11));
        newTreatment2 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 12159, false, fhms.doctors.getSenior(12));
        newMedicalRecord1.getTreatments().addTreatment(newTreatment1);
        newMedicalRecord1.getTreatments().addTreatment(newTreatment2);

        newMedicalRecord2 = new MedicalRecord(tools.stringToDate("20.04.2003"));
        newTreatment1 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 8213, false, fhms.doctors.getSenior(10));
        newTreatment2 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 7234, false, fhms.doctors.getSenior(12));
        newMedicalRecord2.getTreatments().addTreatment(newTreatment1);
        newMedicalRecord2.getTreatments().addTreatment(newTreatment2);

        newPatient.getMedicalRecords().addMedicalRecord(newMedicalRecord1);
        newPatient.getMedicalRecords().addMedicalRecord(newMedicalRecord2);

        fhms.patients.addPatient(newPatient);

        // Patient3
        newPatient = new Patient(103, "Jonathon Smith", 'm', tools.stringToDate("15.12.1998"), 2, true);

        newMedicalRecord1 = new MedicalRecord(tools.stringToDate("20.03.2003"));
        newTreatment1 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 9213, true, fhms.doctors.getSenior(13));
        newTreatment2 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 16352, false, fhms.doctors.getSenior(11));
        newMedicalRecord1.getTreatments().addTreatment(newTreatment1);
        newMedicalRecord1.getTreatments().addTreatment(newTreatment2);

        newMedicalRecord2 = new MedicalRecord(tools.stringToDate("20.05.2003"));
        newTreatment1 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 6153, false, fhms.doctors.getSenior(10));
        newTreatment2 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 22671, false, fhms.doctors.getSenior(12));
        newMedicalRecord2.getTreatments().addTreatment(newTreatment1);
        newMedicalRecord2.getTreatments().addTreatment(newTreatment2);

        newPatient.getMedicalRecords().addMedicalRecord(newMedicalRecord1);
        newPatient.getMedicalRecords().addMedicalRecord(newMedicalRecord2);

        fhms.patients.addPatient(newPatient);

        // Patient4
        newPatient = new Patient(104, "Mondragon Prates", 'm', tools.stringToDate("01.02.1996"), 3, true);

        newMedicalRecord1 = new MedicalRecord(tools.stringToDate("04.03.2003"));
        newTreatment1 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 543, true, fhms.doctors.getSenior(14));
        newTreatment2 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 22345, false, fhms.doctors.getSenior(11));
        newMedicalRecord1.getTreatments().addTreatment(newTreatment1);
        newMedicalRecord1.getTreatments().addTreatment(newTreatment2);

        newMedicalRecord2 = new MedicalRecord(tools.stringToDate("04.04.2003"));
        newTreatment1 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 12317, false, fhms.doctors.getSenior(12));
        newTreatment2 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 4112, false, fhms.doctors.getSenior(11));
        newMedicalRecord2.getTreatments().addTreatment(newTreatment1);
        newMedicalRecord2.getTreatments().addTreatment(newTreatment2);

        newPatient.getMedicalRecords().addMedicalRecord(newMedicalRecord1);
        newPatient.getMedicalRecords().addMedicalRecord(newMedicalRecord2);

        fhms.patients.addPatient(newPatient);

        // Patient5
        newPatient = new Patient(105, "Gamze Harran", 'f', tools.stringToDate("06.03.1991"), 2, false);

        newMedicalRecord1 = new MedicalRecord(tools.stringToDate("16.03.2003"));
        newTreatment1 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 1631, true, fhms.doctors.getSenior(13));
        newTreatment2 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 15412, false, fhms.doctors.getSenior(10));
        newMedicalRecord1.getTreatments().addTreatment(newTreatment1);
        newMedicalRecord1.getTreatments().addTreatment(newTreatment2);

        newMedicalRecord2 = new MedicalRecord(tools.stringToDate("16.04.2003"));
        newTreatment1 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 1357, false, fhms.doctors.getSenior(10));
        newTreatment2 = new Treatment("This is details of treatment.", "This is description of treatment.", "This is medication of treatment.", 22171, false, fhms.doctors.getSenior(12));
        newMedicalRecord2.getTreatments().addTreatment(newTreatment1);
        newMedicalRecord2.getTreatments().addTreatment(newTreatment2);

        newPatient.getMedicalRecords().addMedicalRecord(newMedicalRecord1);
        newPatient.getMedicalRecords().addMedicalRecord(newMedicalRecord2);

        fhms.patients.addPatient(newPatient);

        return fhms;
    }
}
