import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
    }

    private static void solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                DFS(i, j, -1, -1, 0, map[i][j]);
                BFS(i, j);
            }
        }

        System.out.println(answer);
    }

    private static void DFS(int x, int y, int prevX, int prevY, int dist, int sum) {
        if (dist == 3) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }
            if (nx == prevX && ny == prevY) {
                continue;
            }
            DFS(nx, ny, x, y, dist + 1, sum + map[nx][ny]);
        }
    }

    private static void BFS(int x, int y) {
        int sum = map[x][y];
        int min = Integer.MAX_VALUE;
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }
            sum += map[nx][ny];
            min = Math.min(min, map[nx][ny]);
            count++;
        }
        if (count == 4) {
            sum -= min;
        }
        if (count < 3) {
            return;
        }
        answer = Math.max(answer, sum);
    }
}
