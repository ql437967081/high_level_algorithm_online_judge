#include<iostream>
#include<cstring>

using namespace std;

const int MAXN = 105;
int T, N, M, a[MAXN], K;

int main() {
  cin >> T;
  while(T--) {
    cin >> N >> M;
    for(int i = 0; i < N; i++) cin >> a[i];
    string sp = "";
    while(M--) {
      cin >> K;
      int cnt = 0;
      for(int i = 0; i < N; i++) if(a[i] % K == 0) cnt++;
      cout << sp << cnt;
      sp = " ";
    }
    cout << endl;
  }
  return 0;
}
