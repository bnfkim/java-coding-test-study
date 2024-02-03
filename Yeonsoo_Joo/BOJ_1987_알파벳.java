package Yeonsoo_Joo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1987_알파벳 {

    static int R, C;
    static char[][] board;
    static int max;
    static int[] dx = {0, 1, -1, 0};
    static int[] dy = {1, 0, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        max = Integer.MIN_VALUE;
        // 미리 체크
        dfs(0, 0, 1 << board[0][0] - 'A');
        System.out.println(max);
    }

    private static void dfs(int i, int j, int flag) {
        max = Math.max(max, Integer.bitCount(flag));

        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];
            if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;

            // 이미 방문했다면
            if ((flag & 1 << board[nx][ny] - 'A') != 0) {
                continue;
            }
            dfs(nx, ny, flag | 1 << board[nx][ny] - 'A');
        }
    }
}
