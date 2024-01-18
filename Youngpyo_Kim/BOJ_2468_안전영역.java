import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * BOJ_2468_안전영역
 * 44min
 */

class Points {
    private int r;
    private int c;

    Points(int r, int c){
        this.r = r;
        this.c = c;
    }

    public int getC() {
        return c;
    }

    public int getR() {
        return r;
    }
}

public class BOJ_2468_안전영역 {
    private static int[][] delta = {{1,0},{-1,0},{0,1},{0,-1}};

    public static int bfs(int[][] board, int rain, int n){
        int res = 0;
        boolean[][] visited = new boolean[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] <= rain){
                    visited[i][j] = true;
                }
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(visited[i][j] == false){
                    Queue<Points> q = new LinkedList<Points>();
                    q.add(new Points(i,j));
                    visited[i][j] = true;

                    while(q.size() != 0){
                        Points cur = q.poll();

                        for(int d = 0; d < 4; d++){
                            int nr = cur.getR()+delta[d][0];
                            int nc = cur.getC()+delta[d][1];
                            
                            if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                            if(visited[nr][nc] == true) continue;

                            visited[nr][nc] = true;
                            q.add(new Points(nr,nc));
                        }
                    }
                    res++;
                }

            }
        }

        return res;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine()), minRain = 0x3f3f3f, maxRain = -1, ans = 1;
        int[][] board = new int[n][n];

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int j = 0;
            while(st.hasMoreTokens()){
                board[i][j] = Integer.parseInt(st.nextToken());
                minRain = Math.min(board[i][j], minRain);
                maxRain = Math.max(board[i][j], maxRain);
                j++;
            }
        }

        for(int i = minRain; i <= maxRain; i++){
            ans = Math.max(ans, bfs(board, i, n));
        }

        System.out.println(ans);
    }
}
