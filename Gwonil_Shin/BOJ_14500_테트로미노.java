import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int [][]arr;
    static int N,M;
    static int result;
    static int[][] mv= {{-1,0},{1,0},{0,-1},{0,1}};
    static int[][][] etcs= {{{0,1},{0,2},{1,1}},{{1,0},{2,0},{1,1}},{{0,1},{0,2},{-1,1}},{{1,0},{2,0},{1,-1}}};
    static Set<Point> visited=new HashSet<>(4);
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        arr=new int[N][M];

        for(int i=0;i<N;i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                Point p=new Point(i,j);
                visited.add(p);
                dfs(i,j,1,arr[i][j]);
                visited.remove(p);

                etc(i,j,arr[i][j]);
            }
        }

        System.out.println(result);
    }

    static void dfs(int x,int y,int depth,int cur) {
        if(depth==4) {
            result=Math.max(cur, result);
            return;
        }

        for(int []next:mv) {
            int nx=x+next[0];
            int ny=y+next[1];

            if(nx<0||nx==N||ny<0||ny==M) continue;

            Point p=new Point(nx,ny);

            if(visited.contains(p)) continue;

            visited.add(p);
            dfs(nx,ny,depth+1,cur+arr[nx][ny]);
            visited.remove(p);
        }
    }

    static void etc(int x,int y,int start) {
        find:for(int[][] etc_mv:etcs) {
            int cur=start;
            for(int[] next:etc_mv) {
                int nx=x+next[0];
                int ny=y+next[1];

                if(nx<0||nx==N||ny<0||ny==M) continue find;

                cur+=arr[nx][ny];
            }

            result=Math.max(cur,result);
        }
    }

    static class Point{
        int x,y;

        public Point(int x,int y) {
            this.x=x;
            this.y=y;
        }

        @Override
        public boolean equals(Object obj) {
            Point o=(Point) obj;
            return x==o.x&&y==o.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x,y);
        }
    }
}
