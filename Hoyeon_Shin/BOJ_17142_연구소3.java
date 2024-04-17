import java.io.*;
import java.util.*;

/**
 * 29384KB    276ms
 */
public class Main {
	static final int INF = Integer.MAX_VALUE;
	
	static int N, M;
	static int[][] map;
	static int spaceCount;
	
	static List<int[]> virusCoord;  // map 내의 바이러스들의 좌표
	static int[] selectedVirus;  // M개의 선택된 바이러스 인덱스 저장
	
	static int minTime = INF;
	
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		virusCoord = new ArrayList<>();
		selectedVirus = new int[M];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 0) spaceCount++;
				else if (map[i][j] == 2) virusCoord.add(new int[] {i, j});
			}
		}
		
		search(0, 0);
		System.out.println(minTime == INF ? -1 : minTime);
	}
	
	// M개의 바이러스를 선택해 퍼뜨린 후 minTime을 갱신
	public static void search(int idx, int start) {
		if (idx == M) {
			minTime = Math.min(spreadVirus(), minTime);
			return;
		}
		
		for (int i = start; i < virusCoord.size(); ++i) {
			selectedVirus[idx] = i;
			search(idx + 1, i + 1);
		}
	}
	
	// 바이러스를 모두 퍼뜨리는데 걸리는 시간을 반환
	// minTime 보다 걸리는 시간이 커지는 순간 함수 수행을 종료한다.
	public static int spreadVirus() { 
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new ArrayDeque<>();
		
		for (int i = 0; i < M; ++i) {
			q.offer(virusCoord.get(selectedVirus[i]));
		}
		
		int time = -1;
		int infectCount = 0;
		
		while (true) {
			int qSize = q.size();
			if (qSize == 0) break;
			
			time++;
			if (time >= minTime) return time;
			if (infectCount == spaceCount) return time;
			
			for (int i = 0; i < qSize; ++i) {
				int r = q.peek()[0];
				int c = q.poll()[1];
				
				for (int d = 0; d < 4; ++d) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if (!validCoord(nr, nc)) continue;
					if (map[nr][nc] == 1) continue;
					if (visited[nr][nc]) continue;
					
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
					if (map[nr][nc] == 0) infectCount++;
				}
			}
		}
		
		return INF;
	}
	
	public static boolean validCoord(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
