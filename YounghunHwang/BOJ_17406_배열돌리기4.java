import java.util.*;
import java.io.*;

public class Main {

	static int N, M, K;
	static int[][] map;
	static Rotate[] rotates;
	static int answer;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1];
		rotates = new Rotate[K];
		answer = Integer.MAX_VALUE;

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			rotates[i] = new Rotate(r, c, s);
		}

		solve();
	}

	private static void solve() {
		permutation(0);

		System.out.println(answer);
	}

	private static void permutation(int depth) {
		if (depth == K) {
			rotateMap();
			return;
		}

		for (int i = depth; i < K; i++) {
			swap(i, depth);
			permutation(depth + 1);
			swap(i, depth);
		}
	}

	private static void swap(int x, int y) {
		Rotate temp = rotates[x];
		rotates[x] = rotates[y];
		rotates[y] = temp;
	}

	private static void rotateMap() {
		int[][] tempMap = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			tempMap[i] = map[i].clone();
		}

		for (Rotate rot : rotates) {
			rot.rotate(tempMap);
		}

		updateAnswer(tempMap);
	}

	private static void updateAnswer(int[][] map) {
		for (int i = 1; i <= N; i++) {
			int sum = 0;

			for (int j = 1; j <= M; j++) {
				sum += map[i][j];
			}
			answer = Math.min(answer, sum);
		}
	}

	static class Rotate {
		int r;
		int c;
		int s;

		public Rotate(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}

		public void rotate(int[][] map) {
			for (int i = 1; i <= s; i++) {
				int x = r - i;
				int y = c - i;
				int temp = map[x][y];
				int dir = 0;

				while (dir < 4) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					if (nx < r - i || nx > r + i || ny < c - i || ny > c + i) {
						dir++;
						continue;
					}
					map[x][y] = map[nx][ny];
					x = nx;
					y = ny;
				}

				map[r - i][c - i + 1] = temp;
			}
		}
	}
}
