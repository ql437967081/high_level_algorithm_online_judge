import java.util.Scanner;
import static java.lang.Math.max;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t-- > 0) {
            int n = scan.nextInt();
            int[] dp = new int[] {0, 0};
            int[] ndp = new int[2];
            int maxSum = Integer.MIN_VALUE;
            while (n-- > 0) {
                int x = scan.nextInt();
                ndp[0] = max(dp[0] + x, x);
                ndp[1] = max(dp[1] + x, dp[0] != 0 ? dp[0] : dp[1] + x - 1);
                int[] tmp = dp;
                dp = ndp;
                ndp = tmp;
                maxSum = max(maxSum, max(dp[0], dp[1]));
            }
            System.out.println(maxSum);
        }
    }
}
