#include<iostream>

using namespace std;

const int MAXN = 50 + 5;
const int MAXM = 50 + 5;
const int MAXP = 250 * 50;

int T, N, M, A[MAXN], dp[MAXN][MAXM];

int main() {
	cin >> T;
	while(T--) {
		cin >> N;
		for(int i = 1; i <= N; i++) cin >> A[i];
		cin >> M;
		for(int i = 1; i <= N; i++)
			for(int j = 1; j <= M; j++)
				dp[i][j] = MAXP;
		dp[0][0] = dp[0][1] = 0;
		for(int i = 1; i <= N; i++) {
			dp[i][1] = dp[i - 1][1] + A[i];
			if(i <= M) dp[i][i] = max(dp[i - 1][i - 1], A[i]);
		}
		for(int i = 2; i <= N; i++) {
			for(int j = 2; j <= min(M, i - 1); j++) {
				for(int k = j - 1; k < i; k++) {
					dp[i][j] = min(dp[i][j], max(dp[k][j - 1], dp[i][1] - dp[k][1]));
				}
			}
		}
		if(N < M) cout << -1 << endl;
		else cout << dp[N][M] << endl;
	}
	return 0;
} 
