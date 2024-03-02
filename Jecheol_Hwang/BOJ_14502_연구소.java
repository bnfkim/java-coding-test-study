package 알고리즘연습.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 빈 칸의 개수는 최소 3개 이상
 * 바이러스는 상하좌우로 인접 빈칸으로 퍼짐
 * 새로 세울 수 있는 벽의 개수가 3개, 반드시 3개를 세워야 함
 * N과 M의 최대값이 both 8
 *
 * @algorithm bruteforce (combination + bfs)
 * @time O((NM)^3 * NM) -> 516 ms
 * @memory O(NM) -> 116448 KB
 * 
 * N과 M이 최대 8이므로 NM의 4제곱이여도 가능하다 판단
 */
public class BOJ_14502_연구소 {
    private static int N, M;
    private static int[][] arr;
    private static boolean[][] visited;
    private static Queue<int[]> q;
    private static List<int[]> starts = new ArrayList<>();
    private static List<int[]> availables = new ArrayList<>();
    private static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) {
                    starts.add(new int[]{i, j});
                } else if (arr[i][j] == 0) {
                    availables.add(new int[]{i, j});
                }
            }
        }

        comb(0, 0);

        System.out.println(ans);
    }

    private static void comb(int cnt, int start) {
        if (cnt == 3) {
            // business - bfs
            bfs();
            return;
        }
        for (int i = start; i < availables.size(); i++) {
            int[] a = availables.get(i);
            int y = a[0];
            int x = a[1];
            if (arr[y][x] == 0) {
                arr[y][x] = 1;
                comb(cnt + 1, i + 1);
                arr[y][x] = 0;
            }
        }
    }

    private static void bfs() {
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        visited = new boolean[N][M];
        q = new ArrayDeque<>();
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp[i] = arr[i].clone(); // 배열 복사
        }
        for (int[] start : starts) {
            q.add(start);
        }

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int y = now[0];
            int x = now[1];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (canGo(ny, nx, temp)) {
                    temp[ny][nx] = 2;
                    visited[ny][nx] = true;
                    q.add(new int[]{ny, nx});
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (temp[i][j] == 0) {
                    cnt++;
                }
            }
        }
        ans = Math.max(ans, cnt);

    }

    private static boolean inRange(int y, int x) {
        return y >= 0 && y < N & x >= 0 && x < M;
    }

    private static boolean canGo(int y, int x, int[][] temp) {
        return inRange(y, x) && temp[y][x] != 1 && !visited[y][x];
    }
}
