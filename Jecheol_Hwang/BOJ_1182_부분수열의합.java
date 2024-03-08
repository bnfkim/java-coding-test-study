package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1182_부분수열의합 {
    private static int N, S;
    private static int[] arr;
    private static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        go(0, 0, 0);
        System.out.println(ans);
    }

    private static void go(int cnt, int sum, int visited) {
        if (cnt == N) {
            if (sum == S && visited != 0) { // 최소 한개는 고르도록
                ans++;
            }
            return;
        }

        go(cnt + 1, sum + arr[cnt], visited | 1 << cnt);
        go(cnt + 1, sum, visited);
    }
}
