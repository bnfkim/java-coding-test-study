import java.io.*;
import java.util.*;

class Main {
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int N;
	
	// dragon curve가 (x, y)를 지나면 coord[y][x] = true  
	static boolean[][] coord = new boolean[101][101];
	
	public static void main(String[] args) throws IOException {
		
		// 왼쪽으로 시작하는 10세대 dragon curve까지 만들 수 있는 방향을 생성
		// curve[0] ~ [2^g - 1] : 왼쪽으로 시작해 g세대 dragon curve를 만들 수 있는 방향 순서
		List<Integer> curve = new ArrayList<>();
		curve.add(0);  // 0 세대 방향 →
		
		for (int gen = 1; gen <= 10; ++gen) {
			int end = (int) Math.pow(2, gen - 1) - 1;
			
			while (end >= 0) {
				int curDirection = curve.get(end--);
				curve.add((curDirection + 1) % 4);
			}
		}
		
		// 좌표에 dragon curve 그리기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; ++i) {
			
			// input curve info
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			// 드래곤 커브 이동 흔적 표시
			coord[y][x] = true;
			for (int j = 0, terminate = (int) Math.pow(2, g); j < terminate; ++j) {
				
				// 시작 방향 지정
				int direction = (curve.get(j) + d) % 4;
				
				// 해당 방향으로 이동
				y += dy[direction];
				x += dx[direction];
				coord[y][x] = true;
			}
		}
		
		// 정사각형 수 구하기
		int square = 0;
		for (int x = 0; x < 100; ++x) {
			for (int y = 0; y < 100; ++y) {
				if (coord[y][x] && coord[y + 1][x] && coord[y][x + 1] && coord[y + 1][x + 1])
					square++;
			}
		}
		
		System.out.println(square);
	}
}
