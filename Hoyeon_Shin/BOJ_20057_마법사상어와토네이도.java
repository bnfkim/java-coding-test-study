import java.io.*;
import java.util.*;

class Position {
	int x;
	int y;
	int direction;
	
	Position (int x, int y, int direction) { 
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
}

public class Main_20057_마법사상어와토네이도 {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int N;
	static int sandOutOfMap;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		// 격자 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int x = 0; x < N; ++x) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int y = 0; y < N; ++y) {
				map[x][y] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 토네이도 이동
		List<Position> moving = getMove();
		for (Position pos: moving) {
			moveTornado(pos);
		}
		
		System.out.print(sandOutOfMap);
	}
	
	// 토네이도가 이동하는 경로를 반환하는 메서드
	static List<Position> getMove() {
		List<Position> path = new ArrayList<>();
		int direction = 0;
		int step = 1;
		int x = N / 2;
		int y = N / 2;
		
		while (true) {
			if (x < 0 || y < 0) break;
			for (int i = 0; i < step; ++i) {
				x += dx[direction];
				y += dy[direction];
				
				// 토네이도가 (0, -1)에 도달하면 경로 종료
				if (y < 0) return path;
				
				path.add(new Position(x, y, direction));
			}
			
			// 방향 전환 
			direction = (direction + 1) % 4;
			
			// 2번 방향 전환마다 이동 수가 1씩 늘어난다.
			if (direction % 2 == 0)
				step++;
		}
		
		return null;
	}
	
	// 토네이도가 이동한 대로 모래의 상태를 갱신하고
	// 동시에 밖으로 나간 모래의 양을 갱신하는 함수
	static void moveTornado(Position pos) {
		int x = pos.x;
		int y = pos.y;
		int d = pos.direction;
		
		int sand = map[x][y];
		map[x][y] = 0;
		int movedSand = 0;
		
		// 모래 이동
		int left = (d + 1) % 4;
		int back = (d + 2) % 4;
		int right = (d + 3) % 4;
		
		movedSand += addSand(x + 2 * dx[d], y + 2 * dy[d], 5, sand);
		
		movedSand += addSand(x + dx[right], y + dy[right], 7, sand);
		movedSand += addSand(x + dx[left], y + dy[left], 7, sand);
		
		movedSand += addSand(x + dx[d] + dx[right], y + dy[d] + dy[right], 10, sand);
		movedSand += addSand(x + dx[d] + dx[left], y + dy[d] + dy[left], 10, sand);
		
		movedSand += addSand(x + dx[back] + dx[right], y + dy[back] + dy[right], 1, sand);
		movedSand += addSand(x + dx[back] + dx[left], y + dy[back] + dy[left], 1, sand);
		
		movedSand += addSand(x + 2 * dx[right], y + 2 * dy[right], 2, sand);
		movedSand += addSand(x + 2 * dx[left], y + 2 * dy[left], 2, sand);
		
		x += dx[d];
		y += dy[d];
		if (0 <= x && x < N && 0 <= y && y < N)
			map[x][y] += sand - movedSand;
		else
			sandOutOfMap += sand - movedSand;
	}
	
	// x, y에 sand의 num% 만큼 모래를 추가 후 추가된 모래의 양을 반환한다.
	// 만약 모래가 밖으로 나간다면 나간 모래만큼 sandOutOfMap에 추가한다.
	static int addSand(int x, int y, int num, int sand) {
		sand = (int) (sand * ((double) num / 100));
		
		if (x < 0 || N <= x || y < 0 || N <= y)
			sandOutOfMap += sand;
		else
			map[x][y] += sand;
		
		return sand;
	}
}
