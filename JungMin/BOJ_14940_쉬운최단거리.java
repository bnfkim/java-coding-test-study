import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 쉬운최단거리14949 {
    static int[][] arr;
    static int N,M;
    static int[][] result;
    static boolean[][] v;
    static int d_x,d_y;
    static int[] dx={0,0,-1,1};
    static int[] dy={1,-1,0,0};
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());


        arr= new int[N][M];
        result= new int[N][M];
        v= new boolean[N][M] ;
        for(int i=0;i<N;i++)
        {
            st=new StringTokenizer(bf.readLine());
            for(int j=0;j<M;j++)
            {
                arr[i][j]=Integer.parseInt(st.nextToken());

                if(arr[i][j]==2)
                {
                    d_x=i;
                    d_y=j;
                    v[i][j]=true;
                }
                else if(arr[i][j]==0)
                {
                    v[i][j]=true;
                }

            }
        }

        Queue<int []> q = new LinkedList<>();

        q.add(new int[]{d_x,d_y});

        while(!q.isEmpty())
        {
            int[] now= q.poll();
            int x= now[0];
            int y= now[1];

            for(int i=0;i<4;i++)
            {
                int nx=x+dx[i];
                int ny=y+dy[i];

                if(isBoundary(nx,ny)&& arr[nx][ny]==1 && v[nx][ny]==false )
                {
                    result[nx][ny]=result[x][y]+1;
                    v[nx][ny]=true;
                    q.add(new int[]{nx,ny});
                }
            }

        }
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<M;j++)
            {

                if(v[i][j]==false)
                {
                    result[i][j]=-1;
                }
            }
        }


        for(int i=0;i<N;i++)
        {
            for(int j=0;j<M;j++)
            {

                System.out.print(result[i][j]+" ");
            }
            System.out.println();

        }








    }
    public static boolean isBoundary(int x,int y)
    {
        if(x>=0 && x<N && y>=0 && y<M){
            return true;
        }
        return false;
    }
}
// boj 14940 쉬운최단 거리 2360ms 30분 

