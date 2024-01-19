package ct;

import java.util.*;
import java.io.*;

public class bj_2468 {

	static int N, cnt, ans;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {

		int i, j, tmp, h, h_max = 1;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		ans = Integer.MIN_VALUE;
		
		for(i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(j = 0; j < N; j++) {
				tmp = Integer.parseInt(st.nextToken());
				h_max = Math.max(h_max, tmp);
				map[i][j] = tmp;
			}
		}
		
		// 아무 지역도 물에 잠기지 않을 수 있다 -> 높이를 0부터 탐색
		for(h = 0; h <= h_max; h++) {
			visited = new boolean[N][N];
			cnt = 0;
			for(i = 0; i < N; i++) {
				for(j = 0; j < N; j++) {
					if(!visited[i][j] && map[i][j] > h) {
						dfs(map, i, j, h);
						cnt++;
					}
				}
			}
			ans = Math.max(ans, cnt);
		}
		System.out.println(ans);
	}
	
	static void dfs(int[][] map, int x, int y, int h) {
		
		// check1
		if(visited[x][y] || map[x][y] <= h) return;
		
		visited[x][y] = true;
			
		for(int i = 0 ; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(inMap(map, nx, ny) && !visited[nx][ny]) {
				dfs(map, nx, ny, h);
			}
		}
	}
	
	static boolean inMap(int[][] map, int x, int y) {
		return (0 <= x && x < N && 0 <= y && y < N);
	}

}
