#include <iostream>
#include <vector>
#include <string>
#include <map>
#include <algorithm>
#include <queue>
#include <sstream> 
#include <utility>
using namespace std;


int main()
{
	int N, M;
	cin >> N >> M;
	queue<int> q;
	vector<int> dist(N*M, INT32_MAX);

	vector<vector<char>> graph(N, vector<char>(M, 0));
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> graph[i][j];
			if (graph[i][j] == '0') {
				q.push(i*M + j);
				dist[i*M+j] = 0;
			}
		}
	}

	while (!q.empty()) {
		int cur = q.front();
		q.pop();
		int curx = cur / M;
		int cury = cur % M;
		
		vector<int> dx = { -1, 1, 0, 0 };
		vector<int> dy = { 0, 0, -1, 1 };

		for (int i = 0; i < 4; i++) {
			int nx = curx + dx[i];
			int ny = cury + dy[i];
			if (nx >= 0 && nx < N && ny >= 0 && ny < M && dist[nx*M + ny] == INT32_MAX) {
				q.push(nx*M + ny);
				dist[nx*M + ny] = dist[cur] + 1;
			}
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			int h = i*M + j;
			cout << dist[h] << " ";
		}
		cout << endl;
	}



	//system("pause");
	return 0;
}

