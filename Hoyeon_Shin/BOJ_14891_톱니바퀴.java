import java.io.*;
import java.util.*;

/**
 * 
 * 14248KB  128ms 
 *
 */
class Main {
	static int[][] gears = new int[4][8];
	static int[] topPosition = new int[4];  // 각 기어의 12시 방향의 인덱스를 저장
	static int[] dt = new int[4];  // topPosition값을 임시 저장 
	static int K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 4; ++i) {
			String line = br.readLine();
			for (int j = 0; j < 8; ++j) {
				gears[i][j] = line.charAt(j) - '0';
			}
		}
		
		K = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < K; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int gearNum = Integer.parseInt(st.nextToken()) - 1;			
			int prevL = Integer.parseInt(st.nextToken());			
			int prevR = prevL;
			
			dt[gearNum] = (topPosition[gearNum] - prevL + 8) % 8;
			for (int j = 0; j < 3; ++j) {
				prevL = rotate(gearNum + j, prevL, gearNum + j + 1);
				prevR = rotate(gearNum - j, prevR, gearNum - j - 1);
			}
			
			for (int t = 0; t < 4; ++t) {
				topPosition[t] = dt[t];
			}
		}
		
		int score = 0;
		score += gears[0][topPosition[0]];
		score += gears[1][topPosition[1]] * 2;
		score += gears[2][topPosition[2]] * 4;
		score += gears[3][topPosition[3]] * 8;
		
		System.out.println(score);
	}
	
	// cur이 move 방향으로 움직였을 때 next가 움직인 방향을 반환
	public static int rotate(int cur, int move, int next) {
		if (next < 0 || 4 <= next || move == 0) return 0;
		
		int curAdj, nextAdj;  // cur과 next가 각각 맞닿은 부분의 인덱스
		
		// cur의 오른쪽에 next가 있는 상황
		if (next > cur) {
			curAdj = (topPosition[cur] + 2) % 8;
			nextAdj = (topPosition[next] + 6) % 8;
		}
		
		// cur의 왼쪽에 next가 있는 상황
		else {
			curAdj = (topPosition[cur] + 6) % 8;
			nextAdj = (topPosition[next] + 2) % 8;
		}
		
		// cur과 next가 인접한 부분의 극성이 다르다면 next를 cur 반대방향으로 회전		
		if (gears[cur][curAdj] != gears[next][nextAdj])
			dt[next] = (topPosition[next] + move + 8) % 8;
		else
			move = 0;
		
		return -move;
	}
}
