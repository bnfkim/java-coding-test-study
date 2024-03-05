package 알고리즘연습.boj;

import java.io.*;
import java.util.*;

/**
 * @intuition 그냥 중력 시뮬 문제 + 중복 체크
 * @algorithm 시뮬레이션
 * @time O(N^2) -> 124 ms
 * @memory O(N^2) -> 14252 KB
 */
public class BOJ_23796_2147483648게임 {
    private static long[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        map = new long[8][8];

        for (int i = 0; i < 8; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 8; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        String cmd = br.readLine();
        go(cmd);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                sb.append(map[i][j] % (long) 1e10 + " ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    private static void go(String cmd) {
        long[] temp;
        int tdx;
        if (cmd.equals("R")) { // RIGHT
            for (int i = 0; i < 8; i++) {
                temp = new long[8];
                tdx = 0;
                for (int j = 7; j >= 0; j--) {
                    if (map[i][j] > 0) {
                        // collapse 되는 숫자가 있는 경우
                        if (tdx > 0 && temp[tdx - 1] == map[i][j]) {
                            tdx--;
                            temp[tdx++] += map[i][j] + (long) 1e10;
                            continue;
                        }
                        temp[tdx++] = map[i][j];
                    }
                }
                // 커밋
                for (int j = 0; j < 8; j++) {
                    map[i][7-j] = temp[j] % (long) 1e10;
                }
            }
        } else if (cmd.equals("L")) { // LEFT
            for (int i = 0; i < 8; i++) {
                temp = new long[8];
                tdx = 0;
                for (int j = 0; j < 8; j++) {
                    if (map[i][j] > 0) {
                        if (tdx > 0 && temp[tdx - 1] == map[i][j]) {
                            tdx--;
                            temp[tdx++] += map[i][j] + (long) 1e10;
                            continue;
                        }
                        temp[tdx++] = map[i][j];
                    }
                }
                for (int j = 0; j < 8; j++) {
                    map[i][j] = temp[j] % (long) 1e10;
                }
            }
        } else if (cmd.equals("U")) { // UP
            for (int j = 0; j < 8; j++) {
                temp = new long[8];
                tdx = 0;
                for (int i = 0; i < 8; i++) {
                    if (map[i][j] > 0) {
                        if (tdx > 0 && temp[tdx - 1] == map[i][j]) {
                            tdx--;
                            temp[tdx++] += map[i][j] + (long) 1e10;
                            continue;
                        }
                        temp[tdx++] = map[i][j];
                    }
                }
                for (int i = 0; i < 8; i++) {
                    map[i][j] = temp[i] % (long) 1e10;
                }
            }
        } else if (cmd.equals("D")) {
            for (int j = 0; j < 8; j++) {
                temp = new long[8];
                tdx = 0;
                for (int i = 7; i >= 0; i--) {
                    if (map[i][j] > 0) {
                        if (tdx > 0 && temp[tdx - 1] == map[i][j]) {
                            tdx--;
                            temp[tdx++] += map[i][j] + (long) 1e10;
                            continue;
                        }
                        temp[tdx++] = map[i][j];
                    }
                }
                for (int i = 7; i >= 0; i--) {
                    map[i][j] = temp[7-i] % (long) 1e10;
                }
            }
        }

    }
}
