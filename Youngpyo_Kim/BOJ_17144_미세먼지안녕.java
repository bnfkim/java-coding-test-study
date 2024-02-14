import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_17144_미세먼지안녕 {
    static int R, C, T, ans = 0;
    static int[][] board;
    static ArrayList<int[]> cleaner = new ArrayList<>();
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void spread() {
        int[][] newBoard = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] > 0) {
                    int amount = (int) Math.floor(board[i][j] / 5);

                    for (int d = 0; d < 4; d++) {
                        int nr = dr[d] + i, nc = dc[d] + j;
                        if (nr >= R || nr < 0 || nc >= C || nc < 0 || board[nr][nc] == -1)
                            continue;
                        newBoard[nr][nc] += amount;
                        newBoard[i][j] -= amount;
                    }
                }
            }
        }
        
        for (int i = 0; i < R; i++)
        {
            for (int j = 0; j < C; j++)
            {
                board[i][j] += newBoard[i][j];
            }
        }
    }

    public static void clean() {
        int[] up = cleaner.get(0);
        int[] down = cleaner.get(1);
        int upr = up[0], upc = up[1], dor = down[0], doc = down[1];

        board[upr - 1][upc] = 0;
        board[dor + 1][doc] = 0;

        for (int i = upr - 1; i > 0; i--)
            board[i][upc] = board[i - 1][upc];
        for (int i = 0; i < C - 1; i++)
            board[0][i] = board[0][i + 1];
        for (int i = 0; i < upr; i++)
            board[i][C - 1] = board[i + 1][C - 1];
        for (int i = C - 1; i > 1; i--)
            board[upr][i] = board[upr][i - 1];

        for (int i = dor + 1; i < R - 1; i++)
            board[i][doc] = board[i + 1][doc];
        for (int i = 0; i < C - 1; i++)
            board[R - 1][i] = board[R - 1][i + 1];
        for (int i = R - 1; i > dor; i--)
            board[i][C - 1] = board[i - 1][C - 1];
        for (int i = C - 1; i > 1; i--)
            board[dor][i] = board[dor][i - 1];

        board[upr][upc + 1] = 0;
        board[dor][doc + 1] = 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == -1)
                    cleaner.add(new int[]{i, j});
            }
        }

        while (T-- > 0) {
            spread();
            clean();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] != -1)
                    ans += board[i][j];
            }
        }

        System.out.println(ans);
    }
}
