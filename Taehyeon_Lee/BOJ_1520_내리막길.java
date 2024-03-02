package coding.baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
시작 시간 : 24-02-28 09:20
종료 시간 : 24-02-28 10:20
실행 시간 : 320ms
메모리 : 39300KB
 */
public class BOJ_1520_내리막길 {
	
	static int H, W;
	static int[][] map;
	static Integer[][] memo;
	
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	
	private static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		memo = new Integer[H][W];
		memo[0][0] = 1;
		
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
	}
	
	private static int dp(int ci, int cj) {

		if(memo[ci][cj] == null) {
			
			int sum = 0;
			for(int i = 0; i < 4; i ++) {
				int ni = ci + di[i];
				int nj = cj + dj[i];
				
				if(ni < 0|| nj < 0|| ni >= H|| nj >= W) continue;
				if(map[ni][nj] <= map[ci][cj]) continue;
				
				sum += dp(ni, nj);
			}
			memo[ci][cj] = sum;
		}
		return memo[ci][cj];
	}
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		System.out.println(dp(H-1, W-1));
	}
}