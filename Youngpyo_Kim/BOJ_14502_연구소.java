import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {

    public static class Point{
        int r,c;
        Point(int a, int b){
            r = a;
            c = b;
        }
    }
    static int n, m, board[][], ans = 0;
    static int dr[] = {0,1,0,-1};
    static int dc[] = {1,0,-1,0};

    static ArrayList<Point> emptys = new ArrayList<>(), virus = new ArrayList<>();

    public static void solve(Point a, Point b, Point c){
        int [][] tmpboard = new int[n][m];
        for(int i = 0; i < n; i++){
            tmpboard[i] = board[i].clone();
        }
        tmpboard[a.r][a.c] = 1;
        tmpboard[b.r][b.c] = 1;
        tmpboard[c.r][c.c] = 1;
        
        Queue<Point> q = new ArrayDeque<>();
        
        for(int i = 0; i < virus.size(); i++){
            q.offer(virus.get(i));
        }

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i = 0; i < 4; i++){
                int nr = cur.r+dr[i], nc = cur.c+dc[i];

                if(nr >= n || nr < 0 || nc >= m || nc < 0 || tmpboard[nr][nc] == 1 || tmpboard[nr][nc] == 2) continue;
                
                q.offer(new Point(nr, nc));
                tmpboard[nr][nc] = 2;
            }
        }

        int res =0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(tmpboard[i][j] == 0)
                    res++;
            }
        }
        ans = Math.max(res, ans);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0)
                    emptys.add(new Point(i,j));
                else if(board[i][j] == 2)
                    virus.add(new Point(i, j));
            }
        }

        for(int i = 0; i < emptys.size(); i++){
            for (int j = i+1; j < emptys.size(); j++) {
                for(int k = j+1; k < emptys.size(); k++){
                    solve(emptys.get(i),emptys.get(j),emptys.get(k));
                }
            }
        }

        System.out.println((ans));
    }
}

