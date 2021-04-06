import java.util.Arrays;
import java.util.Scanner;
import static java.lang.Math.min;
import static java.lang.Math.max;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t-- > 0) {
            int k = scan.nextInt();
            int n = scan.nextInt();
            int[] sum = new int[n + 1];
            int[] maxLen = new int[k + 1];
            sum[0] = maxLen[0] = 0;
            for (int i = 1; i <= n; i++) {
                int board = scan.nextInt();
                sum[i] = sum[i - 1] + board;
                if (i <= k) {
                    maxLen[i] = max(maxLen[i - 1], board);
                }
            }

            int[] dp = Arrays.copyOf(sum, n + 1);
            int[] ndp = new int[n + 1];

            for (int i = 2; i <= k; i++) {
                ndp[0] = 0;
                for (int j = 1; j <= n; j++) {
                    if (j <= i) {
                        ndp[j] = maxLen[j];
                        continue;
                    }
                    ndp[j] = sum[j];
                    for (int m = i - 1; m <= j; m++) {
                        ndp[j] = min(ndp[j], max(dp[m], sum[j] - sum[m]));
                    }
                }
                int[] tmp = dp;
                dp = ndp;
                ndp = tmp;
            }

            System.out.println(dp[n]);
        }
    }
}
