#include<iostream>

using namespace std;

const int MAXN = 50 + 5;
const int MAXK = 30 + 5;

int T, k, n;
int dp[MAXK][MAXN];

int main() {
  cin >> T;
  while(T--) {
    cin >> k >> n;
    dp[0][0] = dp[1][0] = 0;
    for (int i = 1; i <= n; i++) {
    	int board;
        cin >> board;
        dp[1][i] = dp[1][i - 1] + board;
        if (i <= k) {
            dp[i][i] = max(dp[i - 1][i - 1], board);
        }
    }

    for (int i = 2; i <= k; i++) {
        dp[i][0] = 0;
        for (int j = 1; j <= n; j++) {
            if (j <= i) {
                dp[i][j] = dp[j][j];
                continue;
            }
            dp[i][j] = dp[1][n];
            for (int m = i - 1; m <= j; m++) {
                dp[i][j] = min(dp[i][j], max(dp[i - 1][m], dp[1][j] - dp[1][m]));
            }
        }
    }
    cout << dp[k][n] << endl;
  }
  return 0;
}
