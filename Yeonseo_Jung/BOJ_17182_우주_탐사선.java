import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17182_우주_탐사선 {

    static int N, K, answer;
    static int[][] T;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        T = new int[N][N];
        visited = new boolean[N];
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                T[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    T[i][j] = Math.min(T[i][j], T[i][k] + T[k][j]);
                }
            }
        }

        visited[K] = true;
        dfs(K, 0, 0);
        System.out.println(answer);
    }

    static void dfs(int pos, int cnt, int cost) {
        if (cnt == N - 1) {
            answer = Math.min(answer, cost);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, cnt + 1, cost + T[pos][i]);
                visited[i] = false;
            }
        }
    }
}
