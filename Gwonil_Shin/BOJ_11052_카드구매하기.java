import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//17
public class Main {
    static int N;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        dp=new int[N+1];

        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            dp[i]=Integer.parseInt(st.nextToken());
        }



        for(int i=2;i<=N;i++){
            for(int j=0;j<i;j++){
                dp[i]=Math.max(dp[i],dp[j]+dp[i-j]);
            }
        }

        System.out.println(dp[N]);

    }
}