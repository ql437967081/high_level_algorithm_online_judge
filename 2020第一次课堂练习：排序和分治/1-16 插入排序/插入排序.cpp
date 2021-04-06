#include<iostream>

using namespace std;

const int MAXN = 100000;

int T, n, a[MAXN];

int main() {
	cin >> T;
	while(T--) {
		cin >> n;
		for(int i = 0; i < n; i++) cin >> a[i];
		for(int i = 1; i < n; i++) {
			int l = -1, r = i;
			while(l + 1 < r) {
				int m = (l + r) >> 1;
				if(a[i] < a[m]) r = m;
				else l = m;
			}
			int tmp = a[i];
			for(int j = i; j > l + 1; j--) a[j] = a[j - 1];
			a[l + 1] = tmp;
		}
		for(int i = 0; i < n; i++) cout << a[i] << " ";
		cout << endl;
	}
	return 0;
} 
