import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t-- > 0) {
            int n = scan.nextInt();
            int q = scan.nextInt();
            scan.nextLine();
            String[] lrs = scan.nextLine().split(" ");
            BigInteger[] starts = new BigInteger[n];
            BigInteger[] ends = new BigInteger[n];
            BigInteger[] sumOfCounts = new BigInteger[n];

            for (int i = 0; i < n; i++) {
                int lIndex = i << 1;
                int rIndex = lIndex | 1;
                BigInteger l = new BigInteger(lrs[lIndex]);
                BigInteger r = new BigInteger(lrs[rIndex]);
                starts[i] = l; ends[i] = r;
                sumOfCounts[i] = i > 0 ? sumOfCounts[i - 1].add(r.subtract(l)).add(ONE) : r.subtract(l).add(ONE);
            }

            List<BigInteger> res = new ArrayList<>();
            for (String qStr: scan.nextLine().split(" ")) {
                BigInteger rank = new BigInteger(qStr);
                res.add(search(n, rank, starts, ends, sumOfCounts));
            }
            System.out.println(res.stream().map(BigInteger::toString).collect(Collectors.joining(" ")));
        }
    }

    private static final BigInteger ONE = new BigInteger("1");

    private static BigInteger search(int n, BigInteger rank,
                               BigInteger[] starts, BigInteger[] ends, BigInteger[] sumOfCounts) {
        int l = 0, r = n - 1;
        while(l < r) {
            int m = (l + r) >> 1;
            BigInteger cnt = sumOfCounts[m];
            switch (cnt.compareTo(rank)) {
                case 0: return ends[m];
                case -1: l = m + 1; break;
                case 1: r = m; break;
            }
        }
        return l > 0
                ? starts[l].add(rank).subtract(sumOfCounts[l - 1]).subtract(ONE)
                : starts[l].add(rank).subtract(ONE);
    }
}
