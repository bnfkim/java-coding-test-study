package 문제풀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500 {
    static int R, C, answer;
    static int[][] map;
    static boolean[][] visit;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visit = new boolean[R][C];

        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                visit[i][j] = true;
                dfs(j, i, map[i][j], 1);
                visit[i][j] = false;
            }
        }
        System.out.println(answer);
    }

    public static void dfs(int x, int y, int sum, int cnt) {
        if(cnt == 4) {
            answer = Math.max(sum, answer);
            return;
        }

        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(outOfRange(nx, ny) || visit[ny][nx]) continue;

            if(cnt == 2) { //가운데 있는 경우를 확인해주기 위해
                visit[ny][nx] = true;
                dfs(x, y, sum + map[ny][nx], cnt+1);
                visit[ny][nx] = false;
            }

            visit[ny][nx] = true;
            dfs(nx, ny, sum + map[ny][nx], cnt+1);
            visit[ny][nx] = false;
        }
    }

    public static boolean outOfRange(int x, int y) {
        return x<0 || y<0 || x>=C || y>=R;
    }
}
