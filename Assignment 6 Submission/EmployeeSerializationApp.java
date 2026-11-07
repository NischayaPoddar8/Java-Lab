import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeSerializationApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();

        System.out.print("Enter number of employees: ");

        try {
            int count = Integer.parseInt(sc.nextLine());

            for (int i = 1; i <= count; i++) {
                System.out.println("Employee " + i);

                System.out.print("Enter employee ID: ");
                int id = Integer.parseInt(sc.nextLine());

                System.out.print("Enter employee name: ");
                String name = sc.nextLine();

                System.out.print("Enter department: ");
                String department = sc.nextLine();

                System.out.print("Enter salary: ");
                double salary = Double.parseDouble(sc.nextLine());

                employees.add(new Employee(id, name, department, salary));
            }

            System.out.print("Enter serialization file path: ");
            String filePath = sc.nextLine();

            try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filePath))) {
                output.writeObject(employees);
                System.out.println("Employee records serialized successfully.");
            }

            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath))) {
                List<?> retrievedEmployees = (List<?>) input.readObject();

                System.out.println("\nRetrieved Employee Records:");
                for (Object employee : retrievedEmployees) {
                    System.out.println(employee);
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("File processing error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Employee class could not be found: " + e.getMessage());
        }

        sc.close();
    }
}
