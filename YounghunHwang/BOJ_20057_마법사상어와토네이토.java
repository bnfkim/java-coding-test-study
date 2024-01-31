import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20057_마법사상어와토네이토 {
    static int N;
    static int[][] map;
    static int outSand;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
    }

    private static void solve() {
        moveTornado();
        System.out.println(outSand);
    }

    private static void moveTornado() {
        int x = (N + 1) / 2;
        int y = x;
        int dist = 0;
        for (int i = 0; i < N / 2; i++) {
            // [좌, 하, 우, 상] 순서로 방향 전환
            for (int j = 0; j < 4; j++) {
                if (j == 0 || j == 2) {
                    dist++;
                }
                // dist만큼 움직이며 모래 이동
                for (int k = 0; k < dist; k++) {
                    x += dx[j];
                    y += dy[j];
                    moveSand(x, y, j);
                }
            }
        }

        // 마지막 dist만큼 좌측으로 이동 후 탈출
        for (int i = 0; i < dist; i++) {
            x += dx[0];
            y += dy[0];
            moveSand(x, y, 0);
        }
    }

    private static void moveSand(int x, int y, int dir) {
        int[][] weights = {
                {0, 0, 2, 0, 0},
                {0, 10, 7, 1, 0},
                {5, 55, 0, 0, 0},
                {0, 10, 7, 1, 0},
                {0, 0, 2, 0, 0}
        };

        for (int i = 0; i < dir; i++) {
            weights = rotate(weights);
        }

        int restSand = map[x][y];
        int[] restPos = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int weight = weights[i][j];
                int sand = (map[x][y] * weight) / 100;
                int nx = x + i - 2;
                int ny = y + j - 2;
                if (weight == 55) {
                    restPos[0] = nx;
                    restPos[1] = ny;
                    continue;
                }
                if (weight == 0 || isOutOfRange(nx, ny)) {
                    outSand += sand;
                    restSand -= sand;
                    continue;
                }
                map[nx][ny] += sand;
                restSand -= sand;
            }
        }

        if (isOutOfRange(restPos[0], restPos[1])) {
            outSand += restSand;
        } else {
            map[restPos[0]][restPos[1]] += restSand;
        }

        map[x][y] = 0;
    }

    private static int[][] rotate(int[][] arr) {
        // 반시계방향 90도 회전
        int[][] result = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                result[i][j] = arr[j][4 - i];
            }
        }
        return result;
    }

    private static boolean isOutOfRange(int x, int y) {
        return x < 1 || x > N || y < 1 || y > N;
    }
}