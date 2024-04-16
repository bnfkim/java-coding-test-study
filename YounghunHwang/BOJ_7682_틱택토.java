import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			map = new char[3][3];
			String temp = br.readLine();
			if (temp.equals("end")) {
				return;
			}
			int idx = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					map[i][j] = temp.charAt(idx++);
				}
			}

			String answer = solve();
			System.out.println(answer);
		}
	}

	private static String solve() {
		int xCount = 0;
		int oCount = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (map[i][j] == 'X') {
					xCount++;
				}
				if (map[i][j] == 'O') {
					oCount++;
				}
			}
		}

		// XO 숫자가 맞지 않으면 invalid
		if (oCount > xCount || xCount > oCount + 1) {
			return "invalid";
		}
		// 가득 차지 않았는데 승리도 아니면 invalid
		if (!(xCount == 5 && oCount == 4) && !isWin()) {
			return "invalid";
		}
		// 1개를 뺐는데 승리가 아닌 것이 없으면 invalid
		boolean noWin = false;
		char target = 'X';
		if (xCount == oCount) {
			target = 'O';
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				char temp = map[i][j];
				if (temp != target) {
					continue;
				}
				map[i][j] = '.';
				if (!isWin()) {
					noWin = true;
				}
				map[i][j] = temp;
			}
		}
		if (!noWin) {
			return "invalid";
		}

		return "valid";
	}

	private static boolean isWin() {
		// 가로
		for (int i = 0; i < 3; i++) {
			int xCount = 0;
			int oCount = 0;
			for (int j = 0; j < 3; j++) {
				if (map[i][j] == 'X') {
					xCount++;
				}
				if (map[i][j] == 'O') {
					oCount++;
				}
			}
			if (xCount == 3 || oCount == 3) {
				return true;
			}
		}
		// 세로
		for (int j = 0; j < 3; j++) {
			int xCount = 0;
			int oCount = 0;
			for (int i = 0; i < 3; i++) {
				if (map[i][j] == 'X') {
					xCount++;
				}
				if (map[i][j] == 'O') {
					oCount++;
				}
			}
			if (xCount == 3 || oCount == 3) {
				return true;
			}
		}
		// 대각선
		int xCount = 0;
		int oCount = 0;
		for (int i = 0; i < 3; i++) {
			if (map[i][i] == 'X') {
				xCount++;
			}
			if (map[i][i] == 'O') {
				oCount++;
			}
			if (xCount == 3 || oCount == 3) {
				return true;
			}
		}
		xCount = 0;
		oCount = 0;
		for (int i = 0; i < 3; i++) {
			if (map[i][2 - i] == 'X') {
				xCount++;
			}
			if (map[i][2 - i] == 'O') {
				oCount++;
			}
			if (xCount == 3 || oCount == 3) {
				return true;
			}
		}

		return false;
	}
}
