import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static java.lang.Math.max;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t-- > 0) {
            int n = scan.nextInt();
            Map<Integer, Integer> maxSumMap = new HashMap<>(1 << 10);
            int maxSum = 0;
            while (n-- > 0) {
                int x = scan.nextInt();
                int state = 0;
                for (int y = x; y > 0; y /= 10) {
                    state |= (1 << (y % 10));
                }
                if (x > maxSumMap.getOrDefault(state, 0)) {
                    maxSumMap.put(state, x);
                    maxSum = max(maxSum, x);
                    Map<Integer, Integer> addedSumMap = new HashMap<>(1 << 10);
                    for (Map.Entry<Integer, Integer> entry: maxSumMap.entrySet()) {
                        int prevState = entry.getKey();
                        if ((prevState & state) == 0) {
                            int newState = prevState | state;
                            int newSum = x + entry.getValue();
                            if (newSum > maxSumMap.getOrDefault(newState, 0)) {
                                addedSumMap.put(newState, newSum);
                                maxSum = max(maxSum, newSum);
                            }
                        }
                    }
                    maxSumMap.putAll(addedSumMap);
                }
            }
            System.out.println(maxSum);
        }
    }
}
