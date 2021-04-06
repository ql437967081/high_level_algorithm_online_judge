import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.max;

public class Main {
    private static Scanner scan;
    public static void main(String[] args) {
        scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t-- > 0) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            int[] road1 = inputRoad(n);
            int[] road2 = inputRoad(m);
            Arrays.sort(road1);
            Arrays.sort(road2);
            int sum = 0, sum1 = 0, sum2 = 0;
            for (int i = 0, j = 0; i < n || j < m;) {
                switch (i == n ? 1 : (j == m ? -1
                        : Integer.compare(road1[i], road2[j]))) {
                    case -1: sum1 += road1[i++]; break;
                    case 1: sum2 += road2[j++]; break;
                    case 0: sum += max(sum1, sum2); sum1 = road1[i++]; sum2 = road2[j++]; break;
                }
            }
            sum += max(sum1, sum2);
            System.out.println(sum);
        }
    }

    private static int[] inputRoad(int n) {
        int[] road = new int[n];
        for (int i = 0; i < n; i++) {
            road[i] = scan.nextInt();
        }
        return road;
    }
}
