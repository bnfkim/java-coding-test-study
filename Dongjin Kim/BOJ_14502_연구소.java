package boj;

/*
 * 2024-02-27(화)
 * BOJ_14502_연구소.java
 * [BOJ]14502/골드4/1060ms/1h/김동진
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_연구소 {

	static int N, M, ans;
	static int[][] map, copy_map;
	
	static int[][] dir = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1}
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		System.out.println(ans);
		
	}
	
	public static void dfs(int cnt) {
		
		if(cnt == 3) {
			copy_map();
			virus_spread(copy_map);
			ans = Math.max(ans, find_safe(copy_map));
			return;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					dfs(cnt + 1);
					map[i][j] = 0;
				}
			}
		}
		
	}
	
	public static void copy_map() {
		copy_map = new int[N][M];
		for(int i = 0; i < N; i++) {
			copy_map[i] = map[i].clone();
		}
	}
	
	public static void virus_spread(int[][] arr) {
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> virus = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 2) {
					virus.add(new int[] {i, j});
					visited[i][j] = true;
				}
			}
		}
		
		int ch, cw;
		while(!virus.isEmpty()) {
			
			int[] cur = virus.poll();
			ch = cur[0];
			cw = cur[1];
			
			for(int i = 0; i < 4; i++) {
				
				int nh = ch + dir[i][0];
				int nw = cw + dir[i][1];
				
				if(nh < 0 || nh >= N || nw < 0 || nw >= M || copy_map[nh][nw] != 0 || visited[nh][nw]) continue;
				
				copy_map[nh][nw] = 2;
				virus.offer(new int[] {nh, nw});
			}
		}
		
	}
	
	
	public static int find_safe(int[][] arr) {
		
		int ans = 0; 
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(arr[i][j] == 0) {
					ans++;
				}
			}
		}
		return ans;
	}
	
	public static void print() {
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
}
