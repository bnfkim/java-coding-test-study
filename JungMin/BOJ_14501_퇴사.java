import java.util.ArrayList;
import java.util.Scanner;

public class 퇴사14501 {

    static int N;
    static int[][] arr;
    static int answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N=sc.nextInt();

        arr=new int[N][2];

        for(int i=0;i<N;i++)
        {
            arr[i][0]=sc.nextInt();
            arr[i][1]=sc.nextInt();
        }

        dfs(0,0);
        System.out.println(answer);
    }
    public static void dfs(int idx,int sum)
    {
        if(idx>=N)
        {
            answer=Math.max(sum,answer);
            return;
        }

        if(idx+arr[idx][0]<=N)
        {
            dfs(idx+arr[idx][0],sum+arr[idx][1]);


        }
        else
        {
            dfs(idx+arr[idx][0],sum);
        }

        dfs(idx+1,sum);

    }

}
// 14501 실버3 , 퇴사 ,112ms
