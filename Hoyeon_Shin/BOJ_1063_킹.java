import java.io.*;
import java.util.*;

/**
 * 
 * 16280KB	160ms
 *
 */
class Main {
	static int kr, kc;  // 킹 위치 행, 열 (1 ~ 8 까지의 정수)
	static int sr, sc;  // 돌 위치 행, 열
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String king = st.nextToken();
		String stone = st.nextToken();
		N = Integer.parseInt(st.nextToken());
		
		kr = king.charAt(1) - '0'; 
		kc = king.charAt(0) - 'A' + 1;
		
		sr = stone.charAt(1) - '0'; 
		sc = stone.charAt(0) - 'A' + 1;
		
		for (int i = 0; i < N; ++i) {
			int[] d = move(br.readLine());
			
			int krNext = kr + d[0];
			int kcNext = kc + d[1];
			
			if (invalidCoord(krNext, kcNext)) continue;
			
			// 킹이 이동하고자 하는 곳에 돌이 있다면
			if (krNext == sr && kcNext == sc) {
				int srNext = sr + d[0];
				int scNext = sc + d[1];
				
				if (invalidCoord(srNext, scNext)) continue;
				
				sr = srNext;
				sc = scNext;
			}
			
			kr = krNext;
			kc = kcNext;
		}
		
		System.out.println(coord2Str(kr, kc));
		System.out.println(coord2Str(sr, sc));
	}
	
	public static int[] move(String str) {
		switch(str) {
		case "R": return new int[] {0, 1};
		case "L": return new int[] {0, -1};
		case "B": return new int[] {-1, 0};
		case "T": return new int[] {1, 0};
		case "RT": return new int[] {1, 1};
		case "LT": return new int[] {1, -1};
		case "RB": return new int[] {-1, 1};
		case "LB": return new int[] {-1, -1};
		}
		return new int[] {};
	}
	
	public static boolean invalidCoord(int x, int y) {
		return x < 1 || 8 < x || y < 1 || 8 < y;
	}
	
	public static String coord2Str(int r, int c) {
		String result = "";
		result += (char) (c + 'A' - 1);
		result += r;
		return result;
	}
}
