import java.io.*;
import java.util.*;

/**
 * 14228KB	 124ms
 */
public class Main {
	static long[][] board = new long[8][8];
	
	// 0: 상, 1: 우, 2: 하, 3: 좌
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 8; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 8; ++j) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		push(br.readLine().charAt(0));
		
		StringBuilder sb = new StringBuilder();
		for (long[] line: board) {
			for (long num: line) {
				sb.append(num).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
	}
	
	public static void push(char direction) {
		int baseX, baseY;
		int d;  // 방향 번호 (상, 하, 좌, 우) = (0, 1, 2, 3)
		
		switch(direction) {
		case 'U':
			baseX = 0; baseY = 0;
			d = 0;
			break;
		case 'R':
			baseX = 0; baseY = 7;
			d = 1;
			break;
		case 'D':
			baseX = 7; baseY = 7;
			d = 2;
			break;
		default:
			baseX = 7; baseY = 0;
			d = 3;
		};
		
		int r = (d + 2) % 4;  // d의 반대방향
		
		// 한 줄씩 옆으로 이동해가며 밀기
		while (validCoord(baseX, baseY)) {
			int x = baseX;
			int y = baseY;
			
			long prev = -1;  // 이전에 살펴본 숫자블록
			int space = 0;  // 밀어야하는 칸 수
			
			while (validCoord(x, y)) {
				
				// 같은 수 존재시 합치기
				if (board[x][y] == prev) {
					space++;
					board[x + space * dx[d]][y + space * dy[d]] = 2 * board[x][y];
					board[x][y] = 0;
					prev = -1;
				}
				else if (board[x][y] == 0) {
					space++;
				}
				else {
					prev = board[x][y];
					if (space > 0) {
						board[x + space * dx[d]][y + space * dy[d]] = board[x][y];
						board[x][y] = 0;						
					}
				}
				
				x += dx[r];
				y += dy[r];
			}
			
			baseX += dx[(d + 1) % 4];
			baseY += dy[(d + 1) % 4];
		}
	}
	
	public static boolean validCoord(int x, int y) {
		return 0 <= x && x < 8 && 0 <= y && y < 8;
	}
}
