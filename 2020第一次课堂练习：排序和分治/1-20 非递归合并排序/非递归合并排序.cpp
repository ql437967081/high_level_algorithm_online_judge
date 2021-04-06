#include<iostream>
#include<cstring>
#include<stack>
#include<queue>

using namespace std;

const int MAXN = 100000;

int n, a[MAXN], tmp[MAXN];

queue<int> lqueue, rqueue;
stack<int> lstack, rstack;

int main() {
	while(cin >> n) {
		for(int i = 0; i < n; i++) cin >> a[i];
		lqueue.push(0); rqueue.push(n - 1);
		while(!lqueue.empty()) {
			int l = lqueue.front(); lqueue.pop(); lstack.push(l);
			int r = rqueue.front(); rqueue.pop(); rstack.push(r);
			int m = (l + r) >> 1;
			if(l < m) {
				lqueue.push(l);
				rqueue.push(m);
			}
			if(m + 1 < r) {
				lqueue.push(m + 1);
				rqueue.push(r);
			}
		}
		while(!lstack.empty()) {
			int l = lstack.top(); lstack.pop();
			int r = rstack.top(); rstack.pop();
			int m = (l + r) >> 1;
			int i = l, j = m + 1, k = l;
			while(i <= m && j <= r) {
				if(a[i] > a[j]) tmp[k++] = a[j++];
				else tmp[k++] = a[i++];
			}
			while(i <= m) tmp[k++] = a[i++];
			while(j <= r) tmp[k++] = a[j++];
			memcpy(a + l, tmp + l, sizeof(int) * (k - l));
		}
		for(int i = 0; i < n; i++) cout << a[i] << " ";
		cout << endl;
	}
	return 0;
}
