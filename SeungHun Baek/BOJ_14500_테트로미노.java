import java.util.*;
import java.io.*;

/**
 * 300560kb
 * 872ms
 */
public class BOJ_14500_테트로미노 {

    static int N, M, MAX, arr[][];
    static final int LEN = 4;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                except(i, j);
                dfs(0, new int[] { i, j }, new int[] { -1, -1 }, 0);
            }
        }
        System.out.println(MAX);
    }

    private static void dfs(int depth, int[] current, int[] prev, int value) {
        if (depth == LEN) {
            MAX = Math.max(MAX, value);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = current[0] + dx[i];
            int ny = current[1] + dy[i];
            if (!isRange(nx, ny))
                continue;
            if (nx == prev[0] && ny == prev[1])
                continue;
            dfs(depth + 1, new int[] { nx, ny }, current, value + arr[current[0]][current[1]]);
        }
    }

    private static void except(int x, int y) {
        int tempMax = 0;

        if (isRange(x + 1, y + 1) && isRange(x, y + 2)) {
            int v = 0;
            for (int j = y; j < y + 3; j++) {
                v += arr[x][j];
            }
            tempMax = Math.max(tempMax, v + arr[x + 1][y + 1]);
        }
        
        if (isRange(x + 2, y) && isRange(x + 1, y + 1)) {
            int v = 0;
            for (int i = x; i < x + 3; i++) {
                v += arr[i][y];
            }
            tempMax = Math.max(tempMax, v + arr[x + 1][y + 1]);
        }

        if (isRange(x + 2, y) && isRange(x + 1, y - 1)) {
            int v = 0;
            for (int i = x; i < x + 3; i++) {
                v += arr[i][y];
            }
            tempMax = Math.max(tempMax, v + arr[x + 1][y - 1]);
        }

        if (isRange(x - 1, y + 1) && isRange(x, y + 2)) {
            int v = 0;
            for (int j = y; j < y + 3; j++) {
                v += arr[x][j];
            }
            tempMax = Math.max(tempMax, v + arr[x - 1][y + 1]);
        }
        
        MAX = Math.max(MAX, tempMax);
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        MAX = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}