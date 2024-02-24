import java.io.*;
import java.util.*;

/**
 * memory: 34376kb
 * time: 428ms
 */
public class Main {
	static int N;
	static char[][] stars;
	
	public static void main(String[] args) throws IOException {
		N = (new Scanner(System.in)).nextInt();
		stars = new char[N][N];
		
		getStar(N, 0, 0);
		
		for (int r = 0; r < N; ++r)
			System.out.println(new String(stars[r]));
	}
	
	// n : 가로 길이
	// r, c : 왼쪽 위 좌표
	public static void getStar(int n, int r, int c) {
		if (n == 1) {
			stars[r][c] = '*';
			return;
		}
		
		int sep = n / 3;
		
		// 윗줄
		for (int i = 0; i < 3; ++i)
			getStar(n / 3, r, c + i*sep);
		
		// 가운데
		getStar(n / 3, r + sep, c);
		for (int R = r + sep; R < r + 2*sep; ++R)
			for (int C = c + sep; C < c + 2*sep; ++C)
				stars[R][C] = ' ';
		getStar(n / 3, r + sep, c + 2*sep);
		
		// 아랫줄
		for (int i = 0; i < 3; ++i)
			getStar(n / 3, r + 2*sep, c + i*sep);
	}
}
