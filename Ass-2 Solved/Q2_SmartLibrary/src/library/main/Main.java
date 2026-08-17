package library.main;

import library.model.*;
import library.service.LibraryUtility;
import library.util.InputValidator;

public class Main {
    public static void main(String[] args) {

        LibraryResource[] resources = {
            new Book(1, "Clean Code", "Robert C. Martin"),
            new DigitalResource(2, "Java Documentation", "Oracle"),
            new Book(3, "Effective Java", "Joshua Bloch"),
            new DigitalResource(4, "Computer Networks", "Andrew S. Tanenbaum"),
            new Book(5, "Operating System Concepts", "Silberschatz")
        };

        int[] overdueDays = {5, 10, 0, 7, 3};

        double totalFine = 0;

        System.out.println("Library: " + LibraryResource.getLibraryName());
        System.out.println("==========================================");

        for (int i = 0; i < resources.length; i++) {
            if (!InputValidator.validateResourceId(resources[i].getResourceId())) {
                System.out.println("Invalid resource ID.");
                continue;
            }

            System.out.println("\nResource " + (i + 1));
            ((Printable) resources[i]).printDetails();

            double fine = LibraryUtility.calculateFine(resources[i], overdueDays[i]);
            System.out.println("Overdue Days: " + overdueDays[i]);
            System.out.println("Fine: Rs. " + fine);

            totalFine += fine;
        }

        System.out.println("\n==========================================");
        System.out.println("Total Fine: Rs. " + totalFine);
        LibraryResource.displayTotalResources();
    }
}
