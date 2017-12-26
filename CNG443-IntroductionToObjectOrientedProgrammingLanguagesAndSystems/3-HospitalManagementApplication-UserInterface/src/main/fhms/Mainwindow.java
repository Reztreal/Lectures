package main.fhms;

import main.common.Tools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class Mainwindow {
    private JPanel panel_Main;
    private JTabbedPane tabbedPane_Main;
    private JPanel panel_Management;
    private JPanel panel_Doctor;
    private JTextField textField_DoctorName;
    private JTextField textField_DoctorSurname;
    private JTextField textField_DoctorSsn;
    private JTextField textField_DoctorDateOfBirth;
    private JTextField textField_DoctorInsuranceType;
    private JTextField textField_DoctorSpecialization;
    private JButton button_DoctorSubmit;
    private JComboBox comboBox_DoctorOperation;
    private JTextField textField_DoctorDepartment;
    private JTextField textField_DoctorSalary;
    private JComboBox comboBox_DoctorGender;
    private JTextField textField_DoctorJobStartDate;
    private JTextField textField_DoctorExpectedEndDate;
    private JTextField textField_DoctorSsnOfResponsibleSenior;
    private JButton button_DoctorClearForm;
    private JScrollPane scrollPane_MainInfoPanel;
    private JTextArea textArea_MainInfoPanel;
    private JTextField textField_ManagementLeaveSsn;
    private JTextField textField_ManagementNumOfLeaveDays;
    private JButton button_ManagementAnnualLeave;
    private JButton button_ManagementBudgetStatus;
    private JTextField textField_DoctorEmploymentHistory;
    private JPanel panel_Patient;
    private JButton button_PatientSubmit;
    private JTextField textField_PatientInsuranceType;
    private JTextField textField_PatientDateOfBirth;
    private JComboBox comboBox_PatientGender;
    private JTextField textField_PatientName;
    private JTextField textField_PatientSurname;
    private JTextField textField_PatientSsn;
    private JComboBox comboBox_PatientOperation;
    private JPanel panel_Nurse;
    private JPanel panel_SpendAnnualLeave;
    private JPanel panel_Functionalities;
    private JTextField textField_NurseSsn;
    private JTextField textField_NurseName;
    private JTextField textField_NurseSurname;
    private JTextField textField_NurseDepartment;
    private JTextField textField_NurseSalary;
    private JComboBox comboBox_NurseGender;
    private JComboBox comboBox_NurseOperation;
    private JTextField textField_NurseDateOfBirth;
    private JTextField textField_NurseInsuranceType;
    private JButton button_NurseSubmit;

    public Doctors doctors = new Doctors();
    public Nurses nurses = new Nurses();
    public Patients patients = new Patients();
    private Tools tools = new Tools();

    public Mainwindow() {
        doctorEnableOnlyJunior();
        comboBox_DoctorOperation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int index = comboBox_DoctorOperation.getSelectedIndex();

                if (index == 0) {
                    doctorEnableOnlyJunior();
                } else if (index == 1) {
                    doctorEnableOnlySenior();
                } else if (index == 2) {
                    doctorEnableOnlySsn();
                } else if (index == 3) {
                    doctorEnableOnlySsn();
                } else if (index == 4) {
                    doctorEnableOnlySsn();
                } else {
                    doctorSetEnabledAll(false);
                }
            }
        });
        button_DoctorClearForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                doctorClearForm();
            }
        });
        button_ManagementBudgetStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showBudgetStatus();
            }
        });
        button_ManagementAnnualLeave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                spendAnnualLeaveBySsn();
            }
        });
        button_DoctorSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int index = comboBox_DoctorOperation.getSelectedIndex();

                if (index == 0) {
                    JuniorDoctor newJunior = createJunior();
                    if (newJunior != null) {
                        doctors.addJunior(newJunior);
                        appendInfoToManagementInfoPanel("New junior doctor successfully added to the system.", "New junior doctor");
                        return;
                    }
                    appendInfoToManagementInfoPanel("New junior doctor couldn't added to the system.", "New junior doctor");
                    return;
                } else if (index == 1) {
                    SeniorDoctor newSenior = createSenior();
                    if (newSenior != null) {
                        doctors.addSenior(newSenior);
                        appendInfoToManagementInfoPanel("New senior doctor successfully added to the system.", "New senior doctor");
                        return;
                    }
                    appendInfoToManagementInfoPanel("New senior doctor couldn't added to the system.", "New senior doctor");
                    return;
                } else if (index == 2) {
                    String msg = doctors.retrieveDoctorDetailsBySsn(Integer.valueOf(textField_DoctorSsn.getText()));

                    if (msg != null) {
                        appendInfoToManagementInfoPanel(msg, "Doctor details");
                        return;
                    }

                    appendInfoToManagementInfoPanel("There is no doctor with given SSN.", "Doctor details");
                } else if (index == 3) {
                    boolean resp = doctors.getPromotedBySsn(Integer.valueOf(textField_DoctorSsn.getText()));

                    if (resp) {
                        appendInfoToManagementInfoPanel("Promotion is successful.", "Junior doctor promotion");
                        return;
                    }

                    appendInfoToManagementInfoPanel("There is no junior with given SSN.", "Junior doctor promotion");
                } else if (index == 4) {
                    boolean resp = doctors.removeDoctorBySsn(Integer.valueOf(textField_DoctorSsn.getText()));

                    if (resp) {
                        appendInfoToManagementInfoPanel("The doctor is successfully removed.", "Remove doctor");
                        return;
                    }

                    appendInfoToManagementInfoPanel("There is no doctor with given SSN.", "Remove doctor");
                } else {
                    appendInfoToManagementInfoPanel(doctors.retrieveAllDoctorDetails(), "All doctor details");
                }
            }
        });
        button_PatientSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int index = comboBox_PatientOperation.getSelectedIndex();

                if (index == 0) {
                    Patient newPatient = createPatient();
                    if (newPatient != null) {
                        patients.addPatient(newPatient);
                        appendInfoToManagementInfoPanel("New patient successfully added to the system.", "New patient");
                        return;
                    }
                    appendInfoToManagementInfoPanel("New patient couldn't added to the system.", "New patient");
                    return;
                } else if (index == 1) {
                    String msg = patients.retrievePatientDetailsBySsn(Integer.valueOf(textField_PatientSsn.getText()));

                    if (msg != null) {
                        appendInfoToManagementInfoPanel(msg, "Patient details");
                        return;
                    }

                    appendInfoToManagementInfoPanel("There is no patient with given SSN.", "Patient details");
                } else if (index == 2) {
                    String msg = patients.retrievePatientDepartmentBySsn(Integer.valueOf(textField_PatientSsn.getText()));

                    if (msg != null) {
                        appendInfoToManagementInfoPanel(msg, "Patient department");
                        return;
                    }

                    appendInfoToManagementInfoPanel("There is no patient with given SSN.", "Patient details");
                } else if (index == 3) {
                    boolean resp = patients.removePatient(Integer.valueOf(textField_PatientSsn.getText()));

                    if (resp) {
                        appendInfoToManagementInfoPanel("The patient is successfully removed.", "Remove patient");
                        return;
                    }

                    appendInfoToManagementInfoPanel("There is no patient with given SSN.", "Remove patient");
                } else {
                    appendInfoToManagementInfoPanel(patients.retrieveAllPatientDetails(), "All patient details");
                }
            }
        });
        button_NurseSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int index = comboBox_NurseOperation.getSelectedIndex();

                if (index == 0) {
                    Nurse newNurse = createNurse();
                    if (newNurse != null) {
                        nurses.addNurse(newNurse);
                        appendInfoToManagementInfoPanel("New nurse successfully added to the system.", "New nurse");
                        return;
                    }
                    appendInfoToManagementInfoPanel("New nurse couldn't added to the system.", "New nurse");
                    return;
                } else if (index == 1) {
                    String msg = nurses.retrieveNurseDetailsBySsn(Integer.valueOf(textField_NurseSsn.getText()));

                    if (msg != null) {
                        appendInfoToManagementInfoPanel(msg, "Nurse details");
                        return;
                    }

                    appendInfoToManagementInfoPanel("There is no nurse with given SSN.", "Nurse details");
                } else if (index == 2) {
                    boolean resp = nurses.removeNurse(Integer.valueOf(textField_NurseSsn.getText()));

                    if (resp) {
                        appendInfoToManagementInfoPanel("The nurse is successfully removed.", "Remove nurse");
                        return;
                    }

                    appendInfoToManagementInfoPanel("There is no nurse with given SSN.", "Remove nurse");
                } else {
                    appendInfoToManagementInfoPanel(nurses.retrieveAllNurseDetails(), "All nurse details");
                }
            }
        });
    }

    /**
     * The main program start running by this function.
     */
    public void run() {
        JFrame mainwindow = new JFrame();
        mainwindow.setContentPane(panel_Main);
        mainwindow.setSize(600, 700);
        mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainwindow.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                //tools.saveDataToFile("");
            }
        });

        mainwindow.setLocationRelativeTo(null);
        mainwindow.setVisible(true);


    }

    public void appendInfoToManagementInfoPanel(String msg) {
        textArea_MainInfoPanel.append(msg + "\n----------------------------------------\n");
        // For append to top, use the following code
        // textArea_MainInfoPanel.setText(msg + "\n----------------------------------------\n" + textArea_MainInfoPanel.getText());
    }

    public void appendInfoToManagementInfoPanel(String msg, String title) {
        appendInfoToManagementInfoPanel("[+] " + title + "\n" + msg);
    }

    public void doctorEnableOnlySenior() {
        doctorSetEnabledAll(true);
        textField_DoctorJobStartDate.setEnabled(false);
        textField_DoctorExpectedEndDate.setEnabled(false);
        textField_DoctorSsnOfResponsibleSenior.setEnabled(false);
    }

    public void doctorEnableOnlyJunior() {
        doctorSetEnabledAll(true);
        textField_DoctorEmploymentHistory.setEnabled(false);
    }

    public void doctorEnableOnlySsn() {
        doctorSetEnabledAll(false);
        textField_DoctorSsn.setEnabled(true);
    }

    public void doctorSetEnabledAll(boolean state) {
        textField_DoctorSsn.setEnabled(state);
        textField_DoctorName.setEnabled(state);
        textField_DoctorSurname.setEnabled(state);
        textField_DoctorSpecialization.setEnabled(state);
        textField_DoctorDepartment.setEnabled(state);
        textField_DoctorEmploymentHistory.setEnabled(state);
        textField_DoctorSalary.setEnabled(state);
        comboBox_DoctorGender.setEnabled(state);
        textField_DoctorDateOfBirth.setEnabled(state);
        textField_DoctorInsuranceType.setEnabled(state);
        textField_DoctorJobStartDate.setEnabled(state);
        textField_DoctorExpectedEndDate.setEnabled(state);
        textField_DoctorSsnOfResponsibleSenior.setEnabled(state);
    }

    public void doctorClearForm() {
        textField_DoctorSsn.setText("");
        textField_DoctorName.setText("");
        textField_DoctorSurname.setText("");
        textField_DoctorSpecialization.setText("");
        textField_DoctorDepartment.setText("");
        textField_DoctorEmploymentHistory.setText("");
        textField_DoctorSalary.setText("");
        //comboBox_DoctorGender.setEnabled(state);
        textField_DoctorDateOfBirth.setText("");
        textField_DoctorInsuranceType.setText("");
        textField_DoctorJobStartDate.setText("");
        textField_DoctorExpectedEndDate.setText("");
        textField_DoctorSsnOfResponsibleSenior.setText("");
    }

    // Management Functions
    public SeniorDoctor createSenior() {
        int ssn = Integer.valueOf(textField_DoctorSsn.getText());
        String name = textField_DoctorName.getName() + " " + textField_DoctorSurname.getText();
        char gender = comboBox_DoctorGender.getSelectedItem().toString().charAt(0);
        int insuranceType = Integer.valueOf(textField_DoctorInsuranceType.getText());
        String specialization = textField_DoctorSpecialization.getText();
        String department = textField_DoctorDepartment.getText();

        Date dateOfBirth = tools.stringToDate(textField_DoctorDateOfBirth.getText());
        if (dateOfBirth == null) {
            JOptionPane.showMessageDialog(null, "ERROR! Format of the birth date is wrong. Try again.");
            return null;
        }

        float salary = Float.valueOf(textField_DoctorSalary.getText());
        String employmentHistory = textField_DoctorEmploymentHistory.getText();

        return new SeniorDoctor(ssn, name, gender, dateOfBirth, insuranceType, specialization,
                department, employmentHistory, salary);
    }

    public Patient createPatient() {
        int ssn = Integer.valueOf(textField_PatientSsn.getText());
        String name = textField_PatientName.getName() + " " + textField_PatientSurname.getText();
        char gender = comboBox_PatientGender.getSelectedItem().toString().charAt(0);
        int insuranceType = Integer.valueOf(textField_PatientInsuranceType.getText());

        Date dateOfBirth = tools.stringToDate(textField_PatientDateOfBirth.getText());
        if (dateOfBirth == null) {
            JOptionPane.showMessageDialog(null, "ERROR! Format of the birth date is wrong. Try again.");
            return null;
        }

        return new Patient(ssn, name, gender, dateOfBirth, insuranceType, true);
    }

    public Nurse createNurse() {
        int ssn = Integer.valueOf(textField_NurseSsn.getText());
        String name = textField_NurseName.getName() + " " + textField_NurseSurname.getText();
        char gender = comboBox_NurseGender.getSelectedItem().toString().charAt(0);
        int insuranceType = Integer.valueOf(textField_NurseInsuranceType.getText());
        String department = textField_NurseDepartment.getText();

        Date dateOfBirth = tools.stringToDate(textField_NurseDateOfBirth.getText());
        if (dateOfBirth == null) {
            JOptionPane.showMessageDialog(null, "ERROR! Format of the birth date is wrong. Try again.");
            return null;
        }

        float salary = Float.valueOf(textField_NurseSalary.getText());

        return new Nurse(ssn, name, gender, dateOfBirth, insuranceType, department, salary);
    }

    public JuniorDoctor createJunior() {
        int ssn = Integer.valueOf(textField_DoctorSsn.getText());
        String name = textField_DoctorName.getName() + " " + textField_DoctorSurname.getText();
        char gender = comboBox_DoctorGender.getSelectedItem().toString().charAt(0);
        int insuranceType = Integer.valueOf(textField_DoctorInsuranceType.getText());
        String specialization = textField_DoctorSpecialization.getText();
        String department = textField_DoctorDepartment.getText();

        Date dateOfBirth = tools.stringToDate(textField_DoctorDateOfBirth.getText());
        if (dateOfBirth == null) {
            JOptionPane.showMessageDialog(null, "ERROR! Format of the birth date is wrong. Try again.");
            return null;
        }

        Date startDate = tools.stringToDate(textField_DoctorJobStartDate.getText());
        if (startDate == null) {
            JOptionPane.showMessageDialog(null, "ERROR! Format of the start date is wrong. Try again.");
            return null;
        }

        Date endDate = tools.stringToDate(textField_DoctorExpectedEndDate.getText());
        if (endDate == null) {
            JOptionPane.showMessageDialog(null, "ERROR! Format of the expected end date is wrong. Try again.");
            return null;
        }

        int responsibleDoctorSsn = Integer.valueOf(textField_DoctorSsnOfResponsibleSenior.getText());
        SeniorDoctor responsibleDoctor = doctors.getSenior(responsibleDoctorSsn);
        if (responsibleDoctor == null) {
            JOptionPane.showMessageDialog(null, "ERROR! SSN of responsible senior couldn't found. Try again.");
            return null;
        }

        float salary = Float.valueOf(textField_DoctorSalary.getText());

        return new JuniorDoctor(ssn, name, gender, dateOfBirth, insuranceType, specialization,
                department, startDate, endDate, responsibleDoctor, salary);
    }

    /**
     * Calculates total salary of all employes.
     *
     * @return float Total salary of all employees.
     */
    public float getTotalSalaries() {
        return doctors.getTotalDoctorSalaries() + nurses.getTotalNurseSalaries();
    }

    /**
     * Print outs current budget status of the hospital.
     */
    public void showBudgetStatus() {
        float totalIncome = patients.treatmentIncome();
        float totalSalaries = getTotalSalaries();

        String message =
                "Treatment incomes \t: " + String.valueOf(totalIncome) + "\n" +
                        "Total salaries \t: " + String.valueOf(totalSalaries) + "\n" +
                        "Budget status \t: " + String.valueOf(totalIncome - totalSalaries);

        appendInfoToManagementInfoPanel(message, "Budget status");
    }

    /**
     * Get input from user and according to given information, spend some annual leave of an employee.
     */
    public void spendAnnualLeaveBySsn() {
        int ssn;
        int noDays;

        try {
            ssn = Integer.valueOf(textField_ManagementLeaveSsn.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR! Please enter a SSN in numerical format.");
            return;
        }

        try {
            noDays = Integer.valueOf(textField_ManagementNumOfLeaveDays.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR! Please enter number of days in numerical format.");
            return;
        }

        if (doctors.spendAnnualLeave(ssn, noDays)) {
            appendInfoToManagementInfoPanel("Annual leave successfully spend for the doctor.", "Spend annual leave");
            return;
        }

        if (nurses.spendAnnualLeave(ssn, noDays)) {
            appendInfoToManagementInfoPanel("Annual leave successfully spend for the nurse.", "Spend annual leave");
            return;
        }

        JOptionPane.showMessageDialog(null, "ERROR! There is no employee with given SSN.");
    }
}
