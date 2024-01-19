package doit;

import java.util.*;

public class 계단오르기_2579 {
    /*
    * 
    *  i칸에 가기위해서 
    *  - 1. i-2 밟고 이동
    *  - 2. i-3 밟고 , i-1칸 밝고 이동 
    *  이 두가지 방법을 dp로 풀었습니다.
    * */
    static int[] arr;
    static int[] dp;
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N=sc.nextInt();

        arr = new int[N+1];
        dp= new int[N+1];

        for(int i=1;i<N+1;i++) {
            arr[i] = sc.nextInt();
        }

        dp[1]=arr[1];
        if(N>=2)
        {
            dp[2]=dp[1]+arr[2];
        }
    for(int i=3;i<N+1;i++)
    {
        dp[i]=Math.max(dp[i-2]+arr[i],dp[i-3]+arr[i]+arr[i-1]);
    }
        System.out.println(dp[N]);
    }
    // [] [] [] [] []
    //
}


