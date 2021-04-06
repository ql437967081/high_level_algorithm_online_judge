import java.util.Arrays;
import java.util.Scanner;
import static java.lang.Math.min;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t-- > 0) {
            int n = scan.nextInt();
            int[] dp = new int[3];
            int[] ndp = new int[3];
            Arrays.fill(dp, 0);
            while (n-- > 0) {
                int brinjal = scan.nextInt();
                int carrot = scan.nextInt();
                int tomato = scan.nextInt();
                ndp[0] = min(dp[1], dp[2]) + brinjal;
                ndp[1] = min(dp[0], dp[2]) + carrot;
                ndp[2] = min(dp[0], dp[1]) + tomato;
                int[] tmp = dp;
                dp = ndp;
                ndp = tmp;
            }
            System.out.println(min(dp[0], min(dp[1], dp[2])));
        }
    }
}
