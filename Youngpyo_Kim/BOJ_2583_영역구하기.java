import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    static int r,c,k;
    static boolean board[][];
    static int dr[] = {0,1,0,-1};
    static int dc[] = {1,0,-1,0};

    public static class Point{
        int r,c;
        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new boolean[r][c];

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()),b=Integer.parseInt(st.nextToken()),c=Integer.parseInt(st.nextToken()),d=Integer.parseInt(st.nextToken());
            for(int q = b; q < d; q++){
                for(int p = a; p < c; p++){
                    board[q][p] = true;
                }
            }
        }

        int cnt = 0;
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(board[i][j] == false){
                    cnt++;
                    int area = 1;
                    Queue<Point> q = new ArrayDeque<>();
                    q.offer(new Point(i, j));
                    board[i][j] = true;

                    while(!q.isEmpty()){
                        Point cur = q.poll();
                        
                        for(int d = 0; d < 4; d++){
                            int nr = cur.r + dr[d], nc = cur.c + dc[d];
                            if(nr < 0 || nr >= r || nc < 0 || nc >= c || board[nr][nc] == true) continue;
                            area++;
                            board[nr][nc] = true;
                            q.offer(new Point(nr, nc));
                        }
                    }
                    ans.add(area);
                }
            }
        }
        Collections.sort(ans);
        System.out.println(cnt);
        for(int i = 0; i < ans.size(); i++)
            System.out.print(ans.get(i)+" ");
    }
}
