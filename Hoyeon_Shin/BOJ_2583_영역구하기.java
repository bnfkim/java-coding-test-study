import java.io.*;
import java.util.*;

public class Main {
	static int M, N, K;
	static int[][] map;
	static boolean[][] check;
	
	// r, c로부터 이어진 영역의 check를 true로 변환
	// 영역의 넓이 반환
	public static int getArea(int sr, int sc) {
		int[] dr = {0, 0, 1, -1};
		int[] dc = {1, -1, 0, 0};
		int area = 1;
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sr, sc});
		check[sr][sc] = true;
		
		while (!q.isEmpty()) {
			int r = q.peek()[0];
			int c = q.poll()[1];
			
			for (int i = 0; i < 4; ++i) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (nr < 0 || M <= nr) continue;
				if (nc < 0 || N <= nc) continue;
				if (check[nr][nc] || map[nr][nc] == 1) continue;
				
				area++;
				check[nr][nc] = true;
				q.offer(new int[] {nr, nc});
			}
		}
		
		return area;
	}
	
	public static void main(String[] args) throws IOException {
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		check = new boolean[M][N];
		
		for (int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine());
			int leftBottomX = Integer.parseInt(st.nextToken());
			int leftBottomY = Integer.parseInt(st.nextToken());
			int rightTopX = Integer.parseInt(st.nextToken());
			int rightTopY = Integer.parseInt(st.nextToken());
			
			for (int r = M - rightTopY; r < M - leftBottomY; ++r) {
				for (int c = leftBottomX; c < rightTopX; ++c) {
					map[r][c] = 1;
				}
			}
		}
		
		// 영역 찾기
		List<Integer> areas = new ArrayList<>();	
		for (int i = 0; i < M; ++i) {
			for (int j = 0; j < N; ++j) {
				if (!check[i][j] && map[i][j] == 0) {
					areas.add(getArea(i, j));
				}
			}
		}
		
		// 출력
		Collections.sort(areas);
		System.out.println(areas.size());
		for (int i: areas) {
			System.out.print(i + " ");
		}
	}
}
