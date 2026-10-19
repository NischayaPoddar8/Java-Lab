import java.util.Objects;
import java.util.Optional;

class Result<T extends Number> {
    private final String studentName;
    private final T marks;
    private final double maximumMarks;
    private final double passingPercentage;

    public Result(String studentName, T marks, double maximumMarks, double passingPercentage) {
        this.studentName = Objects.requireNonNull(studentName, "Student name is required");
        this.marks = marks;
        if (maximumMarks <= 0 || passingPercentage < 0 || passingPercentage > 100) {
            throw new IllegalArgumentException("Invalid marks or passing threshold");
        }
        this.maximumMarks = maximumMarks;
        this.passingPercentage = passingPercentage;
    }

    public Optional<T> getMarks() {
        return Optional.ofNullable(marks);
    }

    public double getPercentage() {
        return getMarks().orElseThrow(() -> new IllegalStateException("Marks are unavailable"))
            .doubleValue() / maximumMarks * 100.0;
    }

    public boolean hasPassed() {
        return getPercentage() >= passingPercentage;
    }

    public void display() {
        System.out.println("Student: " + studentName);
        getMarks().ifPresentOrElse(
            value -> {
                System.out.println("Marks: " + value);
                System.out.printf("Percentage: %.2f%%%n", getPercentage());
                System.out.println("Result: " + (hasPassed() ? "Pass" : "Fail"));
            },
            () -> System.out.println("Marks are unavailable")
        );
    }
}

public class Q3 {
    public static void main(String[] args) {
        Result<Integer> first = new Result<>("Aman", 82, 100, 40);
        Result<Double> second = new Result<>("Riya", 35.5, 50, 40);
        Result<Integer> third = new Result<>("Karan", null, 100, 40);

        first.display();
        System.out.println();
        second.display();
        System.out.println();
        third.display();
    }
}
