import java.io.*;
import java.util.*;

/*
 * 35780KB	408ms
 */
public class Main_1520_내리막길 {
	static int M, N;
	static int[][] map;
	static int[][] counts;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		counts = new int[M][N];
		
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				counts[i][j] = -1;
			}
		}
		
		System.out.println(search(M - 1, N - 1));
	}
	
	public static int search(int x, int y) {
		if (x == 0 && y == 0) return 1;
		
		int count = 0;
		
		for (int i = 0; i < 4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(invalidCoord(nx, ny)) continue;
			if (map[nx][ny] > map[x][y]) {
				if (counts[nx][ny] == -1)
					counts[nx][ny] = search(nx, ny);
				
				count += counts[nx][ny];
			}
		}
		
		return count;
	}
	
	public static boolean invalidCoord(int x, int y) {
		return x < 0 || M <= x || y < 0 || N <= y;
	}
}
