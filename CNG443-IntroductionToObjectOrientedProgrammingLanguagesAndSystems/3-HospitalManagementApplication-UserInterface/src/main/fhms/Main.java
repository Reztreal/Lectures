package main.fhms;

import test.fhms.MainTest;
/**
 * This class is implemented to handle arguments, beginning variables
 * preparations of system run.
 *
 * @author Furkan Tokac
 */
public class Main {
    /**
     * Everything start with this function.
     *
     * @param args
     */
    public static void main(String[] args) {
        Mainwindow mainwindow = MainTest.createDumpProgram();
        mainwindow.run();
    }
}
