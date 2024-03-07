import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class asdf {
	static int n, ans;
	static long board[][];
  
	public static void BOJ_23796_2147483648게임(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new long[8][8];
		
		StringTokenizer st;
		for(int i = 0; i < 8; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 8; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		char cmd = br.readLine().charAt(0);
		if(cmd == 'U') {
			long tmp[][] = new long[8][8];
			for(int c = 0; c < 8; c++) {
				int cnt = 0;
				for(int r = 0; r < 8; r++) {
					if(board[r][c] != 0) {
						tmp[cnt++][c] = board[r][c];
					}
				}
			}
			
			for(int c = 0; c < 8; c++) {
				for(int r = 0; r < 7; r++) {
					if(tmp[r][c] == tmp[r+1][c]) {
						tmp[r][c] *= 2;
						tmp[r+1][c] = 0;
					}
				}
			}
			
			long tmp2[][] = new long[8][8];
			for(int c = 0; c < 8; c++) {
				int cnt = 0;
				for(int r = 0; r < 8; r++) {
					if(tmp[r][c] != 0) {
						tmp2[cnt++][c] = tmp[r][c];
					}
				}
			}
			
			for(int r = 0; r < 8; r++) {
				board[r] = tmp2[r].clone();
			}
			
		}
		else if(cmd == 'D') {
			long tmp[][] = new long[8][8];
			for(int c = 0; c < 8; c++) {
				int cnt = 7;
				for(int r = 7; r >= 0; r--) {
					if(board[r][c] != 0) {
						tmp[cnt--][c] = board[r][c];
					}
				}
			}
			
			for(int c = 0; c < 8; c++) {
				for(int r = 7; r > 0; r--) {
					if(tmp[r][c] == tmp[r-1][c]) {
						tmp[r][c] *= 2;
						tmp[r-1][c] = 0;
					}
				}
			}
			
			long tmp2[][] = new long[8][8];
			for(int c = 0; c < 8; c++) {
				int cnt = 7;
				for(int r = 7; r >= 0; r--) {
					if(tmp[r][c] != 0) {
						tmp2[cnt--][c] = tmp[r][c];
					}
				}
			}
			
			for(int r = 0; r < 8; r++) {
				board[r] = tmp2[r].clone();
			}
			
		}
		else if(cmd == 'L') {
			long tmp[][] = new long[8][8];
			for(int r = 0; r < 8; r++) {
				int cnt = 0;
				for(int c = 0; c < 8; c++) {
					if(board[r][c] != 0) {
						tmp[r][cnt++] = board[r][c];
					}
				}
			}
			
			for(int r = 0; r < 8; r++) {
				for(int c = 0; c < 7; c++) {
					if(tmp[r][c] == tmp[r][c+1]) {
						tmp[r][c] *= 2;
						tmp[r][c+1] = 0;
					}
				}
			}
			
			long tmp2[][] = new long[8][8];
			for(int r = 0; r < 8; r++) {
				int cnt = 0;
				for(int c = 0; c < 8; c++) {
					if(tmp[r][c] != 0) {
						tmp2[r][cnt++] = tmp[r][c];
					}
				}
			}
			
			for(int r = 0; r < 8; r++) {
				board[r] = tmp2[r].clone();
			}
		}
		else {
			long tmp[][] = new long[8][8];
			for(int r = 0; r < 8; r++) {
				int cnt = 7;
				for(int c = 7; c >= 0; c--) {
					if(board[r][c] != 0) {
						tmp[r][cnt--] = board[r][c];
					}
				}
			}
			
			for(int r = 0; r < 8; r++) {
				for(int c = 7; c > 0; c--) {
					if(tmp[r][c] == tmp[r][c-1]) {
						tmp[r][c] *= 2;
						tmp[r][c-1] = 0;
					}
				}
			}
			
			long tmp2[][] = new long[8][8];
			for(int r = 0; r < 8; r++) {
				int cnt = 7;
				for(int c = 7; c >= 0; c--) {
					if(tmp[r][c] != 0) {
						tmp2[r][cnt--] = tmp[r][c];
					}
				}
			}
			
			for(int r = 0; r < 8; r++) {
				board[r] = tmp2[r].clone();
			}
		}
		
		for(int r = 0; r < 8; r++) {
			for(int c = 0; c < 8; c++) {
				System.out.print(board[r][c]+" ");
			}System.out.println();
		}
	}
}
