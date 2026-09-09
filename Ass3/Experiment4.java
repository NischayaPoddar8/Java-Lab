import java.util.Scanner;

class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) {
        super(message);
    }
}

public class Experiment4 {
    static int readMarks(Scanner sc, int subject) throws InvalidMarksException {
        System.out.print("Enter marks for subject " + subject + ": ");
        int marks = sc.nextInt();

        if (marks < 0 || marks > 100) {
            throw new InvalidMarksException("Marks must be between 0 and 100");
        }

        return marks;
    }

    static String getGrade(double percentage) {
        if (percentage >= 90) return "A+";
        if (percentage >= 80) return "A";
        if (percentage >= 70) return "B";
        if (percentage >= 60) return "C";
        if (percentage >= 50) return "D";
        return "F";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter number of subjects: ");
            int subjects = sc.nextInt();

            if (subjects <= 0) {
                throw new InvalidMarksException("Number of subjects must be positive");
            }

            int total = 0;

            for (int i = 1; i <= subjects; i++) {
                total += readMarks(sc, i);
            }

            double percentage = (double) total / (subjects * 100) * 100;

            System.out.println("Total: " + total);
            System.out.printf("Percentage: %.2f%%%n", percentage);
            System.out.println("Grade: " + getGrade(percentage));
        } 
        catch (InvalidMarksException e) {
            System.out.println("InvalidMarksException: " + e.getMessage());
        } 
        catch (java.util.InputMismatchException e) {
            System.out.println("Invalid numeric input");
        } 
        finally {
            sc.close();
        }
    }
}
