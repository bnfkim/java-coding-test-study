import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n,answer=Integer.MAX_VALUE;
    static int[][]arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        n=Integer.parseInt(br.readLine());

        arr=new int[n][n];

        for(int i=0;i<n;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        comb(0,0,0);
        System.out.println(answer);
    }

    static void comb(int depth,int start,int bits){
        if(depth==n/2){
            int cur=Math.abs(getSum(bits)-getSum(~bits));
            answer=Math.min(answer,cur);
            return;
        }

        for(int i=start;i<n;i++) {
            comb(depth+1,i+1,bits|1<<i);
        }
    }

    static int getSum(int bits){
        List<Integer> teams=new ArrayList<>();
        int sum=0;

        for(int i=0;i<n;i++){
            if((bits&1<<i)!=0){
                for(int x:teams){
                    sum+=arr[i][x]+arr[x][i];
                }

                teams.add(i);
            }
        }

        return sum;
    }
}