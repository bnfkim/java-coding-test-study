import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] check;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		check = new boolean[N][M];
		
		for (int i = 0; i < N; ++i) {
			String line = br.readLine();
			for (int j = 0; j < M; ++j) {
				map[i][j] = line.charAt(j);
			}
		}
		
		int answer = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (check[i][j]) continue;
				
				check[i][j] = true;
				search(i, j);
				answer++;
			}
		}
		
		System.out.println(answer);
	}
	
	// 하나의 연결된 그래프를 검출, 그래프당 수렴하는 지점이 반드시 하나 존재.
	public static void search(int r, int c) {
		int nr, nc;
		
		// 현 위치로 오게 되는 방향 탐색
		for (int i = 0; i < 4; ++i) {
			nr = r + dr[i];
			nc = c + dc[i];
			
			if (invalidCoord(nr, nc) || check[nr][nc]) continue;
			if (i == 0 && map[nr][nc] != 'D') continue;
			if (i == 1 && map[nr][nc] != 'U') continue;
			if (i == 2 && map[nr][nc] != 'R') continue;
			if (i == 3 && map[nr][nc] != 'L') continue;
			
			check[nr][nc] = true;
			search(nr, nc);
		}
		
		// 현 위치에서 가는 방향 탐색
		int direction = 0;
		switch(map[r][c]) {
		case 'U': direction = 0; break;
		case 'D': direction = 1; break;
		case 'L': direction = 2; break;
		default: direction = 3;
		}
		
		nr = r + dr[direction];
		nc = c + dc[direction];
		
		if (!check[nr][nc]) {
			check[nr][nc] = true;
			search(nr, nc);
		}
	}
	
	public static boolean invalidCoord(int r, int c) {
		return r < 0 || N <= r || c < 0 || M <= c;
	}
}
