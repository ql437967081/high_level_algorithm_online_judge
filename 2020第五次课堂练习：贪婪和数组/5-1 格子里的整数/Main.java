import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private int n;

    private static final int[] DX = new int[] {-1, 1, 0, 0};
    private static final int[] DY = new int[] {0, 0, -1, 1};

    private boolean checkValid(int x) {
        return x >= 0 && x < n;
    }

    private boolean checkValid(int x, int y) {
        //noinspection SuspiciousNameCombination
        return checkValid(x) && checkValid(y);
    }

    private int minCost(int[][] grid, int n) {
        int[][] dist = new int[n][n];
        this.n = n;
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = grid[0][0];
        int cnt = n * n - 1;
        while (cnt-- > 0) {
            boolean flag = false;
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    for (int k = 0; k < 4; k++) {
                        int nx = x + DX[k];
                        int ny = y + DY[k];
                        if (dist[x][y] != Integer.MAX_VALUE && checkValid(nx, ny)) {
                            int newDist = dist[x][y] + grid[nx][ny];
                            if (dist[nx][ny] > newDist) {
                                flag = true;
                                dist[nx][ny] = newDist;
                            }
                        }
                    }
                }
            }
            if (!flag) {
                break;
            }
        }
        return dist[n - 1][n - 1];
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t-- > 0) {
            int n = scan.nextInt();
            int[][] grid = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = scan.nextInt();
                }
            }
            System.out.println(new Main().minCost(grid, n));
        }
    }
}
