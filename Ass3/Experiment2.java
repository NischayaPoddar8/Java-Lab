public class Experiment2 {
    public static void main(String[] args) {
        try {
            System.out.println("Outer try started");

            try {
                int[] numbers = {10, 20, 30};
                System.out.println(numbers[5]);
            } 
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Inner catch: " + e.getMessage());
            }

            int result = 20 / 0;
            System.out.println(result);
        } 
        catch (ArithmeticException e) {
            System.out.println("Outer catch: " + e.getMessage());
        }
    }
}
