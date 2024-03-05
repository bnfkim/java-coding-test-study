import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1520_내리막_길 {

    static int M, N;
    static int[][] board;
    static int[][] dp;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1);
            dp[i][0] = 0;
        }

        System.out.println(dfs(0,0));
    }

    private static int dfs(int x, int y) {
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        if (x== M-1 && y== N-1) {
            return 1;
        }

        dp[x][y] = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx<0 || nx== M || ny<0 || ny== N) {
                continue;
            }

            if (board[nx][ny] < board[x][y]) {
                dp[x][y] += dfs(nx, ny);
            }
        }
        return dp[x][y];
    }
}
