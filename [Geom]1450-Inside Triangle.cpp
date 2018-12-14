#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

long long area(long long ax, long long ay, long long bx, long long by, long long cx, long long cy)
{
	//x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2)
	return abs(ax*(by - cy) + bx*(cy - ay) + cx*(ay - by));
}

void judge(long long px, long long py, long long ax, long long ay, long long bx, long long by, long long cx, long long cy)
{
	long long area1 = area(ax, ay, bx, by, cx, cy);
	long long area_sub1 = area(px, py, ax, ay, bx, by);
	long long area_sub2 = area(px, py, ax, ay, cx, cy);
	long long area_sub3 = area(px, py, bx, by, cx, cy);
	//cout << area1 << " " << area_sub1 << " " << area_sub2 << " " << area_sub3 << endl;
	if (area1 == area_sub1 + area_sub2 + area_sub3) cout << "YES" << endl;
	else cout << "NO" << endl;
}
int main() {
	int n;
	cin >> n;

	for (int i = 0; i < n; i++) {
		long long px, py, ax, ay, bx, by, cx, cy;
		cin >> px >> py >> ax >> ay >> bx >> by >> cx >> cy;
		judge(px, py, ax, ay, bx, by, cx, cy);
	}


	system("pause");
	return 0;

}
