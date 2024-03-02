import java.util.*;
import java.io.*;

public class BOJ_14502_연구소 {

    static int N, M, MAX, arr[][], temp[][];
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static boolean[] idx;
    static boolean[][] visited;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        idx = new boolean[N * M];

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
    }

    private static void solve() {
        comb(0, 0);
        System.out.println(MAX);
    }

    private static void comb(int depth, int index) {
        if (depth == 3) {
            simulate();
            return;
        }

        for (int i = index; i < N * M; i++) {
            if (!isValid(i))
                continue;
            idx[i] = true;
            comb(depth + 1, i + 1);
            idx[i] = false;
        }
    }

    private static void simulate() {
        temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = idx[M * i + j] ? 1 : arr[i][j];
            }
        }

        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j])
                    continue;
                if (temp[i][j] == 0)
                    continue;
                if (temp[i][j] == 1)
                    continue;
                bfs(i, j);
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cnt += temp[i][j] == 0 ? 1 : 0;
            }
        }

        MAX = Math.max(MAX, cnt);

    }

    private static void bfs(int i, int j) {
        ArrayDeque<int[]> que = new ArrayDeque<>();
        que.add(new int[] { i, j });
        visited[i][j] = true;

        while (!que.isEmpty()) {
            int[] pos = que.pop();
            for (int idx = 0; idx < 4; idx++) {
                int nx = pos[0] + dx[idx];
                int ny = pos[1] + dy[idx];
                if (!isRange(nx, ny))
                    continue;
                if (temp[nx][ny] == 1)
                    continue;
                if (visited[nx][ny])
                    continue;

                temp[nx][ny] = 2;
                visited[nx][ny] = true;
                que.add(new int[] { nx, ny });
            }
        }
    }

    private static boolean isValid(int pos) {
        int x = pos / M;
        int y = pos % M;
        if (arr[x][y] == 1)
            return false;
        if (arr[x][y] == 2)
            return false;
        return true;
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
