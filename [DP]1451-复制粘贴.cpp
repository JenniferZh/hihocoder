#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

int power(int k) {
	int MOD = 1000000007;
	long long res = 1;
	for (int i = 0; i < k; i++) {
		res = (res * 4) % MOD;
		//cout << res << endl;
	}
	return res;
}

int main() {
	int n;
	cin >> n;

	int MOD = 1000000007;

	vector<int> dp(30, 0);
	dp[1] = 1;
	dp[2] = 2;

	//最后一次操作是输入A： dp[i] = dp[i-1]+1
	//最后k次操作都是ctrl-v: dp[i] = dp[i-2-k]*(k+1)
	for (int i = 2; i < 30; i++) {
		int max_value = 0;

		if (dp[i - 1] + 1 > max_value) max_value = dp[i - 1] + 1;

		int k = 1;
		while (i - 2 - k >= 0) {
			if (dp[i - 2 - k] * (k + 1) > max_value)
				max_value = dp[i - 2 - k] * (k + 1);
			k++;
		}

		dp[i] = max_value;
		//cout << i << " " << max_value << endl;
		
	}

	if (n <= 15) {
		cout << dp[n] << endl;
	}
	else {

		int k = 0;
		while (n - 5 * k > 15) {
			k++;
		}
		long long finalres = dp[n - 5 * k] * (long long)power(k);

		cout << finalres % MOD << endl;

	}


	//system("pause");
	return 0;

}
