package Yeonsoo_Joo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2615_오목 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[19][19];
        for (int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // (서 동) (북 남) ( .. )
        int[] dx = {-1, 1, 0, 0, 1, -1, -1, 1};
        int[] dy = {0, 0, -1, 1, 1, -1, 1, -1};

        // 왼쪽부터 탐색
        for (int j = 0; j < 19; j++) {
            for (int i = 0; i < 19; i++) {
                if (board[i][j] != 0) {
                    int cnt = 1;
                    for (int k = 0; k < 8; k++) {
                        if (k % 2 == 0) {
                            cnt = 1;
                        }
                        int cx = i;
                        int cy = j;
                        while (true) {
                            cx += dx[k];
                            cy += dy[k];
                            if (cx < 0 || cx >= 19 || cy < 0 || cy >= 19) {
                                break;
                            } else if (board[i][j] == board[cx][cy]) {
                                cnt++;
                            } else {
                                break;
                            }
                        }
                        if (k % 2 == 1 && cnt == 5) {
                            System.out.println(board[i][j]);
                            System.out.println((i + 1) + " " + (j + 1));
                            return;
                        }
                    }
                }
            }
        }
        System.out.println(0);
    }
}
