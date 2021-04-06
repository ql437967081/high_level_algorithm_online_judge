#include<iostream>

using namespace std;

const int MAXN = 100000;

int n, a[MAXN], b[MAXN], pos[MAXN];

int main() {
	while(cin >> n) {
		for(int i = 0; i < n; i++) cin >> a[i];
		for(int i = 0; i < n; i++) {
			pos[i] = 0;
			for(int j = 0; j < n; j++) {
				if(a[j] < a[i] || (a[j] == a[i] && j < i))
					pos[i]++;
			}
		}
		for(int i = 0; i < n; i++) b[pos[i]] = a[i];
		for(int i = 0; i < n; i++) cout << b[i] << " ";
		cout << endl;
	}
	return 0;
} 
