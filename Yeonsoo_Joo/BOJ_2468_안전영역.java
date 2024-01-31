package Yeonsoo_Joo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2468_안전영역 {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (maxHeight < map[i][j]) {
                    maxHeight = map[i][j];
                }
            }
        }

        int answer = 0;
        for (int h = 0; h < maxHeight; h++) {
            // 높이에 따라 dfs 실행
            int safeZone = 0;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] > h && !visited[i][j]) {
                        dfs(i, j, h);
                        safeZone++;
                    }
                }
            }
            answer = Math.max(answer, safeZone);
        }
        System.out.println(answer);
    }

    private static void dfs(int x, int y, int h) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                if (map[nx][ny] > h && !visited[nx][ny]) {
                    dfs(nx, ny, h);
                }
            }
        }
    }
}

