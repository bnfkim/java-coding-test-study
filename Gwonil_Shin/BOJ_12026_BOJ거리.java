import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N,INF=1_000_001;
    static List<Integer>[] boj=new List[3];
    static int[] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        char[] board=br.readLine().toCharArray();
        dp=new int[N];

        Arrays.fill(dp, INF);

        for(int i=0;i<3;i++) {
            boj[i]=new ArrayList<>();
        }

        for(int i=0;i<N;i++) {
            if(board[i]=='B') {
                boj[0].add(i);
            }
            else if(board[i]=='O') {
                boj[1].add(i);
            }
            else{
                boj[2].add(i);
            }
        }

        dp[0]=0;
        for(int i=0;i<N-1;i++) {
            if(board[i]=='B') {
                input(1,i);
            }
            else if(board[i]=='O') {
                input(2,i);
            }
            else{
                input(0,i);
            }
        }

        int result=(dp[N-1]==INF)?-1:dp[N-1];
        System.out.println(result);
    }

    static void input(int bojIdx,int idx) {
        for(int nextIdx:boj[bojIdx]) {
            if(nextIdx<idx) continue;
            int power=(int)Math.pow(nextIdx-idx, 2);

            dp[nextIdx]=Math.min(dp[nextIdx], dp[idx]+power);
        }
    }
}
