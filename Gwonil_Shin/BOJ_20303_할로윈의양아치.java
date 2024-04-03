import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,K;
    static int[] candy;
    static int[] parent;
    static Map<Integer,int[]> group;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        candy=new int[N+1];
        parent=new int[N+1];
        st=new StringTokenizer(br.readLine());

        for(int i=1;i<=N;i++) {
            candy[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());

            union(a,b);
        }

        group=new HashMap<>();

        for(int i=1;i<=N;i++){
            int root=find(i);

            int[] stat=group.getOrDefault(root,new int[2]);
            stat[0]++;
            stat[1]+=candy[i];

            group.put(root,stat);
        }

        int[] dp=new int[K];

        for(int x:group.keySet()){
            int[] stat=group.get(x);

            if(stat[0]>K) continue;

            for(int i=K-1;i>=stat[0];i--){
                dp[i]=Math.max(dp[i],dp[i-stat[0]]+stat[1]);
            }
        }
        int result=0;
        for(int i=0;i<K;i++){
            result=Math.max(result,dp[i]);
        }

        System.out.println(result);
    }

    static boolean union(int a,int b){
        int aRoot=find(a);
        int bRoot=find(b);

        if(aRoot==bRoot) return false;

        parent[bRoot]=aRoot;

        return true;
    }

    static int find(int x){
        if(parent[x]==x) return x;
        return parent[x]=find(parent[x]);
    }
}