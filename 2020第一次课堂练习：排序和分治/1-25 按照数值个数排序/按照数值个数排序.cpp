#include<iostream>
#include<map>
#include<algorithm>

using namespace std;

const int MAXN = 100000;

struct num {
	int v;
	int cnt;
	void input(map<int, int>& countMap) {
		cin >> v;
		if(!countMap.count(v))
			countMap[v] = 1;
		else
			countMap[v]++;
	}
} a[MAXN];

int T, n;

bool cmp(num x, num y) {
	if(x.cnt == y.cnt)
		return x.v <= y.v;
	return x.cnt > y.cnt;
}

int main() {
	cin >> T;
	while(T--) {
		cin >> n;
		map<int, int> countMap;
		for(int i = 0; i < n; i++)
			a[i].input(countMap);
		for(int i = 0; i < n; i++)
			a[i].cnt = countMap[a[i].v];
		sort(a, a + n, cmp);
		for(int i = 0; i < n - 1; i++)
			cout << a[i].v << " ";
		cout << a[n - 1].v << endl;
	}
	return 0;
}
