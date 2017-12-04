package main.fhms;

import test.fhms.MainTest;

import java.util.Scanner;

/**
 * This class is implemented to handle arguments, beginning variables
 * preparations of system run.
 *
 * @author Furkan Tokac
 */
public class Main {
    private static Scanner scan = new Scanner(System.in);

    /**
     * Everything start with this function.
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Do you want to fill start program with dump data (1 for yes, 0 for no): ");
        int choice = scan.nextInt();
        scan.nextLine();

        Fhms mainProgram;

        if( choice==1 )
            mainProgram = MainTest.createDumpProgram();
        else
            mainProgram = new Fhms();

        mainProgram.run();
    }
}
