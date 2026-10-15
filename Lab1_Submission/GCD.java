public class GCD {
    public static void main(String[] args) {
        int n1 = 81, n2 = 153;
        int a = n1, b = n2;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        System.out.println("GCD of " + n1 + " and " + n2 + " is: " + a);
    }
}
