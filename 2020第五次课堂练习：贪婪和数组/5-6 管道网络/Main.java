import java.util.*;

import static java.lang.Math.min;

public class Main {
    private static int find(int x) {
        if (fa[x] == x) return x;
        int y = find(fa[x]);
        minDiameter[y] = min(minDiameter[y], minDiameter[x]);
        return fa[x] = y;
    }

    private static int[] fa;
    private static int[] minDiameter;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int p = sc.nextInt();
            boolean[] in = new boolean[n + 1];
            boolean[] out = new boolean[n + 1];
            minDiameter = new int[n + 1];
            fa = new int[n + 1];
            Arrays.fill(in, false);
            Arrays.fill(out, false);
            Arrays.fill(minDiameter, 0);
            for (int i = 1; i <= n; i++) fa[i] = i;
            while (p-- > 0) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                int d = sc.nextInt();
                in[b] = true;
                out[a] = true;
                minDiameter[a] = minDiameter[a] > 0 ? min(minDiameter[a], d) : d;
                minDiameter[b] = minDiameter[b] > 0 ? min(minDiameter[b], d) : d;
                int ya = find(a);
                int yb = find(b);
                fa[ya] = yb;
            }
            List<Integer> list = new ArrayList<>();
            Map<Integer, TankTap> pairs = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                if (out[i] && !in[i]) {
                    int yi = find(i);
                    list.add(yi);
                    if (!pairs.containsKey(yi)) {
                        pairs.put(yi, new TankTap(minDiameter[yi]));
                    }
                    pairs.get(yi).tank = i;
                }
                if (in[i] && !out[i]) {
                    int yi = find(i);
                    if (!pairs.containsKey(yi)) {
                        pairs.put(yi, new TankTap(minDiameter[yi]));
                    }
                    pairs.get(yi).tap = i;
                }
            }
            System.out.println(list.size());
            for (int yi: list) {
                System.out.println(pairs.get(yi));
            }
        }
    }

    private static class TankTap {
        private int tank;
        private int tap;
        private int diameter;

        private TankTap(int diameter) {
            this.diameter = diameter;
        }

        @Override
        public String toString() {
            return tank + " " + tap + " " + diameter;
        }
    }
}
