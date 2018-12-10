#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

int compare(string str, string target) {
	int i = 0, j = 0;
	while (i < str.length() && j < target.length()) {
		if (str.at(i) == target.at(j)) {
			i++;
			j++;
		}
		else {
			j++;
		}
	}
	if (i == str.length()) return i;
	return -1;
}

int main() {
	int n;
	cin >> n;
	vector<string> strs;
	for (int i = 0; i < n; i++) {
		string sub;
		cin >> sub;
		strs.push_back(sub);
	}
	string target;
	cin >> target;
	int maxlen = -1;
	for (string str : strs) {
		maxlen = max(compare(str, target), maxlen);
	}
	cout << maxlen << endl;
	system("pause");
	return 0;

}
