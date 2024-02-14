import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int R, C, T;
    static int r1, r2;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    if (r1 != 0) {
                        r2 = i;
                    } else {
                        r1 = i;
                    }
                }
            }
        }

        solve();
    }

    private static void solve() {
        for (int i = 0; i < T; i++) {
            // 1. 미세먼지 확산
            spreadDust();
            // 2. 공기청정기 작돟
            cleanAir();
        }
        // 3. 미세먼지 양 출력
        printAnswer();
    }

    private static void spreadDust() {
        int[][] temp = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    temp[i][j] += map[i][j];
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (isOutOfRange(nx, ny) || map[nx][ny] == -1) {
                            continue;
                        }
                        temp[nx][ny] += map[i][j] / 5;
                        temp[i][j] -= map[i][j] / 5;
                    }
                }
                if (map[i][j] == -1) {
                    temp[i][j] = map[i][j];
                }
            }
        }
        map = temp;
    }

    private static boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= R || y < 0 || y >= C;
    }

    private static void cleanAir() {
        // 위
        int dir = 0;
        int x = r1 - 1;
        int y = 0;
        for (int i = 0; i < 2 * (r1 + C - 1) - 1; i++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx < 0 || nx > r1 || ny < 0 || ny >= C) {
                dir++;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }
            map[x][y] = map[nx][ny];
            x = nx;
            y = ny;
        }
        map[r1][1] = 0;

        // 아래
        dir = 2;
        x = r2 + 1;
        y = 0;
        for (int i = 0; i < 2 * (R + C - r2 - 2) - 1; i++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx < r2 || nx >= R || ny < 0 || ny >= C) {
                dir--;
                if (dir < 0) {
                    dir = 3;
                }
                nx = x + dx[dir];
                ny = y + dy[dir];
            }
            map[x][y] = map[nx][ny];
            x = nx;
            y = ny;
        }
        map[r2][1] = 0;
    }

    private static void printAnswer() {
        int answer = 2;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                answer += map[i][j];
            }
        }
        System.out.println(answer);
    }
}
