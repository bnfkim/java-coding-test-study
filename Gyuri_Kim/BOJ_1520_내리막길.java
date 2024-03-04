import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static int[][] map, dp;
    static int[] dc = {0, 1, 0, -1};
    static int[] dr = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        dp = new int[R][C];

        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                dp[i][j] = -1;
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(0, 0));
    }

    public static int dfs(int c, int r) {
        //(1) 목표 위치에 도달한 경우
        if(c == C-1 && r == R-1) return 1;

        //(2) 이미 방문한 위치인 경우
        //저장된 경로의 수를 반환 => 중복 방지 ( 메모제이션 기법 )
        if(dp[r][c] != -1) return dp[r][c];

        //(3) 새로 방문한 위친 경우
        dp[r][c] = 0;

        for(int i = 0; i < 4; i++) {
            int nc = c + dc[i];
            int nr = r + dr[i];

            //이동하려는 위치의 높이가 현재 위치의 높이보다 낮으면 가능
            if(!outOfRange(nc, nr) && map[r][c] > map[nr][nc]) {
                dp[r][c] += dfs(nc, nr); //이동하려는 위치에서의 경로 수를 더함
            }
        }

        return dp[r][c];
    }

    private static boolean outOfRange(int nc, int nr) {
        return nr < 0 || nc < 0 || nr >= R || nc >= C;
    }
}