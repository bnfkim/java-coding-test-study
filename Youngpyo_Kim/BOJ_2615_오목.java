import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2615_오목 {
    final static private int MAX = 19;
    static int board[][] = new int[MAX][MAX];
    static int[][] delta = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{1,-1},{-1,1}};

    static boolean isValidDirection(int r, int c, int d){
        int nr = r+delta[d][0], nc = c+delta[d][1];
        boolean flag1 = true;
        if(nr < 0 || nr >= MAX || nc < 0 || nc >= MAX) flag1 = false;
        if(!flag1 || board[nr][nc] != board[r][c]) flag1 = false;

        nr = r+delta[d+1][0];
        nc = c+delta[d+1][1];
        boolean flag2 = true;
        if(nr < 0 || nr >= MAX || nc < 0 || nc >= MAX) flag2 = false;
        if(!flag2 || board[nr][nc] != board[r][c]) flag2 = false;

        return flag1 || flag2;
    }
    
    static boolean winCheck(int r, int c, int d){
        int cnt = 1;
        int nr = r, nc = c;
        while(true){
            nr += delta[d][0];
            nc += delta[d][1];
            
            if(nr < 0 || nr >= MAX || nc < 0 || nc >= MAX) break;
            if(board[nr][nc] != board[r][c]) break;
            
            cnt++;
        }

        nr = r;
        nc = c;
        while(true){
            nr += delta[d+1][0];
            nc += delta[d+1][1];

            if(nr < 0 || nr >= MAX || nc < 0 || nc >= MAX) break;
            if(board[nr][nc] != board[r][c]) break;
            
            cnt++;
        }

        return cnt == 5;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < MAX; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int j = 0;

            while (st.hasMoreTokens())
                board[i][j++] = Integer.parseInt(st.nextToken());
        }

        int winner = -1, r = 20, c = 20;
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                if(board[i][j] == 1 || board[i][j] == 2){
                    for(int d = 0; d < 8; d+=2){
                        if(isValidDirection(i, j, d) && winCheck(i, j, d)){
                            if(j < c){
                                winner = board[i][j];
                                r = i;
                                c = j;
                            }
                            else if(j == c) {
                            	if(i < r) {
                                    winner = board[i][j];
                                    r = i;
                                    c = j;
                            	}
                            }
                        }
                    }
                }
            }
        }

        if(winner == -1){
            System.out.println(0);
        }
        else{
            System.out.println(winner);
            System.out.println((++r)+" "+(++c));
        }
    }
}