import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    static int n, m, board[][], dp[][];
    static int dr[] = {0,1,0,-1};
    static int dc[] = {1,0,-1,0};

    public static int dfs(int r, int c){
        if(r == n-1 && c == m-1) return 1;

        if(dp[r][c] != -1) return dp[r][c];

        dp[r][c] = 0;
        
        for(int i = 0; i < 4; i++){
            int nr = r+dr[i], nc = c+dc[i];
            if(nr >= n || nr < 0 || nc >= m || nc < 0 || board[r][c] <= board[nr][nc]) continue;

            dp[r][c] += dfs(nr, nc);
        }

        return dp[r][c];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        dp = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], -1);
            for(int j = 0; j < m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        System.out.println(dfs(0,0));
    }
}

