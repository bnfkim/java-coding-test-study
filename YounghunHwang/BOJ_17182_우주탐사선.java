import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, K, visited;
    static int answer = Integer.MAX_VALUE;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
    }

    private static void solve() {
        updateDist();
        visited |= (1 << K);
        DFS(K, 1, 0);
        System.out.println(answer);
    }

    private static void updateDist() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    private static void DFS(int cur, int depth, int time) {
        if (time >= answer) {
            return;
        }

        if (depth == N) {
            answer = time;
            return;
        }

        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) != 0) {
                continue;
            }
            visited |= (1 << i);
            DFS(i, depth + 1, time + dist[cur][i]);
            visited &= ~(1 << i);
        }
    }
}
