import java.io.*;

public class BOJ_2529_부등호 {
	static final int[] desc = {9,8,7,6,5,4,3,2,1,0};
	static final int[] asc = {0,1,2,3,4,5,6,7,8,9};
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");

		for (int i = 0; i < N; i++) {
			boolean t = s[i].equals("<");
			if (t && desc[i] > desc[i + 1]) {
				swap(desc, i, i+1);
				i = -1;
			} else if (!t && asc[i] < asc[i + 1]) {
				swap(asc, i, i+1);
				i = -1;
			}
		}

		print(desc);
		print(asc);
	}
	
	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	private static void print(int[] arr) {
		for (int i = 0; i <= N; i++) System.out.print(arr[i]);
		System.out.println();
	}
}
