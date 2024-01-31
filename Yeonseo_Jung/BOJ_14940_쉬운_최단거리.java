import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14940_쉬운_최단거리 {

    static int n, m, targetX, targetY;
    static int[][] map, sol;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        sol = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    targetX = i;
                    targetY = j;
                }
                if (map[i][j] == 0) {
                    sol[i][j] = 0;
                    continue;
                }
                sol[i][j] = -1;
            }
        }

        bfs(targetX, targetY);

        printSol();
    }

    private static void printSol() {
        for (int i = 0; i < sol.length; i++) {
            for (int j = 0; j < sol[i].length; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void bfs(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;
        sol[startX][startY] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!isInvalid(nx, ny) && map[nx][ny] == 1 && !visited[nx][ny]) {

                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    sol[nx][ny] = sol[x][y] + 1;

                }
            }
        }
    }

    private static boolean isInvalid(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
}
