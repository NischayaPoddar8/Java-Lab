import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Scanner;

public class Q4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine().trim();
        System.out.print("Enter event date (yyyy-MM-dd): ");
        LocalDate eventDate = LocalDate.parse(scanner.nextLine().trim());
        System.out.print("Enter start time (HH:mm): ");
        LocalTime startTime = LocalTime.parse(scanner.nextLine().trim());
        System.out.print("Enter venue (leave blank if not specified): ");
        String venueInput = scanner.nextLine().trim();
        Optional<String> venue = venueInput.isEmpty() ? Optional.empty() : Optional.of(venueInput);

        LocalDate today = LocalDate.now();
        System.out.println("\nEvent: " + eventName);
        System.out.println("Date: " + eventDate);
        System.out.println("Start time: " + startTime);
        System.out.println("End time: " + startTime.plusHours(2));
        System.out.println("Venue: " + venue.orElse("Venue not specified"));

        if (eventDate.isAfter(today)) {
            System.out.println("Event date is in the future.");
        } else if (eventDate.isEqual(today)) {
            System.out.println("Event date is today.");
        } else {
            System.out.println("Event date is in the past.");
        }

        long daysRemaining = ChronoUnit.DAYS.between(today, eventDate);
        System.out.println("Days remaining: " + daysRemaining);
        scanner.close();
    }
}
