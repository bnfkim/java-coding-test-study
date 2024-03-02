import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, answer;
    static int[][] map;
    static List<int[]> blanks;
    static int[][] pickedBlanks = new int[3][2];
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        blanks = new ArrayList<>(N * M);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    blanks.add(new int[]{i, j});
                }
            }
        }

        solve();
    }

    private static void solve() {
        combination(0, 0);
        System.out.println(answer);
    }

    private static void combination(int depth, int start) {
        if (depth == 3) {
            answer = Math.max(answer, calculateSafeAreaSize());
            return;
        }

        for (int i = start; i < blanks.size(); i++) {
            pickedBlanks[depth] = blanks.get(i);
            combination(depth + 1, i + 1);
        }
    }

    private static int calculateSafeAreaSize() {
        // 1. 벽 세우기
        for (int i = 0; i < 3; i++) {
            int[] pos = pickedBlanks[i];
            map[pos[0]][pos[1]] = 1;
        }

        // 2. 바이러스 확산
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2 && !visited[i][j]) {
                    BFS(i, j);
                }
            }
        }

        // 3. 안전 영역 크기 계산
        int size = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    size++;
                }
            }
        }

        // 4. 벽 허물기
        for (int i = 0; i < 3; i++) {
            int[] pos = pickedBlanks[i];
            map[pos[0]][pos[1]] = 0;
        }

        return size;
    }

    private static void BFS(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
                    continue;
                }
                if (map[nx][ny] == 0) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }
}