#include<iostream>
#include<cstring>

using namespace std;

typedef long long LL;

const LL MOD = 1e9 + 7;
const LL COEFFICIENT[2][2] = {{0, 1}, {1, 1}};
const LL IDENTITY[2][2] = {{1, 0}, {0, 1}};

void matrix_multiply(const LL a[2][2], const LL b[2][2], LL c[2][2]) {
  for(int i = 0; i < 2; i++) {
    for(int j = 0; j < 2; j++) {
      LL x = 0;
      for(int k = 0; k < 2; k++) {
        x += a[i][k] * b[k][j] % MOD;
      }
      c[i][j] = x % MOD;
    }
  }
}

void matrix_power(LL n, LL res[2][2]) {
  memcpy(res, IDENTITY, sizeof(IDENTITY));
  LL tmpPower[2][2];
  LL tmp[2][2];
  memcpy(tmpPower, COEFFICIENT, sizeof(COEFFICIENT));
  for(int i = 1;; i <<= 1) {
    if(n & i) {
      n ^= i;
      matrix_multiply(res, tmpPower, tmp);
      memcpy(res, tmp, sizeof(tmp));
    }
    if(!n) {
      break;
    }
    matrix_multiply(tmpPower, tmpPower, tmp);
    memcpy(tmpPower, tmp, sizeof(tmp));
  }
}

int T;
LL N;

int main() {
  cin >> T;
  while(T--) {
    cin >> N;
    LL tmp[2][2];
    matrix_power(N - 1, tmp);
    cout << (tmp[1][0] + tmp[1][1]) % MOD << endl;
  }
  return 0;
}
