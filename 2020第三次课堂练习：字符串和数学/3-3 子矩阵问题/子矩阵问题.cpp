#include<iostream>

using namespace std;

const int MAXN = 1000;

int T, n, m;
bool a[MAXN][MAXN];
int dp[MAXN][MAXN][4];

int f(int i, int j) {
  return max(dp[i][j][0], dp[i][j][1]);
}

int main() {
  cin >> T;
  while(T--) {
    cin >> n >> m;
    for(int i = 1; i <= n; i++) {
      for(int j = 1; j <= m; j++) {
        cin >> a[i][j];
        dp[i][j][0] = max(f(i - 1, j), f(i, j - 1));  // 不包含当前的最大值
        dp[i][j][2] = a[i][j] ? dp[i - 1][j][2] + 1 : 0;  // 往上延申
        dp[i][j][3] = a[i][j] ? dp[i][j - 1][3] + 1 : 0;  // 往左延申
        dp[i][j][1] = a[i][j];  // 包含当前的最大值
        int minh = dp[i][j][2];
        for(int k = j; k > j - dp[i][j][3]; k--) {
          minh = min(minh, dp[i][k][2]);
          dp[i][j][1] = max(dp[i][j][1], (j - k + 1) * minh);
        }
      }
    }
    cout << f(n, m) << endl;
  }
  return 0;
}
