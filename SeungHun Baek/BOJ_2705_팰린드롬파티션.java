import java.io.*;

public class BOJ_2705_팰린드롬파티션 {

	static int T;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		dp = new int[1001];
		dp[0] = 1;

		for (int i = 1; i <= 1000; i++) {
			for (int j = i % 2; j <= i; j += 2) {
				dp[i] += dp[(i - j) >> 1];
			}
		}

		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			System.out.println(dp[N]);
		}
	}
}
