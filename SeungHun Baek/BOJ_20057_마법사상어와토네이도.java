package temp;

import java.io.*;
import java.util.*;

public class BOJ_20057_마법사상어와토네이도 {
	static class Wind {
		int x, y, p;

		Wind(int x, int y, int p) {
			this.x = x;
			this.y = y;
			this.p = p;
		}
	}

	static int N, iter, dir, cnt, sand;
	static int row, col;
	static int[][] arr;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static Wind[] wind;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dir = iter = sand = 0;
		cnt = 1;
		row = col = N / 2;
		
		wind = new Wind[9];
		wind[0] = new Wind(1, 0, 1);
		wind[1] = new Wind(-1, 0, 1);
		wind[2] = new Wind(-1, -1, 7);
		wind[3] = new Wind(1, -1, 7);
		wind[4] = new Wind(2, -1, 2);
		wind[5] = new Wind(-2, -1, 2);
		wind[6] = new Wind(1, -2, 10);
		wind[7] = new Wind(-1, -2, 10);
		wind[8] = new Wind(0, -3, 5);

		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		move();
		System.out.println(sand);
	}

	public static void changeDirection() {
		dir++;
		dir %= 4;
		iter++;
		cnt = (iter / 2) + 1;
	}

	public static void move() {
		end: while (true) {
			for (int i = 0; i < cnt; i++) {
				int nx = row + dx[dir];
				int ny = col + dy[dir];
				if (nx == 0 && ny == -1)
					break end;
				tornado(nx, ny);
				row = nx;
				col = ny;
			}
			changeDirection();
		}
	}

	public static void tornado(int nx, int ny) {
		int amount = arr[nx][ny];
		int used = 0;
		for (int idx = 0; idx < 9; idx++) {
			Wind w = wind[idx];
			
			int[] pos = rotate(w);
			int percent = w.p;
			int getSand = amount * percent / 100;

			int mx = row + pos[0];
			int my = col + pos[1];
			if (!isPossible(mx, my)) {
				used += getSand;
				sand += getSand;
				continue;
			}

			used += getSand;
			arr[mx][my] += getSand;
		}

		int rest = amount - used;
		int rx = nx + dx[dir];
		int ry = ny + dy[dir];
		if (!isPossible(rx, ry)) {
			sand += rest;
			return;
		}

		arr[rx][ry] += rest;
	}

	public static int[] rotate(Wind w) {
		int x = w.x;
		int y = w.y;
		for (int i = 0; i < dir; i++) {
			int temp = -y;
			y = x;
			x = temp;
		}
		return new int[]{x, y};
	}

	public static boolean isPossible(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

}
