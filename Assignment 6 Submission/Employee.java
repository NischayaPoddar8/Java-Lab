import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private int employeeId;
    private String name;
    private String department;
    private double salary;

    public Employee(int employeeId, String name, String department, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId
                + ", Name: " + name
                + ", Department: " + department
                + ", Salary: " + salary;
    }
}
