import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노 {
    final static int TETROMINO = 4;
    static int N, M, score;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        getScore();
        System.out.println(score);
    }

    private static void getScore() {
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, board[i][j], 1);
                visited[i][j] = false;
            }
        }
    }

    private static void dfs(int x, int y, int curScore, int cnt) {
        if (cnt == TETROMINO) {
            score = Math.max(score, curScore);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx == N || ny < 0 || ny == M) {
                continue;
            }

            if (visited[nx][ny]) {
                continue;
            }

            if (cnt == 2) {
                visited[nx][ny] = true;
                dfs(x, y, curScore + board[nx][ny], cnt + 1);
                visited[nx][ny] = false;
            }

            visited[nx][ny] = true;
            dfs(nx, ny, curScore + board[nx][ny], cnt + 1);
            visited[nx][ny] = false;
        }
    }
}
