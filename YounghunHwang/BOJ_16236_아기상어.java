import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class Main {

    static int N, time;
    static int[][] map;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static Shark shark;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        boolean hasFish = false;
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark = new Shark(i, j, 2);
                    map[i][j] = 0;
                }
                if (map[i][j] > 0) {
                    hasFish = true;
                }
            }
        }

        if (!hasFish) {
            System.out.println(0);
            return;
        }

        solve();
    }

    private static void solve() {
        while (true) {
            if (!BFS()) {
                break;
            }
        }

        System.out.println(time);
    }

    private static boolean BFS() {
        int minDist = Integer.MAX_VALUE;

        PriorityQueue<int[]> fishes = new PriorityQueue<>((((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        })));

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        queue.add(new int[]{shark.x, shark.y, 0});
        visited[shark.x][shark.y] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int dist = curr[2];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nDist = dist + 1;
                if (isOutOfRange(nx, ny) || visited[nx][ny]) {
                    continue;
                }
                if (isSmallFish(nx, ny)) {
                    if (!fishes.isEmpty() && minDist < nDist) {
                        queue.clear();
                        break;
                    }
                    fishes.add(new int[]{nx, ny});
                    minDist = nDist;
                }
                queue.add(new int[]{nx, ny, dist + 1});
                visited[nx][ny] = true;
            }
        }

        if (fishes.isEmpty()) {
            return false;
        }

        int[] fish = fishes.poll();
        eat(fish[0], fish[1], minDist);
        return true;
    }

    private static boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N || shark.size < map[x][y];
    }

    private static boolean isSmallFish(int x, int y) {
        return map[x][y] > 0 && shark.size > map[x][y];
    }

    private static void eat(int x, int y, int dist) {
        time += dist;
        map[x][y] = 0;
        shark.x = x;
        shark.y = y;
        shark.count++;
        if (shark.count == shark.size) {
            shark.count = 0;
            shark.size++;
        }
    }

    static class Shark {
        int x;
        int y;
        int size;
        int count;

        public Shark(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }
}
