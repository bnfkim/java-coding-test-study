import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,M,empty_space,result;
    static int [][]arr;
    static boolean[][] visited;
    static List<int[]> virus=new ArrayList<>(10);
    static int[][]mv= {{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        arr=new int[N][M];
        visited=new boolean[N][M];

        for(int i=0;i<N;i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());

                if(arr[i][j]==0) empty_space++;
                else if(arr[i][j]==2) virus.add(new int[]{i,j});
            }
        }

        comb(0,0);

        System.out.println(result);
    }


    static void comb(int idx,int start) {
        if(idx==3) {
            result=Math.max(result,check());
            return;
        }

        for(int i=start;i<N*M;i++) {
            int x=i/M;
            int y=i%M;

            if(arr[x][y]!=0) continue;

            arr[x][y]=1;
            comb(idx+1,i+1);
            arr[x][y]=0;
        }
    }


    static int check() {
        Queue<int[]> q=new ArrayDeque<>();
        visited=new boolean[N][M];

        for(int[] v:virus) {
            q.add(v);
        }

        int result=0;

        while(!q.isEmpty()) {
            int[] cur=q.poll();

            for(int[] next:mv) {
                int nx=cur[0]+next[0];
                int ny=cur[1]+next[1];

                if(nx<0||nx==N||ny<0||ny==M) continue;

                if(arr[nx][ny]!=0||visited[nx][ny]) continue;

                q.add(new int[] {nx,ny});
                visited[nx][ny]=true;
                result++;
            }

        }

        return empty_space-result-3;
    }

}
