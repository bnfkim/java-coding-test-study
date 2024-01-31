package java_codingtest_study;

import java.util.*;

public class BOJ_2579_계단오르기 {
	
	static int[][] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		int[] arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=sc.nextInt();
		}
		
		dp = new int[2][n+1];
		dp[0][1]=arr[0];
		
		for(int i=2;i<=n;i++) {
			if(dp[0][i-2]>dp[1][i-2]) {
				dp[0][i]=dp[0][i-2]+arr[i-1];
			}else {
				dp[0][i]=dp[1][i-2]+arr[i-1];
			}
			
			dp[1][i] = dp[0][i-1] + arr[i-1];
		}
		
		if(dp[0][n]>dp[1][n]) {
			System.out.println(dp[0][n]);
		}else {
			System.out.println(dp[1][n]);
		}
	}

}
