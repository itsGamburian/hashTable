/**********************
 Hambartzum Gamburian
 Driver.java
 **********************/

import java.util.Scanner;
import java.io.File;

public class Driver {

    public static void main(String[] args) {

        File file = new File("students.txt");
        Scanner input = new Scanner(System.in);

        HashTable table = new HashTable();

        if (file.exists()) {
            table.readInitialFromFile();
        }
        else {
            System.out.println("The \"students.txt\" file is either corrupt or is not found.");
            System.exit(0);
        }

        String cmd = "";

        while(!cmd.equals("c")) {

            System.out.println("\nFind Students\n====================\nA. Find Student by ID\nB. Show All Students\nC. Exit");
            System.out.print("Enter Choice Using Letters: ");

            cmd = input.nextLine().toLowerCase();
            System.out.println();

            switch(cmd) {
                case "a": System.out.print("Please enter a 4 digit number to search the database for: ");
                    String in = input.nextLine();
                    String name = table.getStudentName(in);
                    if (!name.equals("")) {
                        System.out.println("\n" + in + " " + name);
                    }
                    else {
                        System.out.println("\nNo name data exists with that ID in the database.");
                    }
                    break;

                case "b": table.showAll();
                    break;


                default:  if (!cmd.equals("c")) {
                    System.out.println("Unrecognized command, please try again.");
                }
                    break;
            }

        }
        System.out.println("Thank you for finding with us. Exitting Program...");
    }

}