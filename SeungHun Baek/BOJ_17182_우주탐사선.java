import java.util.*;
import java.io.*;

/**
 * BOJ_17182_우주탐사선
 */
public class BOJ_17182_우주탐사선 {

    static int N, K, MIN;
    static int[][] arr, floyd;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        MIN = Integer.MAX_VALUE;

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
    }

    private static void solve() {
        doFloyd();
        backTrack(1, K, 1 << K, 0);
        System.out.println(MIN);
    }

    private static void doFloyd() {
        floyd = new int[N][N];
        for (int i = 0; i < N; i++) {
            floyd[i] = arr[i];
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
                }
            }
        }
    }

    private static void backTrack(int depth, int prev, int visited, int dist) {
        if (dist >= MIN) {
            return;
        }

        if (depth == N) {
            MIN = Math.min(MIN, dist);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (prev == i)
                continue;
            if ((visited & 1 << i) != 0)
                continue;
            backTrack(depth + 1, i, visited | 1 << i, dist + floyd[prev][i]);
        }
    }

}