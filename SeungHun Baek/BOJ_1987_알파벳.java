import java.util.*;
import java.io.*;

public class BOJ_1987_알파벳 {

    static int R, C, MAX;
    static char[][] arr;
    static final int[] dx = {-1,0,1,0};
    static final int[] dy = {0,1,0,-1};
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        MAX = 0;
        arr = new char[R][C];
        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        solution();
    }

    private static void solution() {
        dfs(0, 0, 1 << (arr[0][0] - 'A'), 1);
        System.out.println(MAX);
    }

    private static void dfs(int x, int y, int visited, int cnt) {
        MAX = Math.max(MAX, cnt);

        for (int idx = 0; idx < dx.length; idx++) {
            int nx = x + dx[idx];
            int ny = y + dy[idx];
            if (!isPossible(nx, ny)) continue;

            int value = 1 << (arr[nx][ny] - 'A');
            if ((visited & value) != 0) continue;
            dfs(nx, ny, visited | value, cnt+1);
        }
    }

    private static boolean isPossible(int x, int y) {
        return 0<=x && x<R && 0<=y && y<C;
    }
}
