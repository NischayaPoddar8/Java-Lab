public class LCM {
    public static void main(String[] args) {
        int n1 = 12, n2 = 18;
        int a = n1, b = n2;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        int gcd = a;
        int lcm = (n1 * n2) / gcd;
        System.out.println("LCM of " + n1 + " and " + n2 + " is: " + lcm);
    }
}
