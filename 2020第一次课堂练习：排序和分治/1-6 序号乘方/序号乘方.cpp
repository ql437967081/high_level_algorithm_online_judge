#include<iostream>
#include<cstring>
#include<string>

using namespace std;

const int MAXN = 30;

int T, P[MAXN];
string s;

void print(int x[]) {
	cout << x[0] << ':';
	for(int i = x[0]; i > 0; i--) cout << x[i];
	cout << endl;
}

void toBig(int x, int a[]) {
	a[0] = 0;
	while(x > 0) {
		a[++a[0]] = x % 10;
		x /= 10;
	}
}

void compensiteZero(int a[], int n) {
	if(a[0] < n) {
		for(int i = a[0] + 1; i <= n; i++) a[i] = 0;
	}
}

void divide(int a[], int a0[], int a1[], int m) {
	compensiteZero(a, m << 1);
	a0[0] = m;
	for(int i = 1; i <= m; i++) a0[i] = a[i];
	a1[0] = m;
	for(int i = 1; i <= m; i++) a1[i] = a[m + i];
}

void removeLeadingZero(int a[]) {
	while(a[0] > 1 && a[a[0]] == 0) a[0]--;
}

void bigAdd(int a[], int b[], int c[]) {
	int n = max(a[0], b[0]), d = 0;
	compensiteZero(a, n);
	compensiteZero(b, n);
	c[0] = n;
	for(int i = 1; i <= n; i++) {
		c[i] = a[i] + b[i] + d;
		d = c[i] / 10;
		c[i] %= 10;
	}
	if(d) c[++c[0]] = d;
}

void bigSub(int a[], int b[], int c[]) {
	int n = a[0], d = 0;
	compensiteZero(b, n);
	c[0] = n;
	for(int i = 1; i <= n; i++) {
		c[i] = a[i] - b[i] - d;
		if(c[i] < 0) {
			c[i] += 10;
			d = 1;
		}
		else d = 0;
	}
	removeLeadingZero(c);
}

void bigMul(int a[], int b[], int c[]) {
	int n = max(a[0], b[0]);
	if(n == 1) {
		c[1] = a[1] * b[1];
		c[0] = 1;
		if(c[1] / 10) {
			c[++c[0]] = c[1] / 10;
			c[1] %= 10;
		}
		return;
	}
	compensiteZero(a, n);
	compensiteZero(b, n);
	int m = (n + 1) >> 1;
	int a1[MAXN], a0[MAXN], b1[MAXN], b0[MAXN];
	divide(a, a0, a1, m);
	divide(b, b0, b1, m);
//	cout << "a0" << endl; print(a0); cout << endl;
//	cout << "a1" << endl; print(a1); cout << endl;
//	cout << "b0" << endl; print(b0); cout << endl;
//	cout << "b1" << endl; print(b1); cout << endl;
	
	int a_[MAXN], b_[MAXN], _c[MAXN];
	bigAdd(a1, a0, a_);//cout << "a_" << endl; print(a_); cout << endl;
	bigAdd(b1, b0, b_);//cout << "b_" << endl; print(b_); cout << endl;
	bigMul(a_, b_, _c);//cout << "_c" << endl; print(_c); cout << endl;
	
	int c2[MAXN], c1[MAXN], c0[MAXN], c_[MAXN];
	bigMul(a1, b1, c2);//cout << "c2" << endl; print(c2); cout << endl;
	bigMul(a0, b0, c0);//cout << "c0" << endl; print(c0); cout << endl;
	bigAdd(c2, c0, c_);//cout << "c_" << endl; print(c_); cout << endl;
	bigSub(_c, c_, c1);//cout << "c1" << endl; print(c1); cout << endl;
	
	memset(c, 0, sizeof(int) * MAXN);
	for(int i = 1; i <= c0[0]; i++) c[i] = c0[i];
	c[0] = c0[0];
	int d = 0;
	for(int i = 1; i <= c1[0]; i++) {
		c[m + i] += c1[i] + d;
		d = c[m + i] / 10;
		c[m + i] %= 10;
	}
	c[0] = max(c[0], m + c1[0]);
	if(d) c[++c[0]] = d;
	d = 0;
	n = m << 1;
	for(int i = 1; i <= c2[0]; i++) {
		c[n + i] += c2[i] + d;
		d = c[n + i] / 10;
		c[n + i] %= 10;
	}
	c[0] = max(c[0], n + c2[0]);
	if(d) c[++c[0]] = d;
	removeLeadingZero(c);
//	cout << "c" << endl; print(c); cout << endl;
//	cout << "=========" << endl;
}

int cmp(int x[]) {
	if(x[0] > P[0]) return 1;
	if(x[0] < P[0]) return -1;
	for(int i = x[0]; i > 0; i--) {
		if(x[i] > P[i]) return 1;
		if(x[i] < P[i]) return -1;
	}
	return 0;
}

int test(int x1) {
	int x2 = x1 + 1;
	int x3 = (x1 << 1) | 1;
	switch(x1 % 3) {
		case 0: x1 /= 3; break;
		case 1: x3 /= 3; break;
		case 2: x2 /= 3; break;
	}
	if(x1 & 1) x2 >>= 1;
	else x1 >>= 1;
	
	int a[MAXN], b[MAXN], c[MAXN], d[MAXN], e[MAXN];
	toBig(x1, a);
	toBig(x2, b);
	toBig(x3, c);
	bigMul(a, b, d);
//	cout << "a" << endl; print(a);
//	cout << "b" << endl; print(b);
//	cout << "d" << endl; print(d);
	bigMul(d, c, e);
//	cout << "c" << endl; print(c);
//	cout << "e" << endl; print(e);
	return cmp(e);
}

int main() {
	cin >> T;
	while(T--) {
		cin >> s;
		P[0] = s.size();
		for(int i = 0, N = P[0]; i < N; i++) {
			P[i + 1] = s[N - 1 - i] - '0';
		}
		int l = 1, r = 144224, res = l;
		while(l <= r) {
			int m = (l + r) >> 1;
			int testM = test(m);
			if(testM == 0) {
				res = m;
				break;
			}
			else if(testM < 0){
				res = m;
				l = m + 1;
			}
			else r = m - 1;
		}
		cout << res << endl;
	}
	return 0;
} 
