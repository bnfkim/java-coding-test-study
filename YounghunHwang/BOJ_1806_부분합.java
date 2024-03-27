import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int answer = solve();
		System.out.println(answer);
	}

	private static int solve() {
		int minLength = Integer.MAX_VALUE;
		int sum = 0;
		int left = 0;

		for (int right = 0; right < N; right++) {
			sum += nums[right];
			while (sum >= M) {
				minLength = Math.min(minLength, right - left + 1);
				sum -= nums[left];
				left++;
			}
		}

		return minLength == Integer.MAX_VALUE ? 0 : minLength;
	}
}
