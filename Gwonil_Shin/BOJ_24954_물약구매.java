import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
    static int N,result=Integer.MAX_VALUE;
    static int[] potion;
    static List<int[]> discount;

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N=Integer.parseInt(br.readLine());

        potion=new int[N];
        discount=new ArrayList<>(N);

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            potion[i]=Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++) {
            int p=Integer.parseInt(br.readLine());
            int [] items=new int[2*p];
            for(int j=0;j<p;j++) {
                st=new StringTokenizer(br.readLine());
                items[2*j]=Integer.parseInt(st.nextToken());
                items[2*j+1]=Integer.parseInt(st.nextToken());
            }

            discount.add(items);
        }

        dfs(0,0,0,new int[N]);

        System.out.println(result);
    }


    static void dfs(int num,int bit,int cur,int[] disNum) {
        if(num==N) {
            result=Math.min(result, cur);
            return;
        }

        for(int i=0;i<N;i++) {
            if((bit&1<<i)!=0) continue;

            //구매
            int buyCash=Math.max(potion[i]-disNum[i],1);

            //구매로 인한 할인 추가
            int[] dis=discount.get(i);

            for(int j=0,size=dis.length/2;j<size;j++) {
                disNum[dis[2*j]-1]+=dis[2*j+1];
            }

            dfs(num+1,bit|1<<i,cur+buyCash,disNum);

            //복구
            for(int j=0,size=dis.length/2;j<size;j++) {
                disNum[dis[2*j]-1]-=dis[2*j+1];
            }
        }
    }

}
