import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2705_팰린드롬파티션 {
    static int[] dp = new int[1001];
    static int t, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        dp[0] = dp[1] = 1;
        for (int i = 2; i < 1001; i++) {
            if ((i & 1) != 0) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = dp[i - 2] + dp[i / 2];
            }
        }
  
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }
    }
}
