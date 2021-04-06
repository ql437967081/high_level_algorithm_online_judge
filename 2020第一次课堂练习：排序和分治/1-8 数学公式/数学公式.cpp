#include<iostream>
#include<cstring>

using namespace std;

const int MAXN = 100000 + 5;

typedef long long LL;

int T, A, B, C;
LL f[MAXN];

LL pow(int x) {
	if(f[x]) return f[x];
	if(x == 1) return f[x] = A % C;
	int m = x >> 1;
	LL fm = pow(m);
	f[x] = fm * fm % C;
	if(x & 1) f[x] = f[x] * A % C;
	return f[x];
}

int main() {
	cin >> T;
	while(T--) {
		cin >> A >> B >> C;
		memset(f, 0, sizeof(LL) * (B + 5));
		cout << pow(B) << endl;
	}
	return 0;
}
