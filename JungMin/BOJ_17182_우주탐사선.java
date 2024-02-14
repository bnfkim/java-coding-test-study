import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 우주탐사선17192 {
    static int N,start;
    static int[][] board;
    static int answer=Integer.MAX_VALUE;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {

        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st= new StringTokenizer(bf.readLine());

        N=Integer.parseInt(st.nextToken());
        start=Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for(int i=0;i<N;i++)
        {
            st=new StringTokenizer(bf.readLine());
            for(int j=0;j<N;j++)
            {
                board[i][j]=Integer.parseInt(st.nextToken());
            }
        }


        //최단거리 계산
        for(int k=0;k<N;k++)
        {
            for(int i=0;i<N;i++)
            {
                for(int j=0;j<N;j++)
                {
                    if(i==j) continue;

                    board[i][j]=Math.min(board[i][j],board[i][k]+board[k][j]);
                }
            }
        }

        visited=new boolean[N];
        visited[start]=true;
        dfs(start,0,0,visited);
        System.out.println(answer);

    }
    public static void dfs(int node, int sum,int depth,boolean[] v)

    {

        if(depth==N-1)
        {
            answer=Math.min(answer,sum) ;
            return;
        }

        for(int i=0;i<N;i++)
        {
            // 방문하지않은곳이라면
            if(!visited[i])
            {
                visited[i]=true; // 방문처리
                dfs(i,sum+board[node][i],depth+1,v);
                visited[i]=false;
            }
        }


    }
    public static boolean allVisit(boolean[] v)
    {
        boolean flag=true;

        for(int i=0;i<N;i++)
        {
            if(!v[i])
                flag=false;
        }

        return flag;

    }
}

