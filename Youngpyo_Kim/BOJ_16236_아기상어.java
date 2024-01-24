import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16236_아기상어 {
    static int n, ans = 0, eaten = 0;
    static int sharkSize = 2;
    static int[][] delta = {{0,1},{0,-1},{1,0},{-1,0}};
    static int[][] board = new int[21][21];
    static Info sharkPoint;

    public static boolean bfs(){
        int[][] visited = new int[21][21];
        ArrayList<Info> eatable = new ArrayList<>(10);
        Queue<Info> q = new LinkedList<>();
        q.add(sharkPoint);
        visited[sharkPoint.r][sharkPoint.c] = 1;

        while(!q.isEmpty()){
            Info cur = q.poll();
            
            for(int i = 0; i < 4; i++){
                int nr = cur.r+delta[i][0];
                int nc = cur.c+delta[i][1];
                if(nr < 0 || nr >= n || nc < 0 || nc >= n || board[nr][nc] > sharkSize || visited[nr][nc] > 0) continue;
                
                if(board[nr][nc] == sharkSize || board[nr][nc] == 0){
                    q.add(new Info(nr, nc, visited[cur.r][cur.c]+1));
                    visited[nr][nc] = visited[cur.r][cur.c]+1;
                }
                else if(board[nr][nc] < sharkSize){
                    eatable.add(new Info(nr, nc, visited[cur.r][cur.c]+1));
                }
            }
        }
        // System.out.println(eatable.size());
        if(eatable.isEmpty()) return true;
        else{
            Collections.sort(eatable, new Comparator<Info>() {
                public int compare(Info a, Info b){
                    if(a.dist != b.dist)
                        return Integer.compare(a.dist, b.dist);
                    else if(a.r != b.r)
                        return Integer.compare(a.r, b.r);
                    else if(a.c != b.c)
                        return Integer.compare(a.c, b.c);
                    return 1;
                }
            });
            int eatR = eatable.get(0).r, eatC = eatable.get(0).c, dist = eatable.get(0).dist-1;
            board[eatR][eatC] = 0;
            ans += dist;
            sharkPoint = new Info(eatR, eatC, 0);
            eaten++;
            if(eaten == sharkSize){
                eaten = 0;
                sharkSize++;
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        
        n = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j < n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 9){
                    board[i][j] = 0;
                    sharkPoint = new Info(i,j,0);
                }
            }
        }

        while(true){
            if(bfs()){
                break;
            }
            else{

            }
        }

        System.out.println(ans);
    }

    public static class Info{
        int dist,r,c;
        Info(){}
        Info(int r, int c, int dist){
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
}
