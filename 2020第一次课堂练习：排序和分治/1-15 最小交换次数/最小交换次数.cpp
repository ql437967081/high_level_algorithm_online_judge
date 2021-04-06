#include<iostream>
#include<cstring>
#include<algorithm>
#include<map>

using namespace std;

const int MAXN = 100 + 5;

int T, N, A[MAXN], B[MAXN];

bool vis[MAXN];

int main() {
	cin >> T;
	while(T--) {
		cin >> N;
		for(int i = 0; i< N; i++) cin >> A[i];
		memcpy(B, A, sizeof(int) * N);
		memset(vis, 0, sizeof(bool) * N);
		sort(B, B + N);
		map<int, int> map;
		for(int i = 0; i < N; i++) {
			map[B[i]] = i;
			if(A[i] == B[i])
				vis[i] = true;
		}
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			if(!vis[i]) {
				vis[i] = true;
				for(int j = map[A[i]]; !vis[j]; j = map[A[j]]) {
					vis[j] = true;
					cnt++;
				}
			}
		}
		cout << cnt << endl;
	}
	return 0;
} 
