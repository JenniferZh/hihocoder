#include <iostream>
#include <vector>
#include <string>
#include <map>
#include <algorithm>
#include <queue>
#include <sstream> 
#include <utility>
using namespace std;

int M;
int N;

long long countsub(vector<pair<int, int>> blackset) {
	if (blackset.size() == 0) return 1ll*(M+1)*M*(N+1)*N/4;
	int leftmost = 1001;
	int rightmost = -1;
	int upmost = 1001;
	int downmost = -1;

	for (pair<int, int> dot : blackset) {
		int r = dot.first;
		int c = dot.second;
		leftmost = min(leftmost, c);
		rightmost = max(rightmost, c);
		upmost = min(upmost, r);
		downmost = max(downmost, r);
	}
	long long res = 1ll*(M - rightmost + 1)*(N - downmost + 1)*upmost*leftmost;
	if (blackset.size() % 2 == 1) return -res;
	return res;
}

long long subset(vector<pair<int, int>> sets) {
	int size = sets.size();
	int N = 1 << size;
	long long res = 0;
	for (int i = 0; i < N; i++) {
		int bits = i;
		vector<pair<int, int>> subset;
		int j = 0;
		while (bits != 0) {
			int flag = bits & 1;
			if (flag == 1) {
				subset.push_back(sets[j]);
			}
			j++;
			bits = bits >> 1;
		}
		res = res + countsub(subset);
	}
	return res;
}

int main()
{
	int k;
	cin >> N >> M >> k;
	vector<pair<int, int>> blacks;
	for (int i = 0; i < k; i++) {
		int r, c;
		cin >> r >> c;
		pair<int, int> dot(r, c);
		blacks.push_back(dot);
	}
	cout << subset(blacks) << endl;
	//cout << 1000 * 1000 * 999 * 999 / 4 << endl;

	//system("pause");
	return 0;
}

