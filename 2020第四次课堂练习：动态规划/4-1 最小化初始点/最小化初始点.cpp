#include<iostream>

using namespace std;

const int MAXN = 10 + 5;

int T;
int R, C, a[MAXN][MAXN], dp[MAXN][MAXN][2];

int main() {
  cin >> T;
  while(T--) {
    cin >> R >> C;
    for(int i = 0; i < R; i++) {
      for(int j = 0; j < C; j++) {
        cin >> a[i][j];
        if(i == 0 && j == 0) {
          dp[0][0][0] = 1 - a[0][0];
          dp[0][0][1] = 1;
          continue;
        }
        int tmp1Points = j > 0 ? dp[i][j - 1][1] + a[i][j] : 0;
        int tmp2Points = i > 0 ? dp[i - 1][j][1] + a[i][j] : 0;
        if(i == 0) {
          if(tmp1Points > 0) {
            dp[0][j][0] = dp[0][j - 1][0];
            dp[0][j][1] = tmp1Points;
          } else {
            dp[0][j][0] = dp[0][j - 1][0] + 1 - tmp1Points;
            dp[0][j][1] = 1;
          }
          continue;
        }
        if(j == 0) {
          if(tmp2Points > 0) {
            dp[i][0][0] = dp[i - 1][0][0];
            dp[i][0][1] = tmp2Points;
          } else {
            dp[i][0][0] = dp[i - 1][0][0] + 1 - tmp2Points;
            dp[i][0][1] = 1;
          }
          continue;
        }
        int tmp1 = tmp1Points > 0 ? dp[i][j - 1][0] : dp[i][j - 1][0] + 1 - tmp1Points;
        int tmp2 = tmp2Points > 0 ? dp[i - 1][j][0] : dp[i - 1][j][0] + 1 - tmp2Points;
        if(tmp1 < tmp2) {
          dp[i][j][0] = tmp1;
          dp[i][j][1] = max(tmp1Points, 1);
        } else if(tmp1 > tmp2) {
          dp[i][j][0] = tmp2;
          dp[i][j][1] = max(tmp2Points, 1);
        } else {
          dp[i][j][0] = tmp1;
          dp[i][j][1] = max(max(tmp1Points, tmp2Points), 1);
        }
      }
    }
    cout << dp[R - 1][C - 1][0] << endl;
  }
  return 0;
}
