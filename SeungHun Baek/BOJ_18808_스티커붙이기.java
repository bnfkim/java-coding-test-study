import java.io.*;
import java.util.*;

/**
 * 12768kb
 * 116ms
 */
public class BOJ_18808_스티커붙이기 {

    static int N, M, K, R, C;
    static boolean arr[][], sticker[][];
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() throws IOException {
        for (int i = 0; i < K; i++) {
            input();
            simulate();
        }
        count();
    }

    private static void simulate() {
        for (int i = 0; i < 4; i++) {
            if (find()) {
                return;
            }
            rotate();
        }
    }

    private static boolean find() {
        for (int i = 0; i <= N - R; i++) {
            for (int j = 0; j <= M - C; j++) {
                if (isValid(i, j)) {
                    attach(i, j);
                    return true;
                }
            }
        }
        return false;
    }

    private static void attach(int x, int y) {
        for (int i = x; i < x + R; i++) {
            for (int j = y; j < y + C; j++) {
                arr[i][j] |= sticker[i - x][j - y];
            }
        }
    }

    private static boolean isValid(int x, int y) {
        for (int i = x; i < x + R; i++) {
            for (int j = y; j < y + C; j++) {
                if (arr[i][j] && sticker[i - x][j - y]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        sticker = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                sticker[i][j] = st.nextToken().charAt(0) == '1';
            }
        }
    }

    private static void rotate() {
        int temp = R;
        R = C;
        C = temp;
        boolean[][] next = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                next[i][j] = sticker[C - j - 1][i];
            }
        }
        sticker = next;
    }

    private static void count() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cnt += arr[i][j] ? 1 : 0;
            }
        }
        System.out.println(cnt);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new boolean[N][M];
    }
}
