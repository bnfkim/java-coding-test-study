import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2615_오목 {

    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[20][20];
        for (int i = 1; i < 20; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < 20; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
    }

    private static void solve() {
        for (int i = 1; i < 20; i++) {
            for (int j = 1; j < 20; j++) {
                if (map[i][j] == 1 || map[i][j] == 2) {
                    if (isWinner(i, j)) {
                        System.out.println(map[i][j]);
                        System.out.println(i + " " + j);
                        return;
                    }
                }
            }
        }

        System.out.println(0);
    }

    private static boolean isWinner(int x, int y) {
        int[] dx = {0, 1, 1, -1};
        int[] dy = {1, 0, 1, 1};

        for (int i = 0; i < 4; i++) {
            int px = x - dx[i];
            int py = y - dy[i];
            if (!isOutOfRange(px, py) && map[px][py] == map[x][y]) {
                continue;
            }
            int nx = x + dx[i];
            int ny = y + dy[i];
            int count = 1;
            while (count < 6) {
                if (isOutOfRange(nx, ny) || map[nx][ny] != map[x][y]) {
                    break;
                }
                nx += dx[i];
                ny += dy[i];
                count++;
            }
            if (count == 5) {
                return true;
            }
        }

        return false;
    }

    private static boolean isOutOfRange(int x, int y) {
        return x < 1 || x > 19 || y < 1 || y > 19;
    }
}