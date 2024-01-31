import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2468_안전영역 {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int minHeight = 100;
    static int maxHeight = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
    }

    private static void solve() {
        updateHeight();
        int maxArea = getMaxArea();
        System.out.println(maxArea);
    }

    private static void updateHeight() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxHeight = Math.max(maxHeight, map[i][j]);
                minHeight = Math.min(minHeight, map[i][j]);
            }
        }
    }

    private static int getMaxArea() {
        int maxArea = 1;

        for (int h = minHeight; h < maxHeight; h++) {
            visited = new boolean[N][N];
            int area = getArea(h);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    private static int getArea(int h) {
        int area = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] <= h || visited[i][j]) {
                    continue;
                }
                DFS(h, i, j);
                area++;
            }
        }

        return area;
    }

    private static void DFS(int h, int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isOutOfRange(h, nx, ny) || visited[nx][ny]) {
                continue;
            }
            DFS(h, nx, ny);
        }
    }

    private static boolean isOutOfRange(int h, int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N || map[x][y] <= h;
    }
}