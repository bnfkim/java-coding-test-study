import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1987_알파벳 {
    static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static char[][] board;
    static boolean[] checker;
    static int R, C, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        checker = new boolean[26];
        ans = 0;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String row = st.nextToken();
            for (int j = 0; j < C; j++) {
                board[i][j] = row.charAt(j);
            }
        }

        checker[board[0][0] - 'A'] = true;
        bt(0, 0, 1);

        System.out.println(ans);
    }

    static void bt(int r, int c, int cnt) {
        ans = Math.max(cnt, ans);

        for (int i = 0; i < 4; i++) {
            int nr = r + delta[i][0];
            int nc = c + delta[i][1];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C || checker[board[nr][nc] - 'A']) continue;

            checker[board[nr][nc] - 'A'] = true;
            bt(nr, nc, cnt + 1);
            checker[board[nr][nc] - 'A'] = false;
        }
    }
}
