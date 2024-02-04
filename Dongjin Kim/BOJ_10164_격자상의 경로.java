package dj_ct;

/*
 * BOJ_10164_격자상의 경로.java
 * [BOJ]10164/실버1/124ms/0.5h/김동진
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10164_격자상의경로 {

	static int N, M, K, ans;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		if(K!=0) {
			int k_x = K / M;
			int k_y = K % M - 1;
			
			ans = solve(k_x + 1, k_y + 1) * solve(N - k_x, M - k_y);
		}else {
			ans = solve(N, M);
		}
		
		System.out.println(ans);
	}
	
	
	private static int solve(int x, int y) {
		int[][] m = new int[x][y];
		
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				if(i == 0 || j == 0) {
					m[i][j] = 1;
				}else {
					m[i][j] = m[i-1][j] + m[i][j-1];
				}
			}
		}
		
		return m[x-1][y-1];
	}
	

}
