import java.util.Scanner;

public class Excecutor {

    public static void main(String[] args) {

        int choice = -1;

        Registry employeeRegistry = new Registry();

        Scanner inputScanner = new Scanner(System.in);

        while(true) {
            choice = -1;



            System.out.println("Welcome to the employee register. What would you like to do?");
            System.out.println("1: Add New Employee.");
            System.out.println("2: Search Employee Registry");
            System.out.print("Please choose an option from above, or input 0 to quit.");
            choice = inputScanner.nextInt();
            if (choice == 1) employeeRegistry.addNewEmployee(new Scanner(System.in));
            else if (choice == 2) System.out.println("Functionality not implemeted yet.");
            else if (choice == 0) break;


        }
        System.out.println("This is a secret list of all employees in company X");


    }


}
