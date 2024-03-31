import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,S;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        S=Integer.parseInt(st.nextToken());

        arr=new int[N+1];

        st=new StringTokenizer(br.readLine());


        for(int i=1;i<=N;i++){
            arr[i]=Integer.parseInt(st.nextToken())+arr[i-1];
        }

        int start=0;
        int end=0;
        int result=N+1;

        while(start<=end&&end<=N){
            int cur=arr[end]-arr[start];

            if(cur<S){
                end++;
            }
            else {
                result=Math.min(result,end-start);
                start++;
            }
        }

        result=(result==N+1)?0:result;

        System.out.println(result);

    }
}