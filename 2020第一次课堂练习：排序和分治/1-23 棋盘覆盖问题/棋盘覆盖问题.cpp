#include<iostream>

using namespace std;

int T;
int dx[] = { 0, 0, 1, 1 };
int dy[] = { 0, 1, 0, 1 };

void printTheOtherTwo(int ax, int ay, int sx, int sy, int qx, int qy) {
	int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
	for(int i = 0; i < 4; i++) {
		int x = ax + dx[i];
		int y = ay + dy[i];
		if(x == sx && y == sy)
			continue;
		if(x == qx && y == qy)
			continue;
		if(x1 == -1) {
			x1 = x;
			y1 = y;
		}
		else {
			x2 = x;
			y2 = y;
		}
	}
	cout << x1 << " " << y1 << "," << x2 << " " << y2 << endl;
}

bool checkQueryPoint(int ax, int ay, int sx, int sy, int qx, int qy) {
	if(qx == sx && qy == sy)
		return false;
	for(int i = 0; i < 4; i++) {
		int x = ax + dx[i];
		int y = ay + dy[i];
		if(qx == x && qy == y) {
			printTheOtherTwo(ax, ay, sx, sy, qx, qy);
			return true;
		}
	}
	return false;
}

int findInitial(int x, int n) {
	n--;
	return x >> n << n;
}

int findCentral(int ax, int axx, int n) {
	n--;
	return axx - ax == 0 ? axx + (1 << n) - 1 : axx;
}

void f(int n, int ax, int ay, int sx, int sy, int qx, int qy) {
	if(n == 1) {
		printTheOtherTwo(ax, ay, sx, sy, qx, qy);
		return;
	}
	int asx = findInitial(sx, n);
	int asy = findInitial(sy, n);  // (sx, sy) 的左上角坐标 
	int bsx = findCentral(ax, asx, n);
	int bsy = findCentral(ay, asy, n); // (sx, sy) 所处块的中间坐标 
	
	if(checkQueryPoint(findCentral(ax, ax, n), findCentral(ay, ay, n), bsx, bsy, qx, qy))
		return;
	
	int aqx = findInitial(qx, n);
	int aqy = findInitial(qy, n);  // (qx, qy) 的左上角坐标 
	int bqx = findCentral(ax, aqx, n);
	int bqy = findCentral(ay, aqy, n); // (qx, qy) 所处块的中间坐标 
	
	if(asx == aqx && asy == aqy)
		f(n - 1, aqx, aqy, sx, sy, qx, qy);
	else
		f(n - 1, aqx, aqy, bqx, bqy, qx, qy);
}

int main() {
	cin >> T;
	while(T--) {
		int n, sx, sy, qx, qy;
		cin >> n >> sx >> sy >> qx >> qy;
		f(n, 0, 0, sx, sy, qx, qy);
	}
	return 0;
} 
