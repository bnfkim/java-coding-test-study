package codingtest;

import java.io.*;
import java.util.Arrays;

public class BOJ_2579_계단오르기 {
	static Integer dp[];
	static int[] i_array;
	
	public static void main(String[] args) throws IOException  {
        BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(bw.readLine());
        
        i_array = new int[n+1];
        dp = new Integer[n+1];
        
        for (int i = 1; i < n+1; i++ ) {
        	i_array[i] = Integer.parseInt(bw.readLine());
        }
        
        dp[0] = i_array[0];
        dp[1] = i_array[1];
        
        if (n >= 2) {
        	dp[2] = i_array[1] + i_array[2];
        }
        
        System.out.println(find(n));
    }
	
	static int find(int n) {
		if (dp[n] == null) {
			dp[n] = Math.max(find(n-2), find(n-3)+i_array[n-1]) + i_array[n];
		}
		
		return dp[n];
	}
}
