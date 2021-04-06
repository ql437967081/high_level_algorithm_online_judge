import java.util.Scanner;
import static java.lang.Math.min;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        scan.nextLine();
        while (t-- > 0) {
            char[] chars = scan.nextLine().toCharArray();
            int n = chars.length;
            int[] sum = new int[n + 1];
            sum[0] = 0;
            for (int i = 0; i < n; i++) {
                sum[i + 1] = sum[i] + chars[i] - '0';
            }
            int maxK = 0;
            for (int i = 1; n - i > maxK; i++) {
                for (int k = min(i, n - i); k > maxK; k--) {
                    if (sum[i] << 1 == sum[i + k] + sum[i - k]) {
                        maxK = k;
                        break;
                    }
                }
            }
            System.out.println(maxK << 1);
        }
    }
}
