import java.util.*;
import java.io.*;

/**
 * BOJ_17144_미세먼지안녕!
 */
public class BOJ_17144_미세먼지안녕 {
    static int R, C, T, AMT;
    static int[][] arr;
    static final int[] dx = { 1, 0, -1, 0 };
    static final int[] dy = { 0, -1, 0, 1 };
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        AMT = 0;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

    }

    private static void solve() {
        for (int i = 0; i < T; i++) {
            spread();
            run();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                AMT += arr[i][j] == -1 ? 0 : arr[i][j];
            }
        }

        System.out.println(AMT);
    }

    private static void spread() {
        int[][] tmp = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == -1) {
                    tmp[i][j] = -1;
                    continue;
                }

                if (arr[i][j] > 0) {
                    int value = arr[i][j] / 5;
                    int cnt = 0;
                    for (int idx = 0; idx < 4; idx++) {
                        int nx = i + dx[idx];
                        int ny = j + dy[idx];
                        if (!isRange(nx, ny))
                            continue;
                        if (arr[nx][ny] == -1)
                            continue;
                        tmp[nx][ny] += value;
                        cnt++;
                    }
                    tmp[i][j] += arr[i][j] - cnt * value;
                }
            }
        }

        arr = tmp;
    }

    private static void run() {

        for (int i = 0; i < R; i++) {
            if (arr[i][0] == -1) {
                circulate(i, true);
                circulate(i + 1, false);
                break;
            }
        }
    }

    private static void circulate(int row, boolean cc) {
        int stt = cc ? row - 1 : row + 1;
        int end = cc ? 0 : R - 1;
        int sign = cc ? -1 : 1;

        for (int i = stt; i != end; i += sign) {
            arr[i][0] = arr[i + sign][0];
        }

        int r = cc ? 0 : R - 1;
        for (int i = 1; i < C; i++) {
            arr[r][i - 1] = arr[r][i];
        }

        for (int i = r; i != row; i -= sign) {
            arr[i][C - 1] = arr[i - sign][C - 1];
        }

        for (int i = C - 1; i >= 2; i--) {
            arr[row][i] = arr[row][i - 1];
        }

        arr[row][1] = 0;
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}