#include<iostream>

using namespace std;

const int MAXN = 100000;

int n, a[MAXN];

int main() {
	while(cin >> n) {
		for(int i = 0; i < n; i++) cin >> a[i];
		bool flag = false;
		for(int i = 0; i < n - 1; i++) {
			for(int j = 0; j < n - 1 - i; j++) {
				if(a[j] > a[j + 1]) {
					int tmp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = tmp;
					flag = true;
				}
			}
			if(!flag) break;
		}
		for(int i = 0; i < n; i++) cout << a[i] << " ";
		cout << endl;
	}
	return 0;
} 
