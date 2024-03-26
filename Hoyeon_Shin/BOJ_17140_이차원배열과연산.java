import java.io.*;
import java.util.*;

/**
 * 17712KB	212ms
 */
class Main {
	static int r, c, k;
	static int[][] A = new int[100][100];
	int[] dx = {0, 1};
	int[] dy = {1, 0};
	
	static int rowLen = 3;
	static int colLen = 3;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < 3; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; ++j) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		while (A[r - 1][c - 1] != k && answer <= 100) {
			answer++;
			if (rowLen >= colLen) operR();
			else operC();
		}
		
		System.out.println(answer > 100 ? -1 : answer);
	}
	
	// 각 열마다 정렬 수행
	public static void operR() {
		int maxCol = 0;
		
		int row = 0;
		while (row < rowLen) {
			Map<Integer, Integer> counter = new HashMap<>();
			int col = 0;
			
			// 숫자 등장 빈도 세기
			while (col < colLen) {
				if (A[row][col] != 0)
					counter.put(A[row][col], counter.getOrDefault(A[row][col], 0) + 1);
				col++;
			}
			
			// 정렬 수행
			int newC = 0;
			
			if (!counter.isEmpty()) {
				List<Map.Entry<Integer, Integer>> newA = new ArrayList<>(counter.entrySet());
				Collections.sort(newA, (a, b) -> {
					if (a.getValue() == b.getValue())
						return a.getKey() - b.getKey();
					return a.getValue() - b.getValue();
				});
				
				for (Map.Entry<Integer, Integer> entry: newA) {
					if (newC >= 100) break;
					A[row][newC++] = entry.getKey();
					A[row][newC++] = entry.getValue();
				}
				
				maxCol = Math.max(maxCol, newC);				
			}
			
			while (newC < 100) {
				A[row][newC++] = 0;
			}

			row++;
		}
		
		colLen = maxCol;
	}
	
	// 각 행마다 정렬 수행
	public static void operC() {
		int maxRow = 0;
		
		int col = 0;
		while (col < colLen) {
			Map<Integer, Integer> counter = new HashMap<>();
			
			// 0이 숫자보다 먼저 나오는 경우 건너뛰기
			int row = 0;
			while (A[row][col] == 0) row++;
			
			// 숫자 등장 빈도 세기
			while (row < rowLen) {
				if (A[row][col] != 0)
					counter.put(A[row][col], counter.getOrDefault(A[row][col], 0) + 1);
				row ++;
			}
			
			// 정렬 수행
			int newR = 0;
			
			if (!counter.isEmpty()) {
				List<Map.Entry<Integer, Integer>> newA = new ArrayList<>(counter.entrySet());
				Collections.sort(newA, (a, b) -> {
					if (a.getValue() == b.getValue())
						return a.getKey() - b.getKey();
					return a.getValue() - b.getValue();
				});
				
				for (Map.Entry<Integer, Integer> entry: newA) {
					if (newR >= 100) break;
					A[newR++][col] = entry.getKey();
					A[newR++][col] = entry.getValue();
				}
				
				maxRow = Math.max(maxRow, newR);				
			}
			
			while (newR < 100) {
				A[newR++][col] = 0;
			}

			col++;
		}
		
		rowLen = maxRow;
	}
}
