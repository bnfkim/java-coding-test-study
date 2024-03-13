import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, L;
	static int[][] waters;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		waters = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			waters[i][0] = start;
			waters[i][1] = end;
		}

		Arrays.sort(waters, (o1, o2) -> o1[0] - o2[0]);

		solve();
	}

	private static void solve() {
		int totalCount = 0;
		int bridgeEnd = waters[0][0];

		for (int i = 0; i < N; i++) {
			int start = waters[i][0];
			int end = waters[i][1];

			if (bridgeEnd >= end) {
				continue;
			}

			start = Math.max(start, bridgeEnd);
			int dist = end - start;

			if (dist <= L) {
				totalCount++;
				bridgeEnd = start + L;
			} else {
				int count = (int) Math.ceil((double) dist / L);
				totalCount += count;
				bridgeEnd = start + L * count;
			}
		}

		System.out.println(totalCount);
	}
}
