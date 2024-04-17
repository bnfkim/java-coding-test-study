package algo;

import java.io.*;
import java.util.*;

class Main {
	static final int INF = Integer.MAX_VALUE;
	
	static int T;
	static int N, K;
	static Map<Integer, List<int[]>> coords;  // 각 숫자별로 좌표 저장
	static int[][] minDist;  // 최소 거리를 기록
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			minDist = new int[N][N];
			for (int i = 0; i < N; ++i)
				Arrays.fill(minDist[i], INF);
			
			coords = new HashMap<>();
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; ++j) {
					int key = Integer.parseInt(st.nextToken());
					
					if (coords.get(key) == null)
						coords.put(key, new ArrayList<>());
					coords.get(key).add(new int[] {i, j});
					
					if (key == 1)
						minDist[i][j] = 0; 
				}
			}
			
			int answer = coords.get(1) == null ? -1 : 0;
			
			if (K != 1) {
				List<int[]> prevCoord = coords.get(1);
				List<int[]> nextCoord;
				int next = 2;
				
				answer = INF;
				
				while (next <= K) {
					nextCoord = coords.get(next);
					
					if (nextCoord == null) {
						answer = -1;
						break;
					}
					
					for (int[] p: prevCoord) {
						int px = p[0];
						int py = p[1];
						
						for (int[] n: nextCoord) {
							int nx = n[0];
							int ny = n[1];
							
							minDist[nx][ny] = Math.min(
								minDist[nx][ny],
								minDist[px][py] + distance(px, py, nx, ny)
							);
							
							if (next == K)
								answer = Math.min(answer, minDist[nx][ny]);
						}
					}
					
					prevCoord = nextCoord;
					++next;
				}
			}
			
			System.out.printf("#%d %d\n", t, answer == INF ? -1 : answer);
		}
	}
	
	public static int distance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
