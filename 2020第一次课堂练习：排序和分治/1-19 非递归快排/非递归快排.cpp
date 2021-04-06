#include<iostream>
#include<stack>

using namespace std;

const int MAXN = 100000;

int n, a[MAXN];

stack<int> st;

int partition(int l, int r) {
	int m = (l + r) >> 1;
	if(a[m] > a[r]) swap(a[m], a[r]);
	if(a[l] > a[r]) swap(a[l], a[r]);
	if(a[l] < a[m]) swap(a[l], a[m]);
	int p = l;
	int pivot = a[l++];
	
	while(l < r) {
		while(l < r && a[r] >= pivot) r--;
		while(l < r && a[l] <= pivot) l++;
		swap(a[l], a[r]);
	}
	if(a[l] < pivot) swap(a[l], a[p]);
	return l;
} 

int main() {
	while(cin >> n) {
		for(int i = 0; i < n; i++) cin >> a[i];
		st.push(0);
		st.push(n - 1);
		while(!st.empty()) {
			int r = st.top(); st.pop();
			int l = st.top(); st.pop();
			int m = partition(l, r);
			if(l < m - 1) {
				st.push(l);
				st.push(m - 1);
			}
			if(m + 1 < r) {
				st.push(m + 1);
				st.push(r);
			}
		}
		for(int i = 0; i < n; i++) cout << a[i] << " ";
		cout << endl;
	}
	return 0;
}
