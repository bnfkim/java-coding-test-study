import java.io.*;
import java.util.*;

public class BOJ_1520_내리막길 {
	
	static int N, M, arr[][], dp[][];
	static final int[] dx = {1, 0, -1, 0};
	static final int[] dy = {0, -1, 0, 1};
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		dp = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(dp[i], -1);
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(dfs(0, 0));
	}
	
	
	private static int dfs(int i, int j) {
		if (i == N - 1 && j == M - 1) return 1;

		if (dp[i][j] != - 1) return dp[i][j];
		
		dp[i][j] = 0;
		for (int idx = 0; idx < 4; idx++) {
			int nx = i + dx[idx];
			int ny = j + dy[idx];
			if (0 <= nx && nx < N && 0 <= ny && ny < M) {
				dp[i][j] += arr[i][j] > arr[nx][ny] ? dfs(nx, ny) : 0;				
			}
		}
		
		return dp[i][j];
	}

}
