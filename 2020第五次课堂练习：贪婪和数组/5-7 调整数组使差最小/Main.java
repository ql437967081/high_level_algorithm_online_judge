import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Math.max;

public class Main {
    private static class Key {
        private int n;
        private int sum;
        private int m;

        private Key(int n, int sum, int m) {
            this.n = n;
            this.sum = sum;
            this.m = m;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (n != key.n) return false;
            if (sum != key.sum) return false;
            return m == key.m;
        }

        @Override
        public int hashCode() {
            int result = n;
            result = 31 * result + sum;
            result = 31 * result + m;
            return result;
        }
    }

    private static Map<Key, Integer> dp;

    private static int f(int n, int sum, int m) {
        Key key = new Key(n, sum, m);
        if (dp.containsKey(key)) return dp.get(key);

        if (n < m) return -1;

        int minSum = calcSum(0, m);
        if (sum < minSum) return -1;

        int maxSum = calcSum(n - m, n);
        if (sum >= maxSum) return maxSum;

        int notSelect = f(n - 1, sum, m);
        int select = f(n - 1, sum - a[n], m - 1);
        int res = max(notSelect, select == -1 ? -1 : select + a[n]);
        dp.put(key, res);
        return res;
    }

    private static int calcSum(int begin, int end) {
        return sum[end] - sum[begin];
    }

    private static int[] a;
    private static int[] sum;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        scan.nextLine();

        while (t-- > 0) {
            String[] arr1 = scan.nextLine().split(" ");
            String[] arr2 = scan.nextLine().split(" ");
            int n = arr1.length;
            int _2n = n << 1;

            a = new int[_2n + 1];
            for (int i = 0; i < n; i++)
                a[i + 1] = Integer.parseInt(arr1[i]);
            for (int i = 0; i < n; i++)
                a[n + i + 1] = Integer.parseInt(arr2[i]);

            Arrays.sort(a, 1, _2n + 1);
            sum = new int[_2n + 1];
            for (int i = 1; i <= _2n; i++)
                sum[i] = sum[i - 1] + a[i];

            dp = new HashMap<>(_2n * n);
            int maxSelectSum = f(_2n, sum[_2n] >>> 1, n);
            System.out.println(sum[_2n] - (maxSelectSum << 1));
        }
    }
}
