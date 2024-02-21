import java.io.*;
import java.util.*;

public class Main {
	static int H1, H2, W1, W2;
	static int[][] current, target;
	// current[x][y] == 0 : x행 y열 동전
	// current[x][y] == 1 : x행 y열 빈칸
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String line;
		
		// [총 동전 개수] - [겹치는 동전 최대 개수] = [이동해야할 동전 최소 개수]
		int coinCount = 0;
		
		// 현재 동전 배치 입력
		st = new StringTokenizer(br.readLine());
		H1 = Integer.parseInt(st.nextToken());
		W1 = Integer.parseInt(st.nextToken());
		current = new int[H1][W1];
		for(int i = 0; i < H1; ++i) {
			line = br.readLine();
			for (int j = 0; j < W1; ++j) {
				current[i][j] = line.charAt(j) == 'O' ? 0 : 1;
				if (current[i][j] == 0)
					coinCount++;
			}
		}
		
		// 만들어야하는 동전 배치 입력
		st = new StringTokenizer(br.readLine());
		H2 = Integer.parseInt(st.nextToken());
		W2 = Integer.parseInt(st.nextToken());
		target = new int[H2][W2];
		for(int i = 0; i < H2; ++i) {
			line = br.readLine();
			for (int j = 0; j < W2; ++j) {
				target[i][j] = line.charAt(j) == 'O' ? 0 : 1;
			}
		}
		
		// current 판을 이동시키며 target 과 최대한 얼마나 겹칠 수 있는지 확인
		int maxCount = 0;
		
		for (int baseX = 1 - H1; baseX < H2; ++baseX) {
			for (int baseY = 1 - W1; baseY < W2; ++baseY) {
				int count = 0;
				
				for (int dx = 0; dx < H1; ++dx) {
					for (int dy = 0; dy < W1; ++dy) {
						int x = baseX + dx;
						int y = baseY + dy;
						
						// 범위를 벗어나는 곳은 계산하지 않는다.
						if (x < 0 || H2 <= x || y < 0 || W2 <= y)
							continue;
						
						if (current[dx][dy] == 0 && target[x][y] == 0)
							count++;
					}
				}
				
				maxCount = Math.max(count, maxCount);
			}
		}
		
		// 정답 출력
		System.out.println(coinCount - maxCount);
	}
}
