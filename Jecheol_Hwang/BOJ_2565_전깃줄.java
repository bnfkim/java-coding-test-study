package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 왼쪽 줄을 기준으로 정렬하여 오른쪽 숫자를 보았을 때,
 * 가장 긴 증가 부분 수열을 구성하면 답이 됨.
 *
 * dp[i] : i번째 숫자까지 봤을 때 가장 긴 증가 부분 수열의 길이
 */
public class BOJ_2565_전깃줄 {
    private static int[] dp;
    private static int N;
    private static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        arr = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());

        }
        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));

        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            dp[i] = 1; // 모든 항이 최소 스스로까지 최장 증가 부분 수열임.
        }

        go(N);
        int res = 0;
        for (int i = 0; i <= N; i++) {
            res = Math.max(res, dp[i]);
        }
        System.out.println(N - res);

    }

    private static int go(int n) {
        // 느낌대로 잡아가자면
        // 최장 증가 부분 수열을 구하려면
        // 중복 숫자가 없으니까 차근차근 진행해도 되고
        // 오름차순을 구성할 수 있을 때,
        // dp 값 갱신
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if (arr[i][1] > arr[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n];
    }
}
