import java.io.*;
import java.util.Scanner;

public class FileHandlingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter source binary file path: ");
        String source = sc.nextLine();

        System.out.print("Enter destination binary file path: ");
        String destination = sc.nextLine();

        try (FileInputStream input = new FileInputStream(source);
             FileOutputStream output = new FileOutputStream(destination)) {
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }

            System.out.println("Binary file copied successfully.");
        } catch (IOException e) {
            System.out.println("Error copying binary file: " + e.getMessage());
        }

        System.out.print("Enter student details text file path: ");
        String textFile = sc.nextLine();

        try (FileWriter writer = new FileWriter(textFile, true)) {
            System.out.print("Enter number of students: ");
            int count = Integer.parseInt(sc.nextLine());

            for (int i = 1; i <= count; i++) {
                System.out.println("Student " + i);
                System.out.print("Enter student ID: ");
                String id = sc.nextLine();
                System.out.print("Enter student name: ");
                String name = sc.nextLine();
                System.out.print("Enter student course: ");
                String course = sc.nextLine();

                writer.write(id + "," + name + "," + course + System.lineSeparator());
            }

            System.out.println("Student details saved successfully.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error writing student details: " + e.getMessage());
        }

        try (FileReader reader = new FileReader(textFile)) {
            System.out.println("\nStudent Records:");
            int character;

            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
        } catch (IOException e) {
            System.out.println("Error reading student details: " + e.getMessage());
        }

        sc.close();
    }
}
