import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16234_인구이동 {
    static int n, l, r;
    static int[][] board = new int[51][51];
    static int delta[][] = {{0,1},{0,-1},{1,0},{-1,0}};

    static class Point{
        int r, c;
        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static boolean bfs(){
        boolean flag = false;
        boolean[][] visited = new boolean[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j]){
                    Queue<Point> q = new LinkedList<>();
                    ArrayList<Point> arr = new ArrayList<>();
                    q.add(new Point(i,j));
                    arr.add(new Point(i,j));
                    int sum = board[i][j];
                    visited[i][j] = true;

                    while(!q.isEmpty()){
                        Point cur = q.poll();

                        for(int d = 0; d < 4; d++){
                            int nr = cur.r + delta[d][0];
                            int nc = cur.c + delta[d][1];

                            if(nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc]) continue;
                            int diff = Math.abs(board[nr][nc] - board[cur.r][cur.c]);
                            if(diff >= l && diff <= r){
                                q.add(new Point(nr, nc));
                                arr.add(new Point(nr, nc));
                                sum += board[nr][nc];
                                visited[nr][nc] = true;
                            }
                        }
                    }

                    int moved = sum / arr.size();

                    for(int d = 0, sz = arr.size(); d < sz; d++){
                        int nr = arr.get(d).r, nc = arr.get(d).c;
                        if(moved != board[nr][nc]) flag = true;
                        board[nr][nc] = moved;
                    }
                }
            }
        }
        return flag;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        while(true){
            if(bfs())
                ans++;
            else
                break;
        }
        System.out.println(ans);

        

    }
}