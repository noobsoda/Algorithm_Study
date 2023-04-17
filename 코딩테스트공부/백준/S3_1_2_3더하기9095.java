package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S3_1_2_3더하기9095 {
	static int dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int t = Integer.parseInt(st.nextToken());
		dp = new int[11];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;

		for (int j = 4; j <= 10; j++) {
			dp[j] = dp[j - 3] + dp[j - 2] + dp[j - 1];
		}

		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());

			System.out.println(dp[n]);
		}
	}

}
