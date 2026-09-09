import java.util.Scanner;

public class Experiment3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter first number: ");
            double a = Double.parseDouble(sc.nextLine());

            System.out.print("Enter second number: ");
            double b = Double.parseDouble(sc.nextLine());

            System.out.print("Enter operator (+, -, *, /): ");
            String operator = sc.nextLine();

            switch (operator) {
                case "+":
                    System.out.println("Result: " + (a + b));
                    break;
                case "-":
                    System.out.println("Result: " + (a - b));
                    break;
                case "*":
                    System.out.println("Result: " + (a * b));
                    break;
                case "/":
                    if (b == 0) {
                        throw new ArithmeticException("Division by zero is not allowed");
                    }
                    System.out.println("Result: " + (a / b));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator");
            }
        } 
        catch (NumberFormatException e) {
            System.out.println("Invalid numeric input");
        } 
        catch (ArithmeticException e) {
            System.out.println("Division by zero: " + e.getMessage());
        } 
        catch (IllegalArgumentException e) {
            System.out.println("Invalid operator: " + e.getMessage());
        } 
        finally {
            sc.close();
        }
    }
}
