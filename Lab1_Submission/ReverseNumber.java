public class ReverseNumber {
    public static void main(String[] args) {
        int num = 1234;
        int reversed = 0;
        int temp = num;
        while (temp != 0) {
            int digit = temp % 10;
            reversed = reversed * 10 + digit;
            temp /= 10;
        }
        System.out.println("Original: " + num + ", Reversed: " + reversed);
    }
}
