import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class q2565 {
    static int N, maxValue;
    static int[] ar2;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        int[][] ar = new int[N][2];
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        
        for (int i = 0; i < N; i++ ) {
            st = new StringTokenizer(br.readLine());
            ar[i][0] = Integer.parseInt(st.nextToken());
            ar[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(ar, (d1, d2) -> {return d1[0]-d2[0];});

        for (int i = 0; i < N; i++ ) {
            for (int j = 0; j < i; j++ ) {
                if (ar[i][1] > ar[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        int max=0;
        for (int i = 0; i < N; i++ ) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(N - max);
                
    }

}
