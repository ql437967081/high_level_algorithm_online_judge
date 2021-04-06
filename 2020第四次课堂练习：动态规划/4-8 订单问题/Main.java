import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t-- > 0) {
            int n = scan.nextInt();
            int x = scan.nextInt();
            int y = scan.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            int[][] dp = new int[x + 1][y + 1];
            dp[0][0] = 0;
            for (int i = 0; i < n; i++) {
                a[i] = scan.nextInt();
            }
            for (int i = 0; i < n; i++) {
                b[i] = scan.nextInt();
            }
            int maxMount = 0;
            for (int i = 0; i < n; i++) {
                for (int u = max(0, i + 1 - y); u <= min(x, i + 1); u++) {
                    dp[u][i + 1 - u] = max(u > 0 ? dp[u - 1][i + 1 - u] + a[i] : 0, i + 1 - u > 0 ? dp[u][i - u] + b[i] : 0);
                    if (i == n - 1) {
                        maxMount = max(maxMount, dp[u][i + 1 - u]);
                    }
                }
            }
            System.out.println(maxMount);
        }
    }
}
