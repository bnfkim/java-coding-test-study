import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int [][]dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N=Integer.parseInt(br.readLine());
        dp=new int[N][6];

        int []prev_dice=new int[6];

        for(int i=0;i<N;i++) {
            st=new StringTokenizer(br.readLine());
            int[] dice=new int[6];

            for(int j=0;j<6;j++) {
                dice[j]=Integer.parseInt(st.nextToken());
            }

            //1~4
            dp[i][0]=dp[i][5]=Math.max(dice[1], Math.max(dice[2], Math.max(dice[3], dice[4])));

            //0135
            dp[i][2]=dp[i][4]=Math.max(dice[0], Math.max(dice[1], Math.max(dice[3], dice[5])));

            //0245
            dp[i][1]=dp[i][3]=Math.max(dice[0], Math.max(dice[2], Math.max(dice[4], dice[5])));

            if(i!=0) {
                //윗면과 아랫면의 매칭
                for(int a=0;a<6;a++) { //dice
                    for(int b=0;b<6;b++) { //prev_dice
                        if(dice[a]==prev_dice[b]) {
                            if(b==0) {
                                dp[i][a]+=dp[i-1][5];
                            }
                            else if(b==1){
                                dp[i][a]+=dp[i-1][3];
                            }
                            else if(b==2){
                                dp[i][a]+=dp[i-1][4];
                            }
                            else if(b==3){
                                dp[i][a]+=dp[i-1][1];
                            }
                            else if(b==4){
                                dp[i][a]+=dp[i-1][2];
                            }
                            else {
                                dp[i][a]+=dp[i-1][0];
                            }
                            break;
                        }
                    }
                }
            }

            prev_dice=dice;
        }

        int result=0;
        for(int i=0;i<6;i++) {
            result=Math.max(result, dp[N-1][i]);
        }

        System.out.println(result);
    }
}
