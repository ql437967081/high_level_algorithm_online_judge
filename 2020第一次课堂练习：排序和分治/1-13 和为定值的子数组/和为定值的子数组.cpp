#include<iostream>
#include<algorithm>

using namespace std;

const int MAXN = 100 + 5;

int T, N, K, a[MAXN];

int subArr[4];

void find(int index, int pos, int sum) {
	if(index + 4 - pos > N) return;
	
	int target = K - sum;
	
	int minSum = 0, maxSum = 0;
	for(int i = pos; i < 4; i++) {
		minSum += a[index + i - pos];
		maxSum += a[N - (i - pos + 1)];
	}
	if(target < minSum || target > maxSum) return;
	
	if(pos == 3) {
		int l = index, r = N - 1;
		while(l <= r) {
			int m = (l + r) >> 1;
			if(target == a[m]) {
				subArr[pos] = target;
				for(int i = 0; i < 4; i++) {
					cout << subArr[i] << " ";
				}
				cout << "$";
				return;
			}
			if(target < a[m]) {
				r = m - 1;
			}
			else {
				l = m + 1;
			}
		}
	}
	
	for(int i = index; i < N; i++) {
		if(i > index && a[i] == a[i - 1]) continue;
		subArr[pos] = a[i];
		find(i + 1, pos + 1, sum + a[i]);
	}
}

int main() {
	cin >> T;
	while(T--) {
		cin >> N >> K;
		for(int i = 0; i < N; i++) {
			cin >> a[i];
		}
		sort(a, a + N);
		find(0, 0, 0);
		cout << endl;
	}
	return 0;
} 
