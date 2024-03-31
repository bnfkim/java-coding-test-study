import java.io.*;
import java.util.*;

/**
 * 16792KB   184ms
 */
public class Main {
	static int N, M, K;
	static int[][] board;
	static int answer;  // 몇 개의 칸이 채워졌는지 센다.
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		for (int k = 0; k < K; ++k) {
			
			// 스티커 모양 입력받기
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			int[][] sticker = new int[R][C];
			for (int i = 0; i < R ; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; ++j) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			outer:
			for (int rotation = 0; rotation < 4; ++rotation) {
				
				// 붙일 수 있다면 붙이기
				for (int x = 0; x < N - R + 1; ++x) {
					for (int y = 0; y < M - C + 1; ++y) {
						if (canAttach(sticker, R, C, x, y)) {
							attach(sticker, R, C, x, y);
							break outer;
						}
					}
				}
				
				// 스티커 회전
				sticker = rotate(sticker, R, C);
				int temp = R;
				R = C;
				C = temp;
			}
		}
		
		System.out.println(answer);
	}
	
	// 스티커를 시계방향으로 90도 회전
	public static int[][] rotate(int[][] sticker, int r, int c) {
		int[][] newSticker = new int[c][r];
		
		for (int i = 0; i < r; ++i) {
			for (int j = 0; j < c; ++j) {
				newSticker[j][r - 1 - i] = sticker[i][j];
			}
		}
		
		return newSticker;
	}
	
	// r행, c열의 스티커를 board의 x, y 위치에 붙일 수 있는지 여부를 반환
	public static boolean canAttach(int[][] sticker, int r, int c, int x, int y) {
		for (int i = 0; i < r; ++i) {
			for (int j = 0; j < c; ++j) {
				if (board[x + i][y + j] + sticker[i][j] == 2) return false;
			}
		}
		return true;
	}
	
	// r행, c열의 스티커를 board의 x, y 위치에 붙인다.
	public static void attach(int[][] sticker, int r, int c, int x, int y) {
		for (int i = 0; i < r; ++i) {
			for (int j = 0; j < c; ++j) {
				if (sticker[i][j] == 1) {
					answer++;
					board[x + i][y + j] = sticker[i][j];
				}
			}
		}
	}
}
