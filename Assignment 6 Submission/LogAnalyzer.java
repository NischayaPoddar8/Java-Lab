import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LogAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter log file path: ");
        String inputFile = sc.nextLine();

        System.out.print("Enter output file path for filtered entries: ");
        String outputFile = sc.nextLine();

        int totalEntries = 0;
        int filteredEntries = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                totalEntries++;

                if (line.contains("ERROR") || line.contains("WARNING")) {
                    writer.write(line);
                    writer.newLine();
                    filteredEntries++;
                }
            }

            System.out.println("Total log entries: " + totalEntries);
            System.out.println("ERROR/WARNING entries: " + filteredEntries);
            System.out.println("Filtered entries saved to " + outputFile);
        } catch (IOException e) {
            System.out.println("File processing error: " + e.getMessage());
        }

        sc.close();
    }
}
