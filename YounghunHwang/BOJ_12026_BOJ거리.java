import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static char[] letters;
	static int INF = Integer.MAX_VALUE / 4;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		letters = br.readLine().toCharArray();

		int answer = solve();
		System.out.println(answer);
	}

	private static int solve() {
		int[] dp = new int[N];
		for (int i = 1; i < N; i++) {
			dp[i] = INF;
		}

		for (int i = 0; i < N; i++) {
			char target = getNextTarget(letters[i]);
			for (int j = i + 1; j < N; j++) {
				if (letters[j] != target) {
					continue;
				}
				dp[j] = Math.min(dp[j], dp[i] + (j - i) * (j - i));
			}
		}

		if (dp[N - 1] == INF) {
			return -1;
		}
		return dp[N - 1];
	}

	private static char getNextTarget(char curr) {
		if (curr == 'B') {
			return 'O';
		}
		if (curr == 'O') {
			return 'J';
		}
		return 'B';
	}
}
