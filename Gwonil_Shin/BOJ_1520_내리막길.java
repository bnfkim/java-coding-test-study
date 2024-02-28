import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int [][]arr;
    static int [][]dp; //x,y,방향 12시부터 0 (시계)
    static int [][] mv= {{-1,0},{0,1},{1,0},{0,-1}};
    static int result=0;
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        arr=new int[N][M];
        dp=new int[N][M];

        for(int i=0;i<N;i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<N;i++) {
            Arrays.fill(dp[i], -1);
        }

        dp[0][0]=1;

        System.out.println(find(N-1,M-1));
    }

    static int find(int x,int y) {
        if(dp[x][y]==-1) {
            int sum=0;
            for(int[] next:mv) {
                int nx=x+next[0];
                int ny=y+next[1];

                if(nx<0||nx==N||ny<0||ny==M) continue;

                if(arr[x][y]>=arr[nx][ny]) continue;

                sum+=find(nx,ny);
            }

            dp[x][y]=sum;
        }

        return dp[x][y];
    }

}
