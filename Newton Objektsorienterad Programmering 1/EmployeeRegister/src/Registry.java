import java.util.ArrayList;
import java.util.Scanner;

public class Registry {

    private ArrayList<Employee> employeeRegister;

    public Registry() { employeeRegister = new ArrayList<>(); }

    public Registry(ArrayList<Employee> otherRegistry) { employeeRegister = otherRegistry; }



    public void addNewEmployee(Scanner inputScanner) {

        int newAge;


        String newFirstName;
        String newLastName;
        String newPosition;
        String newNumber;

        System.out.println("Enter data for new employee:");
        System.out.print("First Name: ");
        newFirstName = inputScanner.nextLine();
        System.out.print("Last Name: ");
        newLastName = inputScanner.nextLine();
        System.out.print("Age: ");
        newAge = inputScanner.nextInt();
        System.out.print("For what position has " + newFirstName + " " + newLastName + " been hired? ");
        inputScanner.next();
        newPosition = inputScanner.nextLine();

        int tempNumber = (int) (1 + (Math.random()*999999));
        newNumber = "" + tempNumber;
        while (newNumber.length() < 7) {
            newNumber = "0" + newNumber;
        }

        System.out.println("Employee number: " + newNumber);

        switch(newPosition) {
            case "Programmer" :
                employeeRegister.add(new Programmer())
        }



    }



}
