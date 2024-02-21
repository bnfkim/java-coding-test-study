import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
    static int N,M,K;
    static long data[],tree[];
    static int index[];
    static int MOD= 1_000_000_007;

    static long init(int start,int end,int node) {
        if(start==end) {
            index[start]=node;
            return tree[node]=data[start];
        }

        int mid=(start+end)>>1;

        return tree[node]=(init(start,mid,node<<1)*init(mid+1,end,node*2+1))%MOD;
    }

    static void update(int value,int node,boolean is_leaf) {
        if(node==0) {
            return;
        }


        if(is_leaf) tree[node]=value;
        else {
            tree[node]=(tree[node<<1]*tree[node*2+1])%MOD;
        }


        update(value,node>>1,false);

    }

    public static long mul(int start,int end,int node,int left,int right) {
        //범위 밖
        if(left>end||right<start) {
            return 1;
        }

        //구간 합을 구할 범위 내
        if(left<=start && end<=right) {
            return tree[node];
        }

        int mid=(start+end)>>1;

        return mul(start,mid,node<<1,left,right)*mul(mid+1,end,(node<<1)+1,left,right)%MOD;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        StringJoiner sb=new StringJoiner("\n");

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        data=new long[N];
        tree=new long[N<<2];
        index=new int[N];

        for(int i=0;i<N;i++) {
            data[i]=Long.parseLong(br.readLine());
        }


        init(0,N-1,1);

        for(int i=0;i<M+K;i++) {
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());

            if(a==1) { //update
                update(c,index[b-1],true);
            }
            else { // sum
                sb.add(Long.toString(mul(0,N-1,1,b-1,c-1)));
            }
        }

        System.out.println(sb);

    }
}
