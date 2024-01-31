package java_codingtest_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10971_외판원순회2 {
	
	static int[][] arr;
	static int res = 10000000;
	static int n;
	static int start;
	static boolean visited[];
	
	static void dfs(int idx,int depth,int dis) {
		if(dis>=res) return;
		
		if(depth==n) {
			if(arr[idx][start]!=0) {
				int tmp = arr[idx][start]+dis;
				if(res>tmp) {
					res=tmp;
				}
			}
			return;
		}
		
		for(int i=0;i<n;i++) {
			if(arr[idx][i]!=0&&visited[i]==false) {
				visited[i]=true;
				dfs(i,depth+1,dis+arr[idx][i]);
				visited[i]=false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited=new boolean[n];
		for(int i=0;i<n;i++) {
			start = i;
			visited[i]=true;
			dfs(start,1,0);
			visited[i]=false;
		}
		System.out.println(res);
	}

}
