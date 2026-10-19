import java.util.Objects;
import java.util.Optional;

class Utility {
    public static <T> boolean isEqual(T a, T b) {
        return Objects.equals(a, b);
    }

    public static <T> T getDefault(Optional<T> value, T defaultValue) {
        return Objects.requireNonNull(value).orElse(defaultValue);
    }

    public static <T extends Comparable<? super T>> T max(T a, T b) {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);
        return a.compareTo(b) >= 0 ? a : b;
    }

    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }
}

public class Q5 {
    public static void main(String[] args) {
        System.out.println("Strings equal: " + Utility.isEqual("Java", "Java"));
        System.out.println("Integers equal: " + Utility.isEqual(10, 20));
        System.out.println("Default value: " + Utility.getDefault(Optional.<String>empty(), "Not available"));
        System.out.println("Maximum integer: " + Utility.max(15, 27));
        System.out.println("Maximum string: " + Utility.max("Apple", "Orange"));
        System.out.println("Is null: " + Utility.isNull(null));
        System.out.println("Is null: " + Utility.isNull("Hello"));
    }
}
