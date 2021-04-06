import java.util.*;

public class Main {
    private static final boolean[][] COEFFICIENT_MATRIX = new boolean[][] {
            new boolean[] { false, true, false },
            new boolean[] { false, false, true },
            new boolean[] { true, false, true }
    };

    private static final boolean[][] IDENTITY_MATRIX = new boolean[][] {
            new boolean[] { true, false, false },
            new boolean[] { false, true, false },
            new boolean[] { false, false, true }
    };

    private static final boolean[][][] COEFFICIENT_MATRIX_BINARY_POWER;

    private static final int MAX_BINARY_POWER = 64;

    private static final int COEFFICIENT_MATRIX_SIZE = 3;

    static {
        // COEFFICIENT_MATRIX_BINARY_POWER[i] = COEFFICIENT_MATRIX ^ (1 << i)
        COEFFICIENT_MATRIX_BINARY_POWER = new boolean[MAX_BINARY_POWER][COEFFICIENT_MATRIX_SIZE][COEFFICIENT_MATRIX_SIZE];
        COEFFICIENT_MATRIX_BINARY_POWER[0] = COEFFICIENT_MATRIX;
        for (int i = 1; i < MAX_BINARY_POWER; i++) {
            COEFFICIENT_MATRIX_BINARY_POWER[i] = matrixMultiply(COEFFICIENT_MATRIX_BINARY_POWER[i - 1], COEFFICIENT_MATRIX_BINARY_POWER[i - 1]);
        }
    }

    private static final int MAX_N = 1000;

    private static Map<Integer, Boolean> seriesValue = new HashMap<>(MAX_N  * MAX_N);

    private static boolean[][] matrixMultiply(boolean[][] a, boolean[][] b) {
        int n = a.length;
        int m = b[0].length;
        int l = b.length;
        boolean[][] c = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                c[i][j] = false;
                for (int k = 0; k < l; k++) {
                    c[i][j] ^= a[i][k] & b[k][j];
                }
            }
        }
        return c;
    }

    private static final boolean[][] INITIAL_VALUE = new boolean[][] {
            new boolean[] { false },
            new boolean[] { true },
            new boolean[] { true },
    };

    /**
     * @param x i * j
     * @return C(x ^ 3)
     */
    private static boolean calcSeries(int x) {
        if (x == 1) {
            return false;
        }
        boolean[][] coefficient = IDENTITY_MATRIX.clone();
        int j = 0;
        for (long i = 1, y = (long) x * x * x - COEFFICIENT_MATRIX_SIZE; y > 0; i <<= 1, j++) {
            if ((y & i) > 0) {
                y ^= i;
                coefficient = matrixMultiply(coefficient, COEFFICIENT_MATRIX_BINARY_POWER[j]);
            }
        }
        return matrixMultiply(coefficient, INITIAL_VALUE)[2][0];
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t-- > 0) {
            int n = scan.nextInt();
            int cntOfOnes = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    int x = i * j;
                    if (!seriesValue.containsKey(x)) {
                        seriesValue.put(x, calcSeries(x));
                    }
                    if (seriesValue.get(x)) {
                        cntOfOnes++;
                    }
                }
            }
            System.out.println(cntOfOnes);
        }
    }
}
