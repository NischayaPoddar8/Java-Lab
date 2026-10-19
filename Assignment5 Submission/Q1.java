import java.util.Arrays;

public class Q1 {
    public static <T extends Number> double sum(T[] values) {
        double total = 0.0;
        for (T value : values) {
            total += value.doubleValue();
        }
        return total;
    }

    public static void main(String[] args) {
        Integer[] integers = {10, 20, 30, 40};
        Float[] floats = {1.5f, 2.5f, 3.5f};
        Double[] doubles = {2.25, 4.75, 6.0};

        System.out.println("Sum of integers: " + sum(integers));
        System.out.println("Sum of floats: " + sum(floats));
        System.out.println("Sum of doubles: " + sum(doubles));
    }
}
