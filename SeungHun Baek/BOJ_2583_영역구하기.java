import java.util.*;
import java.io.*;

public class BOJ_2583_영역구하기 {
    
    static int N, M, K;
    static boolean[][] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        arr = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for (int j = y1; j < y2; j++) {
                for (int k = x1; k < x2; k++) {
                    arr[j][k] = true;
                }
            }
        }

        solve();
    }

    private static void solve() {
        ArrayList<Integer> lst = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j]) continue;
                lst.add(dfs(i, j));
            }
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(lst.size()).append("\n");
        
        Collections.sort(lst);
        for (int v : lst) {
            sb.append(v).append(" ");
        }

        System.out.println(sb.toString());
    }

    private static int dfs(int x, int y) {
        if (!isRange(x, y)) return 0;
        if (arr[x][y]) return 0;
        arr[x][y] = true;
        
        int cnt = 1;
        cnt += dfs(x + 1, y);
        cnt += dfs(x - 1, y);
        cnt += dfs(x, y + 1);
        cnt += dfs(x, y - 1);
        
        return cnt;
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
