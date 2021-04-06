#include<iostream>

using namespace std;

const int MAXN = 100000;
int T, n, cnt;
int a[MAXN], b[MAXN];

void merge(int l, int r) {
  if(l == r) return;
  int m = (l + r) >> 1;
  merge(l, m);
  merge(m + 1, r);
  int i = l, j = m + 1, k = l;
  while(k <= r) {
    if(i > m) {
      b[k++] = a[j++];
      continue;
    }
    if(j > r) {
      b[k++] = a[i++];
      continue;
    }
    if(a[i] <= a[j]) {
      b[k++] = a[i++];
    }
    else {
      b[k++] = a[j++];
      cnt += m + 1 - i;
    }
  }
  for(k = l; k <= r; k++) {
    a[k] = b[k];
  }
}

int main() {
  cin >> T;
  while(T--) {
    cin >> n;
    for(int i = 0; i < n; i++) {
      cin >> a[i];
    }
    cnt = 0;
    merge(0, n - 1);
    for(int i = 0; i < n; i++) cout << a[i] << " "; cout << endl;
    cout << cnt << endl;
  }
  return 0;
}
