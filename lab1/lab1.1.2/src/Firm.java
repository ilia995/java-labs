import java.util.Scanner;

public class Firm {
    static Scanner sc = new Scanner(System.in, "cp1251");
    public static void main(String[] args) { // main method

        int number = getInt("Enter the number of employees"); // getting number of employees with type check
        if(number == 0){ // exit if 0 is entered
            System.out.println("Exiting...");
            return;
        }
        Employee[] employees = new Employee[number]; // instantiating an array of Employee objects
        for(int i = 0; i < employees.length; i++){ // filling each Employee object with information from console
            employees[i] = new Employee(); // instantiating each Employee object
            System.out.println("\nEnter the information of employee " + (i + 1) + ": ");
            System.out.print("Last name: ");
            employees[i].last_name = sc.nextLine();
            System.out.print("First name: ");
            employees[i].first_name = sc.nextLine();
            System.out.print("Middle name: ");
            employees[i].middle_name = sc.nextLine();
            System.out.print("Position: ");
            employees[i].position = sc.nextLine();
            employees[i].children = getInt("Enter the number of children");

            if(employees[i].children > 0){ // if the employee has children
                employees[i].ch = new Child[employees[i].children]; // instantiating an array of Child objects
                for(int j = 0; j < employees[i].children; j++){ // filling each Child object with information from console
                    employees[i].ch[j] = new Child(); // instantiating each Child object
                    System.out.println("Information of child " + (j + 1) + ": ");
                    System.out.print("Name: ");
                    employees[i].ch[j].name = sc.nextLine();
                    employees[i].ch[j].age = getInt("Age");
                }
            }
        }
        // print entered information on the screen
        System.out.println("\nCompany employees:");
        System.out.format("%15s%15s%15s%15s%n", "last name", "first name", "middle name", "position");
        for (Employee employee : employees) {
            System.out.format("%15s%15s%15s%15s%n",
                    employee.last_name,
                    employee.first_name,
                    employee.middle_name,
                    employee.position);
            System.out.println("children: " + employee.children);
            if (employee.children > 0){
                for (Child ch : employee.ch){
                    System.out.format("name: %6s\t\tage: %3d%n", ch.name, ch.age);
                }
            }
            System.out.println();
        }
    }
    public static int getInt(String s){ // method to check if an integer was entered
        while (true) {
            System.out.print(s + ": ");
            try {
                return Math.abs(Integer.parseInt(sc.nextLine()));
            } catch (NumberFormatException ignore) {
                System.out.print("Expecting a number. ");
            }
        }
    }
}