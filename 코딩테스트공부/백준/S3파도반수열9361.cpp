

#include <iostream>
#define MAX 10
using namespace std;
long long dp[101];
long long fadoban(int n) {
    if (n == 0) {
        dp[0] = 1;
        return 1;
    }
    else if (n == 1) {
        dp[1] = 1;
        return 1;
    }
    else if (n == 2) {
        dp[2] = 1;
    }
    if (dp[n] != 0) {
        return dp[n];
    }
    else {
        return dp[n] = fadoban(n - 2) + fadoban(n - 3);
    }
}


int main() {
    int n, v;
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> v;
        fadoban(v-1);        
        cout << dp[v-1] << "\n";
    }
}