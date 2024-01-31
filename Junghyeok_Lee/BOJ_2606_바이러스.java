package edu.ssafy.chap01;

import java.util.*;

public class BOJ_2606_바이러스 {
	
	static ArrayDeque<Integer> dq = new ArrayDeque<>();
	static int[] visited;
	static ArrayList<Integer>[] arr = new ArrayList[101];
	static int res=0;
	
	static void bfs(int start) {
		visited[start]=1;
		dq.add(start);
		
		while(!dq.isEmpty()) {
			int x=dq.poll();
			
			for(int i=0;i<arr[x].size();i++) {
				int xx = arr[x].get(i);
				if(visited[xx]==0) {
					dq.add(xx);
					visited[xx]=1;
					res+=1;
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		for(int i=1;i<=n;i++) {
			arr[i] = new ArrayList<Integer>();
		}
		
		for(int i=0;i<m;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			arr[a].add(b);
			arr[b].add(a);
		}
		
		visited = new int[n+1];
		bfs(1);
		System.out.println(res);
		
	}

}
