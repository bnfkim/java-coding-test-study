import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 외판원순회2 {
    static int N;
    static int[][] arr;
    static boolean[] visited;
    static int answer=Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N=Integer.parseInt(bf.readLine());

        arr= new int[N][N];
        for(int i=0;i<N;i++)
        {
            st=new StringTokenizer(bf.readLine());
            for(int j=0;j<N;j++)
            {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<N;i++)
        {
            visited= new boolean[N];
            visited[i]=true;
            dfs(i,i,0,0);
        }
        System.out.println(answer);
    }
    public static void dfs(int start,int now,int depth,int sum)
    {
        if(depth==N-1)
        {
            if(arr[now][start]>0)
            {
                sum+=arr[now][start];

                answer=Math.min(sum,answer);
            }
        return;
        }

        for(int i=0;i<N;i++)
        {
            if(visited[i]==false && arr[now][i]>0)
            {
                visited[i]=true;
                dfs(start,i,depth+1,sum+arr[now][i]);
                visited[i]=false;
            }
        }

    }
}
// 외판원순회2 10971 260 실버2 1시간

