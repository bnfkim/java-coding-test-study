package java_codingtest_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2705_펠린드롬파티션 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] dp = new int[1001];
		for(int i=1;i<=1000;i++) {
			dp[i]+=1;
			for(int j=i*2;j<1001;j++) {
				dp[j]+=dp[i];
			}
		}
		
		int tc = Integer.parseInt(st.nextToken());
		for(int t=0;t<tc;t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			System.out.println(dp[n]);
		}
	}

}
