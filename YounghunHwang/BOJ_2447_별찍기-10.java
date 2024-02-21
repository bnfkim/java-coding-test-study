import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2447 {

    static int N;
    static char[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        solve(0, 0, N, true);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void solve(int r, int c, int size, boolean flag) {
        if (size == 1) {
            if (flag) {
                map[r][c] = '*';
            } else {
                map[r][c] = ' ';
            }
            return;
        }

        size /= 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    solve(r + size * i, c + size * j, size, false);
                } else {
                    solve(r + size * i, c + size * j, size, flag);
                }
            }
        }
    }
}
