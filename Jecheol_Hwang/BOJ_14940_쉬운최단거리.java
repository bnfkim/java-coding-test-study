package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BOJ_14940_쉬운최단거리 {
    // time complexity : O(N * M)
    // 실행 시간 : 2376ms
    // 메모리 : 86424KB
    // 로직 : 가중치가 동일한 최단거리 이므로 단순 bfs 이용 (격자 이동이라 인접행렬 구성)
    private static int n, m;
    private static boolean[][] visited;
    private static int[][] arr, distance;
    private static final int Y = 0;
    private static final int X = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        distance = new int[n][m];
        visited = new boolean[n][m];

        // init
        int[] start = new int[]{-1, -1};

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                distance[i][j] = -1;
                int num = Integer.parseInt(st.nextToken());
                if (num == 2) {
                    start = new int[]{i, j};
                }
                arr[i][j] = num;
            }
        }

        // business
        bfs(start);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(distance[i][j] == (int) 1e9 ? "-1 " : distance[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    private static boolean inRange(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }

    private static boolean canGo(int y, int x) {
        return inRange(y, x) && arr[y][x] != 0 && !visited[y][x];
    }

    private static void bfs(int[] start) {
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, 1, 0, -1};

        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        int startY = start[Y];
        int startX = start[X];
        visited[startY][startX] = true;
        distance[startY][startX] = 0;
        while (!q.isEmpty()) {
            int[] current = q.poll();
            int y = current[Y];
            int x = current[X];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (canGo(ny, nx)) {
                    visited[ny][nx] = true;
                    distance[ny][nx] = distance[y][x] + 1;
                    q.add(new int[]{ny, nx});
                }
            }
        }
    }
}
