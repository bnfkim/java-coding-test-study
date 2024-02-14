import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,K,answer=Integer.MAX_VALUE;
    static int [][]arr;

    static void dfs(int cur,int cost,int bit){
        if(bit==(1<<N)-1){
            answer=Math.min(answer,cost);
            return;
        }

        for(int next=0;next<N;next++){
            if((bit&1<<next)!=0) continue;

            dfs(next,cost+arr[cur][next],bit|1<<next);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        arr=new int[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for(int mid=0;mid<N;mid++){
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    arr[i][j]=Math.min(arr[i][j],arr[i][mid]+arr[mid][j]);
                }
            }
        }

        dfs(K,0,1<<K);

        System.out.println(answer);
    }
}