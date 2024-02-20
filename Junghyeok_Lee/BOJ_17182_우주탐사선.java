package java_codingtest_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17182_우주탐사선 {
	static int n,k;
	static int[][] arr;
	static int res;
	static int[] visited;
	
	static void dfs(int depth, int idx, int sum) {
		if(sum>=res) {
			return;
		}
		
		if(depth==n) {
			res=sum;
			return;
		}
		
		for(int i=0;i<n;i++) {
			if((idx!=i)&&(visited[i]==0)) {
				visited[i]=1;
				dfs(depth+1,i,sum+arr[idx][i]);
				visited[i]=0;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				for(int k=0;k<n;k++) {
					arr[j][k] = Math.min(arr[j][k], arr[j][i]+arr[i][k]);
				}
			}
		}
		
		res=1000*n;
		visited = new int[n];
		visited[k]=1;
		dfs(1,k,0);
		System.out.println(res);
		
	}
}
