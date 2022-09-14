import java.util.Scanner;

public class Firm {
    public static void main(String[] args) {  // main method
        Scanner sc = new Scanner(System.in, "cp1251");
        int number; // number of employees
        while (true) { // insuring a number was entered
            System.out.print("Enter the number of employees: ");
            try {
                number = Integer.parseInt(sc.nextLine());
                if(number > 0){
                    break;
                }else{
                    System.out.print("Expecting a positive number. ");
                }
            } catch (NumberFormatException ignore) {
                System.out.print("Expecting a number. ");
            }
        }
        Employee[] employees = new Employee[number]; // instantiating an array of Employee objects
        for(int i = 0; i < employees.length; i++){ // filling each object with information from console
            employees[i] = new Employee(); // instantiating each object
            System.out.println("\nEnter the information of employee " + (i + 1) + ": ");
            System.out.print("Last name: ");
            employees[i].last_name = sc.nextLine();
            System.out.print("First name: ");
            employees[i].first_name = sc.nextLine();
            System.out.print("Middle name: ");
            employees[i].middle_name = sc.nextLine();
            System.out.print("Position: ");
            employees[i].position = sc.nextLine();
            while (true) { // insuring a number was entered
                System.out.print("Salary: ");
                try {
                    employees[i].salary = Double.parseDouble(sc.nextLine());
                    break;
                } catch (NumberFormatException ignore) {
                    System.out.print("Expecting a number. ");
                }
            }
        }
        // output entered information on the screen
        System.out.println("\nCompany employees:");
        System.out.format("%15s%15s%15s%15s%15s%n", "last name", "first name", "middle name", "position", "salary");
        for (Employee employee : employees) {
            System.out.format("%15s%15s%15s%15s%15.2f%n",
                    employee.last_name, employee.first_name, employee.middle_name, employee.position, employee.salary);
        }
    }
}