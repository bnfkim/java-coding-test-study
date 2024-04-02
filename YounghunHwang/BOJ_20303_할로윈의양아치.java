import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 양방향 단방향 그래프 주의!
 * */
public class Main {

	static int N, M, K;
	static int[] values;
	static List<Integer>[] graph;
	static List<Group> groups;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[N + 1];
		values = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			values[i] = Integer.parseInt(st.nextToken());
		}
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}

		int answer = solve();
		System.out.println(answer);
	}

	private static int solve() {
		makeGroups();
		return findAnswer();
	}

	private static void makeGroups() {
		groups = new ArrayList<>();
		groups.add(new Group(0, 0));
		for (int i = 1; i <= N; i++) {
			if (visited[i]) {
				continue;
			}
			Group group = BFS(i);
			groups.add(group);
		}
	}

	private static int findAnswer() {
		int[][] dp = new int[groups.size()][K];

		for (int j = 1; j < K; j++) {
			for (int i = 1; i < groups.size(); i++) {
				if (j < groups.get(i).w) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - groups.get(i).w] + groups.get(i).v);
				}
			}
		}

//		printDP(dp);

		return dp[groups.size() - 1][K - 1];
	}

	private static void printDP(int[][] dp) {
		for (int i = 0; i < groups.size(); i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
		System.out.println();
	}

	private static Group BFS(int start) {
		int w = 0;
		int v = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int curr = queue.poll();
			w++;
			v += values[curr];

			for (int next : graph[curr]) {
				if (visited[next]) {
					continue;
				}
				queue.add(next);
				visited[next] = true;
			}
		}

		return new Group(w, v);
	}

	static class Group {
		int w;
		int v;

		public Group(int w, int v) {
			this.w = w;
			this.v = v;
		}
	}
}
