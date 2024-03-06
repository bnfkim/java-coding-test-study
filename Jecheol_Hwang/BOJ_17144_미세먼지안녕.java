package 알고리즘연습.boj;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_17144_미세먼지안녕 {
    /**
     * 실행시간 : 468 ms
     *
     * 메모리 : 38088 KB
     *
     * 시간복잡도 : O(T * R * C) ; 최대 250_000
     */
    static int R, C, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[][] grid = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        String ans = solution(r, c, t, grid);

        bw.write(ans);

        br.close();
        bw.flush();
        bw.close();
    }

    private static String solution(int r, int c, int t, int[][] grid) {
        R = r;
        C = c;
        T = t;

        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};


        // 공기청정기 위치 저장
        int upAcRow = -1;
        int downAcRow = -1;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == -1) {
                    if (upAcRow == -1) {
                        upAcRow = i;
                    } else {
                        downAcRow = i;
                        break;
                    }
                }
            }
        }

        int[][] temp;
        for (int time = 0; time < T; time++) {
            temp = new int[R][C];

            // TODO 미세먼지 확산 : 연산이 동시에 일어나는 것을 구현해야 하므로 int[][] temp 활용
            for (int y = 0; y < R; y++) {
                for (int x = 0; x < C; x++) {
                    if (grid[y][x] > 0) { // 미세먼지가 있다면
                        int cnt = 0;
                        for (int i = 0; i < 4; i++) {
                            int ny = y + dy[i];
                            int nx = x + dx[i];
                            if (canSpread(ny, nx, grid)) { // 범위 밖이 아님 && 공기청정기 아님
                                temp[ny][nx] += Math.floorDiv(grid[y][x], 5);
                                cnt++;
                            }
                        }
                        temp[y][x] += grid[y][x] - Math.floorDiv(grid[y][x], 5) * cnt;
                    }
                }
            }

            // TODO 공기청정기 작동
            // 1. UP 공기청정기 배열회전

            // 왼쪽면
            for (int i = upAcRow - 1; i >= 1; i--) {
                temp[i][0] = temp[i - 1][0];
            }
            // 위쪽면
            for (int i = 0; i < C - 1; i++) {
                temp[0][i] = temp[0][i + 1];
            }

            // 오른쪽면
            for (int i = 0; i < upAcRow; i++) {
                temp[i][C - 1] = temp[i + 1][C - 1];
            }

            // 아랫면 (공기청정기 전 2번째 칸까지 이동)
            for (int i = C - 1; i >= 2; i--) {
                temp[upAcRow][i] = temp[upAcRow][i - 1];
            }

            temp[upAcRow][0] = 0;
            temp[upAcRow][1] = 0; // 공기청정기에서 나오는 바람은 깨끗한 바람

            // 2. DOWN 공기청정기 배열회전

            // 왼쪽면
            for (int i = downAcRow + 1; i < R - 1; i++) {
                temp[i][0] = temp[i + 1][0];
            }

            // 아랫면
            for (int i = 0; i < C - 1; i++) {
                temp[R - 1][i] = temp[R - 1][i + 1];
            }

            // 오른쪽면
            for (int i = R - 1; i > downAcRow; i--) {
                temp[i][C - 1] = temp[i - 1][C - 1];
            }

            // 윗면
            for (int i = C - 1; i > 1; i--) {
                temp[downAcRow][i] = temp[downAcRow][i - 1];
            }

            temp[downAcRow][0] = 0;
            temp[downAcRow][1] = 0;

            grid = temp;
        }

        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sum += Math.max(grid[i][j], 0);
            }
        }

        return String.valueOf(sum);
    }

    private static void testPrint(int[][] temp) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(temp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("===============");
    }

    private static boolean canSpread(int y, int x, int[][] grid) {
        return inRange(y, x) && !isAirCleaner(y, x, grid);
    }

    private static boolean inRange(int y, int x) {
        return y >= 0 && y < R && x >= 0 && x < C;
    }

    private static boolean isAirCleaner(int y, int x, int[][] grid) {
        return grid[y][x] == -1;
    }
}
