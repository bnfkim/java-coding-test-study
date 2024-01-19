package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2579_계단오르기 {
    /*
    * 시간 : 124ms
    * 메모리 : 14184KB
    * 로직 : 이전에 계단을 한칸 올랐는지 두칸 올랐는지를 2차원 배열 앞 idx로 구분하여 기본 DP 로직으로 구성했습니다.
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[3][n+1];
        int[] arr = new int[n+1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[1][0] = 0;
        dp[2][0] = 0;
        dp[1][1] = arr[1];
        dp[2][1] = arr[1];
        for (int i = 2; i <= n; i++) {
            dp[1][i] = Math.max(dp[2][i - 2] + arr[i], dp[1][i-2] + arr[i]);
            dp[2][i] = dp[1][i - 1] + arr[i];
        }

        System.out.println(Math.max(dp[1][n], dp[2][n]));

    }
}
