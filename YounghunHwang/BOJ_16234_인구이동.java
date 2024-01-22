import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16234_인구이동 {

    static int N, L, R;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
    }

    private static void solve() {
        int count = 0;
        while (true) {
            boolean isMoved = day();
            if (!isMoved) {
                break;
            }
            count++;
        }

        System.out.println(count);
    }

    private static boolean day() {
        boolean isMoved = false;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) {
                    continue;
                }
                if (BFS(i, j)) {
                    isMoved = true;
                }
            }
        }

        return isMoved;
    }

    private static boolean BFS(int x, int y) {
        List<int[]> union = new ArrayList<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        int sum = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            union.add(curr);
            x = curr[0];
            y = curr[1];
            sum += map[x][y];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isOutOfRange(nx, ny) || visited[nx][ny]) {
                    continue;
                }
                int diff = Math.abs(map[x][y] - map[nx][ny]);
                if (diff < L || diff > R) {
                    continue;
                }
                queue.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }

        if (union.size() <= 1) {
            return false;
        }

        int population = sum / union.size();
        for (int[] pair : union) {
            map[pair[0]][pair[1]] = population;
        }

        return true;
    }

    private static boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}