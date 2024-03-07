import java.io.*;
import java.util.*;

/**
 * 22380KB   244ms
 */
public class Main {
	// 방향: 1-상, 2-하, 3-좌, 4-우 
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	
	static int N, M;
	static int[][] map;
	
	static int[][] coords;
	
	// explosed[n] : 폭발한 n번 구슬 개수
	static int[] explosed = new int[4];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 블리자드 마법 실행하는 함수
		// 순회할 좌표를 미리 리스트로 생성
		// 빈칸 있으면 땡기는 함수 한 개
		// 구슬 폭발시키는 함수 한 개 -> 이번에 폭발 했나 안했나에 따라 boolean반환
		// 그룹 찾아서 늘리는 함수
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		coords = new int[N * N - 1][2];
		
		// 구술 정보 입력
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		initCoords();  // 상어로부터 회오리 방향 좌표 순서 생성
		
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			doBlizardMagic(d, s);  // 마법 시전
			compaction();  // 구슬 당기기
			
			// 더이상 폭발할 구슬이 없을 때까지 연쇄 폭발 
			while(explosion())
				compaction();
			
			groupping(); // 그룹 짓기
		}
	
		System.out.println(explosed[1] + 2 * explosed[2] + 3 * explosed[3]);
	}
	
	// 회오리 모양으로 이동할 좌표를 미리 생성
	static void initCoords() {
		int x = N / 2;
		int y = N / 2;
		int direction = 3;
		int move = 1;	// 현재 방향으로 이동할 횟수
		int count = 0;  // move를 증가시킬 타이밍 측정을 위함
		int idx = 0;
		
		outer:
		while (true) {
			
			for (int i = 0; i < move; ++i) {				
				x += dx[direction];
				y += dy[direction];
				
				if (!validCoord(x, y)) break outer;
				
				coords[idx++] = new int[] {x, y};
			}
			
			if (++count % 2 == 0) move++;
			
			direction = nextDirection(direction);
		}
	}
	
	// 회오리의 중심으로부터 밖으로 이동하는 순서로 다음 방향을 반환
	static int nextDirection(int direction) {
		if (direction == 1) return 3;
		if (direction == 2) return 4;
		if (direction == 3) return 2;
		return 1;
	}
	
	// d 방향 s 만큼 블리자드 마법 수행
	static void doBlizardMagic(int d, int s) {
		int x = N / 2;
		int y = N / 2;
		
		for (int i = 0; i < s; ++i) {
			x += dx[d];
			y += dy[d];
			
			if (!validCoord(x, y)) return;
			
			map[x][y] = 0;
		}
	}
	
	// 구슬 사이에 빈칸이 발생한 경우 이동시키는 함수
	static void compaction() {
		int space = 0;
		
		for (int i = 0; i < coords.length; ++i) {
			int srcX = coords[i][0];
			int srcY = coords[i][1];
			
			if (map[srcX][srcY] == 0) {
				space++;
				continue;
			}
			
			if (space == 0) continue;
			
			int dstX = coords[i - space][0];
			int dstY = coords[i - space][1];
			map[dstX][dstY] = map[srcX][srcY];
			map[srcX][srcY] = 0;
		}
	}
	
	// 구슬 그룹을 찾아 폭발. 폭발할 그룹이 없다면 false 반환
	static boolean explosion() {
		int prev = 0;
		int seq = 0;
		boolean isExplosed = false;
		
		for (int i = 0; i < coords.length; ++i) {
			int x = coords[i][0];
			int y = coords[i][1];
			
			if (map[x][y] == 0) break;
			
			if (map[x][y] == prev) seq++;
			else {
				prev = map[x][y];
				seq = 1;
			}
			
			// 구슬이 연속 4개임을 안 순간 3번째 전 구슬부터 지금 구슬까지 터뜨린다.
			if (seq == 4) {
				for (int j = 0; j < 4; ++j) {
					int prevX = coords[i - j][0];
					int prevY = coords[i - j][1];
					explosed[map[prevX][prevY]]++;
					map[prevX][prevY] = 0;
					isExplosed = true;
				}
			}
			
			// 구슬이 연속 5개 이상째라면 현재 위치의 구슬만 터뜨린다.
			if (seq >= 5) {
				explosed[map[x][y]]++;
				map[x][y] = 0;
			}
		}
		
		return isExplosed;
	}
	
	// 구슬의 그룹을 찾아 맵에 반영하는 함수
	static void groupping() {
		List<int[]> groups = new ArrayList<>();
		int prev = 0;
		int x, y;
		
		// {{개수, 번호}, {개수, 번호}, ... } 형식으로 groups에 구슬 그룹을 저장
		for (int i = 0; i < coords.length; ++i) {
			x = coords[i][0];
			y = coords[i][1];
			
			if (map[x][y] == 0) break;
			
			if (map[x][y] == prev) {
				groups.get(groups.size() - 1)[0]++;
			}
			else {
				prev = map[x][y];
				groups.add(new int[] {1, prev});
			}
		}
		
		// 저장된 구슬 그룹을 맵에 반영
		int cnt = 0;
		for (int[] group: groups) {
			x = coords[cnt][0];
			y = coords[cnt][1];
			map[x][y] = group[0];
			if (++cnt == coords.length) break;
			
			x = coords[cnt][0];
			y = coords[cnt][1];
			map[x][y] = group[1];
			if (++cnt == coords.length) break;
		}
	}
	
	static boolean validCoord(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
}
