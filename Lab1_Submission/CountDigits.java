public class CountDigits {
    public static void main(String[] args) {
        int num = 123456;
        int count = 0;
        int temp = Math.abs(num);
        if (temp == 0) {
            count = 1;
        } else {
            while (temp > 0) {
                temp /= 10;
                count++;
            }
        }
        System.out.println("Number of digits in " + num + " = " + count);
    }
}
