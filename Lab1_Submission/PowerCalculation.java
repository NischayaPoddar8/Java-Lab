public class PowerCalculation {
    public static void main(String[] args) {
        double base = 2;
        int exponent = 5;
        double result = 1;
        for (int i = 1; i <= Math.abs(exponent); i++) {
            result *= base;
        }
        if (exponent < 0) {
            result = 1.0 / result;
        }
        System.out.println(base + "^" + exponent + " = " + result);
    }
}
