import java.util.Optional;

public class Q2 {
    public static void main(String[] args) {
        Optional<String> email = Optional.of("student@example.com");
        Optional<String> emptyEmail = Optional.empty();

        email.ifPresent(value -> System.out.println("Email: " + value));
        System.out.println("Default email: " + emptyEmail.orElse("default@example.com"));

        try {
            String requiredEmail = emptyEmail.orElseThrow(
                () -> new IllegalStateException("Email address is missing")
            );
            System.out.println("Required email: " + requiredEmail);
        } catch (IllegalStateException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
