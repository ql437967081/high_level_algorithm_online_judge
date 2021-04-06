import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.min;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t-- > 0) {
            int n = scan.nextInt();
            int amount = scan.nextInt();
            int[] a = new int[n];
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, amount + 1);
            dp[0] = 0;
            for (int i = 0; i < n; i++)
                a[i] = scan.nextInt();
            Arrays.sort(a);
            for (int i = 1; i <= amount; i++)
                for (int j = 0; j < n && i - a[j] >= 0; j++)
                    dp[i] = min(dp[i], 1 + dp[i - a[j]]);
            if(dp[amount] == amount + 1)
                dp[amount] = -1;
            System.out.println(dp[amount]);
        }
    }
}
