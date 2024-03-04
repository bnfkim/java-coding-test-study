import java.io.*;
import java.util.*;

public class BOJ_2565_전깃줄 {

    static int N, MAX;
    static int[][] arr;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        MAX = 0;

        Arrays.sort(arr, (x, y) -> {
            return x[0] - y[0];
        });

        int[] dp = new int[N];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j][1] < arr[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            MAX = Math.max(MAX, dp[i]);
        }

        System.out.println(N - MAX - 1);

    }

}
