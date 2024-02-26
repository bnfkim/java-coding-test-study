import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int N,S,result;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        S=Integer.parseInt(st.nextToken());

        arr=new int[N];
        st=new StringTokenizer(br.readLine());

        Arrays.sort(arr);

        for(int i=0;i<N;i++) {
            arr[i]=Integer.parseInt(st.nextToken());
        }

        subset(0,0);

        System.out.println(result);
    }

    static void subset(int idx,int bit) {
        if(idx==N) {
            if(bit==0) return;
            int sum=0;

            for(int i=0;i<N;i++) {
                if((bit&1<<i)!=0) sum+=arr[i];
            }

            if(sum==S) result++;

            return;
        }

        subset(idx+1,bit|1<<idx);
        subset(idx+1,bit);
    }
}
