import java.io.*;
import java.util.StringTokenizer;

public class BOJ_10971_원판순회2 {
	static int N, ans=Integer.MAX_VALUE;
	static int[] arr;
	static int[][] dist;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N-1];
		dist = new int[N][N];
		
		for (int i = 1; i < N; i++ ) {
			arr[i-1] = i;
		}

		for (int i = 0; i < N; i++ ) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++ ) {
				dist[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		permutation(0);
		System.out.println(ans);
	}	

	private static void permutation(int depth) {
		if (depth == N-1) {
			if (dist[0][arr[0]] == 0 || dist[arr[N-2]][0] == 0) return;
			int tmp = dist[0][arr[0]] + dist[arr[N-2]][0];
			for (int i = 0; i < N-2; i++ ) {
				if (dist[arr[i]][arr[i+1]] == 0) return;
				tmp += dist[arr[i]][arr[i+1]];
			}
			ans = Math.min(ans, tmp);
			return;
		}
		for (int i = depth; i < N-1; i++ ) {
			swap(depth, i);
			permutation(depth+1);
			swap(i, depth);
		}
	}

	private static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
