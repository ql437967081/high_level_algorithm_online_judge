import java.util.*;

public class Main {
    private static long calcCN2(long n) {
        return n * (n - 1) / 2;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t-- > 0) {
            int n = scan.nextInt();
            Map<Integer, Map<Integer, Integer>> xMap = new HashMap<>(n);
            Map<Integer, Map<Integer, Integer>> yMap = new HashMap<>(n);
            for (int i = 0; i < n; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                if (!xMap.containsKey(x)) {
                    xMap.put(x, new HashMap<>());
                }
                if (!yMap.containsKey(y)) {
                    yMap.put(y, new HashMap<>());
                }
                Map<Integer, Integer> xyMap = xMap.get(x);
                xyMap.put(y, xyMap.containsKey(y) ? xyMap.get(y) + 1 : 1);
                Map<Integer, Integer> yxMap = yMap.get(y);
                yxMap.put(x, yxMap.containsKey(x) ? yxMap.get(x) + 1 : 1);
            }
            long cnt = getCnt(xMap) + getCnt(yMap);
            System.out.println(cnt);
        }
    }

    private static long getCnt(Map<Integer, Map<Integer, Integer>> map) {
        long cnt = 0;
        for (Map<Integer, Integer> xyMap: map.values()) {
            int size = xyMap.size();
            if (size < 2) {
                continue;
            }
            cnt += calcCN2(size);
            for (int sameSize: xyMap.values()) {
                if (sameSize > 1) {
                    cnt -= calcCN2(sameSize);
                }
            }
        }
        return cnt;
    }
}
