public class Employee {

    private int age;
    private int employeeNumber;

    private String firstName;
    private String lastName;
    private String position;

    public Employee(String fn, String ln, String pos, int number, int yearsOld) {

        firstName = fn;
        lastName = ln;

        position = pos;

        employeeNumber = number;

        age = yearsOld;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }
}
