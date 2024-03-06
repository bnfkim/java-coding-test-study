package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2705_팰린드롬파티션 {
    public static void main(String[] args) throws IOException {
        int[] dp = new int[1001];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= 1000; i++) {
            dp[i] = 1; // 기본 초기화
            if (i % 2 == 0) { // 짝수면
                dp[i] += dp[i / 2];
            }
            int mid = i;
            while (mid - 2 > 0) {
                mid -= 2;
                dp[i] += dp[(i - mid) / 2];
            }
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append("\n");
        }
        System.out.println(sb.toString());
    }



}
