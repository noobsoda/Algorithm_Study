package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G3색종이3_2571 {
	static int N, answer = 0;
	static int map[][];
	static final int max = 100;

	public static void acc() {
		for (int i = 0; i < max - 1; i++) {
			for (int j = 0; j < max; j++) {
				if (map[i][j] != 0 && map[i + 1][j] != 0) {
					map[i + 1][j] = map[i][j] + 1;
				}
			}
		}
	}

	public static void sum() {
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < max; j++) {
				int h = max;

				for (int k = j; k < max; k++) {
					h = Math.min(map[i][k], h);
					if (h == 0)
						break;
					answer = Math.max(answer, h * (k - j + 1));

				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		map = new int[max][max];
		N = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int down = Integer.parseInt(st.nextToken());

			for (int a = left; a < left + 10; a++)
				for (int b = down; b < down + 10; b++) {
					map[a][b] = 1;
				}

		}

		acc();

		sum();

		System.out.println(answer);

	}
}
// https://www.acmicpc.net/problem/2571