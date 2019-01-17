#include <iostream>
#include <vector>
#include <string>
#include <map>
#include <algorithm>
#include <queue>
#include <sstream> 
#include <memory.h>
using namespace std;


vector<int> graph[101];
const int inf = 0x3f3f3f;

int compute(int root, vector<int>& val, const vector<int>& flag) {
	//cout << root << " " << val[root] << endl;
	if (val[root] != -1) return val[root];
  //注意这里，要先在此计算出left right的值，以免在下面的&&和||运算符中被省略计算而少了递归
	int left = compute(graph[root][0], val, flag);
	int right = compute(graph[root][1], val, flag);
	if (flag[root] == 1) {
		val[root] = left && right;
	}
	if (flag[root] == 2) {
		val[root] = left || right;
	}
	return val[root];
}

int computeRevert(int root, const vector<int>& val, const vector<int>& flag, vector<int>& revert) {
	//已经计算了，注意，叶节点都是已经计算了的
	if (revert[root] != 0) return revert[root];
	//如果两个子节点的值不同：一个true一个false，可以直接返回1
	if (val[graph[root][0]] + val[graph[root][1]] == 1) {
		revert[root] = 1;
		return 1;
	}
	int cntleft = computeRevert(graph[root][0], val, flag, revert);
	int cntright = computeRevert(graph[root][1], val, flag, revert);
  
  //下面分情况讨论，先分为符号，再分为左右子树的值
	if (flag[root] == 1) {
		if (val[graph[root][0]] == 1 && val[graph[root][1]] == 1) {
			revert[root] = min(cntleft, cntright);
			return revert[root];
		}
		else {
			revert[root] = min(cntleft, cntright) + 1;
			return revert[root];
		}
	}
	else {
		if (val[graph[root][0]] == 1 && val[graph[root][1]] == 1) {
			revert[root] = min(cntleft, cntright) + 1;
			return revert[root];
		}
		else {
			revert[root] = min(cntleft, cntright);
			return revert[root];
		}
	}


}

int main()
{

	int N;
	cin >> N;

	int root;
	//true:1, false:0, not computed:-1
	vector<int> val(N + 1, -1);
	//not computed:0, can't revert:inf
	vector<int> revert(N + 1, 0);
	//leaf:0 and:1 or:2
	vector<int> flag(N + 1, 0);
	//init tree
	for (int j = 1; j <= N; j++) {
		int p;
		string op;
		cin >> p >> op;
		if (p == 0) root = j;
		graph[p].push_back(j);

		if (op == "TRUE") {
			val[j] = 1;
			revert[j] = inf;
		}
		else if (op == "FALSE") {
			val[j] = 0;
			revert[j] = inf;
		}
		else if (op == "AND") {
			flag[j] = 1;
		}
		else if (op == "OR") {
			flag[j] = 2;
		}
	}

	//init true/false
	compute(root, val, flag);

	int res = computeRevert(root, val, flag, revert);
	cout << ((res > N) ? -1 : res) << endl;

	return 0;
}
/**
13
0 OR
1 AND
1 OR
2 TRUE
2 TRUE
3 AND
3 OR
6 AND
6 FALSE
7 TRUE
7 FALSE
8 TRUE
8 FALSE
**/
