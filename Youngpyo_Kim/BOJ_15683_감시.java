import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BOJ_15683_감시 {
    static class CCTV {
        int type, r, c;

        CCTV(int type, int r, int c) {
            this.type = type;
            this.r = r;
            this.c = c;
        }
    }

    static int N, M, ans = 0x3f3f3f3f, cctv_size;
    static int[][] board;
    static CCTV[] cctv;
    static int[] rotation = {0, 4, 2, 4, 4, 1};

    static void update(int dir, CCTV cctv) {
        dir %= 4;
        
        if (dir == 0) {
            for (int c = cctv.c + 1; c < M; c++) {
                if (board[cctv.r][c] == 6) break;
                board[cctv.r][c] = -1;
            }
        } else if (dir == 1) {
            for (int r = cctv.r - 1; r >= 0; r--) {
                if (board[r][cctv.c] == 6) break;
                board[r][cctv.c] = -1;
            }
        } else if (dir == 2) {
            for (int c = cctv.c - 1; c >= 0; c--) {
                if (board[cctv.r][c] == 6) break;
                board[cctv.r][c] = -1;
            }
        } else if (dir == 3) {
            for (int r = cctv.r + 1; r < N; r++) {
                if (board[r][cctv.c] == 6) break;
                board[r][cctv.c] = -1;
            }
        }
    }

    static void bt(int cctv_idx) {
        if (cctv_idx == cctv_size) {
            int cnt = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (board[r][c] == 0)
                        cnt++;
                }
            }

            if (ans > cnt)
                ans = cnt;
            return;
        }

        int[][] backup = new int[8][8];
        int type = cctv[cctv_idx].type;

        for (int dir = 0; dir < rotation[type]; dir++) {
            for (int i = 0; i < N; i++) {
                backup[i] = board[i].clone();
            }

            if (type == 1) {
                update(dir, cctv[cctv_idx]);
            } else if (type == 2) {
                update(dir, cctv[cctv_idx]);
                update(dir + 2, cctv[cctv_idx]);
            } else if (type == 3) {
                update(dir, cctv[cctv_idx]);
                update(dir + 1, cctv[cctv_idx]);
            } else if (type == 4) {
                update(dir, cctv[cctv_idx]);
                update(dir + 1, cctv[cctv_idx]);
                update(dir + 2, cctv[cctv_idx]);
            } else if (type == 5) {
                update(dir, cctv[cctv_idx]);
                update(dir + 1, cctv[cctv_idx]);
                update(dir + 2, cctv[cctv_idx]);
                update(dir + 3, cctv[cctv_idx]);
            }

            bt(cctv_idx + 1);

            for (int i = 0; i < N; i++) {
                board[i] = backup[i].clone();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[8][8];
        cctv = new CCTV[8];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
                if (board[r][c] != 0 && board[r][c] != 6) {
                    cctv[cctv_size++] = new CCTV(board[r][c], r, c);
                }
            }
        }
        bt(0);
        System.out.println(ans);
    }
}
