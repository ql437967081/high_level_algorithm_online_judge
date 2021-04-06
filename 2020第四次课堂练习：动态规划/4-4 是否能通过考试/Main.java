import java.util.Arrays;
import java.util.Scanner;
import static java.lang.Math.min;
import static java.lang.Math.max;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t-- > 0) {
            int n = scan.nextInt();
            int h = scan.nextInt();
            int p = scan.nextInt();
            int[] dp = new int[p + 1];
            Arrays.fill(dp, h + 1);
            dp[0] = 0;
            while (n-- > 0) {
                int time = scan.nextInt();
                int score = scan.nextInt();
                for (int i = p; i > 0; i--) {
                    dp[i] = min(dp[i], dp[max(0, i - score)] + time);
                }
            }
            if (dp[p] <= h) {
                System.out.println("YES " + dp[p]);
            } else {
                System.out.println("NO");
            }
        }
    }
}
