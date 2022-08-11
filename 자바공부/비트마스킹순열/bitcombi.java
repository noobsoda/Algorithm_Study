package 자바공부.비트마스킹순열;
public class bitcombi {
	public static void main(String[] args) {
		int[] set = new int[] { 1, 2, 3, 4 };

		for (int i = 1; i < (1 << set.length); i++) {
			for (int j = 0; j < set.length; j++) {
				if ((i & (1 << j)) != 0) {
					System.out.print(set[j]);
				}
			}
			System.out.println();
		}
	}
}