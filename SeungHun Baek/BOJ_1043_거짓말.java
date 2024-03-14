import java.util.*;
import java.io.*;

/**
 * 11828kb
 * 84ms
 */
public class BOJ_1043_거짓말 {

    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, 1, 0, -1 };
    static int N, M, CNT, arr[][];
    static boolean know[];
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        simulate();
        count();
    }

    private static void simulate() {
        for (int i = 0; i < M; i++) {
            for (int j = 1; j <= N; j++) {
                if (arr[i][j] == 2) {
                    group(i, j);
                }
            }
        }
    }

    private static void group(int x, int y) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { x, y });

        while (!dq.isEmpty()) {
            int[] pos = dq.pop();
            for (int i = 0; i < 4; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];

                while (isRange(nx, ny) && arr[nx][ny] != 1) {
                    nx += dx[i];
                    ny += dy[i];
                }

                if (!isRange(nx, ny))
                    continue;

                arr[nx][ny] = 2;
                dq.add(new int[] { nx, ny });
            }
        }
    }

    private static void count() {
        for (int i = 0; i < M; i++) {
            for (int j = 1; j <= N; j++) {
                if (arr[i][j] == 1) {
                    CNT++;
                    break;
                }
            }
        }

        System.out.println(CNT);
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < M && 1 <= y && y <= N;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        CNT = 0;
        know = new boolean[N + 1];
        arr = new int[M][N + 1];

        st = new StringTokenizer(br.readLine());
        st.nextToken();
        while (st.hasMoreTokens()) {
            int idx = Integer.parseInt(st.nextToken());
            know[idx] = true;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            while (st.hasMoreTokens()) {
                int idx = Integer.parseInt(st.nextToken());
                if (know[idx]) {
                    arr[i][idx] = 2;
                } else {
                    arr[i][idx] = 1;
                }
            }
        }
    }
}
