package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2583_영역구하기 {
    private static int m,n;
    private static int[][] grid;
    private static boolean[][] visited;
    private static int area;
    private static int partition;
    private static Queue<int[]> q = new ArrayDeque<>();
    private static Queue<Integer> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        /*
        * INPUT :
        * m : height
        * n : width
        * k : 직사각형 개수
        * (모두 100 이하의 자연수)
        *
        * loop k :
        *   직사각형의 왼쪽 아래 꼭짓점 y,x 좌표값과 오른쪽 위 꼭짓점의 y,x 좌표값이 빈칸을 사이에 두고 주어짐
        *
        * 모눈종이의 왼쪽 아래 꼭짓점의 좌표는 (0,0) - 오른쪽 위 꼭짓점의 좌표는 (N,M)
        *
        * 입력되는 k개의 직사각형들이 모눈종이 전체를 채우는 경우는 없다.
        *
        * OUTPUT :
        * 1) 분리되어 나눠지는 영역의 개수를 출력
        * 2) 각 영역의 넓이를 오름차순으로 정렬하여 빈칸을 사이에 두고 출력 (오름차순 정렬)
        * */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        grid = new int[m + 1][n + 1];
        visited = new boolean[m + 1][n + 1];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = y1 + 1; j <= y2; j++) {
                for (int l = x1 + 1; l <= x2; l++) {
                    grid[j][l] = -1;
                }
            }
        }
        for (int i = 0; i <= m; i++) {
            grid[i][0] = -1;
            visited[i][0] = true;
        }
        for (int i = 0; i <= n; i++) {
            grid[0][i] = -1;
            visited[0][i] = true;
        }
//        for (int i = 0; i <= m; i++) {
//            for (int j = 0; j <= n; j++) {
//                System.out.print(grid[i][j] + " ");
//            }
//            System.out.println();
//        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (grid[i][j] == 0) {
                    q.add(new int[]{i, j});
                }
            }
        }

        bfs();
        System.out.println(partition);
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
    }
    private static void bfs() {
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int y = now[0];
            int x = now[1];
            if (canGo(y, x)) {
//                System.out.println(y + " " + x);
                area = 0;
                partition++;
                dfs(y, x);
                pq.add(area);
            }
        }
    }

    private static void dfs(int y, int x) {
        int[] dy = {0, 0, -1, 1};
        int[] dx = {1, -1, 0, 0};
        area++;
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (canGo(ny, nx)) {
                dfs(ny, nx);
            }
        }
    }

    private static boolean canGo(int y, int x) {
        return inRange(y, x) && grid[y][x] == 0 && !visited[y][x];
    }

    private static boolean inRange(int y, int x) {
        return y >= 1 && y <= m && x >= 1 && x <= n;
    }
}
