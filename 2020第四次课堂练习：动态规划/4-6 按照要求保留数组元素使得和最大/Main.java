import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t-- > 0) {
            int n = scan.nextInt();
            int[] a = new int[n];
            Map<Integer, Integer> map = new HashMap<>(n);
            for (int i = 0; i < n; i++) {
                a[i] = scan.nextInt();
                map.put(a[i], map.getOrDefault(a[i], 0) + 1);
            }
            Arrays.sort(a);
            int sum = 0;
            for (int i = n - 1; i >= 0; i--) {
                int cnt = map.get(a[i]);
                if (cnt > 0) {
                    sum += a[i];
                    map.put(a[i], cnt - 1);
                    if (map.containsKey(a[i] - 1)) {
                        map.put(a[i] - 1, map.get(a[i] - 1) - 1);
                    }
                }
            }
            System.out.println(sum);
        }
    }
}
