#include <iostream>
#include <vector>
#include <string>
#include <map>
#include <algorithm>
#include <queue>
#include <sstream> 
#include <memory.h>
using namespace std;


vector<int> graph[100001];

void getSum(const vector<int>& weight, vector<int> &sum, int i)
{
	//cout << "hi!" << i << endl;
	sum[i] = weight[i];
	for (int j = 0; j < graph[i].size(); j++)
		getSum(weight, sum, graph[i][j]), sum[i] += sum[graph[i][j]];
}

int getallcnt(const vector<int>& sum, vector<int> &cnt, int i, vector<int> &cnt2, int S)
{
	//cout << "hi!" << i << endl;
	int total = 0;
	for (int j = 0; j < graph[i].size(); j++)
	{
		if (sum[i] == S) {
			cnt2[graph[i][j]] = cnt2[i] + 1;
		}
		else {
			cnt2[graph[i][j]] = cnt2[i];
		}
		total += getallcnt(sum, cnt, graph[i][j], cnt2, S);
	}
	cnt[i] = total + (sum[i] == S);
	return cnt[i];
}

int main()
{
	int cnt;
	cin >> cnt;
	for (int i = 0; i < cnt; i++) {
		int N;
		cin >> N;
		memset(graph, 0, sizeof(graph));
		vector<int> weight(N + 1, 0);
		int root;
		for (int j = 1; j <= N; j++) {
			int v, p;
			cin >> v >> p;
			weight[j] = v;
			if (p == 0) root = j;
			graph[p].push_back(j);                   
		}
		vector<int> sum(N + 1, 0);
		vector<int> cnt(N + 1, 0);
		vector<int> cnt2(N + 1, 0);
		getSum(weight, sum, root);

		if (sum[root] % 3 != 0) {
			cout << "0" << endl;
			continue;
		}
		getallcnt(sum, cnt, root, cnt2, sum[root] / 3);

		long long ans1 = 0, ans2 = 0;
		//从2开始计算
		for (int j = 1; j <= N; j++) {
			if (j == root) continue;
			if (sum[j] == sum[root] / 3) {
				ans1 += cnt[root] - cnt[j] - cnt2[j];
			}
			if (sum[j] == sum[root] * 2 / 3) {
				ans2 += cnt[j] - (sum[j] == sum[root] / 3);
			}
		}
		cout << ans1 / 2 + ans2 << endl;
	}
	//system("pause");
	return 0;
}

