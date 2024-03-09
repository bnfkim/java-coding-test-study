import java.io.*;
import java.util.*;

public class BOJ_21611_마법사상어와블리자드 {

	static int N, M, ball[], arr[][], magic[][], order[][];
	static final int[] dx = { 0, 1, 0, -1 };
	static final int[] dy = { -1, 0, 1, 0 };
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		for (int i = 0; i < M; i++) simulate(i);
		score();
	}

	private static void simulate(int index) {
		destroy(index);
		do { move(); } while (explode());
		change();
	}

	private static void destroy(int index) {
		int[] c = magic[index];
		for (int s = 1; s <= c[1]; s++) {
			int nx = s * dx[c[0]] + N / 2;
			int ny = s * dy[c[0]] + N / 2;
			if (!isRange(nx, ny)) continue;
			arr[nx][ny] = 0;
		}
	}

	private static void move() {
		int next = 0;
		for (int i = 0; i < order.length; i++) {
			int[] p = order[i];
			int x = p[0];
			int y = p[1];
			if (arr[x][y] == 0) continue;
			if (arr[x][y] != 0 && next <= i) {
				int temp = arr[x][y];
				arr[x][y] = 0;
				arr[order[next][0]][order[next][1]] = temp;
				next++;
			}
		}
	}

	private static boolean explode() {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] { order[0][0], order[0][1] });
		
		boolean exp = false;
		int now = arr[order[0][0]][order[0][1]];

		for (int i = 1; i < order.length; i++) {
			int val = arr[order[i][0]][order[i][1]];
			if (val == 0) break;
			if (now != val) {
				if (dq.size() >= 4) {
					exp = true;
					while (!dq.isEmpty()) {
						int[] p = dq.pop();
						arr[p[0]][p[1]] = 0;
						ball[now - 1]++;
					}
				} else {
					dq.clear();
				}
			}
			dq.add(new int[] { order[i][0], order[i][1] });
			now = val;
		}

		if (dq.size() >= 4) {
			exp = true;
			while (!dq.isEmpty()) {
				int[] p = dq.pop();
				arr[p[0]][p[1]] = 0;
				ball[now - 1]++;
			}
		}

		return exp;
	}

	private static void change() {
		int[][] temp = new int[N][N];
		ArrayDeque<int[]> dq = new ArrayDeque<>();

		dq.add(new int[] { order[0][0], order[0][1] });
		int now = arr[order[0][0]][order[0][1]];
		int cnt = 0;

		if (now == 0) return;

		for (int i = 1; i < order.length; i++) {
			int val = arr[order[i][0]][order[i][1]];
			if (val == 0) {
				if (!dq.isEmpty()) {
					temp[order[cnt][0]][order[cnt][1]] = dq.size();
					cnt++;
					if (cnt >= N * N - 1) break;

					temp[order[cnt][0]][order[cnt][1]] = now;
					cnt++;
					if (cnt >= N * N - 1) break;
				}
				break;
			}

			if (now != val) {
				temp[order[cnt][0]][order[cnt][1]] = dq.size();
				cnt++;
				if (cnt >= N * N - 1) break;
				temp[order[cnt][0]][order[cnt][1]] = now;
				cnt++;
				if (cnt >= N * N - 1) break;
				dq.clear();
			}
			dq.add(new int[] { order[i][0], order[i][1] });
			now = val;
		}

		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.copyOf(temp[i], N);
		}
	}

	private static void score() {
		int score = 0;
		for (int i = 0; i < 3; i++) {
			score += ball[i] * (i + 1);
		}
		System.out.println(score);
	}

	private static int[][] getOrder() {
		int[][] order = new int[N * N - 1][2];
		int size = 0;
		int x = N / 2;
		int y = N / 2;
		int dir = 0;
		int iter = 1;
		int len = 1;
		end: while (true) {
			for (int i = 1; i <= len; i++) {
				x += dx[dir];
				y += dy[dir];
				if (y == -1) break end;
				order[size][0] = x;
				order[size++][1] = y;
			}
			dir = (dir + 1) % 4;
			iter++;
			len = 1 + (iter - 1) / 2;
		}

		return order;
	}

	private static boolean isRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		magic = new int[M][2];
		ball = new int[3];
		order = new int[N * N - 1][2];
		order = getOrder();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			if (d == 1) {
				magic[i][0] = 3;
			} else if (d == 2) {
				magic[i][0] = 1;
			} else if (d == 3) {
				magic[i][0] = 0;
			} else {
				magic[i][0] = 2;
			}
			magic[i][1] = s;
		}
	}
}
